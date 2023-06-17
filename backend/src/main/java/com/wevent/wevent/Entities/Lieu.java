package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lieu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@NotBlank
    Long idLieu;
    //@NotBlank
    String gouvernorat;
    //@NotBlank
    String ville;
    //@NotBlank
    String nomEmplacement;
    //@NotBlank
    @Enumerated(EnumType.STRING)
    ETypeEmplacement eTypeEmplacement;
    //@NotBlank
    int capaciteEffectifs;
    @OneToOne(mappedBy = "lieuEvenement")
    Evenement evenement;
}
