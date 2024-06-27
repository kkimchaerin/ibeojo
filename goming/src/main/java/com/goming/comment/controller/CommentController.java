package com.goming.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goming.comment.model.CommentDAO;
import com.goming.comment.model.CommentDTO;
import com.goming.weather.model.WeatherDTO;

public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 1 = 패딩, 두꺼운 코트, 목도리, 기모 제품 ~ 4
		// 2 = 코트, 가죽 자켓, 히트텍, 니트, 레깅스 5 ~
		// 3 = 자켓, 트렌치코트, 야상, 니트, 청바지, 스타킹 9 ~
		// 4 = 자켓, 가디건, 야상, 스타킹, 청바지, 면바지 12 ~
		// 5 = 얇은 니트, 맨투맨, 가디건, 청바지 17 ~
		// 6 = 얇은 가디건, 긴팔, 면바지, 청바지 20 ~
		// 7 = 반팔, 얇은 셔츠, 반바지, 면바지 23 ~
		// 8 = 민소매, 반팔, 반바지, 원피스 ~ 28

		HttpSession session = request.getSession();
		/*
		 * WeatherDTO dto = new WeatherDTO(null, null, 0, 0, null, 0, null, null, 0, 0);
		 */
		/* dto = (WeatherDTO) session.getAttribute("locate"); */
		/* CommentDTO d = new CommentDTO(dto.getLat(), dto.getLon()); */
		CommentDAO dao = new CommentDAO();
		/* List<WeatherDTO> weather_info = dao.getWeather(d); */

		/*
		 * double temp = weather_info.get(0).getTemperature();
		 * 
		 * int sel = 1;
		 * 
		 * if (temp >= 4) { sel++; } if (temp >= 9) { sel++; } if (temp >= 12) { sel++;
		 * } if (temp >= 17) { sel++; } if (temp >= 20) { sel++; } if (temp >= 23) {
		 * sel++; } if (temp >= 28) { sel++; }
		 */

		/*
		 * String comment = "no";
		 * 
		 * if (sel <= 1) { comment = "날이 많이 추워요. 추위에 단단히 대비해주세요! 목도리나 장갑을 챙기는 것도 좋아요"; }
		 * else if (sel == 2) { comment =
		 * "다소 쌀쌀한 날씨가 예상됩니다. 자켓이나 코트같은 두꺼운 외투를 챙기는게 좋겠어요"; } else if (sel == 3 || sel
		 * == 4) { comment = "날이 쌀쌀해요. 자켓이나 가디건 같은 외투를 챙겨주세요"; } else if (sel == 5) {
		 * comment = "선선한 날씨에요! 오늘은 입고싶었던 옷을 꺼내보는건 어떨까요?"; } else if (sel == 6) {
		 * comment = "활동하기 좋은 날씨에요! 살짝 더울 수 있으니 주의해주세요"; } else if (sel == 7) { comment
		 * = "약간 더운 날씨가 예상됩니다. 얇고 통풍이 잘되는 옷이 좋겠어요"; } else { comment =
		 * "너무 더운 날이에요. 자외선 대비와 수분 섭취에 신경을 써주세요"; }
		 */

		/*
		 * double rain = Double.parseDouble(weather_info.get(0).getPrecipitation());
		 * String rainper = null;
		 * 
		 * if (rain >= 20) { rainper = "비가 올 것 같아요 우산을 챙겨주세요!"; }
		 * 
		 * request.setAttribute("comment", comment); request.setAttribute("rainper",
		 * rainper);
		 */

		RequestDispatcher dispatcher = request.getRequestDispatcher("Main.jsp");
		dispatcher.forward(request, response);

		response.sendRedirect("Main.jsp");

	}

}
