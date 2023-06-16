package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEvenement;

    String nomEvenement;
    String descriptionEvenement;
    Date dateDebut;
    Date dateFin;
    Double prix;
    int nbrePersonnes;
    String image;
    @Enumerated(EnumType.STRING)
    ETypeEvenement typeEvenement;
    String autreType;
    Boolean interesse;
    Boolean statut;
    @OneToOne
    Lieu lieuEvenement;
    @ManyToMany(mappedBy = "evenements")
    Set<Utilisateur> utilisateurs = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    Set<Avis> avisEvenement = new HashSet<>();

    @ManyToOne
    ProprietaireEvenement proprietaire;
    @OneToMany(mappedBy = "evenement")
    Set<Reclamation> reclamations = new HashSet<>();

    @OneToMany(mappedBy = "evenement")
    Set<Reservation> reservations = new HashSet<>();

}
