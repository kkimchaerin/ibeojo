<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%
// Java 객체 또는 세션에서 img_list 데이터를 가져와 JSON 형식으로 변환
List<String> imgList = (List<String>) session.getAttribute("img_list");
String jsonImgList = new Gson().toJson(imgList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liked Post</title>
<link rel="stylesheet" type="text/css"
	href="./styles/LikedPost.css?ver4">
<link rel="stylesheet" type="text/css"
	href="./styles/CategoryNav.css?ver3" />
<link rel="stylesheet" type="text/css"
	href="./styles/BottomNav.css?ver3" />

<script>
	let userEmail;
	let galleryContainer;
	let allstyle;
	let allseason;
	document.addEventListener("DOMContentLoaded", function() {
		galleryContainer = document.getElementById("imageGallery");
		allstyle = "미니멀";
		allseason = "봄";
		// 서버에서 전달한 세션 데이터 사용하기
		userEmail =
<%=jsonImgList%>
	;
		console.log(userEmail);

		// 이미지 파일 경로 배열 예시 (실제 경로에 맞게 수정 필요)

	});

	function filterByStyle(style) {
		allstyle = style;
		while (galleryContainer.firstChild) {
			galleryContainer.removeChild(galleryContainer.firstChild);
		}
		// userEmail이 배열인지 확인 후 처리
		if (Array.isArray(userEmail)) {
			// 이미지를 동적으로 추가
			for (let i = 0; i < userEmail.length; i++) {
				if (userEmail[i].season === allseason
						&& userEmail[i].style_tag === allstyle) {
					const path = userEmail[i];
					const EmailElement = document.createElement("img");
					EmailElement.src = path.post_img;
					EmailElement.alt = path.post_img; // alt 텍스트 설정 (필요에 따라 수정)

					// 클릭 이벤트 추가
					EmailElement.onclick = function() {
						openPopup(path);
					};

					// 이미지를 갤러리에 추가
					galleryContainer.appendChild(EmailElement);
				}
			}
		} else {
			console.error("userEmail is not an array:", userEmail);
			// userEmail이 배열이 아닌 경우에 대한 오류 처리
		}
	}

	function filterBySeason(season) {
		allseason = season;
		while (galleryContainer.firstChild) {
			galleryContainer.removeChild(galleryContainer.firstChild);
		}
		// userEmail이 배열인지 확인 후 처리
		if (Array.isArray(userEmail)) {
			// 이미지를 동적으로 추가
			for (let i = 0; i < userEmail.length; i++) {
				if (userEmail[i].season === allseason
						&& userEmail[i].style_tag === allstyle) {
					const path = userEmail[i];
					const EmailElement = document.createElement("img");
					EmailElement.src = path.post_img;
					EmailElement.alt = path.post_img; // alt 텍스트 설정 (필요에 따라 수정)

					// 클릭 이벤트 추가
					EmailElement.onclick = function() {
						openPopup(path);
					};

					// 이미지를 갤러리에 추가
					galleryContainer.appendChild(EmailElement);
				}
			}
		} else {
			console.error("userEmail is not an array:", userEmail);
			// userEmail이 배열이 아닌 경우에 대한 오류 처리
		}
	}

	/*     // 팝업 열기 함수 예시 (openPopup 함수는 실제로 정의된 방식으로 대체해야 함)
	 function openPopup(imagePath) {
	 console.log("Opening popup for image: " + imagePath);
	 // 팝업 창 열기 로직 구현
	 // 예: 모달 창을 열거나 다른 작업을 수행할 수 있음
	 } */
</script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h2 style="font-weight: bold; font-size: 80px; padding-bottom: 50px;">좋아요
		한 게시물</h2>

	<!-- category nav -->
	<%@ include file="CategoryNav.jsp"%>

	<div class="gallery" id="imageGallery">
		<!--         <img src="./images/1.jpg" alt="" onclick="openPopup('./images/1.jpg')">
        <img src="./images/2.jpg" alt="" onclick="openPopup('./images/2.jpg')">
        <img src="./images/3.jpg" alt="" onclick="openPopup('./images/3.jpg')">
        <img src="./images/4.jpg" alt="" onclick="openPopup('./images/4.jpg')">
        <img src="./images/5.jpg" alt="" onclick="openPopup('./images/5.jpg')">
        <img src="./images/6.jpg" alt="" onclick="openPopup('./images/6.jpg')">
        <img src="./images/7.jpg" alt="" onclick="openPopup('./images/7.jpg')">
        <img src="./images/8.jpg" alt="" onclick="openPopup('./images/8.jpg')"> -->
	</div>

	<jsp:include page="Likeimg.jsp" />

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
	<script src="LikedPost.js"></script>

	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>

</body>
</html>
