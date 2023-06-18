package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.ReservationRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepo reservationRepo;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public ResponseEntity<?> addReservation(Reservation rs) {
        try{
            rs.setDateReservation(new Date());
            reservationRepo.save(rs);
            return ResponseEntity.ok().body(new MessageResponse("Reservation ajoutée avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur d'ajout"));
        }
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long id) {
        try{
            Reservation resr = reservationRepo.findById(id).orElse(null);
            if(resr == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Reservation introuvable"));
            }
            reservationRepo.deleteById(id);
           return ResponseEntity.ok().body(new MessageResponse("Reservation supprimée avec succès"));
        }catch (Exception e){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur de suppression"));
        }
    }

    @Override
    public ResponseEntity<?> updateReservation(Reservation rs, Long reservationId) {
        Reservation resr = reservationRepo.findById(reservationId).orElse(null);
        try{
            if(resr == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Reservation introuvable"));
            }
            resr.setStatutReservation(rs.getStatutReservation());
            reservationRepo.save(resr);
            return ResponseEntity.ok().body(new MessageResponse("Reservation mise à jour avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur"));
        }
    }

    @Override
    public ResponseEntity<?> getReservation(Long id) {
        try{
            Reservation res =  reservationRepo.findById(id).orElse(null);
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Aucune réservation n'est disponible"));
            }
            return ResponseEntity.ok().body(res);
        }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Erreur"));
        }

    }
}
