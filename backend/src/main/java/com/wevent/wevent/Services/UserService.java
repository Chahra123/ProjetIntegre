package com.wevent.wevent.Services;

import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.RoleRepo;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Token.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService implements  IUserService, UserDetailsService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final RoleRepo roleRepo;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final static String USER_NOT_FOUND = "Utilisateur avec l'email %s est introuvable";
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<Utilisateur> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Role addRole(Role role) {
        log.info("Saving a role to DB");
        return roleRepo.save(role);
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
    public ResponseEntity<?> deleteUser(Long userId) {
        userRepo.deleteById(userId);
        return ResponseEntity.ok().body(new MessageResponse("Utilisateur supprimé avec succès"));
    }

    @Override
    public void addRoleToUser(String email, ERole nomRole) {
        Utilisateur utilisateur = userRepo.findByEmail11(email);
        Role role =  roleRepo.findByNomRole(nomRole);
        utilisateur.getRoles().add(role);
        log.info("Adding role {} to user {}",role.getNomRole(),utilisateur.getEmail());
        userRepo.save(utilisateur);
    }

    @Override
    public int enableUser(String email) {
        return userRepo.enableUser(email);

    }

    @Override
    public ResponseEntity<?> addUser(Utilisateur utilisateur) {
        if (userRepo.existsByEmail(utilisateur.getEmail()))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email déjà utilisé"));
        }
        log.info("Saving new user to the DB");
        userRepo.save(utilisateur);
        return ResponseEntity.ok().body(new MessageResponse("Utilisateur ajouté avec succès"));
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = userRepo.findByEmail11(email);
        if(utilisateur==null)
        {
            log.error("User not found in DB");
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND,email));
        }
        else {
            log.info("User found in DB");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getNomRole().toString()));
        });
        return new org.springframework.security.core.userdetails.User(utilisateur.getEmail(),utilisateur.getMotDePasse(),authorities);
    }

    public String signUpUser(Utilisateur utilisateur)
    {
        boolean userExists = userRepo.findByEmail(utilisateur.getEmail()).isPresent();
        if (userExists)
        {
            throw new IllegalStateException(("email deja existant"));
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(utilisateur.getPassword());
        utilisateur.setMotDePasse(encodedPassword);
        userRepo.save(utilisateur);
        //SEND CONFIRMATION TOKEN:
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                //set the duration of the token to 10 minutes
                LocalDateTime.now().plusMinutes(10),
                utilisateur
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //SEND EMAIL
        return token;
    }
}
