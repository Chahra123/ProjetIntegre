package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Services.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final IReservationService iReservationService;

    @GetMapping
    public List<Reservation> getReservations() {
        List<Reservation> list = iReservationService.getAllReservations();
        return list;
    }
    @GetMapping("/{idRes}")
    public ResponseEntity<?> getReservation(@PathVariable("idRes") Long idReservation) {
        return iReservationService.getReservation(idReservation);
    }

    @PostMapping
    public ResponseEntity<?> addReservation(@RequestBody Reservation rs) {
        return iReservationService.addReservation(rs);

    }

    @DeleteMapping("{idRes}")
    public ResponseEntity<?> deleteReservation(@PathVariable("idRes") Long idReservation) {
        return iReservationService.deleteReservation(idReservation);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation rs, @PathVariable("id") Long idReservation) {
        return iReservationService.updateReservation(rs, idReservation);
    }
}
