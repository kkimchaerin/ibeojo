package com.goming.weather.model;

public class DTO {
	
	private String id;
	private String pw;
	private String nick;
	private int like;
	public DTO(String id, String pw, String nick, int like) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.like = like;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	

}
