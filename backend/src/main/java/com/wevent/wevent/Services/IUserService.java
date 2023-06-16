package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    List<Utilisateur> getUsers();
    Utilisateur getUser(Long userId);
    ResponseEntity<?> updateUser(Long userId, Utilisateur utilisateur);
    void deleteUser(Long userId);
    void addRoleToUser(String email, ERole nomRole);
    int enableUser(String email);

    ResponseEntity<?> addUser(Utilisateur utilisateur);
}
