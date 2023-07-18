package com.wevent.wevent.Services;

import com.wevent.wevent.Email.EmailValidator;
import com.wevent.wevent.Entities.Evenement;
import com.wevent.wevent.Entities.Reservation;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.EventRepo;
import com.wevent.wevent.Repositories.NotificationRepo;
import com.wevent.wevent.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Service
@Slf4j
public class TaskSchedulerService implements ITaskSchedulerService{

    @Autowired
    private final NotificationRepo notificationRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final EventRepo eventRepo;
    @Autowired
    private final JavaMailSender mailSender;
    private final static Logger LOGGER= LoggerFactory.getLogger(EmailService.class);
    private EmailValidator emailValidator;

    public boolean isBefore24Hours(Date date) {
        Date maintenant = new Date();
        Date datePlus24Heures = new Date(maintenant.getTime() + 24 * 60 * 60 * 1000); // Ajoute 24 heures en millisecondes
        return date.before(datePlus24Heures);
    }

    @Override
    @Async
    public void send(String to, Long eventId) {
        try
        {
            Evenement evenement = eventRepo.findById(eventId).orElse(null);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setTo(to);
            helper.setSubject("Reminder wevent");
            helper.setFrom("wevent@management.tn");
            //Envoyer le mail
            String lien = "http://localhost:8754/events/" + eventId;
            String texteAffiche = "<a href=\"" + lien + "\">Show more about the event</a>";
            if(!(evenement.getReservations().isEmpty())){
                for(Reservation r: evenement.getReservations()) {
                    Utilisateur client = r.getUtilisateur();
                    helper.setText("Hello " + client.getPrenom() + ", \n"+ r.getEvenement().getNomEvenement() + " is tomorrow! Don't forget to mark your calendar, looking forward to meeting you in person. \n\n" +
                            "DATE: " + r.getEvenement().getDateDebut()+ "\n"+
                            "PLACE: " + r.getEvenement().getLieuEvenement() + "\n");
                    helper.setText("Join "+texteAffiche, true);
                    helper.setText("See you tomorrow! \n\n Cheers,\n Team WEVENT");
                    if(isBefore24Hours(r.getEvenement().getDateDebut())){
                        mailSender.send(mimeMessage);
                    }
                }
            }
            List<Utilisateur> utilisateurs = userRepo.findAll();
            for(Utilisateur u: utilisateurs) {
                for (Role r : u.getRoles()) {
                    if (r.getNomRole().equals("ADMIN") || r.getNomRole().equals("ORGANISATEUR")) {
                        for(Evenement e: u.getEvenements()){
                            helper.setText("Hello " + u.getPrenom() + ", \n"+ e.getNomEvenement() + " is tomorrow! Don't forget to mark your calendar, looking forward to meeting you in person. \n\n" +
                                    "DATE: " + e.getDateDebut()+ "\n"+
                                    "PLACE: " + e.getLieuEvenement() + "\n");
                            helper.setText("Join "+texteAffiche, true);
                            helper.setText("See you tomorrow! \n\n Cheers,\n Team WEVENT");
                            if(isBefore24Hours(e.getDateDebut())){
                                mailSender.send(mimeMessage);
                            }
                        }
                    }
                }
            }
        }
        catch (MessagingException e)
        {
            LOGGER.error("email non envoyé",e);
            throw new IllegalStateException(("failed to send email"));
        }
    }

    @Scheduled(cron = "0 0 12 * * ?") // Exécuter tous les jours à midi (12h00)
    public void sendReminder(){
        Utilisateur request = new Utilisateur();
        Evenement e = new Evenement();
        send(request.getEmail(),e.getIdEvenement());
    }

}
