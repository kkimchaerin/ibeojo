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

let user_email;
let target_nick;
let booling;
let allidx;
function getLikeCount(currentImageElement) {

	console.log("getLikeCount" + currentImageElement);
	let filePath = currentImageElement;
	console.log('let filePath = currentImageElement;');
	let fileName = filePath.split('/').pop(); // 파일 경로를 '/' 기준으로 나눈 후 마지막 요소를 추출
	console.log('let fileName = filePath.split(' / ').pop(); // 파일 경로를 ' / ' 기준으로 나눈 후 마지막 요소를 추출');
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
			console.log('instance.likeCounter = response.likeCount;');
			updateLikeCount(); // 좋아요 횟수 업데이트
		},
		error: function(error) {
			console.error("Error fetching like count: ", error);
		}


	});
	console.log('$.ajax');
}
let pagename = "";
const instance = new Singleton(0, "", "");
/*let likeCounter = 0; // 좋아요 횟수를 저장할 변수
let currentImageElement = null; // 현재 팝업에 표시된 이미지 요소를 저장하는 변수

let globalImageSrc = "";*/


function openPopup2(imageSrc, nick, comment, user_emails, post_idx) {
	booling = "2";
	allidx = post_idx;
	instance.globalImageSrc = imageSrc;
	console.log("instance.globalImageSrc = imageSrc;");
	user_email = user_emails;
	var popupImg = document.getElementById("popupImage");
	console.log("var popupImg = document.getElementById('popupImage');");

	var animationContainer = document.getElementById("animationContainer");
	console.log('var animationContainer = document.getElementById("animationContainer");');

	let popupnick = document.getElementById("user-nick-text");
	let popupcomment = document.getElementById("comment-text");

	popupImg.src = './post/' + imageSrc; // 팝업 이미지 설정
	console.log('popupImg.src = imageSrc; // 팝업 이미지 설정');

	animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
	console.log('animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화');

	popupnick.textContent = nick;
	target_nick = nick;
	popupcomment.textContent = comment;




	getLikeCount(instance.globalImageSrc);
	console.log('updateLikeCount();');

	openPopupBackground(); // 팝업 창 열기
	console.log('openPopupBackground(); // 팝업 창 열기');
}





// 좋아요 기능 관련 함수
function openPopup(imageSrc, imageElement, comment, nick, user_emails) {
	booling = "1";
	user_email = user_emails;
	console.log("imageSrc : " + imageSrc);
	console.log("imageElement : " + imageElement);
	console.log("comment : " + comment);
	instance.globalImageSrc = imageSrc;

	var popupImg = document.getElementById("popupImage");
	var animationContainer = document.getElementById("animationContainer");

	popupImg.src = imageSrc; // 팝업 이미지 설정
	animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
	openPopupBackground(); // 팝업 창 열기

	instance.currentImageElement = imageElement; // 현재 이미지 요소 저장
	let popupnick = document.getElementById("user-nick-text");
	popupnick.textContent = nick;
	let popupcomment = document.getElementById("comment-text");
	popupcomment.textContent = comment;
	console.log(comment);
	console.log(comment);
	console.log(comment);
	console.log(nick);
	console.log(nick);
	console.log(nick);
	// 좋아요 버튼 보이기
	var likeButton = document.querySelector(".heartBtn img");
	/*likeButton.src = "./images/heart-regular.svg"; // 기본 상태 아이콘 설정*/

	getLikeCount(instance.globalImageSrc);
}


function openPopupBackground() {
	var popupBackground = document.getElementById("imagePopupBackground");
	popupBackground.style.display = "block";  // 팝업 배경을 보이도록 설정
}

function closePopup() {
	var popupBackground = document.getElementById("imagePopupBackground");
	popupBackground.style.display = "none";  // 팝업 배경을 숨김
}

// 먼저 보내야할건 이미지 src
// 서블릿에선 그걸 받아서 좋아요 증감을 해주고
// 그걸 통해서 내가 받아야할건 내 좋아요 여부

// 송신 : 이미지 src
// 처리 : 이미지 증감
// 수신 : 좋아요 boolforwait
function addLike() {
	var forwaits = document.getElementById("forwait");
	forwaits.classList.add('disabled');
	var heartImg = document.querySelector(".heartBtn img");
	/*var currentSrc = heartImg.getAttribute("src");*/

	/*	if (currentSrc.includes("heart-regular.svg")) {
			heartImg.src = "./images/heart-solid.svg"; // 좋아요 추가
			//instance.likeCounter++; // 좋아요 횟수 증가
		} else {
			heartImg.src = "./images/heart-regular.svg"; // 좋아요 취소
			//instance.likeCounter--; // 좋아요 횟수 감소
		}*/

	if (booling == "2") {
		$.ajax({
			type: "POST",
			url: "LikeToggleController",
			data: JSON.stringify({ post_img: instance.globalImageSrc, user_email: user_email, booled: false , allidx:allidx.toString()}),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(response) {
				console.log("Number of likebool: " + response.likebool);
				var heartImg = document.querySelector(".heartBtn img");
				if (response.likebool == 0) {
					heartImg.src = "./images/heart-regular.svg";
				}
				else {
					heartImg.src = "./images/heart-solid.svg";
				}

				instance.likeCounter = response.likeCount;
				var forwaits = document.getElementById("forwait");
				forwaits.classList.remove('disabled');
				updateLikeCount(); // 좋아요 횟수 업데이트
			},
			error: function(error) {
				console.error("Error fetching like count: ", error);
			}


		});
	}
	else {
		$.ajax({
			type: "POST",
			url: "LikeToggleController",
			data: JSON.stringify({ post_img: instance.globalImageSrc, user_email: user_email, booled: true , allidx:"0"}),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(response) {
				console.log("Number of likebool: " + response.likebool);
				var heartImg = document.querySelector(".heartBtn img");
				if (response.likebool == 0) {
					heartImg.src = "./images/heart-regular.svg";
				}
				else {
					heartImg.src = "./images/heart-solid.svg";
				}

				instance.likeCounter = response.likeCount;
				var forwaits = document.getElementById("forwait");
				forwaits.classList.remove('disabled');
				updateLikeCount(); // 좋아요 횟수 업데이트
			},
			error: function(error) {
				console.error("Error fetching like count: ", error);
			}


		});
	}


	//updateLikeCount(); // 좋아요 횟수 업데이트
}



function updateLikeCount() {
	var likeCountElement = document.getElementById("likeCount");
	var heartImg = document.querySelector(".heartBtn img");
	console.log('var likeCountElement = document.getElementById("likeCount");');
	console.log('getLikeCount(instance.globalImageSrc);');
	console.log("likeCountElement : " + likeCountElement);

	likeCountElement.textContent = instance.likeCounter;
	console.log("updateLikeCount : " + instance.likeCounter);
	if (instance.likeCounter == 0) {
		heartImg.src = "./images/heart-regular.svg";
	}
	else {
		heartImg.src = "./images/heart-solid.svg";
	}
	console.log("heartImg.src : " + heartImg.src);


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
