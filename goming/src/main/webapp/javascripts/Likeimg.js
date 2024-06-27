class Singleton {
	constructor(value1, value2, value3) {
		if (Singleton.instance) {
			return Singleton.instance;
		}
		this._likeCounter = value1; // likeCounter 변수
		this._currentImageElement = value2; // currentImageElement 변수
		this._globalImageSrc = value3; // globalImageSrc 변수
		Singleton.instance = this;
		return this;
	}

	// getter 메서드
	get likeCounter() {
		return this._likeCounter;
	}

	get currentImageElement() {
		return this._currentImageElement;
	}

	get globalImageSrc() {
		return this._globalImageSrc;
	}

	// setter 메서드
	set likeCounter(value1) {
		this._likeCounter = value1;
	}

	set currentImageElement(value2) {
		this._currentImageElement = value2;
	}

	set globalImageSrc(value3) {
		this._globalImageSrc = value3;
	}
}

function getLikeCount(currentImageElement) {
	console.log("좋아요 숫자" + currentImageElement);
	let filePath = currentImageElement
	let fileName = filePath.split('/').pop(); // 파일 경로를 '/' 기준으로 나눈 후 마지막 요소를 추출
	console.log("좋아요 숫자" + fileName);

	$.ajax({
		type: "POST",
		url: "LikeCountController",
		data: JSON.stringify({ post_img: fileName }),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(response) {
			console.log("Number of likes: " + response.likeCount);
			instance.likeCounter = response.likeCount;
		},
		error: function(error) {
			console.error("Error fetching like count: ", error);
		}
	});
}

function getTargetLike(currentImageElement) {
	console.log("getTargetLike" + currentImageElement);
	let filePath = currentImageElement
	let fileName = filePath.split('/').pop(); // 파일 경로를 '/' 기준으로 나눈 후 마지막 요소를 추출
	console.log("getTargetLike" + fileName);

	$.ajax({
		type: "POST",
		url: "LikeCountController",
		data: JSON.stringify({ post_img: fileName }),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(response) {
			console.log("Number of likes: " + response.likeCount);
			instance.likeCounter = response.likeCount;
		},
		error: function(error) {
			console.error("Error fetching like count: ", error);
		}
	});
}

const instance = new Singleton(0, "", "");
/*let likeCounter = 0; // 좋아요 횟수를 저장할 변수
let currentImageElement = null; // 현재 팝업에 표시된 이미지 요소를 저장하는 변수

let globalImageSrc = "";*/




// 좋아요 기능 관련 함수
function openPopup(imageSrc, imageElement) {

	instance.globalImageSrc = imageSrc;

	var popupImg = document.getElementById("popupImage");
	var animationContainer = document.getElementById("animationContainer");

	popupImg.src = imageSrc; // 팝업 이미지 설정
	animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
	openPopupBackground(); // 팝업 창 열기

	instance.currentImageElement = imageElement; // 현재 이미지 요소 저장

	// 좋아요 버튼 보이기
	var likeButton = document.querySelector(".heartBtn img");
	likeButton.src = "./images/heart-regular.svg"; // 기본 상태 아이콘 설정

	updateLikeCount();
}

function openPopupBackground() {
	var popupBackground = document.getElementById("imagePopupBackground");
	popupBackground.style.display = "block";  // 팝업 배경을 보이도록 설정
}

function closePopup() {
	var popupBackground = document.getElementById("imagePopupBackground");
	popupBackground.style.display = "none";  // 팝업 배경을 숨김
}

function addLike() {
	var heartImg = document.querySelector(".heartBtn img");
	var currentSrc = heartImg.getAttribute("src");

	if (currentSrc.includes("heart-regular.svg")) {
		heartImg.src = "./images/heart-solid.svg"; // 좋아요 추가
		//instance.likeCounter++; // 좋아요 횟수 증가
	} else {
		heartImg.src = "./images/heart-regular.svg"; // 좋아요 취소
		//instance.likeCounter--; // 좋아요 횟수 감소
	}

	updateLikeCount(); // 좋아요 횟수 업데이트
}

function updateLikeCount() {
	var likeCountElement = document.getElementById("likeCount");

	getLikeCount(instance.globalImageSrc);
	likeCountElement.textContent = instance.likeCounter;
	console.log("updateLikeCount : " + likeCountElement);
	console.log("updateLikeCount : " + instance.likeCounter);
	console.log("updateLikeCount : " + likeCountElement.textContent);
}

// 삭제 기능 관련 함수
function deleteImage(imageElement) {
	var deletePopup = document.getElementById("deletePopupBackground");
	deletePopup.style.display = "block";  // 삭제 확인 팝업을 보이도록 설정

	// 현재 삭제할 이미지 요소를 저장
	instance.currentImageElement = imageElement;
	window.location.herf = "/DeleteImg";
}

function closeDeletePopup() {
	var deletePopup = document.getElementById("deletePopupBackground");
	deletePopup.style.display = "none";  // 삭제 확인 팝업을 숨김
	window.location.href = "MyPage.jsp"; // MyPage로 리디렉트
	if (instance.likeCounter > 0) {
		window.location.href = "/UpdateLike";
	} else {
		const img = imageSrc;
		console.log(img);

		window.location.href = "/DeleteLike?param=" + encodeURIComponent(img);
	}
}

async function deleteConfirmed() {
	// 이미지 소스를 처리

	img = instance.globalImageSrc;

	img = instance.globalImageSrc.substring(7);

	console.log(img);

	const data = { param: img };

	try {
		const res = await fetch('./DeleteImg', {
			method: 'POST', // 데이터 전송 방식
			headers: {
				'Content-Type': 'application/json' // 헤더에 content-type을 json으로 설정
			},
			body: JSON.stringify(data) // json 문자열 데이터로 전송
		});

		const responseText = await res.text(); // 응답 텍스트를 먼저 받음
		console.log('Response status:', res.status);
		console.log('Response text:', responseText);

		if (!res.ok) {
			throw new Error('Network response was not ok ' + res.statusText);
		}

		let result = {};
		if (responseText) {
			result = JSON.parse(responseText);
		}
		console.log(result);

		// 성공 시 실행할 코드
		alert("게시물이 삭제되었습니다."); // 삭제 완료 알림
		window.location.href = "http://localhost:8085/goming/ImgController";
	} catch (error) {
		console.error("Fetch operation has a problem: ", error);
		alert("게시물 삭제에 실패했습니다."); // 실패 알림
		window.location.href = 'http://localhost:8085/goming/ImgController';
	}
}

