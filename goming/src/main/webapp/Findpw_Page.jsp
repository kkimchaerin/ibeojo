<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="../styles/Findpw_Page.css">
<link rel="stylesheet" href="../styles/reset.css">
</head>
<body>
	<form action="" class="Findpw">

		<h1>비밀번호 찾기</h1>
		<input type="text" name="nick" placeholder="바꿀 비밀번호를 입력하세요"> <input
			type="text" name="nick" placeholder="바꿀 비밀번호를 다시 한 번 입력하세요"><br>

		<input type="submit" value="비밀번호 수정">
	</form>

	<div class="bg_video">
		<video autoplay muted loop class="bg_video_content">
			<source src="../video/4753-179739298_medium.mp4" type="video/mp4">
		</video>
	</div>

	<script>
		document.getElementById('passwordForm')
				.addEventListener(
						'submit',
						function(event) {
							event.preventDefault(); // 폼의 기본 제출 동작을 막음

							// 비밀번호와 확인 비밀번호를 가져옴
							const newPassword = document
									.getElementById('newPassword').value;
							const confirmPassword = document
									.getElementById('confirmPassword').value;

							// 비밀번호 일치 여부 확인
							if (newPassword !== confirmPassword) {
								alert('비밀번호와 확인 비밀번호가 일치하지 않습니다.');
								return;
							}

							// 비밀번호가 일치할 경우 여기에 비밀번호 수정 로직을 추가할 수 있음
							// 여기서는 간단히 성공 메시지만 출력
							alert('비밀번호가 성공적으로 수정되었습니다.');
						});
	</script>
</body>
</html>