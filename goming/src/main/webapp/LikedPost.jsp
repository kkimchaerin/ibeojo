<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liked Post</title>
    <link rel="stylesheet" type="text/css" href="./styles/LikedPost.css?ver2">
	<link rel="stylesheet" type="text/css" href="./styles/CategoryNav.css?ver2" />
	<link rel="stylesheet" type="text/css" href="./styles/BottomNav.css" />
</head>
<body>
<%@ include file="Header.jsp"%>
    <h2>좋아요 한 게시물</h2>
    
    <!-- category nav -->
		<%@ include file="CategoryNav.jsp"%>

    <div class="gallery">
        <img src="./images/1.jpg" alt="" onclick="openPopup('./images/1.jpg')">
        <img src="./images/2.jpg" alt="" onclick="openPopup('./images/2.jpg')">
        <img src="./images/3.jpg" alt="" onclick="openPopup('./images/3.jpg')">
        <img src="./images/4.jpg" alt="" onclick="openPopup('./images/4.jpg')">
        <img src="./images/5.jpg" alt="" onclick="openPopup('./images/5.jpg')">
        <img src="./images/6.jpg" alt="" onclick="openPopup('./images/6.jpg')">
        <img src="./images/7.jpg" alt="" onclick="openPopup('./images/7.jpg')">
        <img src="./images/8.jpg" alt="" onclick="openPopup('./images/8.jpg')">
    </div>
    
    <jsp:include page="Likeimg.jsp" />
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.7.1/gsap.min.js"></script>
    <script src="./LikedPost.js"></script>
    
    	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>
	
</body>
</html>
