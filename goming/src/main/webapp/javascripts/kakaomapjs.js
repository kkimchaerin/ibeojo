let fetchWeatherAndSaveToDBs;
let weatherData;
let dbing = false;


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

	// 클릭시 카카오지오코더에서 주소값 가져오기 시작 -------------------------------------------

	// 주소-좌표 변환 객체를 생성합니다
	let geocoder = new kakao.maps.services.Geocoder();

	let marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
		infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

	// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
	searchAddrFromCoords(map.getCenter(), displayCenterInfo);

	// 버튼 엘리먼트 가져오기
	let getInfoButton = document.getElementById('getInfoButton');
	let spinner = document.querySelector('.loading-spinner');

	// 초기에 버튼은 비활성화 상태로 설정
	getInfoButton.disabled = true;
	spinner.style.display = 'none'; 

	let latitude;
	let longitude;

	let addressInput = document.getElementById('addressInput');
	addressInput.addEventListener('keypress', function(e) {

		if (e.key === 'Enter') {
			e.preventDefault(); // 기본 동작 방지

			// Enter 키가 눌렸을 때 주소 검색 실행
			geocoder.addressSearch(addressInput.value, function(result, status) {
				if (status === kakao.maps.services.Status.OK) {
					let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

					// 기존 마커 제거
					marker.setMap(null);
					// 마커 표시
					marker = new kakao.maps.Marker({
						map: map,
						position: coords
					});

					marker.setMap(map);

					let contents = addressInput.value;

					/*console.log(marker.getLat());*/

					latitude = marker.getPosition().getLat();
					longitude = marker.getPosition().getLng();

					contents += '<div>위도 : ' + latitude.toFixed(4) + '</div>';
					contents += '<div>경도 : ' + longitude.toFixed(4) + '</div>';

					// 인포윈도우 표시
					/*					let infowindow = new kakao.maps.InfoWindow({
											content: `<div style="width:150px;text-align:center;padding:6px 0;">${contents}</div>`
										});*/

					infowindow.setContent(contents);
					infowindow.open(map, marker);

					if (dbing == false) {
						// 버튼 활성화
						getInfoButton.disabled = false;

					}


					// 지도 중심 이동
					map.setCenter(coords);
				} else {
					alert('주소를 찾을 수 없습니다.');
				}
			});
		}
	});


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

				if (dbing == false) {
					// 버튼 활성화
					getInfoButton.disabled = false;

				}
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
	// 클릭시 카카오지오코더에서 주소값 가져오기 끝남 -------------------------------------------

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

	// 비동기통신 시작 -------------------------------------------

	// db로 정보올리기 시작 -------------------------------------------
	fetchWeatherAndSaveToDBs = function fetchWeatherAndSaveToDB() {
		spinner.style.display = 'block'; 
		// 버튼 활성화
		dbing = true;
		getInfoButton.disabled = true;
		$.ajax({
			type: 'POST', // HTTP 요청 방식 (POST 추천)
			url: 'WeatherDataUpsertService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
			data: {
				lat: latitude,
				lon: longitude
			}, // 서버로 보낼 데이터 (아이디)
			success: function(response) {
				// 서버에서의 처리가 성공하면 이 함수가 호출됨
				// 아직 내용은 작성중임
				console.log("db 올리기 완료");
				if (response.result === 1) {
					// 성공적으로 데이터베이스에 저장된 경우
					getWeatherFromDBs(); // 추가적인 클라이언트의 처리 로직
				} else {
					
					// 실패한 경우 처리
					alert("DB 업소트가 실패했습니다.");
					// 실패 처리 로직 작성
				}
				spinner.style.display = 'none'; 
			},
			error: function(xhr, status, error) {
				spinner.style.display = 'none'; 
				// 서버에서의 처리가 실패하면 이 함수가 호출됨
				alert("AJAX 호출이 실패했습니다.");
				console.error(xhr, status, error);
			}
		});
	}
	// db로 정보올리기 끝남 -------------------------------------------

	// db로 정보받아오기 시작 -------------------------------------------

	let getWeatherFromDBs = function getWeatherFromDB() {

		$.ajax({
			type: 'POST', // HTTP 요청 방식 (POST 추천)
			url: 'WeatherDataSelectAllService', // 실제 서버에서 아이디 중복 확인을 처리하는 경로
			data: {
				lat: latitude,
				lon: longitude
			},
			success: function(response) {
				// 서버에서의 처리가 성공하면 이 함수가 호출됨
				// 아직 내용은 작성중임
				getWeatherFromDBfunctions(response)
			},
			error: function(xhr, status, error) {
				// 서버에서의 처리가 실패하면 이 함수가 호출됨
				alert("AJAX 호출이 실패했습니다.");
				console.error(xhr, status, error);
			}
		});
	}
	// db로 정보받아오기 끝남 -------------------------------------------

	// db로 받아온 날씨정보로 화면 수정 시작 -------------------------------------------

	/*		1. 날짜		- Data 1-2
			2. 시간		- Data 1-3
			3. 온도		- Title1
			4. 습도		- Title4
			5. 날씨상태	- Title1
			6. 풍속   	- 
			7. 강수확률	- Title2
			8. 강우량		- Title3
			9. 위도와 경도 - Data 1-4*/

	let getWeatherFromDBfunctions = function getWeatherFromDBfunction(response) {
		console.log(response);
		weatherData = response;

		console.log(weatherData.length);

		const table = document.getElementById('daily');


		// 현재 날짜와 시간을 문자열로 가져오기
		const currentDateTimeString = getCurrentDateTime();
		const nearestData = findNearestData(weatherData, currentDateTimeString);

		console.log(nearestData);

		if (nearestData) {
			updateTable(nearestData);
		}

		for (let i = 1; i < weatherData.length; i++) {
			addtable(weatherData[i]);
		}

		dbing = false;
		// 버튼 활성화
		getInfoButton.disabled = false;

		console.log(weatherDatas);
		console.log(days);

		prepareChartDatas(response); // 데이터 가공
		setupCharts();

	}

	let updateTable = function updateTable(weatherData) {
		// 테이블 요소 가져오기
		const table = document.getElementById('daily');

		// 온도
		table.rows[0].cells[0].textContent = `온도: ${weatherData.temperature}°C`;
		// 강수확률
		table.rows[0].cells[1].textContent = `강수확률: ${weatherData.rainy_prob}%`;
		// 강우량
		table.rows[0].cells[2].textContent = `강우량: ${weatherData.precipitation}`;
		// 날씨상태
		table.rows[0].cells[3].textContent = `날씨상태: ${weatherData.weatherInfo}`;

		// 날짜
		const month = weatherData.fcstDate.month < 10 ? '0' + weatherData.fcstDate.month : weatherData.fcstDate.month;
		const day = weatherData.fcstDate.day < 10 ? '0' + weatherData.fcstDate.day : weatherData.fcstDate.day;
		const dateString = `${weatherData.fcstDate.year}-${month}-${day}`;
		document.getElementById('date-cell').textContent = dateString;

		// 시간
		document.getElementById('time-cell').textContent = weatherData.fcstTime;

		// 위도와 경도
		document.getElementById('lat-lon-cell').textContent = `위도: ${weatherData.lat}, 경도: ${weatherData.lon}`;
	}

	let addtable = function addColumn(weatherData) {
		// 테이블 요소 가져오기
		const table = document.getElementById('dynamicTable');

		// thead 행에 셀 추가
		const thead = table.querySelector('thead');
		const headerRow = thead.rows[0];
		const newHeaderCell = document.createElement('th');
		newHeaderCell.textContent = `${weatherData.fcstDate.day}일 ${weatherData.fcstTime}`;
		/*days += `${weatherData.fcstDate.day}일 ${weatherData.fcstTime}`;*/
		headerRow.appendChild(newHeaderCell);

		// tbody 행에 셀 추가
		let index = 0; // 인덱스 변수 초기화
		const tbody = table.querySelector('tbody');
		for (let row of tbody.rows) {
			const newCell = document.createElement('td');

			let contentdata;

			if (index === 0) {
				contentdata = `${weatherData.temperature}`;
				/*weatherDatas += `${weatherData.temperature}`;*/
			} else if (index === 1) {
				contentdata = `${weatherData.rainy_prob}`;
			} else if (index === 2) {
				contentdata = `${weatherData.precipitation}`;
			} else if (index === 3) {
				contentdata = `${weatherData.wind}`;
			} else if (index === 4) {
				contentdata = `${weatherData.humidity}%`;
			}


			newCell.textContent = contentdata;
			row.appendChild(newCell);
			index++;
		}
	}

	// db로 받아온 날씨정보로 화면 수정 끝남 -------------------------------------------

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

	function findNearestData(dataArray, currentDateTime) {
		let nearestData = null;
		let minDifference = Infinity;
		console.log("dataArray : " + dataArray);

		for (let data of dataArray) {
			console.log("읽기");
			// 데이터의 날짜와 시간을 하나의 문자열로 합치기

			const month = data.fcstDate.month < 10 ? '0' + data.fcstDate.month : data.fcstDate.month;
			const day = data.fcstDate.day < 10 ? '0' + data.fcstDate.day : data.fcstDate.day;

			// fcstTime을 24시간 형식으로 변환
			const fcstTime24Hour = convertTo24Hour(data.fcstTime);
			const dataDateTime = `${data.fcstDate.year}-${month}-${day}T${fcstTime24Hour}`;

			console.log("data : " + data);
			console.log("fcstDate : " + data.fcstDate);
			console.log("fcstTime : " + data.fcstTime);
			console.log("fcstTime24Hour : " + fcstTime24Hour);
			console.log("dataDateTime : " + dataDateTime);
			console.log("Date(dataDateTime).getTime() : " + new Date(dataDateTime).getTime());

			const currentDateTimeISO = currentDateTime.replace(' ', 'T');
			// 데이터의 날짜와 현재 시간의 차이 계산
			const dateTimeDifference = Math.abs(new Date(dataDateTime).getTime() - new Date(currentDateTimeISO).getTime());

			console.log("currentDateTime : " + currentDateTime);
			console.log("currentDateTimeISO : " + currentDateTimeISO);
			console.log("Date(currentDateTimeISO).getTime() : " + new Date(currentDateTimeISO).getTime());
			console.log("값입니다" + dateTimeDifference);

			// 가장 작은 차이를 가진 데이터 찾기
			if (dateTimeDifference < minDifference) {
				minDifference = dateTimeDifference;
				nearestData = data;
				console.log("데이터를 최신화함");
			}
		}

		return nearestData;
	}

	// 가장 가까운 데이터 찾기


	// 최근의 날씨 찾기 코드 끝남  -------------------------------------------

	// 온도로 그래프 그리가 시작  -------------------------------------------

	// 기본 날씨 데이터 예시
	let weatherDatas = []; // 온도 데이터 배열
	let days = []; // 날짜 데이터 배열

	let prepareChartDatas = function prepareChartData(weatherData) {
		// 날짜와 온도 데이터 초기화
		weatherDatas = [];
		days = [];

		// 날씨 데이터를 Chart.js가 인식할 수 있는 형태로 변환
		for (let data of weatherData) {
			// 예시: data에서 날짜와 온도 정보 추출
			const date = `${data.fcstDate.year}-${data.fcstDate.month}-${data.fcstDate.day}`;
			const temperature = data.temperature;

			// 날짜와 온도를 데이터 배열에 추가
			days.push(date);
			weatherDatas.push(temperature);
		}
	}

	// Chart.js 그래프 설정
	let setupCharts = function setupChart() {
		const canvas = document.getElementById('weatherGraph');
		const ctx = canvas.getContext('2d');

		const myChart = new Chart(ctx, {
			type: 'line',
			data: {
				labels: days, // X 축 레이블
				datasets: [{
					label: '온도', // 데이터셋 레이블
					data: weatherDatas, // Y 축 데이터
					backgroundColor: 'rgba(54, 162, 235, 0.2)', // 배경색
					borderColor: 'rgba(54, 162, 235, 1)', // 선 색
					borderWidth: 2, // 선 굵기
					pointBackgroundColor: 'rgba(54, 162, 235, 1)', // 데이터 포인트 색상
					pointBorderColor: 'rgba(54, 162, 235, 1)', // 데이터 포인트 테두리 색상
					pointRadius: 5, // 데이터 포인트 반지름
					pointHoverRadius: 7 // 마우스 호버 시 데이터 포인트 반지름
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
	}


	// 온도로 그래프 그리가 끝남  -------------------------------------------



	// 비동기통신 끝남 -------------------------------------------
});


