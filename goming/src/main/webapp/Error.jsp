<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>오늘의옷</title>
  <link rel="stylesheet" type="text/css" href="./styles/Error.css?ver=2" />
  <link rel="stylesheet" type="text/css" href="./styles/Reset.css" />
</head>
<body>
  <section class="error-wrapper">
    <h1 class="logo">오늘의옷</h1>
    <h2 class = "h">페이지를 찾을 수 없습니다😢</h2>
      <p class = "h">방문하시려는 페이지의 주소가 잘못되었거나,<br/>
        페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br/>
        불편을 드려 죄송합니다.
      </p>
      <button type="button" onclick="history.go(-1)" class="prev"> 이전 페이지 이동 </button>
  </section>
</body>
</html>