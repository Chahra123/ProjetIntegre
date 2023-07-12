package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.*;
import com.wevent.wevent.Repositories.NotificationRepo;
import com.wevent.wevent.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Service
@AllArgsConstructor
public class NotificationService implements INotificationService{

    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;
    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepo.findAll();
    }

    @Override
    public void notifForAdd(Object o, Utilisateur ut) {
        Notification n = new Notification();
        if (o instanceof Evenement){
            n.setContenuNotif("Un évenement "+ ((Evenement) o).getNomEvenement() + "a été ajouté");
            ((Evenement) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        } else if (o instanceof Reclamation){
            n.setContenuNotif(((Reclamation) o).getUtilisateur().getNom()+" "+ ((Reclamation) o).getUtilisateur().getPrenom() + " a ajouté une réclamation");
            ((Reclamation) o).setNotification(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        } else if (o instanceof Reservation){
            n.setContenuNotif(((Reservation) o).getUtilisateur().getNom()+" "+ ((Reservation) o).getUtilisateur().getPrenom()+" a reservé l'évènement "+((Reservation) o).getEvenement().getNomEvenement());
            ((Reservation) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        } else if(o instanceof Avis){
            n.setContenuNotif(((Avis) o).getCommentateur().getNom()+" "+ ((Avis) o).getCommentateur().getPrenom() + " a ajouté un avis");
            ((Avis) o).setNotification(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        } else if (o instanceof Question){
            n.setContenuNotif(((Question) o).getUtilisateur().getNom()+" "+ ((Question) o).getUtilisateur().getPrenom() + " a publié une question");
            ((Question) o ).setNotification(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        } else if (o instanceof ReponseQuestion){
            n.setContenuNotif(((ReponseQuestion) o).getAuteur().getNom()+" "+ ((ReponseQuestion) o).getAuteur().getPrenom() + " a répondu à la question que vous avez publié");
            ((ReponseQuestion) o ).setNotification(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        }
    }

    @Override
    public void notifForUpdate(Object o, Utilisateur ut) {
        Notification n = new Notification();
        if (o instanceof Evenement) {
            n.setContenuNotif("Un évenement " + ((Evenement) o).getNomEvenement() + "a été modifié");
            ((Evenement) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        }

    }

    @Override
    public void notifForDelete(Object o, Utilisateur ut) {
        Notification n = new Notification();
        if (o instanceof Evenement) {
            n.setContenuNotif("L'évenement " + ((Evenement) o).getNomEvenement() + "a été annulé");
            ((Evenement) o).getNotifications().add(n);
            ut.getNotifications().add(n);
            n.getUtilisateurs().add(ut);
            notificationRepo.save(n);
            userRepo.save(ut);
        }
    }

    @Override
    public Notification getNotification(Long id) {
        return notificationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Notification> getNotifsForAdminAndOrganisateur(Long userId) {
        return notificationRepo.getNotifsForAdminAndOrganisateur(userId);
    }

    @Override
    public List<Notification> getNotifsForClient(Long userId) {
        return notificationRepo.getNotifsForClient(userId);
    }


}