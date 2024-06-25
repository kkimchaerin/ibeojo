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
<link rel="stylesheet" type="text/css" href="./styles/Main.css?ver=7" />
<link rel="stylesheet" type="text/css" href="./styles/BottomNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/CategoryNav.css" />
<link rel="stylesheet" type="text/css" href="./styles/Reset.css?ver=2" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./javascripts/Post.js?ver=2" defer></script>
<script src="./javascripts/Main.js?ver=5" defer></script>
<script src="./javascripts/ShowDetailModal.js" defer></script> 
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
        <img class="modal-content" id="modal-img">
    </div>
<style>


/* Main.css */

.modal {
    display: none; 
    position: fixed; 
    z-index: 1; 
    padding-top: 60px; 
    left: 50%;
    top: 50%;
    transform: translate(-50%, -40%);
    width: 60%; 
    max-width: 700px;
    min-width: 550px;
    height: 70%; 
    overflow: auto;
    background-color: rgba(255,	255, 255, 0.9); 
    box-shadow: 5px 2px 4px rgba(0, 0, 0, 0.2);
    border-radius: 25px;
}

.modal-content {
    margin: auto;
    display: block;
    width: 50%;
    max-width: 700px;
}

.close {
    position: absolute;
    top: 15px;
    right: 35px;
    color: black;
    font-size: 40px;
    font-weight: bold;
    transition: 0.3s;
}

.close:hover,
.close:focus {
    color: #bbb;
    text-decoration: none;
    cursor: pointer;
}
</style>

</body>
</html>