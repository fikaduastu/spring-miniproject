package com.example.accountservice.controller;

import com.example.accountservice.dto.JwtAuthResponse;
import com.example.accountservice.dto.LoginRequest;
import com.example.accountservice.dto.RegistrationRequest;
import com.example.accountservice.dto.UserDTO;
import com.example.accountservice.entity.User;
import com.example.accountservice.securityConfig.JwtTokenProvider;
import com.example.accountservice.service.UserService;
import com.example.accountservice.service.UserService2;
import com.example.accountservice.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth/")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private EmailValidator emailValidator;

	private static String INVALID_EMAIL = "Invalide email";
	private static String INVALID_EMAIL_OR_PASSWORD = "Incorrect email Or password ";
	private static String DISABLED = "Account Disabled";
	private static String INCORRECT_CONFIRMATION_PASSWORD = "Password confirmation doesn't match";

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
		boolean isValidEmail = emailValidator.test(loginRequest.getEmail());
		if (!isValidEmail) {
			return error(INVALID_EMAIL);
		}
		final ResponseEntity<String> response = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		if (response != null)
			return response;
		User user = (User) userService.loadUserByUsername(loginRequest.getEmail());
		if (user == null)
			return error(INVALID_EMAIL_OR_PASSWORD);
		String token = tokenProvider.generateToken(user.getId().toString());
		UserDTO userDto = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), true,
				null);
		JwtAuthResponse res = new JwtAuthResponse(token, userDto);

		return new ResponseEntity<JwtAuthResponse>(res, HttpStatus.OK);

	}

	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (DisabledException e) {
			return error(DISABLED);

		} catch (BadCredentialsException e) {
			return error(INVALID_EMAIL_OR_PASSWORD);
		}
		return null;
	}
	
	

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody RegistrationRequest req) {

		if (!isValidEmail(req.getEmail())) {
			return error(INVALID_EMAIL);
		}
		if (!req.getPassword().equals(req.getPasswordConfirmation())) {
			return error(INCORRECT_CONFIRMATION_PASSWORD);
		}
		return userService.createUser(new User(req.getUsername(), req.getEmail(), req.getPassword()));

	}

	private boolean isValidEmail(String email) {
		return emailValidator.test(email);
	}

	private ResponseEntity<String> error(String message) {
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	@GetMapping("alert")
	public String hello() {
		return "hello";
	}
}
