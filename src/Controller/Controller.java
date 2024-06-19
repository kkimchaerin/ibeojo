package Controller;

import java.util.ArrayList;

import Model.DAO;
import Model.DTO;
import javazoom.jl.player.MP3Player;

public class Controller {

	MP3Player mp3 = new MP3Player();

	// 회원가입
	public void F_JOIN(DTO dto) {
		DAO dao = new DAO();
		int cnt = dao.F_JOIN(dto);
		if (cnt > 0) {
			System.out.println("등록 성공!");
		} else {
			System.out.println("등록 실패!");
		}
	}

	// 로그인
	public ArrayList<DTO> F_LOGIN(DTO dto) {
		DAO dao = new DAO();
		ArrayList<DTO> LOGIN_list = dao.F_LOGIN(dto);
		boolean loginSuccess = false;
//      System.out.println(dto.getU_PW());

		if (LOGIN_list.size() != 0) {
			if (LOGIN_list.get(0).getU_ID().equals(dto.getU_ID())
					&& LOGIN_list.get(0).getU_PW().equals(dto.getU_PW())) {
				System.out.println("로그인 성공!");

			}
			return LOGIN_list;
		} else {
			System.out.println("로그인 실패!");
			return null;
		}

	}

	// 아이디 찾기
	public void F_FIND_ID(DTO dto) {

		DAO dao = new DAO();
		String result = dao.F_FIND_ID(dto);
		System.out.println(result);
	}

	// 비밀번호 찾기
	public void F_FIND_PW(DTO dto) {

		DAO dao = new DAO();
		String result = dao.F_FIND_PW(dto);
		System.out.println(result);
	}

	// 회원탈퇴
	public void U_DEL(DTO dto) {

		DAO dao = new DAO();

		int cnt = dao.U_DEL(dto);
		if (cnt > 0) {
			System.out.println("회원 삭제 성공");
		} else {
			System.out.println("회원 삭제 실패");
		}
	}

	/* ============================= 게임 시작 후 ================================= */

	// 노래재생

	public void MS_PLAY(DTO dtoM) {
		DAO dao = new DAO();
		String url = dao.MS_PLAY(dtoM);
		mp3.play(url);
	}

	public void stopMusic() {
		if (mp3.isPlaying()) {
			mp3.stop();
		}
	}

	// 누적_하트
	public ArrayList<Integer> QUIZ_SUM(DTO dtoA) {
		DAO dao = new DAO();
		ArrayList<Integer> sum_heart_re = dao.QUIZ_SUM(dtoA);

		return sum_heart_re;
	}

	public void QUIZ_TF(DTO dtoA) {
		DAO dao = new DAO();
		ArrayList<Integer> sum_heart_re = dao.QUIZ_SUM(dtoA);

		if (sum_heart_re.get(2) == 0) {
			System.out.println("정답입니다!");
		} else {
			System.out.println("틀렸습니다...");
		}

	}


	
	// 점수 넣기
	public void sc_in(DTO dtoin) {
		DAO dao = new DAO();

		int cnt = dao.sc_in(dtoin);

		if (cnt > 0) {
			System.out.println("점수 입력 성공");
		} else {
			System.out.println("점수 입력 실패");
		}
	}
	
	// 랭크
	public void rank() {
		DAO dao = new DAO();
		
		ArrayList<DTO> U_ALL = dao.rank();
		
		for(int i = 0 ; i <U_ALL.size() ; i++) {
			System.out.println(i+1 +"등 " +U_ALL.get(i).getU_NM() +" , " +U_ALL.get(i).getU_SC());
		}
	}



}