package com.goming.post.model;

import java.security.Timestamp;

public class TestDTO {
	private int post_idx;
	private String user_email;
	private String post_img;
	private char gender;
	private String style_tag;
	private String season;
	private String comment;
	private String created_at;
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getPost_img() {
		return post_img;
	}
	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getStyle_tag() {
		return style_tag;
	}
	public void setStyle_tag(String style_tag) {
		this.style_tag = style_tag;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public TestDTO(int post_idx, String user_email, String post_img, char gender, String style_tag, String season,
			String comment, String created_at) {
		super();
		this.post_idx = post_idx;
		this.user_email = user_email;
		this.post_img = post_img;
		this.gender = gender;
		this.style_tag = style_tag;
		this.season = season;
		this.comment = comment;
		this.created_at = created_at;
	}
	public TestDTO(String user_email, String post_img, char gender, String style_tag, String season, String comment) {
		super();
		this.user_email = user_email;
		this.post_img = post_img;
		this.gender = gender;
		this.style_tag = style_tag;
		this.season = season;
		this.comment = comment;
	}
	
	
	public TestDTO() {
		
	}
	@Override
	public String toString() {
		return "TestDTO [post_idx=" + post_idx + ", user_email=" + user_email + ", post_img=" + post_img + ", gender="
				+ gender + ", style_tag=" + style_tag + ", season=" + season + ", comment=" + comment + ", created_at="
				+ created_at + "]";
	}
	
	
}