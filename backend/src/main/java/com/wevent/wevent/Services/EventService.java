package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.ETypeEvenement;
import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Repositories.EventRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService implements IEventService{
    EventRepo eventRepo;
    @Override
    public List<Evenement> getEvents() {
        return eventRepo.findAll();
    }

<<<<<<< HEAD
    @Override
=======

>>>>>>> c67251562a24b0c0701f0bea9573d0bb7afc2185
    public ResponseEntity<MessageResponse> getEvent(Long eventId) {
        Evenement e = eventRepo.findById(eventId).orElse(null);
        if(e==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Evénement introuvable"));
        }
        return ResponseEntity.ok().body(new MessageResponse("Voici l'événement: "+e.toString()));
    }

    @Override
    public ResponseEntity<?> updateEvent(Long eventId, Evenement eventDetails) {
        Evenement evenement = eventRepo.findById(eventId).orElse(null);
        if (evenement==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Evénement introuvable"));
        }

        evenement.setNomEvenement(eventDetails.getNomEvenement());
        evenement.setDescriptionEvenement(eventDetails.getDescriptionEvenement());
        evenement.setImage(eventDetails.getImage());
        evenement.setDateDebut(eventDetails.getDateDebut());
        evenement.setDateFin(eventDetails.getDateFin());
        evenement.setAutreType(eventDetails.getAutreType());
        evenement.setPrix(eventDetails.getPrix());
        evenement.setStatut(eventDetails.getStatut());
        eventRepo.save(evenement);
        return ResponseEntity.ok().body(new MessageResponse("Evenement mis à jour avec succès"));
<<<<<<< HEAD
=======

>>>>>>> c67251562a24b0c0701f0bea9573d0bb7afc2185
    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId) {
        Evenement evenement = eventRepo.findById(eventId).orElse(null);
        if(evenement==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResponse("Evénement introuvable"));
        }
                eventRepo.deleteById(eventId);
        return ResponseEntity.ok().body(new MessageResponse("Evenement supprimé avec succès"));
    }

    @Override
<<<<<<< HEAD
    public ResponseEntity<?> addEvent(Evenement e) {
        if( eventRepo.existsByNomEvenement(e.getNomEvenement()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Evenement existe deja"));
        }

        Evenement evenement = new Evenement(e.getNomEvenement(),
                e.getDescriptionEvenement(),e.getDateDebut(),e.getDateFin(),e.getPrix(),e.getNbrePersonnes(),e.getImage(),e.getTypeEvenement(),e.getAutreType(),e.getInteresse(),e.getStatut());

        eventRepo.save(evenement);
        return ResponseEntity.ok().body(new MessageResponse("Evenement ajouté avec succès"));}
=======

    public ResponseEntity<?> addEvent(Evenement evenement) {
        eventRepo.save(evenement);
        return ResponseEntity.ok().body(new MessageResponse("Evenement ajouté avec succès"));
    }
>>>>>>> c67251562a24b0c0701f0bea9573d0bb7afc2185
}
