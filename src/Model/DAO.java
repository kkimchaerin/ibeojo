package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String result = "";

	// 회원가입
	public int F_JOIN(DTO dto) {
		dbOpen();
		int cnt = 0;
		try {
			String sql = "insert into TB_USER values(?, ?, ?, 0 , ?)";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getU_ID());
			psmt.setString(2, dto.getU_PW());
			psmt.setString(3, dto.getU_NM());
			psmt.setInt(4, dto.getU_PH());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql 실행 애러");
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return cnt;
	}

	// 로그인기능
	public ArrayList<DTO> F_LOGIN(DTO dto) {
		ArrayList<DTO> LOGIN_list = new ArrayList<>();
		dbOpen();
		String sql = "SELECT * FROM TB_USER WHERE U_ID = ? AND U_PW = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getU_ID());
			psmt.setString(2, dto.getU_PW());
			rs = psmt.executeQuery();

			while (rs.next()) {
				String U_ID = rs.getString("U_ID");
				String U_PW = rs.getString("U_PW");
				String U_NM = rs.getString("U_NM");
				int U_SC = rs.getInt("U_SC");
				int U_PH = rs.getInt("U_PH");

				dto = new DTO(U_ID, U_PW, U_NM, U_SC, U_PH);

				LOGIN_list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");

			e.printStackTrace();
		} finally {
			dbClose();
		}
		return LOGIN_list;
	}

	// 아이디 찾기
	public String F_FIND_ID(DTO dto) {

		dbOpen();
		String sql = "SELECT * FROM TB_USER WHERE U_NM = ? AND U_PH = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getU_NM());
			psmt.setInt(2, dto.getU_PH());
			rs = psmt.executeQuery();
			if (rs.next()) {
				String U_ID = rs.getString("U_ID");
				result = U_ID;
			}

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		}

		return result;
	}

	// 비밀번호 찾기
	public String F_FIND_PW(DTO dto) {

		dbOpen();
		String sql = "SELECT U_PW FROM TB_USER WHERE U_ID = ? AND U_NM = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getU_ID());
			psmt.setString(2, dto.getU_NM());
			rs = psmt.executeQuery();
			if (rs.next()) {
				String U_PW = rs.getString(1);
				result = U_PW;
			}

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		}

		return result;
	}

	// 회원정보 삭제
	public int U_DEL(DTO dto) { // U_ID, U_PW

		int cnt = 0;
		dbOpen();

		String sql = "DELETE FROM TB_USER WHERE U_ID = ? AND U_PW = ?";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getU_ID());
			psmt.setString(2, dto.getU_PW());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;

	}

	/* =================== 게임 시작 후 ========================= */

	// 음악 재생
	public String MS_PLAY(DTO dtoM) {
		dbOpen();
		int q_index = dtoM.getQ_INDEX();
		ArrayList<DTO> Q_ALL = new ArrayList<>();

		String index_url = null;

		String sql = "SELECT * FROM TB_QUIZ";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String Q_NM = rs.getString("Q_NM");
				String Q_URL = rs.getString("Q_URL");
				int Q_SC = rs.getInt("Q_SC");
				String Q_HI = rs.getString("Q_HI");

				DTO dto = new DTO(Q_NM, Q_URL, Q_SC, Q_HI);

				Q_ALL.add(dto);

			}
			index_url = Q_ALL.get(q_index).getQ_URL();

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return index_url;

	}

	// 문제 정답확인, 누적,하트 반환
	public ArrayList<Integer> QUIZ_SUM(DTO dtoA) {

		int q_index = dtoA.getQ_INDEX();
		ArrayList<DTO> Q_ALL = new ArrayList<>();
		ArrayList<Integer> sum_heart_re = new ArrayList<>();
		String index_nm;
		int index_sc;
		int heart = dtoA.getHeart();
		int sum = dtoA.getSum();
		int tf = 0;

		dbOpen();

		String sql = "SELECT * FROM TB_QUIZ";

		try {

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String Q_NM = rs.getString("Q_NM");
				String Q_URL = rs.getString("Q_URL");
				int Q_SC = rs.getInt("Q_SC");
				String Q_HI = rs.getString("Q_HI");

				DTO dto = new DTO(Q_NM, Q_URL, Q_SC, Q_HI);

				Q_ALL.add(dto);

			}

			index_nm = Q_ALL.get(q_index).getQ_NM();
			index_sc = Q_ALL.get(q_index).getQ_SC();

			if (index_nm.equals(dtoA.getAnswer())) {
				tf = 0;
				sum += index_sc;
			} else {
				heart--;
				tf = 1;

			}
			sum_heart_re.add(0, sum);
			sum_heart_re.add(1, heart);
			sum_heart_re.add(2, tf);

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return sum_heart_re;
	}

	// 점수 입력
	public int sc_in(DTO dtoin) {
		dbOpen();

		int cnt = 0;
		String sql = "update tb_user set u_sc = ? where u_id = ? and ? > u_sc";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, dtoin.getSum());
			psmt.setString(2, dtoin.getU_ID());
			psmt.setInt(3, dtoin.getSum());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return cnt;

	}

	// 랭크보기

	public ArrayList<DTO> rank() {
		dbOpen();

		ArrayList<DTO> U_ALL = new ArrayList<>();

		String sql = "SELECT * FROM TB_USER ORDER BY U_SC DESC";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String U_ID = rs.getString("U_ID");
				String U_PW = rs.getString("U_PW");
				String U_NM = rs.getString("U_NM");
				int U_SC = rs.getInt("U_SC");
				int U_PH = rs.getInt("U_PH");

				DTO dto = new DTO(U_ID, U_PW, U_NM, U_SC, U_PH);

				U_ALL.add(dto);

			}

		} catch (SQLException e) {
			System.out.println("sql 실행 에러");
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return U_ALL;
	}

	// 데이터베이스와의 동적로딩/권한확인
	public void dbOpen() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String id = "campus_24SW_BIG_p1_6";
			String pw = "smhrd6";
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1524:xe";

			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("동적 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("권한 확인 실패");
			e.printStackTrace();
		}

	}

	// 데이터베이스 자원 반납
	public void dbClose() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}