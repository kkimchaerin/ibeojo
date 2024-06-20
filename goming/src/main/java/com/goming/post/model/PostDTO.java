package com.goming.post.model;

import java.security.Timestamp;

public class PostDTO {

   // 글 식별자 
    private int post_idx;

    // 회원 이메일 
    private String user_email;

    // 글 내용 
    private String post_content;

    // 글 첨부파일 
    private String post_img;

    // 스타일 
    private String style_tag;

    // 작성일자 
    private Timestamp created_at;

    public PostDTO() {}
    
   public PostDTO(int post_idx, String user_email, String post_content, String post_img, String style_tag,
         Timestamp created_at) {
      super();
      this.post_idx = post_idx;
      this.user_email = user_email;
      this.post_content = post_content;
      this.post_img = post_img;
      this.style_tag = style_tag;
      this.created_at = created_at;
   }

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

   public String getPost_content() {
      return post_content;
   }

   public void setPost_content(String post_content) {
      this.post_content = post_content;
   }

   public String getPost_img() {
      return post_img;
   }

   public void setPost_img(String post_img) {
      this.post_img = post_img;
   }

   public String getStyle_tag() {
      return style_tag;
   }

   public void setStyle_tag(String style_tag) {
      this.style_tag = style_tag;
   }

   public Timestamp getCreated_at() {
      return created_at;
   }

   public void setCreated_at(Timestamp created_at) {
      this.created_at = created_at;
   }
   
   
}
