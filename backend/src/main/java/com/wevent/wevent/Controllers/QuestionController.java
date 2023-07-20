package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Entities.Question;
import com.wevent.wevent.Services.INotificationService;
import com.wevent.wevent.Services.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService iQuestionService;

    @GetMapping
    public List<Question> getAllQuestions() {
        List<Question> list = iQuestionService.getAllQuestions();
        return list;
    }
    @GetMapping("/{idQuestion}")
    public ResponseEntity<?> getQuestion(@PathVariable("idQuestion") Long idQuestion) {
        return iQuestionService.getQuestion(idQuestion);
    }

    @PostMapping
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return iQuestionService.addQuestion(question);

    }

    @DeleteMapping("{idQuestion}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("idQuestion") Long idQuestion) {
        return iQuestionService.deleteQuestion(idQuestion);
    }

    @PutMapping("{idQuestion}")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question, @PathVariable("idQuestion") Long idQuestion) {

        return iQuestionService.updateQuestion(question, idQuestion);
    }

}
