package com.wevent.wevent.Request;

import com.wevent.wevent.Entities.ERole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String prenom;
    private final String nom;
    private final Date dateNaissance;
    private Long numTel;
    private final String email;
    private final String motDePasse;
}
