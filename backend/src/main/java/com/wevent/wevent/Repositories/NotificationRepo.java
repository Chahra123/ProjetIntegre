package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

    @Query(
            value = "SELECT  notification.id_notif,notification.contenu_notif        \n" +
                    "FROM\n" +
                    "                    notification,users,users_roles,role, notification_utilisateur\n" +
                    "                    WHERE\n" +
                    "                    notification_utilisateur.id_utilisateur=users.id_utilisateur\n" +
                    "                    AND users.id_utilisateur= ?1\n" +
                    "                    AND users.id_utilisateur = users_roles.utilisateur_id_utilisateur\n" +
                    "                    AND role.id_role=users_roles.roles_id_role\n" +
                    "                    AND (role.nom_role='ADMIN' OR role.nom_role='ORGANISATEUR')",
            nativeQuery = true)
    List<Notification> getNotifsForAdminAndOrganisateur(long userId);

    @Query(
            value = "SELECT  notification.id_notif,notification.contenu_notif        \n" +
                    "FROM\n" +
                    "                    notification,users,users_roles,role, notification_utilisateur\n" +
                    "                    WHERE\n" +
                    "                    notification_utilisateur.id_utilisateur=users.id_utilisateur\n" +
                    "                    AND users.id_utilisateur= ?1\n" +
                    "                    AND users.id_utilisateur = users_roles.utilisateur_id_utilisateur\n" +
                    "                    AND role.id_role=users_roles.roles_id_role\n" +
                    "                    AND (role.nom_role='CLIENT')",
            nativeQuery = true)
    List<Notification> getNotifsForClient(long userId);

    @Query(
            value = "SELECT  notification.id_notif,notification.contenu_notif        \n" +
                    "FROM\n" +
                    "                    notification,users,notification_utilisateur, question" +
                    "                    WHERE\n" +
                    "                    question.utilisateur_id_utilisateur=users.id_utilisateur\n" +
                    "                    AND notification_utilisateur.id_utilisateur=users.id_utilisateur\n" +
                    "                    AND users.id_utilisateur= ?1\n" ,
            nativeQuery = true)
    List<Notification> getNotifsResponse(long userId);

}
