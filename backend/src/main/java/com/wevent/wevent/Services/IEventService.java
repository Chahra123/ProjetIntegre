package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface IEventService {

    List<Evenement> getEvents();
    ResponseEntity<MessageResponse> getEvent(Long eventId);
    ResponseEntity<?> updateEvent(Long eventId, Evenement event);
    ResponseEntity<?> deleteEvent(Long eventId);

    ResponseEntity<?> addEvent(Evenement evenement);

    Set<Evenement> getEventsForSociety(Long societyId);

    void affectEventForSociety(Long idSociety,Long idEvent);
}
