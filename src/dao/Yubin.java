package dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.LoginVO;

public class Yubin {

	private SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	private SqlSession ss;
	
	public LoginVO login(String id) {
		ss = factory.openSession();
		LoginVO login_mem = ss.selectOne("yubin.login", id);
		close();
		return login_mem;
	}

	private void close() {
		if(ss != null) {
			ss.close();
		}
	}
}
