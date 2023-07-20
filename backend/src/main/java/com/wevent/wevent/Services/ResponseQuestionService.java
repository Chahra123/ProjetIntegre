package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.ReponseQuestion;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Repositories.ReponseQuestionRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ResponseQuestionService implements IReponseQuestionService{
    private final ReponseQuestionRepo reponseQuestionRepo;

    @Override
    public List<ReponseQuestion> getAllReponseQuestion() {
        return reponseQuestionRepo.findAll();
    }
    @Override
    public ResponseEntity<?> addReponseQuestion(ReponseQuestion rq) {
        try{
            rq.setDateReponse(new Date());
            reponseQuestionRepo.save(rq);
            return ResponseEntity.ok().body(new MessageResponse("Reponse ajoutée avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur d'ajout"));
        }

    }

    @Override
    public ResponseEntity<?> deleteReponseQuestion(Long id) {
        try{
            ReponseQuestion reps = reponseQuestionRepo.findById(id).orElse(null);
            if(reps == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Reponse introuvable"));
            }
            reponseQuestionRepo.deleteById(id);
            return ResponseEntity.ok().body(new MessageResponse("Question supprimée avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur de suppression"));
        }

    }

    @Override
    public ResponseEntity<?> updateReponseQuestion(ReponseQuestion rq, Long repId) {
       ReponseQuestion reps = reponseQuestionRepo.findById(repId).orElse(null);
        try{
            if(reps == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Reponse introuvable"));
            }
            reps.setContenuReponse(rq.getContenuReponse());
            reponseQuestionRepo.save(rq);
            return ResponseEntity.ok().body(new MessageResponse("Reponse mise à jour avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur"));
        }

    }

    @Override
    public ResponseEntity<?> getReponseQuestion(Long id) {
        try{
            ReponseQuestion rep =  reponseQuestionRepo.findById(id).orElse(null);
            if(rep == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Aucune reponse n'est disponible"));
            }
            return ResponseEntity.ok().body(rep);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur"));

        }
    }
}
