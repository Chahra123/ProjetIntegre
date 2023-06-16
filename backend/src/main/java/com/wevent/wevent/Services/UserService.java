package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements  IUserService{
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final static String USER_NOT_FOUND = "Utilisateur avec l'email %s est introuvable";

    @Override
    public List<Utilisateur> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Utilisateur getUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public ResponseEntity<?> updateUser(Long userId,Utilisateur userDetails) {
        Utilisateur u = userRepo.findById(userId).orElse(null);
        if (u==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("Utilisateur introuvable"));
        }
        Utilisateur u1 = userRepo.findByEmail11(userDetails.getEmail());
        if (userRepo.existsByEmail(userDetails.getEmail()) && u1!=u)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email déjà utilisé"));
        }
        u.setNom(userDetails.getNom());
        u.setPrenom(userDetails.getPrenom());
        u.setMotDePasse(userDetails.getMotDePasse());
        u.setNumTel(userDetails.getNumTel());
        u.setDateNaissance(userDetails.getDateNaissance());
        userRepo.save(u);
        return ResponseEntity.ok().body(new MessageResponse("Utilisateur mise à jour avec succès"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public void addRoleToUser(String email, ERole nomRole) {
        Utilisateur u = userRepo.findByEmail11(email);
        u.setRole(nomRole);
        userRepo.save(u);
    }

    @Override
    public ResponseEntity<?> addUser(Utilisateur utilisateur) {
        if (userRepo.existsByEmail(utilisateur.getEmail()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email déjà utilisé"));
        }
        userRepo.save(utilisateur);
        return ResponseEntity.ok().body(new MessageResponse("Utilisateur mise à jour avec succès"));
    }

}
