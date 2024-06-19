package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.Controller;
import Model.DTO;
import javazoom.jl.player.MP3Player;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("명대사 듣고 영화, 드라마 제목 맞추기");
		while (true) {
			System.out.print("[1]회원가입 [2]로그인 [3]아이디찾기 [4]비밀번호찾기 [5]회원탈퇴 [6]게임나가기 ");
			int select = sc.nextInt();
			if (select == 1) {

				// 회원가입
				System.out.println("==== 회원 등록 ====");
				System.out.print("이름 >> ");
				String U_NM = sc.next();
				System.out.print("ID >> ");
				String U_ID = sc.next();
				System.out.print("PW >> ");
				String U_PW = sc.next();
				System.out.print("휴대폰 번호끝 4자리 >> ");
				int U_PH = sc.nextInt();

				DTO dto = new DTO(U_ID, U_PW, U_NM, 0, U_PH);
				Controller con = new Controller();

				con.F_JOIN(dto);

			} else if (select == 2) {
				// 로그인

				boolean isLogin = true;
				// 로그인-F_LOGIN()
				System.out.print("ID를 입력하세요 : ");
				String U_ID = sc.next();
				System.out.print("PW를 입력하세요 : ");
				String U_PW = sc.next();

				DTO dto = new DTO(U_ID, U_PW, null, 0, 0);

				Controller con = new Controller();

				ArrayList<DTO> user = con.F_LOGIN(dto);
				int sum = 0;
				if (user != null) {
					isLogin = false;
					System.out.print("[1]게임시작 [2]랭킹보기 [3]게임종료 ");
					int gameMenu = sc.nextInt();

					// 게임시작
					if (gameMenu == 1) {

						// 문제 시작

						MP3Player mp3 = new MP3Player();

						int Q_INDEX = 0;
						int heart = 3;

						while (Q_INDEX == 10 || heart != 0) {
							System.out.println(Q_INDEX + 1 + "번 문제");
							DTO dtoM = new DTO(Q_INDEX, 0, 0, null);
							con.MS_PLAY(dtoM);

							System.out.print("정답 입력(띄어쓰기 없이 입력) : ");
							String answer = sc.next().trim();

							DTO dtoA = new DTO(Q_INDEX, sum, heart, answer);
							con.QUIZ_SUM(dtoA);
							Q_INDEX++;

							// 누적값, 목숨 받아오기
							ArrayList<Integer> sum_heart_re = con.QUIZ_SUM(dtoA);
							sum = sum_heart_re.get(0);
							heart = sum_heart_re.get(1);

							con.QUIZ_TF(dtoA);

//							if (Q_INDEX == 10) {
//								break;
//							}

						}
						System.out.println();
						System.out.println("하트소진으로 게임 종료");
						System.out.println("점수 : " + sum);

						// 개인점수 업데이트

						DTO dtoin = new DTO(sum, U_ID);
						con.sc_in(dtoin);

						System.out.println("== 랭킹 ==");
						con.rank();

						// 랭킹보기
					} else if (gameMenu == 2) {
						con.rank();

					} else {

						break;
					}

					break;

				} else {
					isLogin = true;
				}

				// 랭킹보기_코드

			} else if (select == 3) {
				// 아이디찾기
				System.out.println("===== 아이디 찾기 =====");

				System.out.print("이름 입력 >> ");
				String U_NM = sc.next();
				System.out.print("폰번호 뒤 4자리 입력 >> ");
				int U_PH = sc.nextInt();
				String U_PW = null;
				int U_SC = 0;
				String U_ID = null;

				DTO dto = new DTO(U_ID, U_PW, U_NM, U_SC, U_PH);
				Controller con = new Controller();
				con.F_FIND_ID(dto);
			} else if (select == 4) {
				// 비밀번호찾기
				System.out.println("===== 비밀번호 찾기 =====");

				System.out.print("아이디 입력 >> ");
				String U_ID = sc.next();
				System.out.print("이름 입력 >> ");
				String U_NM = sc.next();
				String U_PW = null;
				int U_SC = 0;
				int U_PH = 0;

				DTO dto = new DTO(U_ID, U_PW, U_NM, U_SC, U_PH);
				Controller con = new Controller();
				con.F_FIND_PW(dto);
			} else if (select == 5) {

				// 회원탈퇴 - U_DEL()
				System.out.println("회원 탈퇴 진행");

				System.out.print("아이디 입력 : ");
				String U_ID = sc.next();
				System.out.print("비밀번호 입력 : ");
				String U_PW = sc.next();

				DTO dto = new DTO(U_ID, U_PW);

				Controller con = new Controller();
				con.U_DEL(dto);

			} else {
				// 게임나가기
				System.out.println("게임 종료");
				break;
			}

		}

	}

}