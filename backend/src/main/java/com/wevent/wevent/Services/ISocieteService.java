package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Reclamation;
import com.wevent.wevent.Entities.Societe;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISocieteService {
    List<Societe> getAllSocieties();

    ResponseEntity<?> addSociety(Societe s);

    ResponseEntity<?> deleteSociety(Long id);

    public ResponseEntity<?> updateSociety(Societe s, Long societeId);

    Societe getSociety(Long id);
}
