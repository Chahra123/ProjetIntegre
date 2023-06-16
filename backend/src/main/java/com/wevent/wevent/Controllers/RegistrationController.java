package com.wevent.wevent.Controllers;

import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Request.LoginRequest;
import com.wevent.wevent.Request.RegistrationRequest;
import com.wevent.wevent.Services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registration")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    @PostMapping("signup")
    public String signup (@RequestBody Utilisateur request)
    {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    //confirm?token=...
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    @PostMapping(path = "/signin")
    public String login(@RequestBody LoginRequest request) {
        return registrationService.login(request);
    }
}
