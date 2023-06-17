package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Evenement,Long> {
}
