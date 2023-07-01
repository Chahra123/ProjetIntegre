package com.wevent.wevent.Controllers;


import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Services.IUserService;
import com.wevent.wevent.Services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {
    IUserService userService;
    UserRepo userRepo;
    RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getUsers()
    {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("firstandlastnameandid/{id}")
    public ResponseEntity<Object> getUserFirstAndLastName(@PathVariable(value="id") long userId){
        return ResponseEntity.ok().body(userService.getUserByHisLastAndFirstName(userId));
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

    @GetMapping("/check-email-exists")
    public boolean checkIfMailExists(@RequestParam String email)
    {
        return userService.ifEmailExists(email);
    }
}
@Data
class RoleToUserForm
{
    private String email;
    private String nomRole;
}