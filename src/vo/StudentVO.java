package vo;

public class StudentVO {
	private String st_idx, st_num, st_name, st_indate, st_outdate, st_birth, st_addr, st_tel, st_yn;
	private MajorVO mvo;
	private ProfessorVO pvo;
	public String getSt_idx() {
		return st_idx;
	}

	public MajorVO getMvo() {
		return mvo;
	}

	public void setMvo(MajorVO mvo) {
		this.mvo = mvo;
	}

	public ProfessorVO getPvo() {
		return pvo;
	}

	public void setPvo(ProfessorVO pvo) {
		this.pvo = pvo;
	}

	public void setSt_idx(String st_idx) {
		this.st_idx = st_idx;
	}

	public String getSt_num() {
		return st_num;
	}

	public void setSt_num(String st_num) {
		this.st_num = st_num;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_indate() {
		return st_indate;
	}

	public void setSt_indate(String st_indate) {
		this.st_indate = st_indate;
	}

	public String getSt_outdate() {
		return st_outdate;
	}

	public void setSt_outdate(String st_outdate) {
		this.st_outdate = st_outdate;
	}

	public String getSt_birth() {
		return st_birth;
	}

	public void setSt_birth(String st_birth) {
		this.st_birth = st_birth;
	}

	public String getSt_addr() {
		return st_addr;
	}

	public void setSt_addr(String st_addr) {
		this.st_addr = st_addr;
	}

	public String getSt_tel() {
		return st_tel;
	}

	public void setSt_tel(String st_tel) {
		this.st_tel = st_tel;
	}

	public String getSt_yn() {
		return st_yn;
	}

	public void setSt_yn(String st_yn) {
		this.st_yn = st_yn;
	}
	
	
}
