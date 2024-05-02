package util;

import org.apache.ibatis.session.SqlSession;

import vo.LoginVO;
import vo.ProfessorVO;
import vo.StudentVO;

public class LoginManager {

	public static final String ADMIN = "0";
	public static final String PROFESSOR = "1";
	public static final String STUDENT = "2";
	
	private LoginVO loginMember;
	
	private static class LoginMangerHelper {
		private static final LoginManager INSTANCE = new LoginManager();
	}

	public static LoginManager getInstance() {
		return LoginMangerHelper.INSTANCE;
	}
	
	// 로그인 계정 읽기
	public LoginVO getLoginMember() {
		return loginMember;
	}
	
	// 로그인 계정 저장
	public void setLoginMember(LoginVO login_member) {
		loginMember = login_member;
	}
	
	// 로그인 한 교수 정보 가져오기
	@SuppressWarnings("unlikely-arg-type")
	public ProfessorVO getProfessorInfo() {
		SqlSession ss = MybatisManager.getInstance().getFactory().openSession();
		ProfessorVO professor = null;

		if (loginMember.getChk_role().trim().equals(PROFESSOR)) {
			LoginVO loginMem = ss.selectOne("yubin.getProfessor", loginMember.getLog_idx());
			professor = loginMem.getPvo();
		} else {
			System.out.println("교수 계정 로그인이 아닙니다.");
		}

		if (ss != null) {
			ss.close();
		}
		
		return professor;
	}

	// 로그인 한 학생 정보 가져오기
	@SuppressWarnings("unlikely-arg-type")
	public StudentVO getStudentInfo() {
		SqlSession ss = MybatisManager.getInstance().getFactory().openSession();
		StudentVO student = null;

		if (loginMember.getChk_role().trim().equals(STUDENT)) {
			LoginVO loginMem = ss.selectOne("yubin.getStudent", loginMember.getLog_idx());
			student = loginMem.getStvo();

		} else {
			System.out.println("학생 계정 로그인이 아닙니다.");
		}

		if (ss != null) {
			ss.close();
		}

		return student;
	}

}
