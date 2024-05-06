package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.MybatisManager;
import vo.ExamJoinVO;
import vo.ExamSubmitVO;
import vo.ExamVO;
import vo.StudentVO;
import vo.SubjectVO;

public class JongDAO {
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();

	// 학생의 정답표 조회
	public List<ExamSubmitVO> examSubmit(String idx) {
		SqlSession ss = factory.openSession();

		List<ExamSubmitVO> es_list = ss.selectList("jong.exam_submit", idx);
		if (ss != null)
			ss.close();

		return es_list;
	}

	// 교수정보로 과목 목록 출력
	public List<SubjectVO> subjectList(String idx) {
		SqlSession ss = factory.openSession();

		List<SubjectVO> s_list = ss.selectList("jong.subject", idx);
		if (ss != null)
			ss.close();

		return s_list;
	}

	// 시험 목록조회
	public List<ExamVO> exam(String idx) {
		SqlSession ss = factory.openSession();

		List<ExamVO> e_list = ss.selectList("jong.exam_list", idx);
		if (ss != null)
			ss.close();

		return e_list;
	}

	// exam_name_idx 과목시험 이름으로 인덱스 가져오기
	public String examNameIdx(String name, String idx) {
		SqlSession ss = factory.openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("e_name", name);
		map.put("sb_idx", idx);

		ExamVO evo = ss.selectOne("jong.exam_name_idx", map);
		
		if (ss != null)
			ss.close();

		return evo.getE_idx();
	}

	// exam_name_idx_delete 학번과
	public void examNameIdxDelete(String idx) {
		SqlSession ss = factory.openSession();

		int result = ss.delete("jong.exam_name_idx_delete", idx);

		if (result > 0) {
			ss.commit();
			JOptionPane.showMessageDialog(null, "삭제완료");
		} else {
			ss.rollback();
			JOptionPane.showMessageDialog(null, "삭제실패");
		}

		if (ss != null)
			ss.close();
	}

	// student_num_idx 학번으로 학생 인덱스 조회
	public String studentNumIdx(String num) {
		SqlSession ss = factory.openSession();

		StudentVO svo = ss.selectOne("jong.student_num_idx", num);
		if (ss != null)
			ss.close();

		return svo.getSt_idx();
	}

	// 학생 정보 조회
	public StudentVO student() {
		SqlSession ss = factory.openSession();

		StudentVO stvo = ss.selectOne("jong.student");
		if (ss != null)
			ss.close();

		return stvo;
	}
	
	// 학생 정보 조회
	public StudentVO[] searchStudent(String type, String value, String sb_idx, String e_idx) {
		StudentVO[] ar = null;
		SqlSession ss = factory.openSession();

		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", type);
		map.put("searchValue", value);
		map.put("sb_idx", sb_idx);
		map.put("e_idx", e_idx);
		List<StudentVO> list = ss.selectList(
				"jong.search_student", map);
		if(list != null) {
			ar = new StudentVO[list.size()];
			list.toArray(ar);
		}
		if (ss != null)
			ss.close();

		return ar;
	}

	// 시험 참여자 목록조회
	public List<ExamSubmitVO> examJoin(String idx) {
		SqlSession ss = factory.openSession();
		List<ExamSubmitVO> e_list = ss.selectList("jong.exam_join", idx);

		if (ss != null)
			ss.close();

		return e_list;
	}

	// 시험 추가
	public void addExam(String sb_idx, String p_idx, String e_name, String e_date, String e_yn) {
		SqlSession ss = factory.openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("sb_idx", sb_idx);
		map.put("p_idx", p_idx);
		map.put("e_name", e_name);
		map.put("e_date", e_date);
		map.put("e_yn", e_yn);
		
		int result = ss.insert("jong.exam_add", map);
		
		if(result > 0) {
			ss.commit();
			JOptionPane.showMessageDialog(null, "추가완료");
		}else {
			ss.rollback();
			JOptionPane.showMessageDialog(null, "추가실패");
		}

		if (ss != null)
			ss.close();
	}
}
