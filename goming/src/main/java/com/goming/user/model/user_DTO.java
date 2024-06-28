package com.goming.user.model;

public class user_DTO {

	private String user_email;
	private String user_pw;
	private String user_nick;
	private String user_gender;
	private String user_preference;

	public user_DTO(String user_email, String user_pw) {
		this.user_email = user_email;
		this.user_pw = user_pw;
	}

	public user_DTO(String user_email, String user_pw, String user_nick, String user_gender, String user_preference) {
		super();
		this.user_email = user_email;
		this.user_pw = user_pw;
		this.user_nick = user_nick;
		this.user_gender = user_gender;
		this.user_preference = user_preference;
	}
	
	public user_DTO(String user_email, String user_nick, String user_gender, String user_preference){
		super();
		this.user_email = user_email;
		this.user_nick = user_nick;
		this.user_gender = user_gender;
		this.user_preference = user_preference;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_nick() {
		return user_nick;
	}

	public void setUser_nick(String user_nick) {
		this.user_nick = user_nick;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_preference() {
		return user_preference;
	}

	public void setUser_preference(String user_preference) {
		this.user_preference = user_preference;
	}

	public user_DTO() {
	}

}
