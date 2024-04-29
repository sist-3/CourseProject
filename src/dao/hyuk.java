package dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;

public class hyuk {
	private SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private SqlSession ss;
	
	public void addQuiz(Map map) {
		ss= factory.openSession();
		ss.insert("hyuk.add_exam",map);
	}
}
