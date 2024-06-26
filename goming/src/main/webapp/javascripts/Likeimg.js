let likeCounter = 0; // 좋아요 횟수를 저장할 변수
let currentImageElement = null; // 현재 팝업에 표시된 이미지 요소를 저장하는 변수

// 좋아요 기능 관련 함수
function openPopup(imageSrc, imageElement) {
    var popupImg = document.getElementById("popupImage");
    var animationContainer = document.getElementById("animationContainer");

    popupImg.src = imageSrc; // 팝업 이미지 설정
    animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
    openPopupBackground(); // 팝업 창 열기

    currentImageElement = imageElement; // 현재 이미지 요소 저장

    // 좋아요 버튼 보이기
    var likeButton = document.querySelector(".heartBtn img");
    likeButton.src = "./images/heart-regular.svg"; // 기본 상태 아이콘 설정

    // 좋아요 횟수 초기화
    likeCounter = 0;
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
}

function closeDeletePopup() {
    var deletePopup = document.getElementById("deletePopupBackground");
    deletePopup.style.display = "none";  // 삭제 확인 팝업을 숨김
    window.location.href = "MyPage.jsp"; // MyPage로 리디렉트
}

function deleteConfirmed() {
    // 현재 이미지 요소 삭제
    document.gete
    if (currentImageElement) {
        currentImageElement.remove();
        currentImageElement = null;
    }
    closePopup(); // 이미지 팝업 닫기
    closeDeletePopup(); // 삭제 확인 팝업 닫기
    alert("게시물이 삭제되었습니다."); // 삭제 완료 알림
}
