package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamSubmitVO;
import vo.ExamVO;
import vo.ProfessorVO;
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
		
		ExamSubmitVO esvo = ss.selectOne("hyeyoon.solveChk",map);
		if(ss != null)
			ss.close();
		
		return esvo;
	}
	
	public String solveChk2(Map map){
		SqlSession ss = factory.openSession();
		
		String a = ss.selectOne("hyeyoon.solveChk2",map);
		if(ss != null)
			ss.close();
		
		return a;
	}
	
	public String chkScore(Map map){
		SqlSession ss = factory.openSession();
		
		String a = ss.selectOne("hyeyoon.chkScore",map);
		if(ss != null)
			ss.close();
		
		return a;
	}

	
	public String quizYN(Map map) {
		SqlSession ss = factory.openSession();
		 
		 String a = ss.selectOne("hyeyoon.quizYN", map);
		 
		 if(ss != null) {
			 ss.close();
		 }
		 
		 return a;
	}
		
	public void insertAns(List<ExamSubmitVO> list) {
		SqlSession ss = factory.openSession();
		
		for(int i=0; i<list.size(); i++) {
			Map<String, String> map = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
		    map.put("e_idx", list.get(i).getE_idx());
		    map.put("st_idx", list.get(i).getSt_idx());
		    map.put("q_idx", list.get(i).getQ_idx());
		    map.put("esu_answer", list.get(i).getEsu_answer());
		    
		    int a = ss.insert("hyeyoon.insertQuizAns", map);
		   
		    if(a > 0) {
		    	System.out.println("insert 성공");
		    	ss.commit();
		    }else {
		    	System.out.println("insert 실패!");
		    	ss.rollback();
		    	}
		}

		Map<String, String> map2 = new HashMap<>(); //인자로 받은 st_idx와 테이블행을 통해 변환한 sb_idx값을 map에 저장
	    map2.put("e_idx", list.get(0).getE_idx());
	    map2.put("st_idx", list.get(0).getSt_idx());
	    map2.put("ej_score", "");
	    
	    int b = ss.insert("hyeyoon.insertEj", map2);
	    if(b > 0) {
	    	System.out.println("insert 성공");
	    	ss.commit();
	    }else {
	    	System.out.println("insert 실패!");
	    	ss.rollback();
	    	}
	    
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
	/* ******************************** Subject 끝 ********************************** */
	
	public void updateMajor(Map map) {
		
		SqlSession ss = factory.openSession();
		int a = ss.update("hyeyoon.updateMajor",map);
	
		if(a > 0) {
			JOptionPane.showMessageDialog(null, "수정 완료되었습니다.", "개인정보 알림", JOptionPane.INFORMATION_MESSAGE);
			ss.commit();
		}
		else {
			JOptionPane.showMessageDialog(null, "정보를 다시 입력해주세요.", "개인정보 알림", JOptionPane.ERROR_MESSAGE);
			ss.rollback();
		}
	
		if(ss != null)
			ss.close();
	
	}

	public void insertMajor(Map map) {
		SqlSession ss = factory.openSession();

	    int b = ss.insert("hyeyoon.insertMajor", map);
	    if(b > 0) {
	    	System.out.println("insert 성공");
	    	ss.commit();
	    }else {
	    	System.out.println("insert 실패!");
	    	ss.rollback();
	    	}
	    
	    if(ss != null)
			ss.close();
		
	}
}
