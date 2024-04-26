package vo;

import java.util.List;

public class StudentSubjectVO {
	private String ss_idx, ss_date, ss_yn;
	private List<SubjectVO> sb_list;
	private StudentVO stvo;
	private List<ProfessorVO> p_list;
	public String getSs_idx() {
		return ss_idx;
	}
	public void setSs_idx(String ss_idx) {
		this.ss_idx = ss_idx;
	}
	public String getSs_date() {
		return ss_date;
	}
	public void setSs_date(String ss_date) {
		this.ss_date = ss_date;
	}
	public String getSs_yn() {
		return ss_yn;
	}
	public void setSs_yn(String ss_yn) {
		this.ss_yn = ss_yn;
	}
	public List<SubjectVO> getSb_list() {
		return sb_list;
	}
	public void setSb_list(List<SubjectVO> sb_list) {
		this.sb_list = sb_list;
	}
	public StudentVO getStvo() {
		return stvo;
	}
	public void setStvo(StudentVO stvo) {
		this.stvo = stvo;
	}
	public List<ProfessorVO> getP_list() {
		return p_list;
	}
	public void setP_list(List<ProfessorVO> p_list) {
		this.p_list = p_list;
	}
	
	
}
