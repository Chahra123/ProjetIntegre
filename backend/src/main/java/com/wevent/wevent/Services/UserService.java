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
import java.util.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService implements  IUserService, UserDetailsService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND = "Utilisateur avec l'email %s est introuvable";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<Utilisateur> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Utilisateur getUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    @Override
    public Utilisateur getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
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
        u.setEmail(userDetails.getEmail());
        u.setNom(userDetails.getNom());
        u.setPrenom(userDetails.getPrenom());

        // Chiffrer le mot de passe avant de le stocker
        String motDePasse = userDetails.getMotDePasse();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String motDePasseCrypte = passwordEncoder.encode(motDePasse);
        u.setMotDePasse(motDePasseCrypte);

        u.setNumTel(userDetails.getNumTel());
        u.setDateNaissance(userDetails.getDateNaissance());
        userRepo.save(u);
        return ResponseEntity.ok().body(new MessageResponse("User updated successfully"));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        userRepo.deleteUserFromConfirmationToken(userId);
        userRepo.deleteUserFromUserRoles(userId);
        userRepo.deleteById(userId);
        return ResponseEntity.ok().body(new MessageResponse("User deleted successfully"));
    }

    @Override
    public void addRoleToUser(String email, String nomRole) {
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
        if (userRepo.existsByEmail(utilisateur.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Email déjà utilisé"));
        }
        log.info("Saving new user to the DB");
        List<Role> persistedRoles = new ArrayList<>();
        Role roleClient = roleRepo.findByNomRole("CLIENT");
        if(roleClient!=null)
        {
            persistedRoles.add(roleClient);
        }
        utilisateur.setRoles(persistedRoles);
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setRoles(persistedRoles);
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
        if (utilisateur.getMotDePasse()==null) {
            // Handle the case when the password is null
            throw new IllegalArgumentException("Password cannot be null");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(utilisateur.getMotDePasse());
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

    public boolean ifEmailExists(String email)
    {
        return userRepo.existsByEmail(email);
    }

    public Object getUserByHisLastAndFirstName(Long userId)
    {
        return userRepo.getUserByHisLastAndFirstName(userId);
    }

    public String generateResetToken(Utilisateur utilisateur) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusHours(1); // Set the token expiration time (e.g., 1 hour from now)
        utilisateur.setResetToken(token);
        utilisateur.setResetTokenExpiration(expiration);
        userRepo.save(utilisateur); // Save the updated user object to the database
        return token;
    }

    public void resetPassword(String email, String resetToken, String newPassword) {
        Utilisateur utilisateur = userRepo.findByEmail11(email);
        if (utilisateur == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!utilisateur.getResetToken().equals(resetToken)) {
            throw new IllegalArgumentException("Invalid reset token");
        }
        if (utilisateur.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reset token has expired");
        }
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(newPassword));
        utilisateur.setResetToken(null); // Reset the token and expiration after successful password reset
        utilisateur.setResetTokenExpiration(null);
        userRepo.save(utilisateur); // Save the updated user object with the new password
    }

    public void saveResetToken(Utilisateur utilisateur, String resetToken) {
        utilisateur.setResetToken(resetToken);
        // Set the expiration date/time for the reset token as per your requirements
        utilisateur.setResetTokenExpiration(LocalDateTime.now().plusHours(1)); // Example: Token expires in 1 hour
        userRepo.save(utilisateur);
    }

    public Utilisateur validateResetToken(String resetToken) {
        Utilisateur user = getUserByResetToken(resetToken);
        if(user==null)
        {
            throw new IllegalArgumentException("Invalid or expired reset token");
        }

        if (user.isExpired()) {
            throw new IllegalArgumentException("Reset token has expired");
        }

        return user;
    }

    public void resetUserPassword(Utilisateur utilisateur, String newPassword) {
        // Update the user's password with the new password
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(newPassword));
        userRepo.save(utilisateur);
    }

    @Override
    public Utilisateur getUserByResetToken(String token) {
        return userRepo.findByResetToken(token);
    }



}
