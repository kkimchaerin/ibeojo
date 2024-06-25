let latitude;
let longitude;
let dbing = false;

document.addEventListener('DOMContentLoaded', function() {
	// 지도 초기화 및 설정
	let mapContainer = document.getElementById('map');
	let mapOption = {
		center: new kakao.maps.LatLng(33.450701, 126.570667), // 초기 지도 중심 좌표
		level: 3 // 초기 지도 확대 레벨
	};
	let map = new kakao.maps.Map(mapContainer, mapOption);

	// 카카오맵 이벤트 리스너 등록 (주소 검색, 위치 클릭 등)

	// 주소 입력 후 Enter 키를 누를 때의 이벤트 처리
	let addressInput = document.getElementById('addressInput');
	addressInput.addEventListener('keypress', function(e) {
		if (e.key === 'Enter') {
			e.preventDefault();

			// 주소로 좌표 변환 후 마커 표시
			geocoder.addressSearch(addressInput.value, function(result, status) {
				if (status === kakao.maps.services.Status.OK) {
					let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					placeMarker(coords);
				} else {
					alert('주소를 찾을 수 없습니다.');
				}
			});
		}
	});

	// 지도 클릭 시 위치 정보 가져오기
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
		placeMarker(mouseEvent.latLng);
	});

	// 마커를 특정 위치에 표시하고 위치 정보 업데이트
	function placeMarker(position) {
		latitude = position.getLat();
		longitude = position.getLng();

		// 마커 생성
		let marker = new kakao.maps.Marker({
			position: position,
			map: map
		});

		// 인포윈도우 설정
		let infowindow = new kakao.maps.InfoWindow({
			content: `<div class="customInfoWindow">위도: ${latitude.toFixed(4)}<br>경도: ${longitude.toFixed(4)}</div>`
		});
		infowindow.open(map, marker);

		// DB 저장 버튼 활성화
		dbing = false;
		document.getElementById('getInfoButton').disabled = false;
	}

	// DB로 정보 저장하기 (AJAX 통신)
	window.fetchWeatherAndSaveToDBs = function() {
		if (!dbing) {
			dbing = true;
			document.getElementById('getInfoButton').disabled = true;

			$.ajax({
				type: 'POST',
				url: 'WeatherDataUpsertService', // URL to your JSP file handling database update
				data: {
					lat: latitude,
					lon: longitude,
					weatherData: JSON.stringify(weatherData) // Convert weatherData to JSON string
				},
				success: function(response) {
					console.log('DB에 저장 완료');
					if (response.result === 1) {
						// Successfully stored in DB, proceed with further logic if needed
						getWeatherFromDBs(); // Example call to fetch updated data
					} else {
						alert('DB 저장에 실패했습니다.');
					}
				},
				error: function(xhr, status, error) {
					alert('AJAX 호출이 실패했습니다.');
					console.error(xhr, status, error);
				}
			});
		}
	};

	// DB에서 정보 가져오기 (AJAX 통신)
	function getWeatherFromDBs() {
		$.ajax({
			type: 'POST',
			url: 'WeatherDataUpsertService', // 이 URL을 정확히 설정해야 합니다.
			data: {
				lat: latitude,
				lon: longitude,
				weatherData: JSON.stringify(weatherData)
			},
			success: function(response) {
				console.log('DB에 저장 완료');
				if (response.result === 1) {
					getWeatherFromDBs();
				} else {
					alert('DB 저장에 실패했습니다.');
				}
			},
			error: function(xhr, status, error) {
				alert('AJAX 호출이 실패했습니다.');
				console.error(xhr, status, error);
			}
		});
	}

	// 가져온 데이터를 화면에 표시하기
	function displayWeatherData(data) {
		// 여기서 데이터를 원하는 방식으로 처리하여 화면에 표시
		console.log(data); // 예시: 콘솔에 데이터 출력
	}
});
