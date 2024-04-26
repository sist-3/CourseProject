package vo;

public class ExamVO {
	private String e_idx, sb_idx, p_idx, e_name, e_date, e_yn;
	private SubjectVO svo;
	
	
	public ExamVO() {}
	

	public ExamVO(String e_idx,  String s_name, String e_name) {
		super();
		this.e_idx = e_idx;
		this.e_name = e_name;
	}


	public String getE_idx() {
		return e_idx;
	}

	public void setE_idx(String e_idx) {
		this.e_idx = e_idx;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}


	public SubjectVO getSvo() {
		return svo;
	}


	public void setSvo(SubjectVO svo) {
		this.svo = svo;
	}


	
}
