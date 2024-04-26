package vo;

import java.util.List;

public class ExamJoinVO {
	private String ej_idx, ej_score, ej_yn;
	private ExamVO evo;
	private StudentVO stvo;
	private List<StudentVO> st_list;
	public String getEj_idx() {
		return ej_idx;
	}
	public StudentVO getStvo() {
		return stvo;
	}
	public void setStvo(StudentVO stvo) {
		this.stvo = stvo;
	}
	public void setEj_idx(String ej_idx) {
		this.ej_idx = ej_idx;
	}
	public String getEj_score() {
		return ej_score;
	}
	public List<StudentVO> getSt_list() {
		return st_list;
	}
	public void setSt_list(List<StudentVO> st_list) {
		this.st_list = st_list;
	}
	public void setEj_score(String ej_score) {
		this.ej_score = ej_score;
	}
	public String getEj_yn() {
		return ej_yn;
	}
	public void setEj_yn(String ej_yn) {
		this.ej_yn = ej_yn;
	}
	public ExamVO getEvo() {
		return evo;
	}
	public void setEvo(ExamVO evo) {
		this.evo = evo;
	}
	
	
}
