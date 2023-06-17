package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Lieu;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Services.ILieuService;
import com.wevent.wevent.Services.LieuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lieu")
@AllArgsConstructor
public class LieuController {
    private ILieuService lieuService;

    @GetMapping
    public List<Lieu> getAllLieux(){
            return lieuService.getAllLieux();
    }


    @PostMapping
    public Lieu addLieu(@RequestBody Lieu l){
        return lieuService.addLieu(l);
    }

    @DeleteMapping("/{id}")
    public void deleteLieu(@PathVariable Long id){
        lieuService.deleteLieu(id);
    }

    @PutMapping("/{id}/lieu")
    public ResponseEntity<MessageResponse> updateLieu(@PathVariable Long id, @RequestBody Lieu newl){
        return lieuService.updateLieu(id,newl);
    }

    @GetMapping("/{id}")
    public Lieu getLieu(@PathVariable Long id){
        return lieuService.getLieu(id);
    }
}
