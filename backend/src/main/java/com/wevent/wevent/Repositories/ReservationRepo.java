package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepo extends JpaRepository<Reservation,Long> {


}
