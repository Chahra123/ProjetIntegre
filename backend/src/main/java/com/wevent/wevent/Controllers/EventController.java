package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("events")
public class EventController {
    IEventService iEventService;

    @GetMapping
    public List<Evenement> getEvents() {
        return iEventService.getEvents();
    }


    @GetMapping("/society/{societyId}")
    public Set<Evenement> getEventsForSociety(@PathVariable Long societyId) {
        return iEventService.getEventsForSociety(societyId);
    }


    @GetMapping("/affect/{idSociety}/{idEvent}")
    public void affectEventForSociety(@PathVariable Long idSociety, @PathVariable Long idEvent) {
        iEventService.affectEventForSociety(idSociety,idEvent);
    }

        @GetMapping("/{eventId}")
    public ResponseEntity<MessageResponse> getEvent(@PathVariable Long eventId) {
            return iEventService.getEvent(eventId);
    }

    @PostMapping
    ResponseEntity<?> addEvent(@RequestBody Evenement evenement)
    {
        return iEventService.addEvent(evenement);
    }

    @PutMapping("/{eventId}")
    @RolesAllowed({"ADMIN","ORGANISATEUR"})
    public ResponseEntity<?> updateEvent(@PathVariable Long eventId, @RequestBody Evenement eventDetails)
    {
        return iEventService.updateEvent(eventId,eventDetails);
    }

    @DeleteMapping("/{eventId}")
    @RolesAllowed({"ADMIN","ORGANISATEUR"})
    public void deleteEvent(@PathVariable Long eventId) {
        iEventService.deleteEvent(eventId);
    }
    }
