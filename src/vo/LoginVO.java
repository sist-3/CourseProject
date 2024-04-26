package vo;

public class LoginVO {
	private String log_idx,log_id,log_pw,log_date,log_yn, chk_role;
	private StudentVO stvo;
	private ProfessorVO pvo;
	public String getLog_idx() {
		return log_idx;
	}
	public void setLog_idx(String log_idx) {
		this.log_idx = log_idx;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getLog_pw() {
		return log_pw;
	}
	public void setLog_pw(String log_pw) {
		this.log_pw = log_pw;
	}
	public String getLog_date() {
		return log_date;
	}
	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}
	public String getLog_yn() {
		return log_yn;
	}
	public void setLog_yn(String log_yn) {
		this.log_yn = log_yn;
	}
	public String getChk_role() {
		return chk_role;
	}
	public void setChk_role(String chk_role) {
		this.chk_role = chk_role;
	}
	public StudentVO getStvo() {
		return stvo;
	}
	public void setStvo(StudentVO stvo) {
		this.stvo = stvo;
	}
	public ProfessorVO getPvo() {
		return pvo;
	}
	public void setPvo(ProfessorVO pvo) {
		this.pvo = pvo;
	}
	
	
}
