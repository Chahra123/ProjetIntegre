package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.ReponseQuestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReponseQuestionService {
    List<ReponseQuestion> getAllReponseQuestion();

    ResponseEntity<?> addReponseQuestion(ReponseQuestion rq);

    ResponseEntity<?> deleteReponseQuestion(Long id);

    ResponseEntity<?> updateReponseQuestion(ReponseQuestion rq, Long RepId);

    ResponseEntity<?> getReponseQuestion(Long id);
}
