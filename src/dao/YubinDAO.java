package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamJoinVO;
import vo.ExamVO;
import vo.LoginVO;
import vo.StudentSubjectVO;
import vo.SubjectVO;

public class YubinDAO {

	private SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private SqlSession ss;
	
	// id를 인자로 받아 pw, role를 받는 기능
	public LoginVO login(String id) {
		ss = factory.openSession();
		LoginVO login_mem = ss.selectOne("yubin.login", id);
		close();
		return login_mem;
	}
	
	public List<SubjectVO> getSubjectList(String idx) {
		ss = factory.openSession();
		List<SubjectVO> subjectList = ss.selectList("yubin.getSubject", idx);
		close();
		return subjectList;
	}
	
	public List<ExamVO> getExamList(String idx) {
		ss = factory.openSession();
		List<ExamVO> subjectList = ss.selectList("yubin.getExam", idx);
		close();
		return subjectList;
	}
	
	public List<StudentSubjectVO> getStudentSubjectList(String idx) {
		ss = factory.openSession();
		List<StudentSubjectVO> studentSubjectList = ss.selectList("yubin.getStudentSubject", idx);
		close();
		return studentSubjectList;
	}
	
	public List<ExamJoinVO> getExamJoinList(String idx) {
		ss = factory.openSession();
		List<ExamJoinVO> subjectList = ss.selectList("yubin.getExamJoin", idx);
		close();
		return subjectList;
	}

	private void close() {
		if(ss != null) {
			ss.close();
		}
	}
}
