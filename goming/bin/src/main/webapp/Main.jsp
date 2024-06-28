
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>
<%@page import="java.util.List"%>
<%@page import="com.goming.post.model.PostDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<%
String rainper = (String) session.getAttribute("rainper");
String comment = (String) session.getAttribute("comment");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷</title>
<link rel="stylesheet" type="text/css" href="./styles/Main.css?ver=1" />
<link rel="stylesheet" type="text/css" href="./styles/BottomNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/CategoryNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<link rel="stylesheet" type="text/css"
	href="./styles/LocationWeather.css?ver=1" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./javascripts/LocationWeather.js" defer></script>
<script src="./javascripts/Main.js?ver=9" defer></script>
<script src="./javascripts/ShowDetailModal.js?ver=1" defer></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>
<script>
	console.log("들어와")
	document.addEventListener('DOMContentLoaded', function() {
		const params = new URLSearchParams(window.location.search);
		if (params.get('upload') === 'success') {
			alert('업로드가 성공적으로 완료되었습니다!');
		} else if (params.get('upload') === 'failure') {
			alert('업로드에 실패했습니다. 다시 시도해 주세요.');
		}
	});
</script>
<script type="text/javascript">
    // JavaScript 코드에서 세션 값 사용
    var style_tag = "<%= session.getAttribute("user_preference") %>";
    console.log("Session Value: " + style_tag);
</script>

</head>
<body>
	<!-- header -->
	<!-- include지시자 파일명(확장자까지) 작성 -->
	<%@ include file="Header.jsp"%>

	<!-- main -->
	<main>
		<section class="weather-wrapper">
			<div class="head_info">
				<h2 class="sr-only">날씨 정보</h2>
				<div class="weather-info">
					<div id="additional-text"></div>
					<span class="weather-icon" id="weatherIconContainer"> <!-- 기본적으로 로딩 스피너를 표시하도록 준비 -->
						<div class="loading-spinner"></div> <!-- 이미지는 JavaScript에서 동적으로 추가될 예정 -->
					</span> <span id="temperature">28°C</span> <span id="weatherInfo">맑음</span>
				</div>
				<div id="weather-comment">
					<%
					if (rainper != null) {
						out.print(rainper);
					}
					%>
				</div>
			</div>
		</section>

		<section class="gallery-wrapper">
			<h2 id="style-name">미니멀</h2>
			<div class="gender-category">
				<a href="#" id="M" class="get-category checked">MEN</a> <a href="#"
					id="F" class="get-category">WOMEN</a>
			</div>

			<!-- category nav -->
			<%@ include file="CategoryNav.jsp"%>

			<div class="sort">
				<a href="#" id="new" class="checked">최신순</a> <a href="#"
					id="popular">인기순</a>

			</div>
			<div class="gallery">
				<!-- 카테고리별 필터링된 이미지 출력 -->
			</div>

		</section>

		<!-- upload button -->
		<%@ include file="UploadButton.jsp"%>
	</main>

	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>

	<!-- show detail modal -->
	<div id="modal" class="modal">
		<span class="close">&times;</span>
		<div class="modal-content">
			<p id="modal-user-nick"></p>
			<img id="modal-img">
			<div class="modal-info">
				<button id="modal-like-btn" class="liked">
					<img src="./icons/heart-regular.svg" alt="좋아요">
				</button>
				<p id="modal-comment"></p>
			</div>
		</div>
	</div>

</body>
</html>