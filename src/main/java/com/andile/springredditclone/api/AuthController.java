package com.andile.springredditclone.api;

import com.andile.springredditclone.api.dto.AuthenticationResponse;
import com.andile.springredditclone.api.dto.LoginRequest;
import com.andile.springredditclone.api.dto.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {
    private final AuthService authService;
    final ObjectMapper objectMapper;

    /**
     * End-point for registering a user
     * @param registerRequest payload
     * @return ResponseEntity object
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/signup",consumes = "application/json")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) throws JsonProcessingException {
        log.info("Request: {}", objectMapper.writeValueAsString(registerRequest));
        authService.signUp(registerRequest);
        return new ResponseEntity(OK);
    }

    /**
     * @param loginRequest
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
        log.info("Login Request: {}",objectMapper.writeValueAsString(loginRequest));
        return authService.login(loginRequest);
    }
    /**
     * @param token
     * @return ResponseEntity
     */
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        log.info("Verification Code: {}",token);
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }
}
