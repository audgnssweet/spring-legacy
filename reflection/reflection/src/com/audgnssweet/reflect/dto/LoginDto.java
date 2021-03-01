package com.audgnssweet.reflect.dto;

public class LoginDto {

	private String username;
	private String password;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "username : " + username + '\n' + "password : " + password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
