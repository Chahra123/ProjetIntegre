package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Question;
import com.wevent.wevent.Entities.ReponseQuestion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IQuestionService {
    List<Question> getAllQuestions();

    ResponseEntity<?> addQuestion(Question question);

    ResponseEntity<?> deleteQuestion(Long id);

    ResponseEntity<?> updateQuestion(Question rq, Long quesId);

    ResponseEntity<?> getQuestion(Long id);
}
