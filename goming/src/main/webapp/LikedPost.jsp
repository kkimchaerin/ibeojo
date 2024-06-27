<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%
// Java 객체 또는 세션에서 img_list 데이터를 가져와 JSON 형식으로 변환
List<String> LikeList = (List<String>) session.getAttribute("Like_list");
String jsonImgList = new Gson().toJson(LikeList);
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

<script type="text/javascript">
	pagename = "LikedPost";
	console.log(pagename);
	document.addEventListener('DOMContentLoaded', function() {
		var deleteButton = document.querySelector('.delete');
		var likeButton = document.querySelector('#likeCount');
		if (deleteButton) {
			deleteButton.classList.add('disabled');
			deleteButton.onclick = null; // onclick 이벤트를 제거하여 버튼을 비활성화
		}
		if (likeButton) {
			likeButton.hidden = true; // 요소를 숨깁니다.
		}

	});
</script>

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
		filterByStyle("미니멀");
		// 이미지 파일 경로 배열 예시 (실제 경로에 맞게 수정 필요)

	});
	function updateGallery() {
	    // 갤러리 컨테이너의 자식 요소들을 모두 제거
	    while (galleryContainer.firstChild) {
	        galleryContainer.removeChild(galleryContainer.firstChild);
	    }

	    // userEmail이 배열인지 확인
	    if (Array.isArray(userEmail)) {
	        // userEmail 배열을 순회하면서 조건에 맞는 이미지 요소 추가
	        for (let i = 0; i < userEmail.length; i++) {
	            // userEmail[i] 객체의 season과 style_tag 속성이 allseason과 allstyle과 일치하는지 확인
	            if (userEmail[i].season === allseason && userEmail[i].style_tag === allstyle) {
	                const path = userEmail[i]; // 현재 사용자 이메일 객체
	                const EmailElement = document.createElement("img"); // 이미지 요소 생성
	                EmailElement.src = './post/' + path.post_img; // 이미지의 src 속성 설정
	                EmailElement.alt = path.post_img; // 이미지의 alt 텍스트 설정

	                // 이미지를 클릭했을 때 실행할 함수 설정
	                EmailElement.onclick = function() {
	                	console.log(path);
	                    openPopup2(path.post_img, path.user_nick, path.comment); // openPopup2 함수 호출하여 이미지 팝업 열기
	                };

	                // 갤러리 컨테이너에 이미지 요소 추가
	                galleryContainer.appendChild(EmailElement);
	            }
	        }
	    } else {
	        // userEmail이 배열이 아닌 경우 에러 메시지 출력
	        console.error("userEmail is not an array:", userEmail);
	    }
	}

	function filterByStyle(style) {
	    allstyle = style;
	    updateGallery();
	}

	function filterBySeason(season) {
	    allseason = season;
	    updateGallery();
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
	<h2 class="title">좋아요 한 게시물</h2>

	<!-- category nav -->
	<%@ include file="CategoryNav.jsp"%>

	<div class="gallery" id="imageGallery"></div>

	<jsp:include page="Likeimg.jsp" />

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
	<script src="./javascripts/Likeimg.js"></script>

	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>

</body>
</html>
