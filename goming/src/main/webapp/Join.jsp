<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="./javascripts/Join_Pagejs.js"></script>
<link rel="stylesheet" type="text/css" href="./styles/Join.css?ver=4" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
</head>

<body>

	<form action="JoinController" method="post" class="join" id="joinForm"
		onsubmit="check_submitForm(event)">
		<h1>회원가입</h1>
		<h3>아이디 입력</h3>
		<br> <input type="email" name="user_id" id="user_id"
			placeholder="아이디를 입력하세요" required> <br>
		<button type="button" class="check" onclick="checkId()">아이디
			중복확인</button>

		<br>
		<h3>비밀번호 입력</h3>
		<br> <input type="password" name="user_pw" id="user_pw"
			placeholder="비밀번호를 입력하세요" required>
		<h3>비밀번호 확인</h3>
		<br> <input type="password" name="user_pw_check"
			id="user_pw_check" placeholder="비밀번호 확인" required>
		<div class="failure-message">비밀번호를 확인해주세요</div>
		<div class="success-message hide">비밀번호가 일치합니다</div>
		<br>
		<h3>닉네임 입력</h3>
		<br> <input type="text" id="user_nick" name="user_nick"
			placeholder="닉네임을 입력하세요" required>
		<button type="button" class="check" onclick="checkNick()">닉네임
			중복확인</button>
		<br>
		<h3>성별선택</h3>
		<br>
		<div class="radio-group-gender">
			<div>

				<input type="radio" name="user_gender" value="M">남성 <input
					type="radio" name="user_gender" value="F">여성
			</div>
		</div>
		<br> <br>
		<h3>선호하는 스타일 선택</h3>
		<br>
		<div class="radio-group-style">
			<div>
				<input type="radio" name="user_preference" value="미니멀">미니멀 <input
					type="radio" name="user_preference" value="스포티">스포티
			</div>
			<div>
				<input type="radio" name="user_preference" value="캐주얼">캐주얼 <input
					type="radio" name="user_preference" value="비즈니스">비즈니스<br>
			</div>
		</div>
		<br> <br> <br> <input type="submit" class="black"
			value="회원가입">
		<button type="button" class="black"
			onclick="window.location.href='First.jsp'">돌아가기</button>


	</form>
</body>
</html>
