<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./styles/Login.css?ver=4" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
</head>
<body>
	<form action="LoginController" class="login" method="post">

		<h1>로그인</h1>
		<input type="text" name="user_id" placeholder="아이디를 입력하세요"><br>
		<input type="password" name="user_pw" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" value="로그인">
		<input type="button" value="회원가입" onclick="location.href='Join.jsp'">

		<div class="button-group" style="font-size: 14px;" > 
			<button type="button" onclick="location.href='FindId.jsp'">아이디
				찾기</button>
			<br>
			<button type="button" onclick="location.href='FindPw.jsp'">비밀번호
				찾기</button>
			<br>
		</div>

	</form>


	<script>
    // 서버에서 전달된 에러 메시지 확인 및 경고창 표시
    window.onload = function() {
        const errorMessage = '<%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%>';
			if (errorMessage) {
				alert(errorMessage);
			}
		};
	</script>

</body>
</html>