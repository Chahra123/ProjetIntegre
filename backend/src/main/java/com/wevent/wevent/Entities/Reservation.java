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
@Builder
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReservation;

    Date dateReservation;
    @ManyToOne
    Utilisateur utilisateur;

    @ManyToOne
    Evenement evenement;

    Boolean statutReservation;

    @OneToMany(mappedBy = "reservation")
    Set<Notification> notifications = new HashSet<>();
}
