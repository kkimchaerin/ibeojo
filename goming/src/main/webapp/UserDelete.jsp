<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserDelete</title>
<link rel="stylesheet" type="text/css"
	href="./styles/UserDelete.css?ver=1">
</head>
<body>
	<h2>회원탈퇴</h2>
	<form id="deleteForm" action="DeleteUserController" method="post"
		onsubmit="return validateForm()">
		<div class="info-container">
			<div class="info-item">
				<strong>아이디(이메일)</strong> <input type="text" name="user_email"
					id="user_email" class="info-box" placeholder="이메일을 입력하세요" required>
			</div>
			<div class="info-item">
				<strong>닉네임</strong> <input type="text" name="user_nick"
					id="user_nick" class="info-box" placeholder="닉네임을 입력하세요" required>
			</div>
			<div class="info-item">
				<strong>비밀번호</strong> <input type="password" name="user_pw"
					id="user_pw" class="info-box" placeholder="비밀번호를 입력하세요" required>
			</div>
		</div>
		<br>
		<button type="submit" id="deleteButton">회원탈퇴</button>
	</form>


 	<script type="text/javascript">
        window.onload = function() {
            <% String result = (String) request.getAttribute("result"); %>
            <% if ("success".equals(result)) { %>
                alert("회원 탈퇴 성공");
        	window.location.href = "First.jsp";
            <% } else if ("error".equals(result)) { %>
                alert("회원 탈퇴 실패");
            <% } %>
        }
        
    </script>

</body>
</html>
