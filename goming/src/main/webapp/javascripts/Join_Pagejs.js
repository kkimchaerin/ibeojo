let check_id = 0;
let check_nick = 0;

function checkId() {
	var userId = $('#user_id').val(); // 입력된 아이디 가져오기
	
	$.ajax({
		type: 'POST', // HTTP 요청 방식 (POST 추천)
		url: 'CheckUserIdService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
		data: {
			user_id: userId
		}, // 서버로 보낼 데이터 (아이디)
		success: function(response) {
			// 서버에서의 처리가 성공하면 이 함수가 호출됨
			if (response === "0") {
				alert("사용 가능한 아이디입니다.");
				$('#check_id').val('1');
			} else {
				alert("이미 사용 중인 아이디입니다.");
				$('#check_id').val('0');
			}
		},
		error: function(xhr, status, error) {
			// 서버에서의 처리가 실패하면 이 함수가 호출됨
			alert("AJAX 호출이 실패했습니다.");
			console.error(xhr, status, error);
		}
	});
}
function checkNick() {
	var usernick = $('#user_nick').val(); // 입력된 아이디 가져오기
	
	$.ajax({
		type: 'POST', // HTTP 요청 방식 (POST 추천)
		url: 'CheckUserNickService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
		data: {
			user_nick: usernick
		}, // 서버로 보낼 데이터 (아이디)
		success: function(response) {
			// 서버에서의 처리가 성공하면 이 함수가 호출됨
			if (response === "0") {
				alert("사용 가능한 닉네임입니다.");
				$('#check_nick').val('1');
			} else {
				alert("이미 사용 중인 닉네임입니다.");
				$('#check_nick').val('0');
			}
		},
		error: function(xhr, status, error) {
			// 서버에서의 처리가 실패하면 이 함수가 호출됨
			alert("AJAX 호출이 실패했습니다.");
			console.error(xhr, status, error);
		}
	});
	}
	
	function submitForm(){
        var success = '<%=request.getAttribute("success") != null ? request.getAttribute("success") : ""%>
		';
		check_id = '<%=request.getAttribute("check_id_re") %>';
		check_nick = '<%=request.getAttribute("check_nick_re") %>';
								console.log("Success attribute value: "
										+ success); // 디버깅을 위한 로그

								if (success === 'true' && id_check === 1
										&& nick_check === 1) {
									alert("회원가입이 성공적으로 완료되었습니다!");
									window.location.href = 'First.jsp';
								} else if (id_check === 0) {
									alert("아이디 중복 확인을 해주세요")
								} else if (nick_check === 0) {
									alert("닉네임 중복 확인을 해주세요")
								} else if (success === 'false') {
									alert("회원가입에 실패했습니다. 다시 시도해주세요.");
								}
							}
}