package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.ReponseQuestion;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Services.IReponseQuestionService;
import com.wevent.wevent.Services.IReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponse")
@RequiredArgsConstructor
public class ReponseQuestionController {

    private final IReponseQuestionService iReponseQuestionService;

    @GetMapping
    public List<ReponseQuestion> getAllReponseQuestions() {
        List<ReponseQuestion> list = iReponseQuestionService.getAllReponseQuestion();
        return list;
    }
    @GetMapping("/{idResp}")
    public ResponseEntity<?> getReponseQuestion(@PathVariable("isResp") Long idReponseQuestion) {
        return iReponseQuestionService.getReponseQuestion(idReponseQuestion);
    }

    @PostMapping
    public ResponseEntity<?> addReponseQuestion(@RequestBody ReponseQuestion rep) {
        return iReponseQuestionService.addReponseQuestion(rep);

    }

    @DeleteMapping("{idRep}")
    public ResponseEntity<?> deleteReponseQuestion(@PathVariable("idRes") Long idReponseQuestion) {
       return iReponseQuestionService.deleteReponseQuestion(idReponseQuestion);
    }

    @PutMapping("{idRep}")
    public ResponseEntity<?> updateReservation(@RequestBody ReponseQuestion rep, @PathVariable("idRes") Long idReponseQuestion) {
        return iReponseQuestionService.updateReponseQuestion(rep, idReponseQuestion);
    }
}
