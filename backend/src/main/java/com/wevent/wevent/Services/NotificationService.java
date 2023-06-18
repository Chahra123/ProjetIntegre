package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Repositories.NotificationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class NotificationService implements INotificationService{

    private final NotificationRepo notificationRepo;
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    @Override
    public Notification addNotification(Notification nt) {
        return notificationRepo.save(nt);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);
    }

    @Override
    public Notification updateNotification(Notification nt) {
        return notificationRepo.save(nt);
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationRepo.findById(id).orElse(null);
    }
}
