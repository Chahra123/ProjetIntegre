package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Entities.ReponseQuestion;

import java.util.List;

public interface INotificationService {

    List<Notification> getAllNotifications();

    Notification addNotification(Notification nt);

    void deleteNotification(Long id);

    Notification updateNotification(Notification nt);

    Notification getNotification(Long id);
}
