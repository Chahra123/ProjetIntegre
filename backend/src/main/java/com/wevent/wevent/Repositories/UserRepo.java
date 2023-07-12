package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepo extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findByEmail(String email);

    Boolean existsByEmail(String email);
    @Query(
            value = "SELECT * FROM users WHERE users.email = ?1",
            nativeQuery = true)
    Utilisateur findByEmail11(String email);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE users SET users.enabled = TRUE WHERE users.email = ?1",
            nativeQuery = true)
    int enableUser(String email);

}