package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();

    ResponseEntity<?> addReservation(Reservation rs);

    ResponseEntity<?> deleteReservation(Long id);

    ResponseEntity<?> updateReservation(Reservation rs, Long reservationId);

    ResponseEntity<?> getReservation(Long id);

    public void affecterResrvationAUtislisateur(Long idReservation, long idUser);

    void affecterReservationAEvenement(Long idReservation, long idEvenement);

    int nbrPers(Long idEvenement, Long idReservation);


}
