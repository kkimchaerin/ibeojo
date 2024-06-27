<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.List"%>
<%@page import="com.goming.mypage.model.MyPageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
MyPageDTO dto = (MyPageDTO) session.getAttribute("userinfo");

String email = dto.getUser_email();
String nick = dto.getUser_nick();
String gender = dto.getUser_gender();
String style = dto.getUser_preference();

if (gender.equals("M")) {
	gender = "남자";
} else {
	gender = "여자";
}

List<MyPageDTO> img_list = (List<MyPageDTO>) session.getAttribute("img_list");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link rel="stylesheet" type="text/css" href="./styles/MyPage.css">
<style>
body {
	padding-top: 80px; /* Header의 높이만큼 body를 내립니다 */
}
</style>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="content">
		<h2>My Page</h2>
		<div class="info-container">
			<div class="info-item">
				<strong>아이디</strong> <span class="info-box"><%=email%></span>
			</div>
			<div class="info-item">
				<strong>닉네임</strong> <span class="info-box"><%=nick%></span>
			</div>
			<div class="info-item">
				<strong>성별</strong> <span class="info-box"><%=gender%></span>
			</div>
			<div class="info-item">
				<strong>선호스타일 </strong> <span class="info-box"><%=style%></span>
			</div>
		</div>

		<div class="post-title">
			<span class="post-icon">📷</span>게시물
		</div>

		<div class="gallery">
			<!-- 이미지 추가 -->
			<%
			for (int i = 0; i < img_list.size(); i++) {
				out.print("<img src='./" + img_list.get(i) + "' alt=''>");

			}
			%>
		</div>

		<button>좋아요 한 게시물 보기</button>
		<br>
		<button>내 정보 수정하기</button>
		<br>
		<button class="red">회원탈퇴</button>
	</div>
</body>
</html>
