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
public class Avis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAvis;

    String contenuAvis;
    Date dateCreationAvis;
    int note;

    @ManyToOne
    Utilisateur commentateur;

    @ManyToOne
    Evenement evenement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_notif")
    private Notification notification;

}
