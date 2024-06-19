package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DTO {

   private String U_ID;
   private String U_PW;
   private String U_NM;
   private int U_SC;
   private int U_PH;

   private String Q_NM;
   private String Q_URL;
   private int Q_SC;
   private String Q_HI;

   private int Q_INDEX;
   private int sum;

   private int heart;
   private String answer;
   private int tf;

   public DTO(int tf) {
      this.tf = tf;
   }

   public DTO(String U_ID, String U_PW, String U_NM, int U_SC, int U_PH) {
      this.U_ID = U_ID;
      this.U_PW = U_PW;
      this.U_NM = U_NM;
      this.U_SC = U_SC;
      this.U_PH = U_PH;

   }

   public DTO(String Q_NM, String Q_URL, int Q_SC, String Q_HI) {

      this.Q_NM = Q_NM;
      this.Q_URL = Q_URL;
      this.Q_SC = Q_SC;
      this.Q_HI = Q_HI;

   }

   public DTO(int Q_INDEX, int sum, int heart, String answer) {
      this.Q_INDEX = Q_INDEX;
      this.sum = sum;
      this.heart =heart;
      this.answer = answer;
   }

   // 회원정보 삭제, 로그인 목적
   public DTO(String U_ID, String U_PW) {
      this.U_ID = U_ID;
      this.U_PW = U_PW;
   }
   
	// 점수 넣고 바꾸기
	public DTO(int sum, String U_ID) {
		this.sum = sum;
		this.U_ID = U_ID;
	}

   // getter, setter
   // USER
   public String getU_NM() {
      return U_NM;
   }

   public void setU_NM(String u_NM) {
      this.U_NM = u_NM;
   }

   public String getU_ID() {
      return U_ID;
   }

   public void setU_ID(String u_ID) {
      this.U_ID = u_ID;
   }

   public String getU_PW() {
      return U_PW;
   }

   public void setU_PW(String u_PW) {
      this.U_PW = u_PW;
   }

   public int getU_PH() {
      return U_PH;
   }

   public void setU_PH(int u_PH) {
      this.U_PH = u_PH;
   }

   public int getU_SC() {
      return U_SC;
   }

   public void setU_SC(int u_SC) {
      U_SC = u_SC;
   }

   // QUIZ
   public String getQ_NM() {
      return Q_NM;
   }

   public void setQ_NM(String q_NM) {
      Q_NM = q_NM;
   }

   public String getQ_URL() {
      return Q_URL;
   }

   public void setQ_URL(String q_URL) {
      Q_URL = q_URL;
   }

   public int getQ_SC() {
      return Q_SC;
   }

   public void setQ_SC(int q_SC) {
      Q_SC = q_SC;
   }

   public String getQ_HI() {
      return Q_HI;
   }

   public void setQ_HI(String q_HI) {
      Q_HI = q_HI;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }

   public int getQ_INDEX() {
      return Q_INDEX;
   }

   public void setQ_INDEX(int q_INDEX) {
      Q_INDEX = q_INDEX;
   }

   public int getSum() {
      return sum;
   }

   public void setSum(int sum) {
      this.sum = sum;
   }

   public int getHeart() {
      return heart;
   }

   public void setHeart(int heart) {
      this.heart = heart;
   }
   public int getTf() {
      return tf;
   }

   public void setTf(int tf) {
      this.tf = tf;
   }
}