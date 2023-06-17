package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEventService {

    List<Evenement> getEvents();
    Evenement getEvent(Long eventId);
    Evenement updateEvent(Long eventId, Evenement event);
    void deleteEvent(Long eventId);

    Evenement addEvent(Evenement evenement);
}
