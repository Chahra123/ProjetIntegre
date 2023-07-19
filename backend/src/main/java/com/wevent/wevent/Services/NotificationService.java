package com.wevent.wevent.Services;

import com.wevent.wevent.Email.EmailValidator;
import com.wevent.wevent.Entities.*;
import com.wevent.wevent.Repositories.EventRepo;
import com.wevent.wevent.Repositories.NotificationRepo;
import com.wevent.wevent.Repositories.ReservationRepo;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class NotificationService implements INotificationService{

    @Autowired
    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    @Override
    public void notifForAdd(Object o, Utilisateur ut) {
        Notification n = new Notification();
        try {
            if (o instanceof Evenement) {
                if(((Evenement) o).getUtilisateurs() != null) {
                    n.setContenuNotif("Un évenement " + ((Evenement) o).getNomEvenement() + " a été ajouté");
                    ((Evenement) o).getNotifications().add(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setEvenement((Evenement) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            } else if (o instanceof Reclamation) {
                if(((Reclamation) o).getUtilisateur() != null) {
                    n.setContenuNotif(((Reclamation) o).getUtilisateur().getNom() + " " + ((Reclamation) o).getUtilisateur().getPrenom() + " a ajouté une réclamation");
                    ((Reclamation) o).setNotification(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setReclamation((Reclamation) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            } else if (o instanceof Reservation) {
                if(((Reservation) o).getUtilisateur() != null) {
                    n.setContenuNotif(ut.getNom() + " " + ut.getPrenom() + " a reservé l'évènement " + ((Reservation) o).getEvenement().getNomEvenement());
                    ((Reservation) o).getNotifications().add(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setReservation((Reservation) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            } else if (o instanceof Avis) {
                if(((Avis) o).getCommentateur() != null) {
                    n.setContenuNotif(((Avis) o).getCommentateur().getNom() + " " + ((Avis) o).getCommentateur().getPrenom() + " a ajouté un avis");
                    ((Avis) o).setNotification(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setAvis((Avis) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            } else if (o instanceof Question) {
                if(((Question) o).getUtilisateur() != null) {
                    n.setContenuNotif(((Question) o).getUtilisateur().getNom() + " " + ((Question) o).getUtilisateur().getPrenom() + " a publié une question");
                    ((Question) o).setNotification(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setQuestion((Question) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            } else if (o instanceof ReponseQuestion) {
                if(((ReponseQuestion) o).getAuteur() != null) {
                    n.setContenuNotif(((ReponseQuestion) o).getAuteur().getNom() + " " + ((ReponseQuestion) o).getAuteur().getPrenom() + " a répondu à la question que vous avez publié");
                    ((ReponseQuestion) o).setNotification(n);
                    ut.getNotifications().add(n);
                    n.getUtilisateurs().add(ut);
                    n.setReponseQuestion((ReponseQuestion) o);
                    notificationRepo.save(n);
                    userRepo.save(ut);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void notifForUpdate(Object o, Utilisateur ut) {
        Notification n = new Notification();
        if (o instanceof Evenement) {
            n.setContenuNotif("Un évenement " + ((Evenement) o).getNomEvenement() + " a été modifié");
            ((Evenement) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            n.setEvenement((Evenement) o);
            notificationRepo.save(n);
            userRepo.save(ut);
        }

    }

    @Override
    public void notifForDelete(Object o, Utilisateur ut) {
        Notification n = new Notification();
        if (o instanceof Evenement) {
            n.setContenuNotif("L'évenement " + ((Evenement) o).getNomEvenement() + " a été annulé");
            ((Evenement) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            n.setEvenement((Evenement) o);
            notificationRepo.save(n);
            userRepo.save(ut);
        }
    }

    @Override
    public Notification getNotification(Long idNotif) {
        return notificationRepo.findById(idNotif).orElse(null);
    }

    @Override
    public List<Notification> getNotifsForSpecefic(Long userId) {
        return notificationRepo.getNotifsForSpecefic(userId);
    }


}