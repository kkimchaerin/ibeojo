<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="../styles/First_Page.css">
<link rel="stylesheet" href="../styles/reset.css">
<title>Insert title here</title>
</head>
<body>
    <header class="title">
        <h1>고밍</h1>
    </header>

    <div class="bg_video">
        <video autoplay muted loop class="bg_video_content">
            <source src="../video/4753-179739298_medium.mp4" type="video/mp4">
        </video>
    </div>

    <div class="button_container" >
        <button class="join" type="button" onclick="javascript:sensorBtnClick('join');">회원가입</button>
        <button class="login" type="button" onclick="javascript:sensorBtnClick('login');">로그인</button>
    </div>
</body>
</html>