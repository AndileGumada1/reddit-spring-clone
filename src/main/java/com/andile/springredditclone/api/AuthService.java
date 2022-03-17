package com.andile.springredditclone.api;

import com.andile.springredditclone.api.dto.RegisterRequest;
import com.andile.springredditclone.exception.SpringRedditException;
import com.andile.springredditclone.api.service.MailService;
import com.andile.springredditclone.persistance.UserRepository;
import com.andile.springredditclone.persistance.VerificationTokenRepository;
import com.andile.springredditclone.persistance.model.NotificationEmail;
import com.andile.springredditclone.persistance.model.User;
import com.andile.springredditclone.persistance.model.VerificationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.andile.springredditclone.util.Constants.ACTIVATION_EMAIL;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    /**
     * @param registerRequest
     */
    @Transactional
    public void signUp(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);


        userRepository.save(user);
        String token = generateVerificationToken(user);
        String message = mailContentBuilder.build("Thank you for signing up to Spring Reddit, please click on the below url to activate your account : "
                + ACTIVATION_EMAIL + "/" + token);

        mailService.sendMail(new NotificationEmail("Please Activate your account", user.getEmail(), message));

    }

    /**
     * @param user
     * @return
     */
    public String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    /**
     * @param password
     * @return
     */
    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    /**
     * @param token
     */
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        verificationTokenOptional.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get());
    }

    /**
     * @param verificationToken
     */
    @Transactional
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User Not Found with id - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
