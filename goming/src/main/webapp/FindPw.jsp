<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./styles/FindPw.css?ver=2" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<script>
window.onload = function() {
    console.log("도착완료");
    // 비밀번호 변경 결과 메시지
    var message = "<%=request.getAttribute("message") != null ? request.getAttribute("message") : ""%>";
    if (message !== "") {
        alert(message);
        // 비밀번호 변경 성공 시 로그인 페이지로 리디렉션
        if (message === "비밀번호가 성공적으로 변경되었습니다.") {
            window.location.href = "Login.jsp";
        }
    }
}
</script>

</head>
<body>
	<form action="FindPwController" class="Findpw">

		<h1>비밀번호 찾기</h1>
		<input type="text" name="user_email" placeholder="이메일을 입력하세요"><br>
		<input type="text" name="user_pw" placeholder="새 비밀번호를 입력하세요"><br>

		<input type="submit" value="비밀번호 수정">
	</form>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="./video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>


</body>
</html>