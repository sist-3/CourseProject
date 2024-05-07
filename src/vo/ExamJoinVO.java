package vo;

public class ExamJoinVO {
	private String ej_idx, ej_score, ej_yn,e_idx,st_idx;
	public String getSt_idx() {
		return st_idx;
	}
	public void setSt_idx(String st_idx) {
		this.st_idx = st_idx;
	}
	private ExamVO evo;
	private StudentVO stvo;
	
	public StudentVO getStvo() {
		return stvo;
	}
	public void setStvo(StudentVO stvo) {
		this.stvo = stvo;
	}
	public String getEj_idx() {
		return ej_idx;
	}
	public void setEj_idx(String ej_idx) {
		this.ej_idx = ej_idx;
	}
	public String getEj_score() {
		return ej_score;
	}
	public void setEj_score(String ej_score) {
		this.ej_score = ej_score;
	}
	public String getE_idx() {
		return e_idx;
	}
	public void setE_idx(String e) {
		this.e_idx = e;
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
