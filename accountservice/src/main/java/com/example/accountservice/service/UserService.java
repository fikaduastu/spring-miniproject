package com.example.accountservice.service;


import com.example.accountservice.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

	ResponseEntity<?> createUser(User user);
	User loadUserById(Long userId);



}
