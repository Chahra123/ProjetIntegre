package com.wevent.wevent.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private Long id;
    private String email;
    private List<String> roles;
    private String type = "Bearer";
    private String token;
    private String refreshToken;
    private String firstName;
    private String lastName;


    public JwtResponse(String accessToken, String refreshToken, Long id,String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }

    public JwtResponse(Long id, String email, List<String> roles, String token, String refreshToken,
                       String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.token = token;
        this.refreshToken = refreshToken;
    }

}