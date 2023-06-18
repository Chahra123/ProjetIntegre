package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEventService {

    List<Evenement> getEvents();
<<<<<<< HEAD
=======

>>>>>>> c67251562a24b0c0701f0bea9573d0bb7afc2185
    ResponseEntity<MessageResponse> getEvent(Long eventId);
    ResponseEntity<?> updateEvent(Long eventId, Evenement event);
    ResponseEntity<?> deleteEvent(Long eventId);

    ResponseEntity<?> addEvent(Evenement evenement);

}
