<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<link rel="stylesheet" href="./styles/kakaomap.css">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dynamic Table and Chart Example</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
			<p><div>
				<input type="text" id="addressInput" placeholder="Enter Address and press Enter">
			</div>


			<p id="result"></p>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>
			
			<form id="locationForm" action="WeatherDataUpsertService"
				method="post" style="display: none;">
				<input type="hidden" id="latitude" name="lat" value=""> <input
					type="hidden" id="longitude" name="lng" value="">
			</form>
			<button id="getInfoButton" onclick="getInfo()">지도 정보 가져오기</button>
		</div>

		<div class="table-container">

			<div class="my-class">
				<table>
					<tr>
						<th rowspan="2">Title 1</th>
						<th>Title 2</th>
						<th>Title 3</th>
						<th>Title 4</th>
					</tr>
					<tr>
						<!-- <td>Data 1-1</td> -->
						<td>Data 1-2</td>
						<td>Data 1-3</td>
						<td>Data 1-4</td>
					</tr>
				</table>
			</div>
			<table>
				<thead>
					<tr>
						<th class="my-class">열 1</th>
						<%
						for (int i = 2; i <= 20; i++) {
						%>
						<th>셀 1-<%=i%></th>
						<%
						}
						%>
					</tr>
				</thead>
				<tbody>
					<%
					for (int row = 1; row <= 5; row++) {
					%>
					<tr>
						<th class="my-class">열 <%=row%></th>
						<%
						for (int col = 2; col <= 20; col++) {
						%>
						<td>셀 <%=row%>-<%=col%></td>
						<%
						}
						%>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<div id="weatherGraphContainer">
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

	<script>
    // 날씨 데이터 (예시)
    const weatherData = [<%for (int i = 0; i < 7; i++) {%>
        <%=(int) (Math.random() * 30)%>,
    <%}%>];
    const days = ['월', '화', '수', '목', '금', '토', '일'];

    // Canvas 요소 가져오기
    const canvas = document.getElementById('weatherGraph');

    // Chart.js를 사용하여 그래프 설정
    const ctx = canvas.getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: days,
            datasets: [{
                label: '주간 날씨',
                data: weatherData,
                backgroundColor: 'rgba(54, 162, 235, 0.2)', // 배경색 (옵션)
                borderColor: 'rgba(54, 162, 235, 1)', // 선 색 (옵션)
                borderWidth: 2, // 선 굵기 (옵션)
                pointBackgroundColor: 'rgba(54, 162, 235, 1)', // 데이터 포인트 색상
                pointBorderColor: 'rgba(54, 162, 235, 1)', // 데이터 포인트 테두리 색상
                pointRadius: 5, // 데이터 포인트 반지름
                pointHoverRadius: 7, // 마우스 호버 시 데이터 포인트 반지름
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false, // Canvas의 가로 세로 비율을 유지하지 않음
            scales: {
                y: {
                    beginAtZero: false // Y 축 시작 값 설정
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return `Temperature: ${context.raw.toFixed(1)}°C`; // 소수점 첫째 자리까지 수치 표시
                        }
                    }
                }
            }
        }
    });
</script>

	<!-- <script>
    // Kakao Map 관련 스크립트
    function getInfo() {
        var center = map.getCenter(); 
        document.getElementById('latitude').value = center.getLat();
        document.getElementById('longitude').value = center.getLng();
        document.getElementById('locationForm').submit();
    }

    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        }; 
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    // 지도 이동 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'idle', function() {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    });

    function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
    }

    function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            for(var i = 0; i < result.length; i++) {
                // 행정동의 region_type 값이 'H'인 정보를 찾습니다
                if (result[i].region_type === 'H') {
                    document.getElementById('centerAddr').innerHTML = result[i].address_name;
                    break;
                }
            }
        }    
    }
</script> -->

</body>
</html>
