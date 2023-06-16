package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lieu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLieu;
    String gouvernorat;
    String ville;
    String nomEmplacement;
    @Enumerated(EnumType.STRING)
    ETypeEmplacement eTypeEmplacement;
    int capaciteEffectifs;
    @OneToOne(mappedBy = "lieuEvenement")
    Evenement evenement;
}
