<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel="stylesheet" type="text/css" href="./styles/MyPage.css">
    <style>
        body {
            padding-top: 80px; /* Header의 높이만큼 body를 내립니다 */
        }
    </style>
</head>
<body>
<%@ include file="Header.jsp"%>
    <div class="content">
        <h2>My Page</h2>
        <div class="info-container">
            <div class="info-item">
                <strong>아이디</strong>
                <span class="info-box">이메일 주소</span>
            </div>
            <div class="info-item">
                <strong>닉네임</strong>
                <span class="info-box">닉네임</span>
            </div>
            <div class="info-item">
                <strong>성별</strong>
                <span class="info-box">성별</span>
            </div>
            <div class="info-item">
                <strong>선호스타일 </strong>
                <span class="info-box">선호스타일</span>
            </div>
        </div>

        <div class="post-title">
            <span class="post-icon">📷</span>게시물
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
        
        <button>좋아요 한 게시물 보기</button><br>
        <button>내 정보 수정하기</button><br>
        <button class="red">회원탈퇴</button>
    </div>
</body>
</html>
