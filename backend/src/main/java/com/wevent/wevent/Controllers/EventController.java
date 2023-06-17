package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController {
    IEventService iEventService;

    @GetMapping
    public List<Evenement> getEvents() {
        return iEventService.getEvents();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<MessageResponse> getEvent(@PathVariable Long eventId) {
            return iEventService.getEvent(eventId);
    }

    @PostMapping
    ResponseEntity<?> addEvent(Evenement evenement)
    {
        return iEventService.addEvent(evenement);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody Evenement eventDetails)
    {
        return iEventService.updateEvent(eventId,eventDetails);
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        iEventService.deleteEvent(eventId);
    }
    }
