document.addEventListener('DOMContentLoaded', function() {
    // 기본 카카오 api의 화면 생성 시작 -------------------------------------------
    let mapContainer = document.getElementById('map'); // 지도를 표시할 div 
    let mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

    let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    let mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도 타입 컨트롤을 지도에 표시합니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 기본 카카오 api의 화면 생성 끝남 -------------------------------------------

    // 주소-좌표 변환 객체를 생성합니다
    let geocoder = new kakao.maps.services.Geocoder();

    let marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
        infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

    // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    // 버튼 엘리먼트 가져오기
    let getInfoButton = document.getElementById('getInfoButton');

    // 초기에 버튼은 비활성화 상태로 설정
    getInfoButton.disabled = true;

	let latitude;
	let longitude;

    // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                let detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                
                let content = '<div class="bAddr">' +
                                '<span class="title">법정동 주소정보</span>' + 
                                detailAddr + 
                            '</div>';

                // 기존 마커 제거
                marker.setMap(null);
                
                // 마커를 클릭한 위치에 표시합니다 
                marker = new kakao.maps.Marker({
                    position: mouseEvent.latLng
                });
                marker.setMap(map);

                // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                latitude = mouseEvent.latLng.getLat();
                longitude = mouseEvent.latLng.getLng();
                
                content += '<div>위도 : ' + latitude.toFixed(4) + '</div>';
                content += '<div>경도 : ' + longitude.toFixed(4) + '</div>';
                infowindow.setContent(content);
                infowindow.open(map, marker);
                    
                // 버튼 활성화
                getInfoButton.disabled = false;
            }   
        });
    });

    // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(map, 'idle', function() {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    });

    function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
    }

    function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    }

    // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
    function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            let infoDiv = document.getElementById('centerAddr');

            for (let i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === 'H') {
                    infoDiv.innerHTML = result[i].address_name;

                    break;
                }
            }
        }    
    }

    // 중심좌표의 값 구하기 시작 -------------------------------------------

    window.getInfo = function() {
        // 지도의 현재 중심좌표를 얻어옵니다 
        let center = map.getCenter();

        // 지도의 현재 레벨을 얻어옵니다
        let level = map.getLevel();

        // 지도타입을 얻어옵니다
        let mapTypeId = map.getMapTypeId();

        // 지도의 현재 영역을 얻어옵니다 
        let bounds = map.getBounds();

        // 영역의 남서쪽 좌표를 얻어옵니다 
        let swLatLng = bounds.getSouthWest();

        // 영역의 북동쪽 좌표를 얻어옵니다 
        let neLatLng = bounds.getNorthEast();

        // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
        let boundsStr = bounds.toString();

        let message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
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
    // 중심좌표의 값 구하기 끝남 -------------------------------------------
});
