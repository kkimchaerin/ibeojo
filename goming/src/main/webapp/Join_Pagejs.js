function checkId() {
	var userId = $('#user_id').val(); // 입력된 아이디 가져오기
	alert(userId);
	$.ajax({
		type: 'POST', // HTTP 요청 방식 (POST 추천)
		url: 'CheckUserIdService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
		data: {
			user_id: userId
		}, // 서버로 보낼 데이터 (아이디)
		success: function(response) {
			// 서버에서의 처리가 성공하면 이 함수가 호출됨
			if (response === "available") {
				alert(response);
				/*alert("사용 가능한 아이디입니다.");*/
			} else {
				alert(response);
				alert("이미 사용 중인 아이디입니다.");
			}
		},
		error: function(xhr, status, error) {
			// 서버에서의 처리가 실패하면 이 함수가 호출됨
			alert("AJAX 호출이 실패했습니다.");
			console.error(xhr, status, error);
		}
	});
}