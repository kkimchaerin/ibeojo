<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liked Post</title>
    <link rel="stylesheet" type="text/css" href="./styles/LikedPost.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script> <!-- Font Awesome CDN -->
</head>
<body>
    <h2>좋아요 한 게시물</h2>
    
    <div class="icons">
        <button class="icon" data-image="./images/icon1.jpg">
            <img src="./icons/minimal.png" alt="아이콘 1">
            미니멀
        </button>
        <button class="icon" data-image="./images/icon2.jpg">
            <img src="./icons/business.png" alt="아이콘 2">
     		비즈니스
            </button>
        <button class="icon" data-image="./images/icon3.jpg">
            <img src="./icons/sporty.png" alt="아이콘 3">
            스포티
        </button>
        <button class="icon" data-image="./images/icon4.jpg">
            <img src="./icons/casual.png" alt="아이콘 4">
            캐주얼
        </button>
        <br>
        <button class="icon" data-image="./images/icon5.jpg">
            <img src="./icons/spring.png" alt="아이콘 5">
            봄
        </button>
        <button class="icon" data-image="./images/icon6.jpg">
            <img src="./icons/summer.png" alt="아이콘 6">
            여름
        </button>
        <button class="icon" data-image="./images/icon7.jpg">
            <img src="./icons/autumn.png" alt="아이콘 7">
            가을
        </button>
        <button class="icon" data-image="./images/icon8.jpg">
            <img src="./icons/winter.png" alt="아이콘 8">
            겨울
        </button>
    </div>
    
    <div class="gallery">
        <!-- 이미지 추가 -->
        <img src="./images/1.jpg" alt="">
        <img src="./images/2.jpg" alt="">
        <img src="./images/3.jpg" alt="">
        <img src="./images/4.jpg" alt="">
        <img src="./images/5.jpg" alt="">
        <img src="./images/6.jpg" alt="">
        <img src="./images/7.jpg" alt="">
        <img src="./images/8.jpg" alt="">
    </div>
    
    <script src="./scripts/LikePost.js"></script>
</body>
</html>
