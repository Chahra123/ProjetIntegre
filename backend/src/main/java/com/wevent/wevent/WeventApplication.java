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

	@Bean
	CommandLineRunner run(UserService userService)
	{
		return args ->{
		userService.addRole(new Role(null, ERole.ADMIN));
			userService.addRole(new Role(null, ERole.ORGANISATEUR));
			userService.addRole(new Role(null, ERole.CLIENT));

			userService.addUser(new Utilisateur(null, "CHAHRAZED","BENAZAIEZ","cbenazaiez6@gmail.com",new ArrayList<>(),"chahrazed"));

			userService.addRoleToUser("cbenazaiez6@gmail.com",ERole.ADMIN);
		};
	}

}
