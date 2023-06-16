package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Services.IUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("crudusers")
public class UserController {
    IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<Utilisateur>> getUsers()
    {
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("users/{userId}")
    public Utilisateur getUser(@PathVariable Long userId)
    {
        return userService.getUser(userId);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur)
    {
        return userService.updateUser(id,utilisateur);
    }
    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
    }

    @PutMapping("/addRole/user")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm f)
    {
        userService.addRoleToUser(f.getEmail(),f.getNomRole());
        return ResponseEntity.ok().build();
    }

}
@Data
class RoleToUserForm
{
    private String email;
    private ERole nomRole;
}