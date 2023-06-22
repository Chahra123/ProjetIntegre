package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Services.IUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    IUserService userService;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getUsers()
    {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<ResponseEntity<?>> addUser(@RequestBody Utilisateur utilisateur)
    {
        return ResponseEntity.ok().body(userService.addUser(utilisateur));
    }
    @GetMapping("{userId}")
    public Utilisateur getUser(@PathVariable Long userId)
    {
        return userService.getUser(userId);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur)
    {
        return userService.updateUser(id,utilisateur);
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId)
    {
        return userService.deleteUser(userId);
    }

    @PutMapping("/addRole/user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm f) {
        userService.addRoleToUser(f.getEmail(), f.getNomRole());
        return ResponseEntity.ok().build();
    }
}
@Data
class RoleToUserForm
{
    private String email;
    private String nomRole;
}