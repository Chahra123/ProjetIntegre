package com.wevent.wevent.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@DiscriminatorValue(value = "User")
@EqualsAndHashCode
public class Utilisateur implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    @NotBlank(message = "Prénom obligatoire")
    private String prenom;

    @NotBlank(message = "Nom obligatoire")
    private String nom;

    @NotBlank(message = "Email obligatoire")
    @Size(max = 50,message = "L'email ne doit pas dépasser les 50 caractères")
    @Email(message ="Format non respecté")
    private String email;

    //@NotEmpty(message="Role obligatoire")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @NotBlank(message = "Mot de passe obligatoire")
    @Size(max=1000, message ="La taille du mot de passe ne doit pas depasser les 1500 caractères")
    private String motDePasse;

    @Temporal(TemporalType.DATE)
    Date dateNaissance;

    Long numTel;

    Boolean locked = false;
    Boolean enabled = false;
    @OneToMany(mappedBy = "utilisateur")
    Set<Reclamation> reclamations = new HashSet<>();

    @OneToMany(mappedBy = "auteur")
    Set<ReponseQuestion> reponseQuestions = new HashSet<>();

    @ManyToMany
    Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "commentateur")
    Set<Avis> avis = new HashSet<>();

    @ManyToMany
    Set<Evenement> evenements = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    Set<Reservation> reservations = new HashSet<>();

    public Utilisateur(String prenom,
                       String nom,
                       String email,
                       ERole role,
                       String motDePasse,
                       Date dateNaissance,
                       Long numTel,
                       Boolean locked,
                       Boolean enabled) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.locked = locked;
        this.enabled = enabled;
    }

    public Utilisateur(String prenom, String nom, String email, ERole role, String motDePasse) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.role = role;
        this.motDePasse = motDePasse;
    }
}
