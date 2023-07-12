package com.wevent.wevent.Entities;

import lombok.*;

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
@ToString
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

    @OneToMany(mappedBy = "evenement")
    Set<Notification> notifications = new HashSet<>();

    public Evenement(String nomEvenement, String descriptionEvenement, Date dateDebut, Date dateFin, Double prix, int nbrePersonnes, String image, ETypeEvenement typeEvenement, String autreType, Boolean interesse, Boolean statut) {
        this.nomEvenement = nomEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.nbrePersonnes = nbrePersonnes;
        this.image = image;
        this.typeEvenement = typeEvenement;
        this.autreType = autreType;
        this.interesse = interesse;
        this.statut = statut;
    }
}
