package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamJoinVO;
import vo.ExamSubmitVO;
import vo.ExamVO;
import vo.QuizVO;
import vo.StudentVO;
import vo.SubjectVO;

public class JongDAO {
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	
	// 학생의 정답표 조회
	public List<ExamSubmitVO> examSubmit(String idx){
		SqlSession ss = factory.openSession();
		
		List<ExamSubmitVO> es_list = ss.selectList("jong.exam_submit", idx);
		if(ss != null)
			ss.close();
		
		return es_list;
	}
	
	// 시험 목록조회
	public List<ExamVO> exam(String idx) {
		SqlSession ss = factory.openSession();
		
		List<ExamVO> e_list = ss.selectList("jong.exam_list", idx);
		if(ss != null)
			ss.close();
		
		return e_list;
	}
	
	// 학생 정보 조회
	public StudentVO student() {
		SqlSession ss = factory.openSession();
		
		StudentVO stvo = ss.selectOne("jong.student");
		if(ss != null)
			ss.close();
		
		return stvo;
	}
	
	// 시험 참여자 목록조회
	public List<ExamJoinVO> examJoin(String idx) {
		SqlSession ss = factory.openSession();
		List<ExamJoinVO> e_list = ss.selectList("jong.exam_join", idx);
		
		if(ss != null)
			ss.close();
		
		return e_list;
	}
	
	// 과목 조회
	public SubjectVO subject() {
		SqlSession ss = factory.openSession();
		SubjectVO sbvo = ss.selectOne("jong.subject");
		
		if(ss != null)
			ss.close();
		
		return sbvo;
	}
}
