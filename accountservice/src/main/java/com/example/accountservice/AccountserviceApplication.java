package com.example.accountservice;

import java.util.Arrays;

import com.example.accountservice.entity.User;
import com.example.accountservice.entity.UserRole;
import com.example.accountservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.accountservice.entity.Role;
import com.example.accountservice.enums.Profile;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableEurekaClient
public class AccountserviceApplication implements CommandLineRunner {


	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(AccountserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Role roleUser = new Role(Profile.ROLE_USER);
		User user = new User();
		user.setEmail("medk@gmail.com");
		user.setPassword(passwordEncoder.encode("med"));
		user.setUsername("Mohamed");
		UserRole urUser = new UserRole(user, roleUser);
		user.getUserRoles().add(urUser);


		Role roleAdmin = new Role(Profile.ROLE_ADMIN);
		User admin = new User();
		admin.setEmail("devmedk@gmail.com");
		admin.setPassword(passwordEncoder.encode("med"));
		admin.setUsername("khaild");
		UserRole urAdmin1 = new UserRole(admin, roleAdmin);
		UserRole urAdmin2 = new UserRole(admin, roleUser);
		admin.getUserRoles().addAll(Arrays.asList(urAdmin2, urAdmin1));

		userRepo.saveAll(Arrays.asList(user, admin));

	}

}
