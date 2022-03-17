package com.andile.springredditclone.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
* This is a Register Payload
**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    public String username;
    private String email;
    private String password;
}
