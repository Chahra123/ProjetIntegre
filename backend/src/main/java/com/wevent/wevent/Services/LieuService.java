package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Lieu;
import com.wevent.wevent.Repositories.LieuRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class LieuService implements ILieuService{

    private final LieuRepo lieuRepo;



    @Override
    public List<Lieu> getAllLieux() {
        return lieuRepo.findAll();
    }

    @Override
    public Lieu addLieu(Lieu l) {
        if(lieuRepo.existsByNomEmplacement(l.getNomEmplacement()))
        {
            return null;
        }
         return  lieuRepo.save(l);
    }

    @Override
    public void deleteLieu(Long id) {

        lieuRepo.deleteById(id);

    }

    @Override
    public ResponseEntity<MessageResponse> updateLieu(Long id, Lieu newl) {

        Lieu lieu=lieuRepo.findById(id).orElse(null);
        if (lieu==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Lieu introuvable"));
        }
        lieu.setGouvernorat(newl.getGouvernorat());
        lieu.setVille(newl.getVille());
        lieu.setNomEmplacement(newl.getNomEmplacement());
        lieu.setETypeEmplacement(newl.getETypeEmplacement());
        lieu.setCapaciteEffectifs(newl.getCapaciteEffectifs());
        lieuRepo.save(lieu);
        return ResponseEntity.ok().body(new MessageResponse("Lieu mis à jour avec succès"));
    }

    @Override
    public Lieu getLieu(Long id) {
        return lieuRepo.findById(id).orElse(null); }
}
