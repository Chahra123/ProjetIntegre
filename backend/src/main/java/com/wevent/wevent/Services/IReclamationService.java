package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Reclamation;

import java.util.List;

public interface IReclamationService {
    List<Reclamation> getAllReclamations();

    Reclamation addReclamation(Reclamation rs);

    void deleteReclamation(Long id);

    Reclamation updateReclamation(Reclamation rs);

    Reclamation getReservation(Long id);
}
