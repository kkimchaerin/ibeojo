if (!window.likeImgScriptLoaded) {
    const script = document.createElement('script');
    script.src = './javascripts/Likeimg.js';
    document.head.appendChild(script);
    window.likeImgScriptLoaded = true;
}

let likeCounter = 0; // 좋아요 횟수를 저장할 변수
let currentImageElement = null; // 현재 팝업에 표시된 이미지 요소를 저장하는 변수

let globalImageSrc = "";

// 데이터를 서블릿으로 보내는 비동기 함수
async function checkLike(imageSrc) {
	img = imageSrc;
	img = img.substring(7);
	
	console.log(img);
	
	// 서블릿으로 보낼 데이터
	const data = {param : img};
	
	try{
		const res = await fetch('./CheckLike', {
			method: 'POST', // 데이터 전송 방식
			headers: {
				'Content-Type': 'application/json' // 헤더에 content-type이 json
			},
			body: JSON.stringify(data) // json 문자열 데이터로 전송
		});
		
		if(!res.ok){
			throw new Error('Network response was not ok ' + res.statusText);	
		}
		const result = await res.json();
		
		return result;
	}catch (error){
		console.error("fetch operation has ploblem : ", error)
		throw error;
	}
}



// 좋아요 기능 관련 함수
function openPopup(imageSrc, imageElement) {
	
	globalImageSrc = imageSrc;
	
	checkLike(imageSrc)
	.then(result => {
		var cnt = result.reponseParam
		console.log('좋아요 수 반환 결과 : ', cnt);
	}).catch(error => {
		console.error('에러 발생 : ', error);
	})
	
	var popupImg = document.getElementById("popupImage");
	var animationContainer = document.getElementById("animationContainer");

	popupImg.src = imageSrc; // 팝업 이미지 설정
	animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
	openPopupBackground(); // 팝업 창 열기

	currentImageElement = imageElement; // 현재 이미지 요소 저장

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
		likeCounter++; // 좋아요 횟수 증가
	} else {
		heartImg.src = "./images/heart-regular.svg"; // 좋아요 취소
		likeCounter--; // 좋아요 횟수 감소
	}

	updateLikeCount(); // 좋아요 횟수 업데이트
}

function updateLikeCount() {
	var likeCountElement = document.getElementById("likeCount");
	likeCountElement.textContent = likeCounter;
}

// 삭제 기능 관련 함수
function deleteImage(imageElement) {
	var deletePopup = document.getElementById("deletePopupBackground");
	deletePopup.style.display = "block";  // 삭제 확인 팝업을 보이도록 설정

	// 현재 삭제할 이미지 요소를 저장
	currentImageElement = imageElement;
	window.location.herf="/DeleteImg";
}

function closeDeletePopup() {
	var deletePopup = document.getElementById("deletePopupBackground");
	deletePopup.style.display = "none";  // 삭제 확인 팝업을 숨김
	window.location.href = "MyPage.jsp"; // MyPage로 리디렉트
	if (likeCounter > 0) {
		window.location.href = "/UpdateLike";
	} else {
		const img = imageSrc;
		console.log(img);
		
		window.location.href = "/DeleteLike?param="+encodeURIComponent(img);
	}
}

async function deleteConfirmed() {
    // 이미지 소스를 처리
    
    img = globalImageSrc;
    
    img = globalImageSrc.substring(7);
    
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
		if(responseText) {
			result = JSON.parse(responseText);
		}
        console.log(result);

        // 성공 시 실행할 코드
        alert("게시물이 삭제되었습니다."); // 삭제 완료 알림
        window.location.href = "http://localhost:8085/goming/MyPageController2";
    } catch (error) {
        console.error("Fetch operation has a problem: ", error);
        alert("게시물 삭제에 실패했습니다."); // 실패 알림
        window.location.href = 'http://localhost:8085/goming/MyPageController2';
    }
}

