package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.javassist.bytecode.Opcode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import page.ProfessorManagementPage;
import page.StudentSubjectManagementPage;
import util.MybatisManager;
import vo.MajorVO;
import vo.ProfessorVO;
import vo.StudentSubjectVO;
import vo.StudentVO;
import vo.SubjectVO;

public class jeong2_DAO {
	SqlSessionFactory factory = MybatisManager.getInstance().getFactory();
	
	//----- StudentMyPage 학생 개인정보 페이지 -----//
	
	public StudentVO StudentMyPageDAO(String n) {
		
		SqlSession ss = factory.openSession();
		StudentVO vo = ss.selectOne("jeong2.getStudentMyPage",n); //n = st_idx
		
		if(ss!=null)
			ss.close();
		
		return vo;
	}
	
	public void StudentMyPageUpdate(StudentVO vo) {
		
		SqlSession ss = factory.openSession();
		int a = ss.update("jeong2.updateStudentMyPage",vo);
		
		if(a > 0) {
			JOptionPane.showMessageDialog(null, "수정 완료되었습니다.", "개인정보 알림", JOptionPane.INFORMATION_MESSAGE);
			ss.commit();
		}
		else {
			JOptionPane.showMessageDialog(null, "정보를 다시 입력해주세요.", "개인정보 알림", JOptionPane.ERROR_MESSAGE);
		    ss.rollback();
		}
		
		if(ss != null)
			ss.close();
	}//------------------------------------//
	
	
	
	//----- ProfessorMyPage 교수 개인정보 페이지 -----//
	
	public ProfessorVO ProfessorMyPageDAO(String n) {
		
		SqlSession ss = factory.openSession();
		ProfessorVO vo = ss.selectOne("jeong2.getProfessorMyPage",n); //n = p_idx
		
		if(ss!=null)
			ss.close();
		
		return vo;
		
	}
	
	public void ProfessorMyPageUpdate(ProfessorVO vo) {
	
		SqlSession ss = factory.openSession();
		int a = ss.update("jeong2.updateProfessorMyPage",vo);
	
		if(a > 0) {
			JOptionPane.showMessageDialog(null, "수정 완료되었습니다.", "개인정보 알림", JOptionPane.INFORMATION_MESSAGE);
			ss.commit();
		}
		else {
			JOptionPane.showMessageDialog(null, "정보를 다시 입력해주세요.", "개인정보 알림", JOptionPane.ERROR_MESSAGE);
			ss.rollback();
		}
	
		if(ss != null)
			ss.close();
	
	}//------------------------------------//
	
	
	
	//----- StudentSubjectManagementPage 수강신청한 과목 관리 페이지 -----//
	
	public List<SubjectVO> StudentSubjectManagementPageDAO(String n) {
		
		SqlSession ss = factory.openSession();
		List<SubjectVO> voList = ss.selectList("jeong2.mySubject",n);
		
		if(ss!=null)
			ss.close();
		return voList;
	}
	
	public List<SubjectVO> SearchDAO(Map<String, String> map){
		
		SqlSession ss = factory.openSession();
		List<SubjectVO> voList = ss.selectList("jeong2.searchMy",map);
		
		if(ss!=null)
			ss.close();
		return voList; 	
	}
	
	public String deleteDAO(String subName) {
		
		SqlSession ss = factory.openSession();
		String subIdx = ss.selectOne("jeong2.selectSubjectIdx", subName);
		return subIdx;				
	}
	
	public Map<String, String> deleteDAO2(Map<String, String> map){
		
		SqlSession ss = factory.openSession();
		//StudentSubjectManagementPage studentSubjectManagementPage = new StudentSubjectManagementPage();
			
		int a = ss.update("jeong2.deleteMySubject", map);
		
		if(a>0) {
			JOptionPane.showMessageDialog(null, "수강취소 되었습니다.", "수강과목관리 알림", JOptionPane.INFORMATION_MESSAGE);
			ss.commit();
			//studentSubjectManagementPage.mySubject(); //삭제 완료 후 새로고침
		}else {
			JOptionPane.showMessageDialog(null, "수강취소처리가 실패하였습니다.", "수강과목관리 알림", JOptionPane.ERROR_MESSAGE);
			ss.rollback();
		}
		if(ss!=null)
			ss.close();
		return null;
	}//------------------------------------//
	
	
	
	//----- StudentSubjectSelectionPage 수강신청 페이지 -----//
	
	public List<SubjectVO> total() {
		
		SqlSession ss = factory.openSession();
		List<SubjectVO> list = ss.selectList("jeong2.total");
		
		return list;
	}
	
	public List<SubjectVO> stsb(String str){
		
		SqlSession ss = factory.openSession();
		List<SubjectVO> list = ss.selectList("jeong2.search",str);
		
		return list;
	}
	
	public  Map<String, Object> sbse(Map<String, Object> map){
		
		SqlSession ss = factory.openSession();
		
		String vo = ss.selectOne("jeong2.searchVo", map);
		
		if(vo == null) {
		
			int a = ss.insert("jeong2.selection", map);
		
			if(a>0) {
				JOptionPane.showMessageDialog(null, "수강신청이 완료되었습니다.", "수강신청 알림", JOptionPane.INFORMATION_MESSAGE);
				//System.out.println("저장완료");
				ss.commit();
			}else {
				JOptionPane.showMessageDialog(null, "이미 수강중인 과목입니다.", "수강신청 알림", JOptionPane.ERROR_MESSAGE);
				//System.out.println("저장실패");
				ss.rollback();
			}
			if(ss != null)
				ss.close();
		}else {
			int a = ss.update("jeong2.selection2", map);
		
				if(a>0) {
					JOptionPane.showMessageDialog(null, "수강신청이 완료되었습니다.", "수강신청 알림", JOptionPane.INFORMATION_MESSAGE);
					//System.out.println("저장완료");
					ss.commit();
				}else {
					JOptionPane.showMessageDialog(null, "이미 수강중인 과목입니다.", "수강신청 알림", JOptionPane.ERROR_MESSAGE);
					//System.out.println("저장실패");
					ss.rollback();
				}
				if(ss != null)
					ss.close();
		}
		
		return null;
		
	}//------------------------------------//
	
	
	//----- ProfessorManagementPage 교수관리 페이지 -----//
	
	public List<ProfessorVO> ProfessorListDAO() {
			
			SqlSession ss = factory.openSession();
			List<ProfessorVO> list = ss.selectList("jeong2.ProfessorList");
			
			if(ss!=null)
				ss.close();
			return list;
	}
	
	public List<ProfessorVO> SearchProDAO(Map<String, String> map){
			
			SqlSession ss = factory.openSession();
			List<ProfessorVO> voList = ss.selectList("jeong2.searchPro",map);
			
			if(ss!=null)
				ss.close();
			return voList; 	
		}
	public String deleteProDAO(Map<String, String> map) {
			
			SqlSession ss = factory.openSession();
			String del_pidx = ss.selectOne("jeong2.selectProIdx", map);
			return del_pidx;				
		}
	
	public String deleteProDAO2(String pidx){
		
		SqlSession ss = factory.openSession();
			
		int a = ss.update("jeong2.deletePro", pidx);
		
		if(a>0) {
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.", "교수관리 알림", JOptionPane.INFORMATION_MESSAGE);
			ss.commit();
		}else {
			JOptionPane.showMessageDialog(null, "삭제할 수 없습니다.", "교수관리 알림", JOptionPane.ERROR_MESSAGE);
			ss.rollback();
		}
		if(ss!=null)
			ss.close();
		return null;
	}//------------------------------------//
	
	
//---- AddProfessorDialog 교수 추가 다이어로그 ----//
	public Map<String, String> addProfessor(Map<String, String> map){
		
		SqlSession ss = factory.openSession();
		String vo = ss.selectOne("jeong2.searchVo2", map);
		
		if(vo == null) {
		
			int a = ss.insert("jeong2.addProfessor", map);
					
			if(a>0){
				JOptionPane.showMessageDialog(null, "추가 되었습니다.", "교수관리 알림", JOptionPane.INFORMATION_MESSAGE);
				ss.commit();
			}else {
				JOptionPane.showMessageDialog(null, "이미 등록된 교수 입니다.", "교수관리 알림", JOptionPane.ERROR_MESSAGE);
				ss.rollback();
			}
			if(ss!=null)
				ss.close();
		}else {

			int a = ss.update("jeong2.re_addProfessor", map);
					
			if(a>0){
				JOptionPane.showMessageDialog(null, "추가 되었습니다.", "교수관리 알림", JOptionPane.INFORMATION_MESSAGE);
				ss.commit();
			}else {
				JOptionPane.showMessageDialog(null, "이미 등록된 교수 입니다.", "교수관리 알림", JOptionPane.ERROR_MESSAGE);
				ss.rollback();
			}
			if(ss!=null)
				ss.close();
		}
		return null;
	}
	
	public List<MajorVO> totalMajor() {
			
			SqlSession ss = factory.openSession();
			List<MajorVO> list = ss.selectList("jeong2.totalMajor");
			
			return list;
		}
		
	public Map<String, String> updateProfessorDAO(Map<String, String> map) {
		
		SqlSession ss = factory.openSession();
		
		String vo = ss.selectOne("jeong2.searchVo3", map);
		
		if(vo == null) {
		
			int a = ss.update("jeong2.updateProfessor", map);
			
			if(a>0){
				JOptionPane.showMessageDialog(null, "저장 되었습니다.", "교수관리 알림", JOptionPane.INFORMATION_MESSAGE);
				ss.commit();
			}else {
				JOptionPane.showMessageDialog(null, "저장할 수 없습니다.", "교수관리 알림", JOptionPane.ERROR_MESSAGE);
				ss.rollback();
			}	
		}else {
			JOptionPane.showMessageDialog(null, "이미 등록된 교수 입니다.", "교수관리 알림", JOptionPane.ERROR_MESSAGE);
			ss.rollback();
		}
		if(ss!=null)
			ss.close();
		return null;
		}
	
	public List<SubjectVO> professorDetail(String p_idx) {
		SqlSession ss = factory.openSession();
		List<SubjectVO> list = ss.selectList("jeong2.professorDetail", p_idx);
		return list;
	}
	
	public String SearchP_idxDAO(Map<String, String> map){
		
		SqlSession ss = factory.openSession();
		
		String p_idx = ss.selectOne("jeong2.SearchP_idx", map);
				
		return p_idx;
		
	}
	
	public String getProfessorMajorIdx(String mName) {
		SqlSession ss = factory.openSession();
		
		String m_idx = ss.selectOne("jeong2.getProfessorMajorIdx", mName);
		return m_idx;
	}
	

}
