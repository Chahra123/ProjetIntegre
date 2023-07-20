package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.ETypeEvenement;
import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Societe;
import com.wevent.wevent.Repositories.EventRepo;
import com.wevent.wevent.Repositories.ReservationRepo;
import com.wevent.wevent.Repositories.SocieteRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventService implements IEventService{
    EventRepo eventRepo;
    SocieteRepo societeRepo;
    ReservationRepo reservationRepo;
    @Override
    public List<Evenement> getEvents() {
        return eventRepo.findAll();
    }

    @Override
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
    public ResponseEntity<?> addEvent(Evenement evenement) {
        eventRepo.save(evenement);
        return ResponseEntity.ok().body(new MessageResponse("Evenement ajouté avec succès"));
    }

    @Override
    public Set<Evenement> getEventsForSociety(Long societyId) {
        Societe s = societeRepo.findById(societyId).orElse(null);
        if(s!=null)
        {
            return s.getEvenements();
        }
        return null;
    }

    @Override
    public void affectEventForSociety(Long idSociety,Long idEvent) {
        Evenement e = eventRepo.findById(idEvent).orElse(null);
        Societe s= societeRepo.findById(idSociety).orElse(null);
        if(e!=null && s!=null)
        {
            s.getEvenements().add(e);
            e.setSociete(s);
            eventRepo.save(e);
            societeRepo.save(s);
        }
    }
}
