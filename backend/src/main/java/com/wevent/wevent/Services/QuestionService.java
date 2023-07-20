package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Question;
import com.wevent.wevent.Entities.ReponseQuestion;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Repositories.NotificationRepo;
import com.wevent.wevent.Repositories.QuestionRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepo questionRepo;
    @Override
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    @Override
    public ResponseEntity<?> addQuestion(Question question) {
        try{
            question.setDateCreationQuestion(new Date());
            questionRepo.save(question);
            return ResponseEntity.ok().body(new MessageResponse("Question ajoutée avec succès"));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur d'ajout"));
        }

    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long id) {
        try{
            Question ques = questionRepo.findById(id).orElse(null);
            if(ques == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Question introuvable"));
            }
            questionRepo.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Question supprimée avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur de suppression"));

        }

    }

    @Override
    public ResponseEntity<?> updateQuestion(Question rq, Long quesId) {
        Question ques = questionRepo.findById(quesId).orElse(null);
        try{
            if(ques == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Question introuvable"));
            }
            ques.setContenuQuestion(rq.getContenuQuestion());
            ques.setStatutQuestion(rq.getStatutQuestion());
            questionRepo.save(rq);
            return ResponseEntity.ok().body(new MessageResponse("Question mise à jour avec succès"));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur de modification"));
        }

    }

    @Override
    public ResponseEntity<?> getQuestion(Long id) {
        try{
            Question ques =  questionRepo.findById(id).orElse(null);
            if(ques == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Aucune question n'est disponible"));
            }
            return ResponseEntity.ok().body(ques);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur"));

        }
    }
}
