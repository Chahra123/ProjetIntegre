package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.ReponseQuestion;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReponseQuestionRepo extends JpaRepository<ReponseQuestion,Long> {
}
