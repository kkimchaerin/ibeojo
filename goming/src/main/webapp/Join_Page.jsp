<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/Join_Page.css">
<link rel="stylesheet" href="styles/reset.css">
</head>
<body>
	<form action="JoinController" method="post" class="join" id="joinForm">
		<h1>회원가입</h1>
		<h3>아이디 입력</h3>
		<br> <input type="text" name="user_id" placeholder="아이디를 입력하세요">
		<button type="button" onclick="checkId">아이디 중복확인</button>
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

	<script>
		function checkId() {
			var userId = $('input[name="user_id"]').val(); // 입력된 아이디 가져오기

			$.ajax({
				type : 'POST', // HTTP 요청 방식 (POST 추천)
				url : 'checkUserId.jsp', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
				data : {
					user_email : userId
				}, // 서버로 보낼 데이터 (아이디)
				success : function(response) {
					// 서버에서의 처리가 성공하면 이 함수가 호출됨
					if (response === "available") {
						alert("사용 가능한 아이디입니다.");
					} else {
						alert("이미 사용 중인 아이디입니다.");
					}
				},
				error : function(xhr, status, error) {
					// 서버에서의 처리가 실패하면 이 함수가 호출됨
					alert("AJAX 호출이 실패했습니다.");
					console.error(xhr, status, error);
				}
			});
		}
	</script>
</body>
</html>