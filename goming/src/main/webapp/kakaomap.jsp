<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<link rel="stylesheet" href="./styles/kakaomap.css">
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dynamic Table and Chart Example</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
#map {
	width: 100%;
	height: 350px;
	margin-top: 20px;
}

#addressInput {
	width: 300px; /* 너비를 조정합니다 */
	height: 40px; /* 높이를 조정합니다 */
	padding: 10px; /* 내부 여백을 추가합니다 */
	font-size: 16px; /* 글씨 크기를 조정합니다 */
	border-radius: 5px; /* 모서리를 둥글게 만듭니다 */
	border: 1px solid #ccc; /* 테두리 색상과 두께를 설정합니다 */
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1); /* 약간의 그림자를 추가합니다 */
	margin-bottom: 20px; /* 아래 여백을 추가합니다 */
}

.customInfoWindow {
	width: 200px;
	padding: 10px;
	font-size: 14px;
	line-height: 1.5;
}
</style>
<script src="./javascripts/kakaomapjs.js"></script>
</head>
<body>

	<div class="container">
		<div class="map-container">
			<!-- 카카오 맵 API가 담긴 부분 -->
			<div class="map_wrap">
				<div id="map"
					style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
				<div class="hAddr">
					<span class="title">지도중심기준 행정동 주소정보</span> <span id="centerAddr"></span>
				</div>
			</div>
			<p>
			<div>
				<input type="text" id="addressInput"
					placeholder="Enter Address and press Enter">
				<div class ="loading-spinner"></div>
			</div>

			<p id="result"></p>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>

			<!-- 			<form id="locationForm" action="WeatherDataUpsertService"
				method="post" style="display: none;">
				<input type="hidden" id="latitude" name="lat" value=""> <input
					type="hidden" id="longitude" name="lng" value="">
			</form> -->
			<button id="getInfoButton" onclick="fetchWeatherAndSaveToDBs()">지도
				정보 가져오기</button>
		</div>

		<div class="table-container">

			<div class="my-class">
				<table id="daily">
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
						<%-- 						<%
						for (int i = 2; i <= 20; i++) {
						%>
						<th>셀 1-<%=i%></th>
						<%
						}
						%> --%>
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

	<script>
/* document.addEventListener('DOMContentLoaded', function() {
            console.log('Enter key pressed!');
    let addressInput = document.getElementById('addressInput');

    addressInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            console.log('Enter key pressed!');
            // 여기에 원하는 동작을 추가하면 됩니다.
        }
    });
}); */
</script>





</body>
</html>

<!-- 
	값 대입하기
	- db의 컬럼들
		1. 날짜		- Data 1-2
		2. 시간		- Data 1-3
		3. 온도		- Title1
		4. 습도		- Title4
		5. 날씨상태	- Title1
		6. 풍속   	- 
		7. 강수확률	- Title2
		8. 강우량		- Title3
		9. 위도와 경도 - Data 1-4
	
		열1 - 온도
		열2 - 강수확률
		열3 - 강수량
		열4 - 풍속
		열5 - 습도
	





 -->
