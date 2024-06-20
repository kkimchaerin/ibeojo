package com.goming.like.model;

import java.security.Timestamp;

public class LikeDTO {

   // 좋아요 식별자 
    private int like_idx;

    // 글 식별자 
    private int post_idx;

    // 회원 이메일 
    private String user_email;

    // 작성일자 
    private Timestamp created_at;

    public LikeDTO() {}
    
   public LikeDTO(int like_idx, int post_idx, String user_email, Timestamp created_at) {
      super();
      this.like_idx = like_idx;
      this.post_idx = post_idx;
      this.user_email = user_email;
      this.created_at = created_at;
   }

   public int getLike_idx() {
      return like_idx;
   }

   public void setLike_idx(int like_idx) {
      this.like_idx = like_idx;
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

   public Timestamp getCreated_at() {
      return created_at;
   }

   public void setCreated_at(Timestamp created_at) {
      this.created_at = created_at;
   }
    
   
    
    
}
