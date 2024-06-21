<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liked Post</title>
    <link rel="stylesheet" type="text/css" href="./styles/LikedPost.css?ver2">
</head>
<body>
<%@ include file="Header.jsp"%>
    <h2>좋아요 한 게시물</h2>
    <div class="icons">
        <button class="icon">
            <img src="./icons/minimal.png" alt="아이콘 1">
            미니멀
        </button>
        <button class="icon">
            <img src="./icons/business.png" alt="아이콘 2">
            비즈니스
        </button>
        <button class="icon">
            <img src="./icons/sporty.png" alt="아이콘 3">
            스포티
        </button>
        <button class="icon">
            <img src="./icons/casual.png" alt="아이콘 4">
            캐주얼
        </button>
        <br>
        <button class="icon">
            <img src="./icons/spring.png" alt="아이콘 5">
            봄
        </button>
        <button class="icon">
            <img src="./icons/summer.png" alt="아이콘 6">
            여름
        </button>
        <button class="icon">
            <img src="./icons/autumn.png" alt="아이콘 7">
            가을
        </button>
        <button class="icon">
            <img src="./icons/winter.png" alt="아이콘 8">
            겨울
        </button>
    </div>
    
    <div class="gallery">
        <!-- 이미지 추가 -->
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
    <script src="./LikedPost.js"></script> <!-- 새로 추가한 부분 -->
    
    	<!-- bottom nav -->
	<%@ include file="BottomNav.jsp"%>
	
</body>
</html>
