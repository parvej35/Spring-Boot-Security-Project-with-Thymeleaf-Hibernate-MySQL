package com.travel.agent.web.dto;

public class UserRegistrationDto {
	private String fullname;
	private String email;
	private String password;
	private String repassword;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String fullname, String email, String password, String repassword) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.repassword = repassword;
	}
	
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
