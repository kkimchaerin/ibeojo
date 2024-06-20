<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷</title>
<link rel="stylesheet" href="./styles/First.css">
</head>
<body>
	<header class="title">
		<h1>오늘의 옷</h1>
	</header>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="./video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>

	<div class="button_container">
		<button class="join" type="button"
			onclick="location.href='Join.jsp'">회원가입</button>
		<button class="login" type="button"
			onclick="location.href='Login.jsp'">로그인</button>
	</div>
</body>
</html>