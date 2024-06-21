package com.goming.mypage.model;

import java.security.Timestamp;

public class MyPageDTO {

	 // 회원 이메일 
    private String user_email;

    // 회원 비밀번호 
    private String user_pw;

    // 회원 닉네임 
    private String user_nick;

    // 회원 성별 
    private String user_gender;

    // 회원 선호도 
    private String user_preference;

    // 회원 가입일자 
    private Timestamp joined_at;

    // 회원 유형 
    private String user_type;

    // 게시글 이미지
	private String post_img;

    public MyPageDTO() {}
    
    // 회원 가입
   public MyPageDTO(String user_email, String user_pw, String user_nick, String user_gender, String user_preference,
         Timestamp joined_at, String user_type) {
      super();
      this.user_email = user_email;
      this.user_pw = user_pw;
      this.user_nick = user_nick;
      this.user_gender = user_gender;
      this.user_preference = user_preference;
      this.joined_at = joined_at;
      this.user_type = user_type;
   }
   
   public MyPageDTO(String post_img) {
	   this.post_img = post_img;
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

   public Timestamp getJoined_at() {
      return joined_at;
   }

   public void setJoined_at(Timestamp joined_at) {
      this.joined_at = joined_at;
   }

   public String getUser_type() {
      return user_type;
   }

   public void setUser_type(String user_type) {
      this.user_type = user_type;
   }

   public String getPost_img() {
	   return post_img;
   }
   
   public void setPost_img(String post_img) {
	   this.post_img = post_img;
   }
}
