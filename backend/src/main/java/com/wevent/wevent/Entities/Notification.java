package com.wevent.wevent.Entities;

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
    @ManyToOne
    Evenement evenement;

    @OneToOne(mappedBy = "notification")
    private Reclamation reclamation;

}
