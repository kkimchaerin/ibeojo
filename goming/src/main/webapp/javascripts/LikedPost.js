// 현재 사용이 되고있지 않습니다
// 비슷하게 함수가 구현된 Likeimg.js가 있어서 일단 얘를 그놈으로 대체한상태입니다

function openPopup(imageSrc, post_idx) {
    var popupImg = document.getElementById("popupImage");
    var animationContainer = document.getElementById("animationContainer");
    getLikeCount(post_idx);
    popupImg.src = imageSrc; // 팝업 이미지 설정
    animationContainer.innerHTML = ""; // 애니메이션 컨테이너 초기화
    openPopupBackground(); // 팝업 창 열기
}


function openPopupBackground() {
    var popupBackground = document.getElementById("imagePopupBackground");
    popupBackground.style.display = "block";  // 팝업 배경을 보이도록 설정
}

function closePopup() {
    var popupBackground = document.getElementById("imagePopupBackground");
    popupBackground.style.display = "none";  // 팝업 배경을 숨김
}

function toggleLike(button) {
    var icon = button.querySelector('i');
    icon.classList.toggle('far'); // Font Awesome Regular (빈 하트)
    icon.classList.toggle('fas'); // Font Awesome Solid (채워진 하트)
    icon.classList.toggle('liked'); // 색상 변경을 위한 liked 클래스 토글
}
// 기존의 js ----------------------------------------


// 갤러리 증감 시작 ----------------------------------------



// 갤러리 증감 끝남 ----------------------------------------


