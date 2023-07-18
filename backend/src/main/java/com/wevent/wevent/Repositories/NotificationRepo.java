package com.wevent.wevent.Repositories;

import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

    @Query(
            value = "SELECT * "+
                    "FROM notification_utilisateur, notification " +
            "WHERE notification_utilisateur.id_utilisateur = ?1 " +
            "AND notification_utilisateur.id_notif = notification.id_notif ",
            nativeQuery = true)
    List<Notification> getNotifsForSpecefic(long userId);

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
