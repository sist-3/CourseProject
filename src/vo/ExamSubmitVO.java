package vo;


public class ExamSubmitVO {
	private String esu_idx, esu_answer;
	private ExamVO evo;
	private QuizVO qvo;
	private StudentVO stvo;
	
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
