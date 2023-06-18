package com.wevent.wevent.Entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Array;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@DiscriminatorValue(value = "User")
@EqualsAndHashCode
public class Utilisateur implements Serializable, UserDetails {
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
    //@Enumerated(EnumType.STRING)
    //private ERole role;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

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

    public Utilisateur(Long idUtilisateur, String prenom, String nom, String email, Collection<Role> roles, String motDePasse) {
        this.idUtilisateur = idUtilisateur;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.roles = roles;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(String prenom, String nom, String email, Collection<Role> roles, String motDePasse, Date dateNaissance, Long numTel) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.roles = roles;
        this.motDePasse = motDePasse;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(simpleGrantedAuthority);*/
        return null;
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
