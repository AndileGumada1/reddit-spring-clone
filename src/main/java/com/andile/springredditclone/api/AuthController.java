package com.andile.springredditclone.api;

import com.andile.springredditclone.api.dto.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @PostMapping("/signup")
    public ResponseEntity signup(RegisterRequest registerRequest) throws JsonProcessingException {
        log.info("Request: {}", objectMapper.writeValueAsString(registerRequest));
        authService.signUp(registerRequest);
        return new ResponseEntity(OK);
    }
    
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        log.info("Verification Code: {}",token);
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }
}
