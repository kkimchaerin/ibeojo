package com.goming.weather.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;



public class InputData
{
		// 계산용 변수들
	 	private static final double RE = 6371.00877; // 지구 반경(km)
	    private static final double GRID = 5.0; // 격자 간격(km)
	    private static final double SLAT1 = 30.0; // 투영 위도1(degree)
	    private static final double SLAT2 = 60.0; // 투영 위도2(degree)
	    private static final double OLON = 126.0; // 기준점 경도(degree)
	    private static final double OLAT = 38.0; // 기준점 위도(degree)
	    private static final double XO = 43; // 기준점 X좌표(GRID)
	    private static final double YO = 136; // 기준점 Y좌표(GRID)

		private String jsonData;
	    
		// 좌표-위경도간의 변환을 계산한다
	    public CoordinateDTO dfs_xy_conv(boolean code, double v1, double v2) {
	        double DEGRAD = Math.PI / 180.0;
	        double RADDEG = 180.0 / Math.PI;

	        double re = RE / GRID;
	        double slat1 = SLAT1 * DEGRAD;
	        double slat2 = SLAT2 * DEGRAD;
	        double olon = OLON * DEGRAD;
	        double olat = OLAT * DEGRAD;

	        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	        ro = re * sf / Math.pow(ro, sn);
	        CoordinateDTO rs = new CoordinateDTO();

	        if (code) {
	            rs.lat = v1;
	            rs.lng = v2;
	            double ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
	            ra = re * sf / Math.pow(ra, sn);
	            double theta = v2 * DEGRAD - olon;
	            if (theta > Math.PI) theta -= 2.0 * Math.PI;
	            if (theta < -Math.PI) theta += 2.0 * Math.PI;
	            theta *= sn;
	            rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
	            rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
	        } else {
	            rs.x = v1;
	            rs.y = v2;
	            double xn = v1 - XO;
	            double yn = ro - v2 + YO;
	            double ra = Math.sqrt(xn * xn + yn * yn);
	            if (sn < 0.0) ra = -ra;
	            double alat = Math.pow((re * sf / ra), (1.0 / sn));
	            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

	            double theta;
	            if (Math.abs(xn) <= 0.0) {
	                theta = 0.0;
	            } else {
	                if (Math.abs(yn) <= 0.0) {
	                    theta = Math.PI * 0.5;
	                    if (xn < 0.0) theta = -theta;
	                } else {
	                    theta = Math.atan2(xn, yn);
	                }
	            }
	            double alon = theta / sn + olon;
	            rs.lat = alat * RADDEG;
	            rs.lng = alon * RADDEG;
	        }
	        rs.toString();
	        return rs;
	    }
	
	    
	    // 좌표값을 받아서 날씨정보가 담긴 json으로 바꾼다
		public String dataout(String nx, String ny) throws IOException
		{
			System.out.println("ApiExplorer2 : 진입");

			LocalDate today = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String getCurrentDateFormatted = today.format(formatter);
			
			// API 요청 URL 설정
			StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /* URL */

			// 각 파라미터 추가
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=OTFDySiLkuQWdO6RvJqpQdxLVPgX6t2e7g2mRPMQIfyyetvMHWIj58Z5NIGG23x9%2BszbbGkMMlU79a07HC%2BjXw%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("1000", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "="
					+ URLEncoder.encode("XML", "UTF-8")); /* 요청자료형식(XML/JSON)Default: XML */
			urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "="
					+ URLEncoder.encode(getCurrentDateFormatted, "UTF-8")); /* 기준 날짜 */
			urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode("0500", "UTF-8")); /* 기준 시간 */
			urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(nx), "UTF-8")); /* 예보지점 X 좌표 */
			urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ny), "UTF-8")); /* 예보지점 Y 좌표 */

			URL url = new URL(urlBuilder.toString());

			// HTTP 연결 설정
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// HTTP 응답 코드 확인
//	        System.out.println("Response code: " + conn.getResponseCode());

			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
			{
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else
			{
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			// 응답 데이터 읽기
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null)
			{
				sb.append(line);
			}
			System.out.println("ApiExplorer2 : "+nx);
			System.out.println("ApiExplorer2 : "+ny);
			
			rd.close();
			conn.disconnect();

			// 사용자 홈 디렉토리 확인
			String userHome = System.getProperty("user.home");
			System.out.println("ApiExplorer2 : User home directory: " + userHome);

			// XML을 JsonNode로 변환
			ObjectMapper xmlMapper = new XmlMapper();
			JsonNode jsonNode = xmlMapper.readTree(sb.toString());

			// JsonNode를 JSON 문자열로 변환
			String jsonString = jsonNode.toString();
			System.out.println("ApiExplorer2 : Converted JSON: " + jsonString.length());



			// 콘솔에 출력 (옵션)
			System.out.println("ApiExplorer2 : jsonString : " + jsonString.length());
			return jsonString;
		}

		// json으로 카테고리의 값을 찾아낸다
		public List<String> getForecastValues(String targetDate) throws IOException
		{
			List<String> forecastValues = new ArrayList<>();

			// 입력 값 검증
			if (targetDate == null || targetDate.isEmpty())
			{
				System.out.println("TemperatureExtractor: targetDate parameter is required");
				throw new IllegalArgumentException("targetDate parameter is required");
			}

			// JSON 데이터를 JsonNode로 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(this.jsonData);

			// 필요한 노드로 이동
			JsonNode itemsNode = rootNode.path("body").path("items").path("item");

			// 아이템 노드가 배열인지 확인하고, 배열일 경우 순회하면서 필터링
			if (!itemsNode.isArray())
			{
				System.out.println("TemperatureExtractor: itemsNode가 배열이 아닙니다 ");
				return null; // 빈 리스트 반환
			}

			// 순회하기
			for (JsonNode itemNode : itemsNode)
			{
				JsonNode fcstDateNode = itemNode.path("category");
				if (fcstDateNode.isMissingNode() || !fcstDateNode.asText().equals(targetDate))
				{
					continue; // fcstDate가 없거나 targetDate와 일치하지 않으면 다음 항목으로 넘어감
				}

				// fcstValue 값을 추출하여 리스트에 추가
				JsonNode fcstValueNode = itemNode.path("fcstValue");
				if (!fcstValueNode.isMissingNode())
				{
					forecastValues.add(fcstValueNode.asText());
//		            System.out.println("TemperatureExtractor: "+itemNode.asText());
//		            System.out.println("TemperatureExtractor: "+fcstValueNode.asText());
				}

			}

//		    System.out.println("TemperatureExtractor:"+forecastValues.get(0));

			// 리스트를 반환
			return forecastValues;
		}

		// json으로 찾아낸 카테고리값과 아이템 값으로 날씨 DTO리스트를 만든다
		public List<WeatherDTO> sumdata() throws IOException
		{
			// 리턴할 날씨클래스의 리스트
			List<WeatherDTO> sum = new ArrayList<>();

			// 예측날짜
			List<String> fcstDate = getCurrentTemperature(0);
			// 예측시간
			List<String> fcstTime = getCurrentTemperature(1);
			// x좌표
			List<String> nx = getCurrentTemperature(2);
			// y좌표
			List<String> ny = getCurrentTemperature(3);

			// 값들
			List<String> SKY = getForecastValues("SKY");
			List<String> WSD = getForecastValues("WSD");
			List<String> PCP = getForecastValues("PCP");
			List<String> POP = getForecastValues("POP");
			List<String> TMP = getForecastValues("TMP");
			List<String> REH = getForecastValues("REH");

			if (TMP.size() != REH.size() || TMP.size() != fcstDate.size())
			{
				System.out.println("TemperatureExtractor : " + "값의길이가 잘못되어있습니다" + TMP + ", " + REH);
				return null;
			}

			System.out.println("TemperatureExtractor : 값들출력시작");
			System.out.println("fcstDate.size()" + fcstDate.size());
			System.out.println("fcstTime.size()" + fcstTime.size());
			System.out.println("nx.size()" + nx.size());
			System.out.println("ny.size()" + ny.size());
			System.out.println("SKY.size()" + SKY.size());
			System.out.println("WSD.size()" + WSD.size());
			System.out.println("PCP.size()" + PCP.size());
			System.out.println("POP.size()" + POP.size());
			System.out.println("TMP.size()" + TMP.size());
			System.out.println("REH.size()" + REH.size());

			for (int i = 0; i < TMP.size(); i++)
			{
				float lat = 0;
				float lon = 0;

				CoordinateDTO cond = dfs_xy_conv(false, Float.parseFloat(nx.get(i)), Float.parseFloat(ny.get(i)));

				lat = (float) cond.getLat();
				lon = (float) cond.getLng();


				String skyinput = "";

				switch (SKY.get(i))
				{
				case "1":
					skyinput = "맑음";
					break;
				case "3":
					skyinput = "구름많음";
					break;
				case "4":
					skyinput = "흐림";
					break;

				default:
					skyinput = "예외상황";
					break;
				}
				
				System.out.println("InputData : 변환중");
				System.out.println("InputData : 변환중" + fcstDate.get(i));
				System.out.println("InputData : 변환중" + fcstTime.get(i));
				String dates = formatDateString(fcstDate.get(i));
				String times = formatTimeString(fcstTime.get(i));
				System.out.println("InputData : 변환중" + dates);
				System.out.println("InputData : 변환중" + times);
				WeatherDTO weather = new WeatherDTO(dates, times, Float.parseFloat(TMP.get(i)),
						Float.parseFloat(REH.get(i)), skyinput, Float.parseFloat(WSD.get(i)), POP.get(i),
						PCP.get(i), lat, lon);

				sum.add(weather);

			}

			sum.get(0).weatherString();
			sum.get(0).weatherString();

			System.out.println("TemperatureExtractor : " + sum.size());

			return sum;
		}

		// json으로 아이템 값을 찾아낸다
		public List<String> getCurrentTemperature(int fdate) throws IOException
		{
			List<String> result = new ArrayList<>();

			// JSON 데이터를 JsonNode로 파싱
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(this.jsonData);

			// 필요한 노드로 이동
			JsonNode itemsNode = rootNode.path("body").path("items").path("item");

			// 아이템 노드가 배열인지 확인하고, 배열일 경우 순회하면서 필터링
			if (!itemsNode.isArray())
			{
				System.out.println("TemperatureExtractor : " + "itemsNode가 배열이 아닙니다 ");
				return null;
			}

			// 순회하기
			for (JsonNode itemNode : itemsNode)
			{
				// fcstDate가 존재하는지 확인하고, targetDate와 일치하는 항목을 찾기
				JsonNode fcstDateNode = itemNode.path("category");
				if (fcstDateNode.isMissingNode())
				{
					System.err.println("TemperatureExtractor : data is missing in item: " + itemNode.toString());
					break;
				} else if (fcstDateNode.asText().equals("TMP"))
				{
					if (fdate == 0)
					{
						result.add(itemNode.path("fcstDate").asText());
					} else if (fdate == 1)
					{
						result.add(itemNode.path("fcstTime").asText());
					} else if (fdate == 2)
					{
						result.add(itemNode.path("nx").asText());
					} else
					{
						result.add(itemNode.path("ny").asText());
					}

				}
			}

			return result;
		}

		// 전부 종합하여 위경도로 DTO리스트를 반환한다
		public List<WeatherDTO> getmasterdate(String nx, String ny)
		{
			System.out.println("Masterdate : ");
			// 일단 시작해서 위도 경도를 변환하여 좌표값으로 바꾼다
			CoordinateDTO rs = dfs_xy_conv(true, Double.parseDouble(nx), Double.parseDouble(ny));
			// 초기화하기
			
			// 좌표를 정수값의 문자열로 바꾼다
			String x = String.format("%.0f", rs.getX());
			String y = String.format("%.0f", rs.getY());
			
			try
			{
				// 좌표값을 받아서 json을 받아온다
				// 그리고 전역변수에 저장한다
				jsonData = (dataout(x, y));
			} catch (IOException e)
			{
				System.out.println("masterdate : " + "데이터 초기화 실패!");
				e.printStackTrace();
			}
			
			// 리턴용 데이터 생성
			List<WeatherDTO> returnlist = new ArrayList<WeatherDTO>();
			
			// 
			try
			{
				// 반환용 데이터에 json으로 아이템과 카테고리벨류를 찾아서 리스트를 반환하는 친구에게 리턴을 받는다
				returnlist = sumdata();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(returnlist.size());
			
			return returnlist;
					
		}
	    
		
	    private DateTimeFormatter dateFormatterInput = DateTimeFormatter.ofPattern("yyyyMMdd");
	    private DateTimeFormatter dateFormatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    private DateTimeFormatter timeFormatterInput = DateTimeFormatter.ofPattern("HHmm");
	    private DateTimeFormatter timeFormatterOutput = DateTimeFormatter.ofPattern("HH:mm:ss");

	    public String formatDateString(String inputDateStr) {
	        if (inputDateStr == null || inputDateStr.isEmpty()) {
	            return null;
	        }

	        try {
	            LocalDate date = LocalDate.parse(inputDateStr, dateFormatterInput);
	            return date.format(dateFormatterOutput);
	        } catch (Exception e) {
	            // 처리할 수 있는 기본값 또는 예외 처리 추가
	            e.printStackTrace();
	            return null;
	        }
	    }

	    public String formatTimeString(String inputTimeStr) {
	        if (inputTimeStr == null || inputTimeStr.isEmpty()) {
	            return null;
	        }

	        try {
	            LocalTime time = LocalTime.parse(inputTimeStr, timeFormatterInput);
	            return time.format(timeFormatterOutput);
	        } catch (Exception e) {
	            // 처리할 수 있는 기본값 또는 예외 처리 추가
	            e.printStackTrace();
	            return null;
	        }
	    }
	    
}
// 현재 코드가 응집도도 낮고 결합도가 높아서 굉장히 스파게티 코드이다
// 이를 해결하기 위해선 일단 클래스들이 하는 일을 찾아야 한다

// 1. Coordinate :
// 2의 계산이 완료 된 후 값을 리턴할때 쓰이는 클래스이다.


// 2. CoordinateConverter :
// 몇가지 계산을 끝내고 값을 1로 넘겨주는 클래스이다
// input : boolean code, double v1, double v2
// return : Coordinate

// 3. ApiExplorer2 :
// 위도 경도를 입력받아서 해당 좌표에 따른 날씨 정보값들을 api로 받아온다
// 카카오에서는 위도경도를 주기 때문에 이 클래스의 매개변수는 1, 2로 변형된 nx, ny가 필요하다
// input : String nx, String ny
// return : String(json형태를 하고있다)

// 4. TemperatureExtractor :
// 3의 문자열들을 DB에 맞게 변형하여 정리해서 DTO리스트로 만들어주는 클래스이다
// input :
// 		public TemperatureExtractor(String jsonData)(생성자)
//		public List<String> getForecastValues(String targetDate)
//		public List<String> getCurrentTemperature(int fdate)

// 5. Masterdate :
// 위 3가지의 계산클래스들을 서로 묶어주는 역할을 한다.

// ApiExplorer2 api;
// TemperatureExtractor temp;

// input :
//		public List<WeatherInfo> getmasterdate(String nx, String ny)

// 일단 1번은 DTO의 형태를 하고있고
// 2, 3번은 함수의 형태를 하고 있다 2번에서 기본 전역변수가 있긴 하지만 큰 문제는 없다
// 5번은 이전의 클래스들을 다 잘 정리 한다면 굳이 필요 없어지고

// 결국 4번클래스를 잘 다듬어서 23번 클래스를 집어 넣고 1번을 DTO로 만들면 1개의 DAO로 해결된다