package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.StudentVO;
import vo.SubjectVO;

public class gummoDAO {
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();

	// 학생 관리 페이지
	public int addStudent(StudentVO vo) {
		SqlSession ss = factory.openSession();

		int cnt = ss.insert("gummo.add_student", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		if (ss != null)
			ss.close();
		return cnt;

	}

	public int updateStudent(StudentVO vo) {
		SqlSession ss = factory.openSession();

		try {
			int cnt = ss.update("gummo.update_student", vo); 
			if (cnt > 0) {
				ss.commit();

			} else {
				ss.rollback();

			}
			return cnt;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}

	public boolean deleteStudent(StudentVO vo) {
		SqlSession ss = factory.openSession();
		try {
			int cnt = ss.delete("gummo.delete_student", vo);
			if (cnt > 0) {
				ss.commit();
				return true;
			} else {
				ss.rollback();
				return false;
			}
		} finally {
			ss.close();
		}
	}

	// 과목 관리 페이지

	public int addSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();

		int cnt = ss.insert("gummo.add_subject", vo);
		if (cnt > 0)
			ss.commit();
		else
			ss.rollback();

		if (ss != null)
			ss.close();
		return cnt;

	}

	public int updateSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();

		try {
			int cnt = ss.update("gummo.update_subject", vo); 
			if (cnt > 0) {
				ss.commit();

			} else {
				ss.rollback();

			}
			return cnt;
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}

	public boolean deleteSubject(SubjectVO vo) {
		SqlSession ss = factory.openSession();
		try {
			int cnt = ss.delete("gummo.delete_subject", vo);
			if (cnt > 0) {
				ss.commit();
				return true;
			} else {
				ss.rollback();
				return false;
			}
		} finally {
			ss.close();
		}
	}
	
	

}