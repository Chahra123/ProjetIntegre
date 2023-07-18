package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Entities.ReponseQuestion;
import com.wevent.wevent.Entities.Utilisateur;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INotificationService {

    List<Notification> getAllNotifications();

    void notifForAdd(Object o, Utilisateur ut);
    void notifForUpdate(Object o, Utilisateur ut);
    void notifForDelete(Object o, Utilisateur ut);
    Notification getNotification(Long idNotif);
    List<Notification> getNotifsForSpecefic(Long userId);


}
