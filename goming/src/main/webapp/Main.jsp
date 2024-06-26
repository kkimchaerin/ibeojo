<%@page import="java.util.List"%>
<%@page import="com.goming.post.model.PostDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp" %>
<%
String rainper = (String)session.getAttribute("rainper");
String comment = (String)session.getAttribute("comment");

%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷</title>
<link rel="stylesheet" type="text/css" href="./styles/Main.css" />
<link rel="stylesheet" type="text/css" href="./styles/BottomNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/CategoryNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./javascripts/Post.js?ver=2" defer></script>
<script src="./javascripts/Main.js?ver=7" defer></script>
<script src="./javascripts/ShowDetailModal.js?ver=1" defer></script> 
</head>
<body>
	<!-- header -->
	<!-- include지시자 파일명(확장자까지) 작성 -->
	<%@ include file="Header.jsp"%>

	<!-- main -->
	<main>
		<section class="weather-wrapper">
			<h2 class="sr-only">날씨 정보</h2>
			<div class="weather-info">
				<img src="./icons/sun.png" alt="맑음"> <span id="temperature">28°C</span>
				<span id="">맑음</span>
			</div>
			<div id="weather-comment"><%=comment%>
			<% 
				if(rainper!=null){
					out.print(rainper);					
				}
			%>
			</div>
		</section>
		<section class="gallery-wrapper">
			<h2 id="style-name">미니멀</h2>
			<div class="gender-category">
				<a href="#" id="M" class="get-category checked">MEN</a> 
				<a href="#" id="F" class="get-category">WOMEN</a>
			</div>
			
			<!-- category nav -->
			<%@ include file="CategoryNav.jsp"%>

			<div class="sort">
				<a href="#" id="new" class="checked">최신순</a> 
				<a href="#" id="popular">인기순</a>
			</div>
			<div class="gallery">
				<!-- 카테고리별 필터링된 이미지 출력 -->
				<!-- Main.js -->
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
	        <img id="modal-img">
	        <div class="modal-info">
	            <button id="modal-like-btn" class="liked">
	            	<img src="./icons/heart-regular.svg" alt="좋아요">
	            </button>
	            <p id="modal-comment"></p>
	        </div>
	    </div>
	</div>
        <!-- <span id="modal-like-count">0</span> -->
    </div>
</body>
<style>
body{
background-image: url("./images/background03.png");

background-size: cover;
}

.weather-wrapper{
	background-color: white;
}
</style>
</html>