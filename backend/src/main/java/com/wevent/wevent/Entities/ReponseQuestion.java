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
public class ReponseQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReponseQuestion;
    String contenuReponse;

    @ManyToOne
    Utilisateur auteur;

    Date dateReponse;

    @ManyToOne
    Question question;

    @OneToOne
    @JoinColumn(name = "idNotif")
    private Notification notification;
}
