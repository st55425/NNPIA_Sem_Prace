package com.example.semprace.security;

import lombok.*;

@Data
@AllArgsConstructor
public class JwtRequest {

    private String username;
    private String password;
}
