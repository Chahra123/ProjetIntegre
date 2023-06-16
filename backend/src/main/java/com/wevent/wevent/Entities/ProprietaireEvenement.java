package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProprietaireEvenement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPropEvenement;
    String nomPropEvenement;
    @NotBlank(message = "Email obligatoire")
    @Size(max = 50,message = "L'email ne doit pas dépasser les 50 caractères")
    @Email(message ="Format non respecté")
    String emailPropEvenement;
    Long numTelPropEvenement;
    String lienFbPropEvenement;
    String lienIGPropEvenement;
    String lienLinkedINPropEvenement;

    @OneToMany(mappedBy = "proprietaire")
    Set<Evenement> evenements = new HashSet<>();

}
