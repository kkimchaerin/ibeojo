<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>좌표로 주소를 얻어내기</title>
    <style>
    .map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>
</head>
<body>

<!-- 
	먼저 카카오 웹페이지의 형태를 생각해보자
	
	제일 처음에 주소를 입력받거나 카카오 맵에서 좌표를 클릭하여 지정한다
	해당 주소를 클릭하면 날씨가 있으면 날씨정보를 db에서 바로 불러오고
	날씨정보가 db에 없으면 날씨정보를 api부터 받아온다
	
	
	
	1. 주소를 입력하는 창
		1.1. 입력창 만들기 
		1.2. 카카오api에서 주소로 위도경도 알아오기 
		1.3. ajax로 비동기 교환하기
	
	2. 현재 날씨를 보여주는 창
		2.1. 새로고침 버튼같은걸 추가해서 날짜 받아오기
		2.2. 현재 시간대를 불러와서 가장 근접한 시간 보여주기
	
	3. 시간별 날씨 정보를 제공하는 창
		3.1. db에서 오늘 날씨를 불러온다
		
	4. 주간예보 보류

	
		1) html에서 해야 할 것
			1.1. 123씩 미리 틀 만들어두기 
				1.1.1. 아이콘 준비하기
				1.1.2. 그래프 준비하기
				
			1.2. 카카오 map api 화면 띄우기
				1.2.1. 주소로 마커와 위경도 받아오기
				1.2.2. 마커 클릭으로 주소와 위경도 받아오기
				
		2) js에서 해야 할 것
			1.1. 날씨정보 받아오는 함수 만들기
				1.1.2. db받아오는 서블릿 만들기
				
			1.2. 그래프 그리기
				1.2.1. 그래프 양식찾기
				

 -->

<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
    </div>
</div>
<p>개발자도구를 통해 직접 확인해 보세요.</p>
<p id="result"></p>  
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7b5d7cfbe3d759287c1aad17b89b913&libraries=services"></script>
<script src="./javascripts/kakaomapjs.js"></script>
<form id="locationForm" action="WeatherDataUpsertService" method="post" style="display:none;">
    <input type="hidden" id="latitude" name="lat" value="">
    <input type="hidden" id="longitude" name="lng" value="">
</form>
<button id="getInfoButton" onclick="getInfo()">지도 정보 가져오기</button>
</body>
</html>
