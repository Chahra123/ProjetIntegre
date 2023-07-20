package com.wevent.wevent.Repositories;

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

    @Transactional
    @Modifying
    @Query(value = "delete from confirmation_token where utilisateur_id_utilisateur =?1", nativeQuery = true)
    void deleteUserFromConfirmationToken(long id);

    @Transactional
    @Modifying
    @Query(value = "delete from users_roles where utilisateur_id_utilisateur = ?1", nativeQuery = true)
    void deleteUserFromUserRoles(long id);

    @Query(
            value = "SELECT "
                    + "users.id,"
                    + "users.prenom,"
                    + "users.nom "
                    + "FROM "
                    + "users,roles users_roles "
                    + "WHERE "
                    + "users.id_utilisateur= ?1",
            nativeQuery = true)
    Object getUserByHisLastAndFirstName(long userId);
    Utilisateur findByResetToken(String resetToken);
}