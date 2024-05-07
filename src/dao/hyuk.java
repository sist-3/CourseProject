package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamJoinVO;
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
		ss = factory.openSession();
		
		List<QuizVO> q_list = ss.selectList("hyuk.quizList",e_idx);
		if(ss != null)
			ss.close();
		
		return q_list;
	}
	
	public String getEname(String e_idx) {
		ss = factory.openSession();
		String e_name = ss.selectOne("hyuk.getEname",e_idx);
		if(ss!=null)
			ss.close();
		return e_name;
	}
	
	public List<Map<String, String>> getAssess(String e_idx,String st_idx){
		ss = factory.openSession();
		
		Map<String, String> map = new HashMap<>();
		map.put("e_idx", e_idx);
		map.put("st_idx", st_idx);
		List<Map<String, String>> list = ss.selectList("hyuk.getAssess",map);
		if(ss!=null)
			ss.close();
		return list;
	}
	
	public void deleteAll(String e_idx) {
		ss= factory.openSession();
		ss.delete("hyuk.deleteSubmit");
		ss.commit();
		ss.delete("hyuk.deleteAll", e_idx);
		ss.commit();
		if(ss!=null)
			ss.close();
	}
	
	public void add_Score(ExamJoinVO vo) {
		ss = factory.openSession();
		ss.insert("hyuk.add_score",vo);
		ss.commit();
		if(ss!=null)
			ss.close();
		
	}
	
	public void update_Score(ExamJoinVO vo) {
		ss = factory.openSession();
		ss.update("hyuk.updateExamJoin",vo);
		ss.commit();
		if(ss!=null)
			ss.close();
		
	}
	public List<ExamJoinVO> getExamJoin(String e_idx,String st_idx){
		ss = factory.openSession();
		
		Map<String, String> map = new HashMap<>();
		map.put("e_idx", e_idx);
		map.put("st_idx", st_idx);
		List<ExamJoinVO> list = ss.selectList("hyuk.getExamJoin",map);
		if(ss!=null)
			ss.close();
		return list;
	}

}
