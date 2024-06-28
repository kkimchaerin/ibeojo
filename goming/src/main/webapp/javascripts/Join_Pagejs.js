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
				check_id = 1;
			} else if (response === "2") {
				alert("아이디를 입력해주세요")
			}

			else {
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
				check_nick = 1;
			} else if (response === "2") {
				alert("닉네임을 입력해주세요")
			} else {
				alert("이미 사용 중인 닉네임입니다.");
			}
		},
		error: function(xhr, status, error) {
			// 서버에서의 처리가 실패하면 이 함수가 호출됨
			alert("AJAX 호출이 실패했습니다.");
			console.error(xhr, status, error);
		}
	});
}

function submitForm() {
	var check_pw = document.getElementById('user_pw').value;

	var genderSelected = false;

	var genderRadios = document.getElementsByName('user_gender');
	for (var i = 0; i < genderRadios.length; i++) {
		if (genderRadios[i].checked) {
			genderSelected = true;
			break;
		}
	}

	// 선호 스타일 선택 여부 확인
	var preferenceSelected = false;

	var preferenceRadios = document.getElementsByName('user_preference');
	for (var i = 0; i < preferenceRadios.length; i++) {
		if (preferenceRadios[i].checked) {
			preferenceSelected = true;
			break;
		}
	}

	// 각 선택지 선택 여부 확인
	if (check_id !== 1) {
		alert("아이디 중복 확인을 해주세요");
		return false;
	} else if (!check_pw) {
		alert("비밀번호를 입력해주세요");
		return false;
	} else if (check_nick !== 1) {
		alert("닉네임 중복 확인을 해주세요");
		return false;
	} else if (!genderSelected) {
		alert("성별을 선택해주세요")
		return false;
	} else if (!preferenceSelected) {
		alert("선호하는 스타일을 선택해주세요");
		return false;
	} else {
		return true;
	}


}




function check_submitForm(event) {

	// 기본적으로 제출을 막음
	event.preventDefault();

	let check = submitForm();
	let is_success = false;

	console.log("check : ", check);

	// submitForm 에서 true를 반환한 경우에 submit 진행
	if (check === false) {
		console.log("폼 제출을 막았습니다.");
	} else {
		document.getElementById("joinForm").submit();
		$.ajax({
			type: 'POST',
			url: 'JoinController', // 회원가입 처리 서블릿 주소
			data: $('#joinForm').serialize(), // 폼 데이터 직렬화하여 전송
			async: false, // 동기 방식으로 설정 (결과를 기다림)
			success: function(response) {
				if (response === "success") {
					is_success = true;
				} else {
					is_success = false;
					window.location.reload();
				}
			},
			error: function(xhr, status, error) {
				console.error(xhr, status, error);
				is_success = false;
			}
		});
	}

	if (check === true && is_success === true) {
		alert("회원가입에 성공했습니다!");
		window.location.href = "First.jsp"
	} else {

	}

}

// 추가사항
let user_pw = null;
let user_pw_check = null;
let elSuccessMessage = null;
let elFailureMessage = null;
let val_user_pw = null;
let val_user_pw_check = null;


// 페이지가 완전히 로드된 후 실행될 함수
window.onload = function() {
    // 필요한 요소들을 CSS 선택자를 사용하여 변수에 할당
    user_pw = document.querySelector('input#user_pw'); // id가 'user_pw'인 input 요소
    user_pw_check = document.querySelector('input#user_pw_check'); // id가 'user_pw_check'인 input 요소
    elSuccessMessage = document.querySelector('.success-message'); // 클래스가 'success-message'인 요소
    elFailureMessage = document.querySelector('.failure-message'); // 클래스가 'failure-message'인 요소

    // 초기에는 성공 메시지와 실패 메시지를 숨김
    if (elSuccessMessage) {
        elSuccessMessage.classList.add('hide');
    }
    if (elFailureMessage) {
        elFailureMessage.classList.add('hide');
    }

    // user_pw 입력 요소가 존재하는지 확인 후 이벤트 핸들러 추가
    if (user_pw) {
        user_pw.addEventListener('input', function() {
            val_user_pw = user_pw.value;
            checkPasswordMatch();
        }); // addEventListener 종료
    }

    // user_pw_check 입력 요소가 존재하는지 확인 후 이벤트 핸들러 추가
    if (user_pw_check) {
        user_pw_check.addEventListener('input', function() {
            val_user_pw_check = user_pw_check.value;
            checkPasswordMatch();
        }); // addEventListener 종료
    }
};

// 비밀번호가 일치하는지 확인하는 함수
function checkPasswordMatch() {
    if (val_user_pw.length !== 0 && val_user_pw_check.length !== 0) {
        if (val_user_pw === val_user_pw_check) {
            elSuccessMessage.classList.remove('hide'); // 비밀번호가 일치합니다 메시지 표시
            elFailureMessage.classList.add('hide'); // 실패 메시지 숨김
        } else {
            elSuccessMessage.classList.add('hide'); // 성공 메시지 숨김
            elFailureMessage.classList.remove('hide'); // 비밀번호가 일치하지 않습니다 메시지 표시
        }
    }
}