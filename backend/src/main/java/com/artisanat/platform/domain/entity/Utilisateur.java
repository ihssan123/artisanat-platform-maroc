package com.artisanat.platform.domain.entity;
import com.artisanat.platform.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data // génère getters, setters, toString, equals, hashCode
@NoArgsConstructor // génère un constructeur vide
@AllArgsConstructor // génère un constructeur avec tous les attributs
@Document(collection = "utilisateurs")
public class Utilisateur {

    @Id
    private String id;

    private String nom;
    private String prenom;
    @Indexed(unique = true)
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private Role role;



}
