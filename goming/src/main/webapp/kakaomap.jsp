<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="styles/kakaomap.css">
<link rel="stylesheet" type="text/css" href="styles/BottomNav.css" />
<link rel="stylesheet" type="text/css" href="styles/Header.css" />
<title>오늘의옷</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>
<script src="./javascripts/kakaomapjs.js"></script>
</head>
<body>

   <div class="container">
      <div class="map-container">
         <!-- 카카오 맵 API가 담긴 부분 -->
         <div class="map_wrap" style="margin-top:70px;">
            <div id="map"
               style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
            <div class="hAddr">
               <span class="title">지도중심기준 행정동 주소정보</span> <span id="centerAddr"></span>
            </div>
         </div>
         <div>
            <input type="text" id="addressInput" placeholder="Enter Address and press Enter">
            <button id="getInfoButton" onclick="fetchWeatherAndSaveToDBs()">날씨 정보 가져오기</button>
         </div>
         <p id="result"></p>

      </div>
      <div class="loading-spinner"></div>
      <div class="table-container" id="Table">

         <div class="my-class">
            <table id = "daily">
               <tr>
                  <th rowspan="2">온도</th>
                  <th>강수확률</th>
                  <th>강우량</th>
                  <th>날씨상태</th>
               </tr>
               <tr>
                  <!-- <td>Data 1-1</td> -->
                  <td id="date-cell">날짜</td>
                  <!-- 날짜 -->
                  <td id="time-cell">시간</td>
                  <!-- 시간 -->
                  <td id="lat-lon-cell">위도와 경도</td>
                  <!-- 위도와 경도 -->
               </tr>
            </table>
         </div>
         <table id="dynamicTable">
            <thead>
               <tr>
                  <th class="my-class">날짜/시간</th>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <th class="my-class">온도</th>
               </tr>
               <tr>
                  <th class="my-class">강수확률</th>
               </tr>
               <tr>
                  <th class="my-class">강수량</th>
               </tr>
               <tr>
                  <th class="my-class">풍속</th>
               </tr>
               <tr>
                  <th class="my-class">습도</th>
               </tr>
            </tbody>
            
         </table>

         <div id="weatherGraphContainer" style="position: sticky;">
            <canvas id="weatherGraph"></canvas>
         </div>
      </div>
   </div>
   <%@ include file="BottomNav.jsp"%>
   <%@ include file="Header.jsp"%>
   <%@ include file="UploadButton.jsp"%>
</body>
</html>

<!-- 
   값 대입하기
   - db의 컬럼들
      1. 날짜      - Data 1-2
      2. 시간      - Data 1-3
      3. 온도      - Title1
      4. 습도      - Title4
      5. 날씨상태   - Title1
      6. 풍속      - 
      7. 강수확률   - Title2
      8. 강우량      - Title3
      9. 위도와 경도 - Data 1-4
   
      열1 - 온도
      열2 - 강수확률
      열3 - 강수량
      열4 - 풍속
      열5 - 습도
 -->
