package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamSubmitVO;
import vo.ExamVO;
import vo.QuizVO;
import vo.SubjectVO;

public class HyeyoonDAO {
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	
	/* ******************************** Quiz 시작 ********************************** */
	public List<QuizVO> quizList(String e_idx) {
		SqlSession ss = factory.openSession();
		
		List<QuizVO> q_list = ss.selectList("hyeyoon.quizList",e_idx);
		if(ss != null)
			ss.close();
		
		return q_list;
	}

	
	/* ******************************** Quiz 끝 ********************************** */
	
	/* ******************************** Exam 시작 ********************************** */
	
	public List<ExamVO> examList(Map map){
		SqlSession ss = factory.openSession();
		
		List<ExamVO> e_list = ss.selectList("hyeyoon.examList",map);
		if(ss != null)
			ss.close();
		
		return e_list;
	}
	
	public ExamSubmitVO solveChk(Map map){
		SqlSession ss = factory.openSession();
		
		ExamSubmitVO esu_answer = ss.selectOne("hyeyoon.solveChk",map);
		if(ss != null)
			ss.close();
		
		return esu_answer;
	}
	
	public void insertAns(String e_idx, String st_idx, String q_idx, String esu_answer ) {
		SqlSession ss = factory.openSession();
	    Map<String, String> map = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
	    map.put("e_idx", e_idx);
	    map.put("st_idx", st_idx);
	    map.put("q_idx", q_idx);
	    map.put("esu_answer", esu_answer);
	          
	    ss.insert("hyeyoon.insertQuizAns", map);
	    System.out.println("insert 성공!");
		
		if(ss != null)
			ss.close();
		
	}
	
	public void updateAns(String e_idx, String st_idx, String q_idx, String esu_answer ) {
		SqlSession ss = factory.openSession();
	    Map<String, String> map = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
	    map.put("e_idx", e_idx);
	    map.put("st_idx", st_idx);
	    map.put("q_idx", q_idx);
	    map.put("esu_answer", esu_answer);
	          
	    ss.update("hyeyoon.updateQuizAns", map);
	    System.out.println("update 성공!");
		
		if(ss != null)
			ss.close();
		
	}
	
	/* ******************************** Exam 끝 ********************************** */
	/* ******************************** Subject 시작 ********************************** */
	public SubjectVO getSubject(String sb_idx) {
		SqlSession ss = factory.openSession();
		
		SubjectVO sbvo = ss.selectOne("hyeyoon.getSubject",sb_idx);
		if(ss != null)
			ss.close();
		
		return sbvo;
	}
	/* ******************************** Subject 시작 ********************************** */
}
