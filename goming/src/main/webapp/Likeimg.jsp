<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liked Images</title>
    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./styles/Likeimg.css?ver2">
</head>
<body>

<!-- 팝업 창 -->
<div id="imagePopupBackground" class="popup-background" onclick="closePopup()">
    <div class="popup-content" onclick="event.stopPropagation()">
        <span class="close" onclick="closePopup()">&times;</span>
        <img id="popupImage" src="" style="max-width:100%; max-height:100%;">
        <div id="animationContainer"></div>
        
        <!-- 텍스트를 나타낼 div 추가 -->
        <div class="popup-text">
            <!-- 하트 버튼 추가 -->
            <button class="heartBtn" onclick="addLike()">
                <img id="heartIcon" src="./images/heart-regular.svg" alt="like"> <!-- 로컬 SVG 아이콘 사용 -->
            </button>
        </div>
    </div>
</div>

<script src="Likeimg.js"></script>

</body>
</html>
