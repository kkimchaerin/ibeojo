<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<style>
.disabled {
	opacity: 0.5;
	pointer-events: none;
	cursor: not-allowed;
}
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liked Images</title>
<!-- Font Awesome CDN -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="./styles/Likeimg.css?ver2">
</head>
<body>

	<!-- 팝업 창 -->
	<div id="imagePopupBackground" class="popup-background"
		onclick="closePopup()">
		<div class="popup-content" onclick="event.stopPropagation()">
			<!-- 닫기 버튼 -->
			<span class="close" onclick="closePopup()"> <img
				src="./icons/CloseButton.png" alt="Close"
				style="width: 24px; height: 24px;">
			</span>
			<!-- 삭제 버튼 -->
			<span class="delete" onclick="deleteImage()"> <img
				src="./icons/DeleteButton.png" alt="Delete"
				style="width: 24px; height: 24px;">
			</span> <img id="popupImage" src=""
				style="max-width: 100%; max-height: 100%;">
			<div id="animationContainer"></div>

			<!-- 하트 & 하트수 -->
			<div class="popup-text">
				<button class="heartBtn" onclick="addLike()">
					<img id="heartIcon" src="./images/heart-regular.svg" alt="like">
					<!-- 로컬 SVG 아이콘 사용 -->
					<span id="likeCount" style="color: black;">101</span>
				</button>
			</div>
		</div>
	</div>

	<!-- <script src="./javascripts/Likeimg.js"></script> -->
</body>
</html>
