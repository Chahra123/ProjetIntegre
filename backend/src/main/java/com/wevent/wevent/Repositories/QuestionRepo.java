package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Question;
import com.wevent.wevent.Entities.ReponseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
