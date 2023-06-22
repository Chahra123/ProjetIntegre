package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByNomRole(String nom);
}
