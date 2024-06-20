<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>오늘의집-업로드</title>
  <link rel="stylesheet" type="text/css" href="./styles/Reset.css" />
  <link rel="stylesheet" type="text/css" href="./styles/Upload.css?ver=1" />
</head>
<body>
  <main>
    <h2 id="logo">오늘의옷</h2>
    <form action="" method="post">
      <div class="content-wrapper">
				<label for="photo" class="form-label">
          <span>이미지 업로드</span></br>
          <span id="file-name">Click to add image</span>
        </label> 
        <div class="photo-wrapper">

          <input type="file" accept="*.jpg,*.png,*.jpeg,*.webp,*.avif"
           id="photo" name="photo" onChange="" style="display: none;"/>
        </div>
			</div>
      <div class="content-wrapper">
				<label for="gender-list" class="form-label">성별</label> 
        <ul id="gender-list">
          <li><button type="button" class="form-btn checked" name="gender" onClick="">여성</button></li>
          <li><button type="button" class="form-btn" name="gender" onClick="">남성</button></li>
        </ul>
			</div>
      <div class="content-wrapper">
				<label for="style-list" class="form-label">스타일</label> 
        <ul id="style-list">
          <li><button type="button" class="form-btn checked" name="style" onClick="">미니멀</button></li>
          <li><button type="button" class="form-btn" name="style" onClick="">비즈니스</button></li>
          <li><button type="button" class="form-btn" name="style" onClick="">스포티</button></li>
          <li><button type="button" class="form-btn" name="style" onClick="">캐주얼</button></li>
        </ul>
			</div>
      <div class="content-wrapper">
				<label for="season-list" class="form-label">계절</label> 
        <ul id="season-list">
          <li><button type="button" class="form-btn" name="season" onClick="">봄</button></li>
          <li><button type="button" class="form-btn checked" name="season" onClick="">여름</button></li>
          <li><button type="button" class="form-btn" name="season" onClick="">가을</button></li>
          <li><button type="button" class="form-btn" name="season" onClick="">겨울</button></li>
        </ul>
			</div>
      <button type="submit" id="submit-btn">등록하기</button>
    </form>
  </main>

  <script>
    document.getElementById('photo').addEventListener('change', function() {
      let fileName = this.files[0] ? this.files[0].name : 'Click to add image';
      document.getElementById('file-name').textContent = fileName;
    });
  </script>
</body>
</html>