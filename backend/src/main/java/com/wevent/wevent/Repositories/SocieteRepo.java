package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocieteRepo extends JpaRepository<Societe,Long> {
    Boolean existsByNomSociete(String nom);

    Societe findByNomSociete(String nomSociete);
}
