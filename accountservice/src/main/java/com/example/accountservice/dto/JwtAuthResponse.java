package com.example.accountservice.dto;


public class JwtAuthResponse {
    private String accessToken;
    private UserDTO user ;

    public JwtAuthResponse(String accessToken, UserDTO userDTO) {
        this.accessToken =  accessToken;
        this.user = userDTO;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
    
    

}
