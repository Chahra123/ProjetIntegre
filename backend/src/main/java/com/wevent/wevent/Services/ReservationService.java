package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.*;
import com.wevent.wevent.Repositories.EventRepo;
import com.wevent.wevent.Repositories.ReservationRepo;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {
    private final ReservationRepo reservationRepo;
    UserRepo userRepo;
    NotificationService notificationService;
    EventRepo eventRepo;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Override
    public ResponseEntity<?> addReservation(Reservation rs) {
        try{
            rs.setDateReservation(new Date());
            reservationRepo.save(rs);
            List<Utilisateur> utilisateurs = userRepo.findAll();
           for(Utilisateur u: utilisateurs){
                for(Role r: u.getRoles()){
                    if(r.getNomRole().equals("ADMIN") || r.getNomRole().equals("ORGANISATEUR")){
                        notificationService.notifForAdd(rs,u);
                    }

                }
            }
            return ResponseEntity.ok().body(new MessageResponse("Reservation effectuée avec succès"));
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
           return ResponseEntity.ok().body(new MessageResponse("Reservation annulée avec succès"));
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
            resr.setStatutReservation(rs.isStatutReservation());
            resr.setNbPlace(rs.getNbPlace());
            resr.setEmail(rs.getEmail());
            resr.setNumTel(rs.getNumTel());
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
    public void affecterResrvationAUtislisateur(Long idReservation, long idUser){
        Reservation rs = reservationRepo.findById(idReservation).orElse(null);
        Utilisateur ut = userRepo.findById(idUser).orElse(null);
        ut.getReservations().add(rs);
        rs.setUtilisateur(ut);
        userRepo.save(ut);
        reservationRepo.save(rs);
    }

    @Override
    public void affecterReservationAEvenement(Long idReservation, long idEvenement){
        Reservation rs = reservationRepo.findById(idReservation).orElse(null);
        Evenement ev = eventRepo.findById(idEvenement).orElse(null);
        ev.getReservations().add(rs);
        rs.setEvenement(ev);
        eventRepo.save(ev);
        reservationRepo.save(rs);
    }

    @Override
    public int nbrPers(Long idEvenement, Long idReservation){
        int nbr = 0;
        Reservation rs = reservationRepo.findById(idReservation).orElse(null);
        Evenement ev = eventRepo.findById(idEvenement).orElse(null);
        if(rs.isStatutReservation()){
            nbr = ev.getNbrePersonnes() - rs.getNbPlace();
        }
        return nbr;
    }

}
