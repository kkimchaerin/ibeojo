let loadingSpinner;
document.addEventListener('DOMContentLoaded', function() {
	loadingSpinner = weatherIconContainer.querySelector('.loading-spinner');
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition, showError);
	} else {
		document.getElementById('weather-comment').textContent = "이 브라우저는 Geolocation을 지원하지 않습니다.";
		return; // 함수 실행 종료
	}



});



// 카카오맵위치정보 시작 -------------------------------------------

// 위경도를 주소로 변환하는 함수
function getAddressFromCoords(latitude, longitude) {
	// Geocoder 객체 생성
	const geocoder = new kakao.maps.services.Geocoder();

	// 좌표 객체 생성
	const coord = new kakao.maps.LatLng(latitude, longitude);

	// 좌표를 주소로 변환
	geocoder.coord2Address(coord.getLng(), coord.getLat(), function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			console.log('변환된 주소:', result[0].address.address_name);
			const additionalTextElement = document.querySelector('#additional-text');
			if (additionalTextElement) {
				
				additionalTextElement.innerHTML  = result[0].address.address_name;
			} else {
				console.error('additional-text 클래스를 가진 요소를 찾을 수 없습니다.');
			}
			// 변환된 주소를 여기에서 처리할 수 있음 (예: 화면에 표시)
		} else {
			console.error('주소 변환 실패:', status);
		}
	});
}

// 카카오맵위치정보 끝남 -------------------------------------------


// 위치정보 비동기통신 시작 -------------------------------------------
// 위치 정보를 사용해 날씨 데이터를 받아오는 함수
function getWeatherFromDBs(latitude, longitude) {
	$.ajax({
		type: 'POST', // HTTP 요청 방식 (POST 추천)
		url: 'WeatherDataSelectAllService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
		data: {
			lat: latitude,
			lon: longitude
		},
		success: function(response) {
			// 서버에서의 처리가 성공하면 이 함수가 호출됨
			const currentDateTime = getCurrentDateTime();
			const nearestData = findNearestData(response, currentDateTime);

			loadWeatherIcon(nearestData.weatherInfo);
			updateWeatherText((nearestData.temperature + "°C"), nearestData.weatherInfo);
			document.getElementById('weather-comment').textContent = getWeatherComment(nearestData.temperature);
			console.log(nearestData);
			getAddressFromCoords(latitude, longitude);
		},
		error: function(xhr, status, error) {
			// 서버에서의 처리가 실패하면 이 함수가 호출됨
			alert("AJAX 호출이 실패했습니다.");
			console.error(xhr, status, error);
		}
	});
}

function getWeatherComment(temp) {
	console.log(temp);
	let sel = 1;

	if (temp >= 4) {

		sel++;
	}
	if (temp >= 9) {

		sel++;
	}
	if (temp >= 12) {

		sel++;
	}
	if (temp >= 17) {

		sel++;
	}
	if (temp >= 20) {

		sel++;
	}
	if (temp >= 23) {

		sel++;
	}
	if (temp >= 28) {

		sel++;
	}

	let comment = "no";

	if (sel <= 1) {
		comment = "날이 많이 추워요. 추위에 단단히 대비해주세요! 목도리나 장갑을 챙기는 것도 좋아요";
	} else if (sel == 2) {
		comment = "다소 쌀쌀한 날씨가 예상됩니다. 자켓이나 코트같은 두꺼운 외투를 챙기는게 좋겠어요";
	} else if (sel == 3 || sel == 4) {
		comment = "날이 쌀쌀해요. 자켓이나 가디건 같은 외투를 챙겨주세요";
	} else if (sel == 5) {
		comment = "선선한 날씨에요! 오늘은 입고싶었던 옷을 꺼내보는건 어떨까요?";
	} else if (sel == 6) {
		comment = "활동하기 좋은 날씨에요! 살짝 더울 수 있으니 주의해주세요";
	} else if (sel == 7) {
		comment = "약간 더운 날씨가 예상됩니다. 얇고 통풍이 잘되는 옷이 좋겠어요";
	} else {
		comment = "너무 더운 날이에요. 자외선 대비와 수분 섭취에 신경을 써주세요";
	}

	return comment;
}
// 위치정보 비동기통신 끝남 -------------------------------------------

// 위치정보 받아오는함수 시작 -------------------------------------------
// 위치 정보를 받아오는 함수
function showPosition(position) {
	const latitude = position.coords.latitude;
	const longitude = position.coords.longitude;

	getWeatherFromDBs(latitude, longitude); // 위치 정보를 사용하여 날씨 데이터를 받아옴
}

function showError(error) {
	let errorMessage;
	switch (error.code) {
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
	document.getElementById('weather-comment').textContent = errorMessage;
}

// 위치정보 받아오는함수 끝남 -------------------------------------------

// 최근의 날씨 찾기 코드 시작  -------------------------------------------
// 현재 시간을 계산하는 함수
function getCurrentDateTime() {
	const now = new Date();
	const year = now.getFullYear();
	const month = String(now.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요, 2자리 숫자로 변환
	const date = String(now.getDate()).padStart(2, '0'); // 날짜는 2자리 숫자로 변환
	const hours = String(now.getHours()).padStart(2, '0'); // 시간은 2자리 숫자로 변환
	const minutes = String(now.getMinutes()).padStart(2, '0'); // 분은 2자리 숫자로 변환
	const seconds = String(now.getSeconds()).padStart(2, '0'); // 초는 2자리 숫자로 변환
	return `${year}-${month}-${date} ${hours}:${minutes}:${seconds}`;
}

// 오전/오후 시간을 24시간 형식으로 변환하는 함수
function convertTo24Hour(time) {
	const [timePart, period] = time.split(' ');
	let [hour, minute, second] = timePart.split(':').map(Number);

	if (period === '오전' && hour === 12) {
		hour = 0;
	} else if (period === '오후' && hour !== 12) {
		hour += 12;
	}

	return `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
}

// 데이터 배열에서 현재 시간과 가장 가까운 데이터를 찾는 함수
function findNearestData(dataArray, currentDateTime) {
	let nearestData = null;
	let minDifference = Infinity;

	// 데이터 배열이 유효한지 확인
	if (!Array.isArray(dataArray) || dataArray.length === 0) {
		console.error("유효한 데이터 배열이 아닙니다.");
		return null;
	}

	// 현재 시간을 ISO 형식으로 변환
	const currentDateTimeISO = currentDateTime.replace(' ', 'T');

	for (let datas of dataArray) {
		// 데이터의 날짜와 시간을 하나의 문자열로 합치기
		const month = datas.fcstDate.month < 10 ? '0' + datas.fcstDate.month : datas.fcstDate.month;
		const day = datas.fcstDate.day < 10 ? '0' + datas.fcstDate.day : datas.fcstDate.day;
		const fcstTime24Hour = convertTo24Hour(datas.fcstTime);
		const dataDateTime = `${datas.fcstDate.year}-${month}-${day}T${fcstTime24Hour}`;

		// 날짜와 시간 차이 계산
		const dateTimeDifference = Math.abs(new Date(dataDateTime).getTime() - new Date(currentDateTimeISO).getTime());

		// 가장 작은 차이를 가진 데이터 찾기
		if (dateTimeDifference < minDifference) {
			minDifference = dateTimeDifference;
			nearestData = datas;
		}
	}

	return nearestData;
}


// 최근의 날씨 찾기 코드 끝남  -------------------------------------------

// 날씨아이콘 시작  -------------------------------------------

// 조건 설정 (예: 로딩할 이미지가 있는지 확인)
let shouldLoadImage = true; // 예시 조건, 실제로는 여러 조건에 따라 결정할 수 있음


function loadWeatherIcon(iconType) {
	const weatherIconContainer = document.getElementById('weatherIconContainer');

	let imageSrc;
	switch (iconType) {
		case "맑음":
			imageSrc = './icons/맑음.png';
			break;
		case "구름많음":
			imageSrc = './icons/구름많음.png';
			break;
		case "흐림":
			imageSrc = './icons/흐림.png';
			break;
		default:
			console.error('잘못된 아이콘 타입입니다.');
			return; // 잘못된 경우 함수 종료
	}

	// 이미지 로딩
	const imgElement = new Image(); // 새 이미지 요소 생성
	imgElement.src = imageSrc; // 이미지 경로 설정

	// 이미지 로딩 완료 시
	imgElement.onload = function() {
		// 로딩 스피너 숨기기

		loadingSpinner.style.display = 'none';

		// 기존 이미지 삭제 (있을 경우)
		const existingImage = weatherIconContainer.querySelector('img');
		if (existingImage) {
			weatherIconContainer.removeChild(existingImage);
		}

		// 이미지 요소 추가
		weatherIconContainer.appendChild(imgElement);
	};

	// 이미지 로딩 실패 시 처리 (옵션)
	imgElement.onerror = function() {
		console.error('이미지 로딩에 실패했습니다.');
		// 대체 처리 가능
	};
}

// 날씨아이콘 끝남  -------------------------------------------

// 날씨텍스트 시작  -------------------------------------------

// 예시로 텍스트를 변경하는 함수
function updateWeatherText(temperature, weatherInfo) {
	const temperatureSpan = document.getElementById('temperature');
	const weatherInfoSpan = document.getElementById('weatherInfo');

	if (temperatureSpan && weatherInfoSpan) {
		temperatureSpan.textContent = temperature;
		weatherInfoSpan.textContent = weatherInfo;
	} else {
		console.error('해당 id를 가진 요소를 찾을 수 없습니다.');
	}
}

// 날씨텍스트 끝남  -------------------------------------------





