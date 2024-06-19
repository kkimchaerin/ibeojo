<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="../styles/Login_Page.css">
<link rel="stylesheet" href="../styles/reset.css">
</head>
<body>

	<form action="login" class="login">

		<h1>로그인</h1>
		<input type="text" name="id" placeholder="아이디를 입력하세요"><br>
		<input type="password" name="pw" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" value="로그인">
	</form>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="../video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>

</body>
</html>