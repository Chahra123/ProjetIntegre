package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEventService {

    List<Evenement> getEvents();
    ResponseEntity<MessageResponse> getEvent(Long eventId);
    ResponseEntity<?> updateEvent(Long eventId, Evenement event);
    ResponseEntity<?> deleteEvent(Long eventId);

    ResponseEntity<?> addEvent(Evenement evenement);

}