package com.example.accountservice.serviceImpl;

import com.example.accountservice.entity.User;
import com.example.accountservice.repository.UserRepo;
import com.example.accountservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(username);
	}

	@Override
	public ResponseEntity<?> createUser(User user) {
		// TODO Auto-generated method stub
		return new ResponseEntity<User>(userRepo.save(user),HttpStatus.OK);
	}
	
	public User loadUserById(Long id) {
		System.out.print("service user"+id);
		return userRepo.findById(id).get();

	}
	
	

	

}
