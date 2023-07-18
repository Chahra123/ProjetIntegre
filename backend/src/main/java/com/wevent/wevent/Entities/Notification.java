package com.wevent.wevent.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idNotif;
    String ContenuNotif;

    @ManyToMany
    @JoinTable(name = "notification_utilisateur",
            joinColumns = @JoinColumn(name = "idNotif"),
            inverseJoinColumns = @JoinColumn(name = "idUtilisateur"))
    Set<Utilisateur> utilisateurs = new HashSet<>();
    @JsonIgnore
    @ManyToOne
    Evenement evenement;

    @JsonIgnore
    @OneToOne(mappedBy = "notification")
    private Reclamation reclamation;

    @JsonIgnore
    @ManyToOne
    Reservation reservation;

    //@JsonIgnore
   @OneToOne(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Avis avis;

    //@JsonIgnore
    @OneToOne(mappedBy = "notification")
    Question question;

    //@JsonIgnore
    @OneToOne(mappedBy = "notification")
    ReponseQuestion reponseQuestion;

}
