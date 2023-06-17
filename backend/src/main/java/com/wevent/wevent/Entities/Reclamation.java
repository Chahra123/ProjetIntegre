package com.wevent.wevent.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReclamation;

    String contenuReclamation;

    Date dateCreation;

    Boolean statutReclamation;

    @ManyToOne
    Utilisateur utilisateur;

    @ManyToOne
    Evenement evenement;

    @OneToOne
            @JoinColumn(name="idNotif")
    Notification notification;


}
