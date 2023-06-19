package com.wevent.wevent;

import com.wevent.wevent.Entities.ERole;
import com.wevent.wevent.Entities.Role;
import com.wevent.wevent.Entities.Utilisateur;
import com.wevent.wevent.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class WeventApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeventApplication.class, args);
	}
}
