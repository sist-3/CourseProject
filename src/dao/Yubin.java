package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.StudentVO;

public class Yubin {

	public List<StudentVO> test() {
		SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
		SqlSession ss = factory.openSession();
		
		List<StudentVO> vo;
		
		vo = ss.selectList("yubin.student");
		
		for(StudentVO student : vo) {
			System.out.println(student.getSt_idx());
		}
		if(ss != null) {
			ss.close();
		}
		return vo; 
	}
}
