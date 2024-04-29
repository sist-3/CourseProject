package vo;

public class QuizVO {
	private String q_idx, e_idx, q_type, q_quiz, q_q1, q_q2, q_q3, q_q4, q_answer, q_point, q_cnt;
	private ExamVO evo;
	
	public String getQ_idx() {
		return q_idx;
	}
	public void setQ_idx(String q_idx) {
		this.q_idx = q_idx;
	}
	public String getQ_type() {
		return q_type;
	}
	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}
	public String getE_idx() {
		return e_idx;
	}
	public void setE_idx(String e_idx) {
		this.e_idx = e_idx;
	}
	public String getQ_quiz() {
		return q_quiz;
	}
	public void setQ_quiz(String q_quiz) {
		this.q_quiz = q_quiz;
	}
	public String getQ_q1() {
		return q_q1;
	}
	public void setQ_q1(String q_q1) {
		this.q_q1 = q_q1;
	}
	public String getQ_q2() {
		return q_q2;
	}
	public void setQ_q2(String q_q2) {
		this.q_q2 = q_q2;
	}
	public String getQ_q3() {
		return q_q3;
	}
	public void setQ_q3(String q_q3) {
		this.q_q3 = q_q3;
	}
	public String getQ_q4() {
		return q_q4;
	}
	public void setQ_q4(String q_q4) {
		this.q_q4 = q_q4;
	}
	public String getQ_answer() {
		return q_answer;
	}
	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}
	public String getQ_point() {
		return q_point;
	}
	public void setQ_point(String q_point) {
		this.q_point = q_point;
	}
	public String getQ_cnt() {
		return q_cnt;
	}
	public void setQ_cnt(String q_cnt) {
		this.q_cnt = q_cnt;
	}
	public ExamVO getEvo() {
		return evo;
	}
	public void setEvo(ExamVO evo) {
		this.evo = evo;
	}
	
}
