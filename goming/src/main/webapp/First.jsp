<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷</title>
<link rel="stylesheet" type="text/css" href="styles/First.css?ver=4" />
<link rel="stylesheet" type="text/css" href="styles/Reset.css?ver=2" />
</head>
<body>
	<div class="container">

		<header class="title">
			<h1 class="header-logo">오늘의 옷</h1>
		</header>


		<div class="button_container">
			<button class="join" type="button"
				onclick="location.href='Join.jsp'">회원가입</button>
			<button class="login" type="button"
				onclick="location.href='Login.jsp'">로그인</button>
		</div>
	</div>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="./video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>
</body>
</html>