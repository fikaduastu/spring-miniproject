package com.example.accountservice.dto;


public class LoginRequest {

    private String email;
    private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	LoginRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
    

}
