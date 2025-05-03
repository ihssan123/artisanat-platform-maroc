package com.artisanat.platform.application.dto;
import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String motDePasse;
}
