<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel="stylesheet" type="text/css" href="./styles/MyPage.css?ver3">
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
        <img src="./images/1.jpg" alt="" onclick="openPopup('./images/1.jpg', this)">
        <img src="./images/2.jpg" alt="" onclick="openPopup('./images/2.jpg', this)">
        <img src="./images/3.jpg" alt="" onclick="openPopup('./images/3.jpg', this)">
        <img src="./images/4.jpg" alt="" onclick="openPopup('./images/4.jpg', this)">
        <img src="./images/5.jpg" alt="" onclick="openPopup('./images/5.jpg', this)">
        <img src="./images/6.jpg" alt="" onclick="openPopup('./images/6.jpg', this)">
        <img src="./images/7.jpg" alt="" onclick="openPopup('./images/7.jpg', this)">
        <img src="./images/8.jpg" alt="" onclick="openPopup('./images/8.jpg', this)">
    </div>
    
        <button>좋아요 한 게시물 보기</button><br>
        <button class="red">회원탈퇴</button>
    </div>
    <jsp:include page="Likeimg.jsp" />
    <jsp:include page="DeleteButton.jsp" />
    
    <script src="Likeimg.js"></script>
    
    <!-- bottom nav -->
    <%@ include file="BottomNav.jsp"%>

			}
			%>
		</div>

		<button>좋아요 한 게시물 보기</button>
		<br>
		<button class="red" onclick="location.href='UserDelete.jsp'">회원탈퇴</button>
	</div>
</body>
</html>