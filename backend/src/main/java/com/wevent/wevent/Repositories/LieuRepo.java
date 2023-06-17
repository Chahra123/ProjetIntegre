package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieuRepo extends JpaRepository<Lieu,Long> {Boolean existsByNomEmplacement(String nom);
}
