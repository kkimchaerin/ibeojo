<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>지도 정보 얻어오기</title>
    
</head>
<body>
<p style="margin-top:-12px">
    <em class="link">
        <a href="/web/documentation/#MapTypeId" target="_blank">지도 타입을 보시려면 여기를 클릭하세요!</a>
    </em>
</p>
<div id="map" style="width:100%;height:350px;"></div>
<p>개발자도구를 통해 직접 확인해 보세요.</p>
<p id="result"></p>  
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도 타입 컨트롤을 지도에 표시합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

//지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'center_changed', function() {

    // 지도의  레벨을 얻어옵니다
    var level = map.getLevel();

    // 지도의 중심좌표를 얻어옵니다 
    var latlng = map.getCenter(); 

    var message = '<p>지도 레벨은 ' + level + ' 이고</p>';
    message += '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';

    var resultDiv = document.getElementById('result');
    resultDiv.innerHTML = message;

});

function getInfo() {
    // 지도의 현재 중심좌표를 얻어옵니다 
    var center = map.getCenter(); 
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    // 지도타입을 얻어옵니다
    var mapTypeId = map.getMapTypeId(); 
    
    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 
    
    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
    var boundsStr = bounds.toString();
    
    
    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
    message += '경도 ' + center.getLng() + ' 이고 <br>';
    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
    
   
    // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
    // ex) console.log(message);
    console.log(message);
    
    // 숨겨진 폼의 입력값 설정
    document.getElementById("latitude").value = center.getLat();
    document.getElementById("longitude").value = center.getLng();

    // 폼 제출
    document.getElementById("locationForm").submit();

}

</script>
<form id="locationForm" action="WeatherDataUpsertService" method="post" style="display:none;">
    <input type="hidden" id="latitude" name="lat" value="">
    <input type="hidden" id="longitude" name="lng" value="">
</form>
<button onclick="getInfo()">지도 정보 가져오기</button>



</body>
</html>