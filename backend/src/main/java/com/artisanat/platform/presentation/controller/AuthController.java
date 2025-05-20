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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Utilisateur utilisateur) {
        AuthenticationResponse response = authenticationService.register(utilisateur);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>  login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
