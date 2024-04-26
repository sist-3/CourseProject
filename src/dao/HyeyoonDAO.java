package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
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
