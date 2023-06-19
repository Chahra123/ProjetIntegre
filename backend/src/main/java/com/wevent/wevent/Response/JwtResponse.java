package com.wevent.wevent.Response;

import com.wevent.wevent.Entities.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private Utilisateur user;
    private String jwtToken;

    public JwtResponse(Utilisateur user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
}