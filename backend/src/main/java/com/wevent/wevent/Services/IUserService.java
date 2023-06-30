package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface IUserService {

    ResponseEntity<?> addUser(Utilisateur utilisateur);
    ResponseEntity<?> updateUser(Long userId, Utilisateur utilisateur);
    ResponseEntity<?> deleteUser(Long userId);
    void addRoleToUser(String email, String nomRole);
    int enableUser(String email);
    Utilisateur getUser(Long userId);
    Object getUserByHisLastAndFirstName(Long userId);

    List<Utilisateur> getUsers();
    boolean ifEmailExists(String email);
    String generateResetToken(Utilisateur utilisateur);
    void saveResetToken(Utilisateur utilisateur, String resetToken);
    Utilisateur validateResetToken(String resetToken);
    void resetUserPassword(Utilisateur utilisateur, String newPassword);
    Utilisateur getUserByResetToken(String token);
    }
