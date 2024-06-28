<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Image Confirmation</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./styles/DeleteButton.css?ver3">
</head>
<body>

<div id="deletePopupBackground" class="popup-background" onclick="closeDeletePopup()">
    <div class="popup-content" onclick="event.stopPropagation()">
        <!-- 닫기 버튼 -->
        <span class="close" onclick="closeDeletePopup()">
            <img src="./icons/CloseButton.png" alt="Close" style="width: 24px; height: 24px;">
        </span>
        
        <!-- 삭제 확인 메시지 -->
        <p> 게시물을 삭제하시겠습니까?</p>
        
        <!-- 예/아니오 버튼 -->
        <div class="buttons">
            <button onclick="deleteConfirmed()">예</button>
            <button onclick="closeDeletePopup()">아니오</button>
        </div>
    </div>
</div>
<!-- <script src="./javascripts/Likeimg.js"></script> -->

</body>
</html>
