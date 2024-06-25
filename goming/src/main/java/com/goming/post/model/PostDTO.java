package com.goming.post.model;

import java.security.Timestamp;

public class PostDTO {
	// 글 식별자
	private int PostIdx;

	// 회원 이메일
	private String UserEmail;

	// 글 첨부파일
	private String PostImg;

	// 성별
	private String Gender;

	// 선호 스타일
	private String Style;

	// 계절
	private String Season;

	// 작성일자
	private Timestamp CreatedAt;
	
	public PostDTO() {
	}

	public PostDTO(int postIdx, String userEmail, String postImg, String gender, String style, String season,
			Timestamp createdAt) {
		super();
		PostIdx = postIdx;
		UserEmail = userEmail;
		PostImg = postImg;
		Gender = gender;
		Style = style;
		Season = season;
		CreatedAt = createdAt;
	}

	public PostDTO(String userEmail, String postImg, String gender, String style, String season) {
		super();
		UserEmail = userEmail;
		PostImg = postImg;
		Gender = gender;
		Style = style;
		Season = season;
	}

	public int getPostIdx() {
		return PostIdx;
	}

	public void setPostIdx(int postIdx) {
		PostIdx = postIdx;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public String getPostImg() {
		return PostImg;
	}

	public void setPostImg(String postImg) {
		PostImg = postImg;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

	public String getSeason() {
		return Season;
	}

	public void setSeason(String season) {
		Season = season;
	}

	public Timestamp getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		CreatedAt = createdAt;
	}

}
