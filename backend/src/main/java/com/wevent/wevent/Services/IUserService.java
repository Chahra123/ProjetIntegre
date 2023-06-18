package com.wevent.wevent.Services;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<?> addUser(Utilisateur utilisateur);
    ResponseEntity<?> updateUser(Long userId, Utilisateur utilisateur);
    ResponseEntity<?> deleteUser(Long userId);
    void addRoleToUser(String email, ERole nomRole);
    int enableUser(String email);
    Utilisateur getUser(Long userId);

    List<Utilisateur> getUsers();
    Role addRole(Role role);
}
