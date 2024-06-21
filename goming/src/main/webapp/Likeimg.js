// Likeimg.js

function openPopup(imageSrc) {
    var popupImg = document.getElementById("popupImage");
    var animationContainer = document.getElementById("animationContainer");

    popupImg.src = imageSrc; // 팝업 이미지 설정
    animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
    openPopupBackground(); // 팝업 창 열기

    // 좋아요 버튼 보이기
    var likeButton = document.querySelector(".heartBtn img");
    likeButton.src = "./images/heart-regular.svg"; // 기본 상태 아이콘 설정
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
    } else {
        heartImg.src = "./images/heart-regular.svg"; // 좋아요 취소
    }
}
