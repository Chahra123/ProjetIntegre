package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Societe;
import com.wevent.wevent.Repositories.SocieteRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SocieteService implements ISocieteService{
    SocieteRepo societeRepo;

    @Override
    public List<Societe> getAllSocieties() {
        return societeRepo.findAll();
    }

    @Override
    public ResponseEntity<?> addSociety(Societe s) {
        Societe societe = societeRepo.findByNomSociete(s.getNomSociete());
        if (societeRepo.existsByNomSociete(s.getNomSociete()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Societé existe deja"));
        }
        societe = new Societe();
        societe.setNomSociete(s.getNomSociete());
        societe.setLogoSociete(s.getLogoSociete());
        societe.setEmail(s.getEmail());
        societe.setAddresse(s.getAddresse());
        societe.setNumTel(s.getNumTel());
        societe.setEvenements(s.getEvenements());
        societeRepo.save(societe);
        return ResponseEntity.ok().body(new MessageResponse("Societé ajoutée avec succès"));

    }

    @Override
    public ResponseEntity<?> deleteSociety(Long id) {
        societeRepo.deleteById(id);
        return ResponseEntity.ok().body(new MessageResponse("Societé supprimée avec succès"));
    }

    @Override
    public ResponseEntity<?> updateSociety(Societe s, Long societeId) {
        Societe soc = societeRepo.findById(societeId).orElse(null);
        try{
            if(soc == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new MessageResponse("Societé introuvable"));
            }
            soc.setNomSociete(s.getNomSociete());
            soc.setLogoSociete(s.getLogoSociete());
            soc.setEmail(s.getEmail());
            soc.setAddresse(s.getAddresse());
            soc.setNumTel(s.getNumTel());
            societeRepo.save(soc);
            return ResponseEntity.ok().body(new MessageResponse("Societé mise à jour avec succès"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Erreur"));
        }
    }

    @Override
    public Societe getSociety(Long id) {
        return societeRepo.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> addSocietyWithNoLogo(Societe s) {
        Societe soc = societeRepo.findByNomSociete(s.getNomSociete());
        if (societeRepo.existsByNomSociete(s.getNomSociete()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Societé existe deja"));
        }

        soc = new Societe();
        soc.setNumTel(s.getNumTel());
        soc.setNomSociete(s.getNomSociete());
        s.setAddresse(s.getAddresse());
        s.setEmail(s.getEmail());

        societeRepo.save(soc);

        return ResponseEntity.ok().body(new MessageResponse("Societé ajoutée avec succès"));


    }
}
