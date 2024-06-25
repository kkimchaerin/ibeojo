<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>오늘의옷-업로드</title>
<link rel="stylesheet" type="text/css" href="./styles/Reset.css" />
<link rel="stylesheet" type="text/css" href="./styles/Upload.css?ver=3" />
<script src="./javascripts/Post.js?ver=1" defer></script>
</head>
<body>
   <%@ include file="Header.jsp"%>
   <main>
      <h2 id="logo">오늘의옷</h2>
      <form action="UploadService" method="post"
         enctype="multipart/form-data">
      <!-- 이미지 -->
         <div class="content-wrapper test">
            <label for="photo" class="form-label"> <span>이미지 업로드</span>
               <span id="file-name">Click to add image</span>
            </label>
        <input type="file" accept="*.jpg,*.png,*.jpeg,*.webp,*.avif"
          id="photo" name="postImg" style="display: none;" /> 
        <!-- 얼굴 인식 모자이크 여부  -->
        <div class="face-mosaic-wrapper">
          <label for="face-mosaic-toggle" class="form-label" id="face-masic-label">얼굴 모자이크</label>
          <div class="toggle-switch">
            <input type="checkbox" id="face-mosaic-toggle" name="faceMasaic" />
            <label for="face-mosaic-toggle" class="toggle-label">
              <span class="toggle-inner"></span>
              <span class="toggle-switch"></span>
            </label>
          </div>
        </div>
         </div>   
      <!-- 성별 -->
         <div class="content-wrapper">
            <label for="gender-list" class="form-label">성별</label>
            <ul id="gender-list">
               <li>
                  <button type="button" class="form-btn" data-value="F" onClick="setValue('userGender', 'F', this)">여성</button>
               </li>
               <li>
                  <button type="button" class="form-btn" data-value="M" onClick="setValue('userGender', 'M', this)">남성</button>
               </li>
            </ul>
         </div>
      <!-- 스타일 -->
         <div class="content-wrapper">
            <label for="style-list" class="form-label">스타일</label>
            <ul id="style-list">
               <li>
                  <button type="button" class="form-btn" data-value="미니멀" onClick="setValue('style', '미니멀', this)">미니멀</button>
               </li>
               <li>
                  <button type="button" class="form-btn" data-value="비즈니스" onClick="setValue('style', '비즈니스', this)">비즈니스</button>
               </li>
               <li>
                  <button type="button" class="form-btn" data-value="스포티" onClick="setValue('style', '스포티', this)">스포티</button></li>
               <li>
                  <button type="button" class="form-btn" data-value="캐주얼" onClick="setValue('style', '캐주얼', this)">캐주얼</button>
               </li>
            </ul>
         </div>
      <!-- 계절 -->
         <div class="content-wrapper">
            <label for="season-list" class="form-label">계절</label>
            <ul id="season-list">
               <li><button type="button" class="form-btn" data-value="봄" onClick="setValue('season', '봄', this)">봄</button></li>
               <li><button type="button" class="form-btn" data-value="여름" onClick="setValue('season', '여름', this)">여름</button></li>
               <li><button type="button" class="form-btn" data-value="가을" onClick="setValue('season', '가을', this)">가을</button></li>
               <li><button type="button" class="form-btn" data-value="겨울" onClick="setValue('season', '겨울', this)">겨울</button></li>
            </ul>
         </div>
         
      <!-- hidden -->
         <input type="hidden" id="userGender" name="userGender" value=userGender>
         <input type="hidden" id="style" name="style" value=style>
         <input type="hidden" id="season" name="season" value=season>
         <input type="hidden" id="faceCheck" name="faceCheck" value = faceCheck>
            
         <button type="submit" id="submit-btn">등록하기</button>
      </form>
   </main>

   <script>
   document.addEventListener('DOMContentLoaded', function() {
        // 초기값 설정
        document.getElementById('userGender').value = 'F'; // 기본 성별은 여성으로 설정
        document.getElementById('style').value = '미니멀'; // 기본 스타일은 미니멀로 설정
        document.getElementById('season').value = '여름'; // 기본 계절은 여름으로 설정
        document.getElementById('faceCheck').value = "OFF"; // 기본 모자이크 값은 OFF로 설정

        // UI에도 반영 (버튼에 checked 클래스 추가)
        const genderChecked = document.querySelector('#gender-list .checked');
        if (genderChecked !== null) {
            genderChecked.classList.remove('checked');
        }

        const styleChecked = document.querySelector('#style-list .checked');
        if (styleChecked !== null) {
            styleChecked.classList.remove('checked');
        }

        const seasonChecked = document.querySelector('#season-list .checked');
        if (seasonChecked !== null) {
            seasonChecked.classList.remove('checked');
        }

        const faceMosaicToggleChecked = document.querySelector('#face-mosaic-toggle.checked');
        if (faceMosaicToggleChecked !== null) {
            faceMosaicToggleChecked.classList.remove('checked');
        }

        // 초기값에 해당하는 버튼에 checked 클래스 추가
        const genderButton = document.querySelector('#gender-list button[data-value="F"]');
        if (genderButton !== null) {
            genderButton.classList.add('checked');
        }

        const styleButton = document.querySelector('#style-list button[data-value="미니멀"]');
        if (styleButton !== null) {
            styleButton.classList.add('checked');
        }

        const seasonButton = document.querySelector('#season-list button[data-value="여름"]');
        if (seasonButton !== null) {
            seasonButton.classList.add('checked');
        }
    });

    // 이미지 업로드
    document.getElementById('photo').addEventListener('change', function() {
        let fileName = this.files[0] ? this.files[0].name : 'Click to add image';
        document.getElementById('file-name').textContent = fileName;
    });

    function setValue(hiddenInputId, value, button) {
        document.getElementById(hiddenInputId).value = value;

        // 같은 그룹의 모든 버튼에서 checked 클래스 제거
        const group = button.parentElement.parentElement;
        group.querySelectorAll('.form-btn').forEach(btn => btn.classList.remove('checked'));

        // 클릭된 버튼에 checked 클래스 추가
        button.classList.add('checked');

        console.log(hiddenInputId + ": " + value);
    }

    // 얼굴인식 토글 값 추출
    let faceCheck = "OFF";

    document.getElementById('face-mosaic-toggle').addEventListener('click', function() {
        if (this.checked) {
            faceCheck = "ON";
        } else {
            faceCheck = "OFF";
        }
    });

    document.getElementById('gender-list').addEventListener('click', function() {
        console.log(userGender);
        console.log(style);
        console.log(season);
        console.log(faceCheck)
    });

    document.getElementById('submit-btn').addEventListener('click', function(event) {
        document.getElementById('faceCheck').value = faceCheck;
    });
  </script>

			<input type="hidden" id="gender" name="gender" value="F">
			<input type="hidden" id="style" name="style" value="미니멀">
			<input type="hidden" id="season" name="season" value="여름">
			
			<button type="submit" id="submit-btn">등록하기</button>
			<div  id="cancel-btn"><a href="Main.jsp">취소하기</a></div>
		</form>
	</main>
</body>
</html>
