package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Repositories.UserRepo;
import com.wevent.wevent.Response.MessageResponse;
import com.wevent.wevent.Services.IUserService;
import com.wevent.wevent.Services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class PasswordController {
    IUserService userService;
    RegistrationService registrationService;
    UserRepo userRepo;

    @PostMapping("changepassword")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap)
    {
        return null;
    }

    @PostMapping("forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        // Check if the email exists in the database
        if (!userService.ifEmailExists(email)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email not found"));
        }
        // Generate and save the reset token for the user
        Utilisateur utilisateur = userRepo.findByEmail11(email);
        String resetToken = userService.generateResetToken(utilisateur);
        userService.saveResetToken(utilisateur, resetToken);
        // Send the password reset email to the user
        registrationService.sendPasswordResetEmail(utilisateur, resetToken);

        return ResponseEntity.ok().body(new MessageResponse("Password reset email sent"));
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String resetToken,
                                           @RequestParam("password") String newPassword) {
        // Validate the reset token
        Utilisateur utilisateur = userService.validateResetToken(resetToken);
        if (utilisateur == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired reset token"));
        }

        // Reset the user's password
        userService.resetUserPassword(utilisateur, newPassword);

        return ResponseEntity.ok().body(new MessageResponse("Password reset successful"));
    }
}
