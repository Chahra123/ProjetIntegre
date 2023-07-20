package com.wevent.wevent.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Societe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idSociete;

    String nomSociete;

    String logoSociete;

    int numTel;

    String email;
    String addresse;


    @OneToMany(mappedBy = "societe")
    @JsonIgnore
    Set<Evenement> evenements= new HashSet<>();
}
