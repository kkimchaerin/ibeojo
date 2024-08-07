<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷-업로드</title>
<link rel="stylesheet" type="text/css" href="./styles/Reset.css" />
<link rel="stylesheet" type="text/css" href="./styles/Upload.css?ver=3" />

<script src="./javascripts/Post.js?ver=2" defer></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<main>
		<h2 id="logo">오늘의옷</h2>
		<form action="UploadService" method="post"
			enctype="multipart/form-data">
			<!-- 이미지 -->
			<div class="content-wrapper test">
				<label for="photo" class="form-label"> <span>이미지 업로드</span></br>
					<span id="file-name">Click to add image</span>
				</label> 
				<input type="file" accept="*.jpg,*.png,*.jpeg,*.webp,*.avif"
					id="photo" name="postImg" style="display: none;" />

			</div>
			<!-- 성별 -->
			<div class="content-wrapper">
				<label for="gender-list" class="form-label">성별</label>
				<ul id="gender-list">
					<li>
						<button type="button" class="form-btn checked"
							onClick="setValue('gender', 'F', this)">여성</button>
					</li>
					<li>
						<button type="button" class="form-btn"
							onClick="setValue('gender', 'M', this)">남성</button>
					</li>
				</ul>
			</div>
			<!-- 스타일 -->
			<div class="content-wrapper">
				<label for="style-list" class="form-label">스타일</label>
				<ul id="style-list">
					<li>
						<button type="button" class="form-btn checked"
							onClick="setValue('style', '미니멀', this)">미니멀</button>
					</li>
					<li>
						<button type="button" class="form-btn"
							onClick="setValue('style', '비즈니스', this)">비즈니스</button>
					</li>
					<li>
						<button type="button" class="form-btn"
							onClick="setValue('style', '스포티', this)">스포티</button>
					</li>
					<li>
						<button type="button" class="form-btn"
							onClick="setValue('style', '캐주얼', this)">캐주얼</button>
					</li>
				</ul>
			</div>
			<!-- 계절 -->
			<div class="content-wrapper">
				<label for="season-list" class="form-label">계절</label>
				<ul id="season-list">
					<li><button type="button" class="form-btn checked"
							onClick="setValue('season', '봄', this)">봄</button></li>
					<li><button type="button" class="form-btn"
							onClick="setValue('season', '여름', this)">여름</button></li>
					<li><button type="button" class="form-btn"
							onClick="setValue('season', '가을', this)">가을</button></li>
					<li><button type="button" class="form-btn"
							onClick="setValue('season', '겨울', this)">겨울</button></li>
				</ul>
			</div>
			<!-- 코멘트 -->
			<div class="content-wrapper">
				<label for="comment" class="form-label">코멘트</label>
				<textarea id="comment" name="comment" rows="5" maxlength="300"></textarea>
			</div>
			<!-- 얼굴 인식 모자이크 여부  -->
			<div class="face-mosaic-wrapper">
				<label for="face-mosaic-toggle" class="form-label">얼굴 모자이크</label>
				<div class="toggle-switch">
					<input type="checkbox" id="face-mosaic-toggle" name="faceMasaic" />
					<label for="face-mosaic-toggle" class="toggle-label"> <span
						class="toggle-inner"></span> <span class="toggle-switch"></span>
					</label>
				</div>
			</div>

			<!-- hidden -->
			<input type="hidden" id="gender" name="gender" value="F"> 
			<input type="hidden" id="style" name="style" value="미니멀"> 
			<input type="hidden" id="season" name="season" value="봄"> 
			<input type="hidden" id="faceCheck" name="faceCheck" value=faceCheck>
			<button type="submit" id="submit-btn">등록하기</button>
			<div id="cancel-btn"><a href="Main.jsp">취소하기</a>
			</div>
		</form>
	</main>
	<script>
		// 얼굴인식 토글 값 추출
		let faceCheck = "OFF";

		document.getElementById('face-mosaic-toggle').addEventListener('click',
				function() {
					if (this.checked) {
						faceCheck = "ON";
					} else {
						faceCheck = "OFF";
					}
				});

		document.getElementById('submit-btn').addEventListener('click',
				function(event) {
					document.getElementById('faceCheck').value = faceCheck;
				});
	</script>
</body>
</html>