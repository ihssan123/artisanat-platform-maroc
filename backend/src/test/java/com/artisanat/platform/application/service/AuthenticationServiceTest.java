package com.artisanat.platform.application.service;

import com.artisanat.platform.domain.entity.Utilisateur;
import com.artisanat.platform.domain.enums.Role;
import com.artisanat.platform.infrastructure.persistance.repository.UtilisateurRepository;
import com.artisanat.platform.infrastructure.security.JwtUtils;
import com.artisanat.platform.application.dto.AuthenticationRequest;
import com.artisanat.platform.application.dto.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    private AuthenticationService authenticationService;
    private UtilisateurRepository utilisateurRepository;
    private JwtUtils jwtUtils;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        utilisateurRepository = mock(UtilisateurRepository.class); // on crée un faux repository
        jwtUtils = mock(JwtUtils.class); // faux JwtUtils
        passwordEncoder = new BCryptPasswordEncoder();
        authenticationService = new AuthenticationService(utilisateurRepository, jwtUtils);
    }

    @Test
    void register_ShouldCreateNewUserSuccessfully() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur(null, "Sarah","sarah", "sarah@example.com", "password123", "0700000000", "Rue artisans", Role.CLIENT);
        when(utilisateurRepository.findByEmail(utilisateur.getEmail())).thenReturn(Optional.empty());
        when(jwtUtils.generateToken(anyString())).thenReturn("fake-jwt-token");

        // Act
        AuthenticationResponse response = authenticationService.register(utilisateur);

        // Assert
        assertNotNull(response);
        assertEquals("fake-jwt-token", response.getToken());
        verify(utilisateurRepository, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        // Arrange
        Utilisateur utilisateur = new Utilisateur(null, "Sarah", "sarah","sarah@example.com", "password123", "0700000000", "Rue artisans", Role.CLIENT);
        when(utilisateurRepository.findByEmail(utilisateur.getEmail())).thenReturn(Optional.of(utilisateur));

        // Act + Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> authenticationService.register(utilisateur));

        assertEquals("Email déjà utilisé !", exception.getMessage());
    }

    @Test
    void authenticate_ShouldReturnToken_WhenCredentialsAreCorrect() {
        // Arrange
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Utilisateur utilisateur = new Utilisateur(null, "Sarah","sarah", "sarah@example.com", encodedPassword, "0700000000", "Rue artisans", Role.CLIENT);
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("sarah@example.com");
        request.setMotDePasse("password123");

        when(utilisateurRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(utilisateur));
        when(jwtUtils.generateToken(request.getEmail())).thenReturn("fake-jwt-token");

        // Act
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Assert
        assertNotNull(response);
        assertEquals("fake-jwt-token", response.getToken());
    }

    @Test
    void authenticate_ShouldThrowException_WhenPasswordIsIncorrect() {
        // Arrange
        String correctPassword = passwordEncoder.encode("correctPassword");

        Utilisateur utilisateur = new Utilisateur(null, "Sarah","sarah", "sarah@example.com", correctPassword, "0700000000", "Rue artisans", Role.CLIENT);
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("sarah@example.com");
        request.setMotDePasse("wrongPassword");

        when(utilisateurRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(utilisateur));

        // Act + Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> authenticationService.authenticate(request));

        assertEquals("Mot de passe incorrect", exception.getMessage());
    }
}
