package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.Notification;
import com.wevent.wevent.Services.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notif")
@RequiredArgsConstructor
public class NotificationController {
    private final INotificationService iNotificationService;


    @GetMapping
    public List<Notification> getAllNotifications() {
        List<Notification> list = iNotificationService.getAllNotifications();
        return list;
    }

    @GetMapping("/one/{idNotif}")
    public Notification getNotification(@PathVariable("idNotif") Long idNotif) {
        return iNotificationService.getNotification(idNotif);
    }

    @GetMapping("spec/{userId}")
    public List<Notification> getNotifForSpecefic(@PathVariable("userId") Long userId) {
        List<Notification> list = iNotificationService.getNotifsForSpecefic(userId);
        return list;
    }


    // @GetMapping("/rappel/{userId}")
    //  public String triggerReminder(Long userId){
    //    return iNotificationService.triggerReminder(@PathVariable)

    }


    /*@PostMapping
    public Notification addNotifaction(@RequestBody Notification notif) {
        Notification notification = iNotificationService.addNotification(notif);
        return notification;
    }

    @DeleteMapping("{idNotif}")
    public void deleteNotification(@PathVariable("idNotif") Long idNotif) {
        iNotificationService.deleteNotification(idNotif);
    }

    @PutMapping
        public Notification updateNotification(@RequestBody Notification notif) {
        return iNotificationService.updateNotification(notif);
    }*/


