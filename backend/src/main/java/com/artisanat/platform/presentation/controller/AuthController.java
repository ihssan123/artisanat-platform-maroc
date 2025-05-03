package com.artisanat.platform.presentation.controller;
import com.artisanat.platform.application.dto.AuthenticationRequest;
import com.artisanat.platform.application.dto.AuthenticationResponse;
import com.artisanat.platform.application.service.AuthenticationService;
import com.artisanat.platform.domain.entity.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur utilisateur) {
        try {
            AuthenticationResponse response = authenticationService.register(utilisateur);
            return ResponseEntity.ok(response); // 200 OK avec le token
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(403)
                    .body(e.getMessage()); // retourne "Email déjà utilisé !"
        }
    }


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
