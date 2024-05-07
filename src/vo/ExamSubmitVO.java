package vo;


public class ExamSubmitVO {
	private String esu_idx, esu_answer, st_idx, e_idx, q_idx;
	private ExamVO evo;
	private QuizVO qvo;
	private StudentVO stvo;
	private ExamJoinVO ejvo;
	
	public ExamJoinVO getEjvo() {
		return ejvo;
	}
	public void setEjvo(ExamJoinVO ejvo) {
		this.ejvo = ejvo;
	}

	public String getSt_idx() {
		return st_idx;
	}
	public void setSt_idx(String st_idx) {
		this.st_idx = st_idx;
	}
	public String getE_idx() {
		return e_idx;
	}
	public void setE_idx(String e_idx) {
		this.e_idx = e_idx;
	}
	public String getQ_idx() {
		return q_idx;
	}
	public void setQ_idx(String q_idx) {
		this.q_idx = q_idx;
	}
	public QuizVO getQvo() {
		return qvo;
	}
	public void setQvo(QuizVO qvo) {
		this.qvo = qvo;
	}
	public String getEsu_idx() {
		return esu_idx;
	}
	public void setEsu_idx(String esu_idx) {
		this.esu_idx = esu_idx;
	}
	public String getEsu_answer() {
		return esu_answer;
	}
	public void setEsu_answer(String esu_answer) {
		this.esu_answer = esu_answer;
	}
	public ExamVO getEvo() {
		return evo;
	}
	public void setEvo(ExamVO evo) {
		this.evo = evo;
	}
	public StudentVO getStvo() {
		return stvo;
	}
	public void setStvo(StudentVO stvo) {
		this.stvo = stvo;
	}
	
	
}
