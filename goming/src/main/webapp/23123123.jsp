<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Geolocation API 예제</title>
</head>
<body>
    <h2>사용자의 위치 정보 가져오기</h2>
    <button id="getLocationButton">위치 정보 가져오기</button>
    <p id="locationInfo"></p>

    <script>
        document.getElementById('getLocationButton').addEventListener('click', function() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition, showError);
            } else {
                document.getElementById('locationInfo').textContent = "이 브라우저는 Geolocation을 지원하지 않습니다.";
            }
        });

        function showPosition(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            document.getElementById('locationInfo').textContent = `위도: ${latitude}, 경도: ${longitude}`;
        }

        function showError(error) {
            let errorMessage;
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    errorMessage = "사용자가 위치 정보 요청을 거부했습니다.";
                    break;
                case error.POSITION_UNAVAILABLE:
                    errorMessage = "위치 정보를 사용할 수 없습니다.";
                    break;
                case error.TIMEOUT:
                    errorMessage = "요청 시간이 초과되었습니다.";
                    break;
                case error.UNKNOWN_ERROR:
                    errorMessage = "알 수 없는 오류가 발생했습니다.";
                    break;
            }
            document.getElementById('locationInfo').textContent = errorMessage;
        }
    </script>
</body>
</html>
