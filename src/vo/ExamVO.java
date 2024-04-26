package vo;

public class ExamVO {
	private String e_idx, e_name, e_date, e_yn;
	private SubjectVO sbvo;

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

	public String getE_date() {
		return e_date;
	}

	public void setE_date(String e_date) {
		this.e_date = e_date;
	}

	public String getE_yn() {
		return e_yn;
	}

	public void setE_yn(String e_yn) {
		this.e_yn = e_yn;
	}

	public void setSbvo(SubjectVO sbvo) {
		this.sbvo = sbvo;
	}

	public SubjectVO getSbvo() {
		return sbvo;
	}
	
}
