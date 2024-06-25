<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dynamic Table and Chart Example</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
/* Flexbox 컨테이너 설정 */
.container {
    display: flex;
    justify-content: flex-start; /* 좌측 정렬 */
    align-items: flex-start;
    margin: 20px;
}

/* 카카오 맵 컨테이너 설정 */
.map-container {
    width: 60%; /* 적절한 비율로 설정 */
    margin-right: 20px; /* 오른쪽 여백 */
}

/* Canvas의 최대 너비 설정 */
#weatherGraphContainer {
    max-width: 800px;
    margin: 20px auto; /* 가운데 정렬을 위한 margin 설정 */
}

.table-container {
    width: 40%; /* 적절한 비율로 설정 */
    overflow: auto;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    padding: 8px;
    border: 1px solid #ddd;
    text-align: center;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

/* 첫 번째 열 고정 */
.my-class {
    position: sticky;
    left: 0;
    background-color: #f2f2f2; /* 선택적으로 배경색 설정 */
}

th[rowspan="2"] {
    width: 50%;
}
</style>
</head>
<body>

<div class="container">
    <div class="map-container">
        <!-- 카카오 맵 API가 담긴 부분 -->
        <div id="map" style="width:100%;height:400px;"></div>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>
        <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new kakao.maps.LatLng(37.5665, 126.9780), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            }; 
        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        </script>
    </div>

    <div class="table-container">
        <div class="my-class">
            <input type="text" placeholder="Enter Data 1-2">
        </div>
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
                    <% for (int i = 2; i <= 20; i++) { %>
                    <th>셀 1-<%=i%></th>
                    <% } %>
                </tr>
            </thead>
            <tbody>
                <% for (int row = 1; row <= 5; row++) { %>
                <tr>
                    <th class="my-class">열 <%=row%></th>
                    <% for (int col = 2; col <= 20; col++) { %>
                    <td>셀 <%=row%>-<%=col%></td>
                    <% } %>
                </tr>
                <% } %>
            </tbody>
        </table>

        <div id="weatherGraphContainer">
            <canvas id="weatherGraph"></canvas>
        </div>
    </div>
</div>

<script>
    // 날씨 데이터 (예시)
    const weatherData = [<% for (int i = 0; i < 7; i++) { %>
        <%= (int) (Math.random() * 30) %>,
    <% } %>];
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

</body>
</html>
