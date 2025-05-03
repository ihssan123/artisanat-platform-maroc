package com.artisanat.platform.application.service;
import com.artisanat.platform.application.dto.AuthenticationRequest;
import com.artisanat.platform.application.dto.AuthenticationResponse;

import com.artisanat.platform.domain.entity.Utilisateur;
import com.artisanat.platform.domain.enums.Role;

import com.artisanat.platform.infrastructure.persistance.repository.UtilisateurRepository;
import com.artisanat.platform.infrastructure.security.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthenticationService(UtilisateurRepository utilisateurRepository, JwtUtils jwtUtils) {
        this.utilisateurRepository = utilisateurRepository;
        this.jwtUtils = jwtUtils;
    }

    public AuthenticationResponse register(Utilisateur utilisateur) {
            if(utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()){
                throw new RuntimeException("Email déjà utilisé !");
            }
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setRole(Role.CLIENT); // par défaut ou tu changes
        utilisateurRepository.save(utilisateur);
        String token = jwtUtils.generateToken(utilisateur.getEmail());
        return new AuthenticationResponse(
                token,
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getAdresse(),
                utilisateur.getRole()

        );
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(request.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        String token = jwtUtils.generateToken(utilisateur.getEmail());
        return new AuthenticationResponse(
                token,
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getAdresse(),
                utilisateur.getRole()

        );
    }
}
