package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.QuizVO;

public class hyuk {
	private SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private SqlSession ss;
	
	public void addQuiz(QuizVO qvo) {
		ss= factory.openSession();
		ss.insert("hyuk.add_exam",qvo);
		ss.commit();
		if(ss!=null)
			ss.close();
		
	}
	
	public List<QuizVO> quizList(String e_idx) {
		SqlSession ss = factory.openSession();
		
		List<QuizVO> q_list = ss.selectList("hyuk.quizList",e_idx);
		if(ss != null)
			ss.close();
		
		return q_list;
	}
}
