<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="Join_Pagejs.js"></script>
<link rel="stylesheet" href="styles/Join_Page.css">
<link rel="stylesheet" href="styles/reset.css">
</head>
<body>
	<form action="JoinController" method="post" class="join" id="joinForm">
		<h1>회원가입</h1>
		<h3>아이디 입력</h3>
		<br> <input type="text" name="user_id" placeholder="아이디를 입력하세요">
		<button type="button" onclick="checkId()">아이디 중복확인</button>
		<br>
		<h3>비밀번호 입력</h3>
		<br> <input type="password" name="user_pw"
			placeholder="비밀번호를 입력하세요"> <input type="password"
			placeholder="비밀번호를 다시 한 번입력하세요"><br>

		<h3>닉네임 입력</h3>
		<br> <input type="text" name="user_nick" placeholder="닉네임을 입력하세요">
		<button type="button" onclick="checkNick">닉네임 중복확인</button>
		<br>
		<h3>성별선택</h3>
		<br>
		<div class="radio-group">
			<input type="radio" name="user_gender" value="M">남성 <input
				type="radio" name="user_gender" value="F">여성
		</div>
		<br> <br>
		<h3>선호하는 스타일 선택</h3>
		<br>
		<div class="radio-group">
			<input type="radio" name="user_preference" value="미니멀">미니멀 <input
				type="radio" name="user_preference" value="스포티">스포티 <input
				type="radio" name="user_preference" value="캐주얼">캐주얼 <input
				type="radio" name="user_preference" value="비즈니스">비즈니스<br>
		</div>
		<br> <br> <br> <input type="submit" value="회원가입"
			onclick="submitForm()">
	</form>
	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>


</body>
</html>