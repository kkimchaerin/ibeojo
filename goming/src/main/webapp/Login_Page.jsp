<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./styles/Login_Page.css?ver=2" />
<link rel="stylesheet" type="text/css" href="./styles/reset.css?ver=2" />
</head>
<body>
	<form action="login" class="login">

		<h1>로그인</h1>
		<input type="text" name="id" placeholder="아이디를 입력하세요"><br>
		<input type="password" name="pw" placeholder="비밀번호를 입력하세요"><br>
		<input type="submit" onclick="location.href='index.jsp'" value="로그인">

		<div class="button-group">
			<button type="button" onclick="location.href='Findid_Page.jsp'">아이디
				찾기</button>
			<br>
			<button type="button" onclick="location.href='Findpw_Page.jsp'">비밀번호
				찾기</button>
			<br>
		</div>

	</form>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="./video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>

	<script>
        document.getElementById('loginForm').addEventListener('submit', async function (event) {
            event.preventDefault(); // 폼의 기본 제출 동작을 막음

            const form = event.target;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            try {
                const response = await fetch('/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                });

                if (response.ok) {
                    window.location.href = 'index.html';
                } else {
                    alert('로그인 실패. 아이디와 비밀번호를 확인하세요.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('서버 오류. 나중에 다시 시도하세요.');
            }
        });
    </script>
</body>
</html>