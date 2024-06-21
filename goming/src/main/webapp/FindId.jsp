<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./styles/FindId.css?ver=2" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />

<script>
window.onload = function() {
    // 아이디 찾기 성공 시
    <%if (request.getAttribute("foundId") != null) {%>
        alert("아이디는 '<%=request.getAttribute("foundId")%>' 입니다.");
        window.location.href = "Login.jsp"; // 로그인 페이지로 리디렉션
    <%} else if (request.getAttribute("message") != null) {%>
        // 아이디 찾기 실패 시
        alert("<%=request.getAttribute("message")%>
	");
<%}%>
	}
</script>

</head>
<body>
	<form action="FindIdController" class="Findid">

		<h1>아이디 찾기</h1>
		<input type="text" name="user_nick" placeholder="닉네임를 입력하세요"><br>
		<input type="submit" value="아이디 찾기">
	</form>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="./video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>
</body>
</html>