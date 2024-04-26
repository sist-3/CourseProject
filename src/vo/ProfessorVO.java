package vo;

import java.util.List;

public class ProfessorVO {
	private String p_idx, p_name, p_tel, p_addr, p_birth, p_yn;
	private List<MajorVO> list;
	
	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public String getP_addr() {
		return p_addr;
	}
	public void setP_addr(String p_addr) {
		this.p_addr = p_addr;
	}
	public String getP_birth() {
		return p_birth;
	}
	public void setP_birth(String p_birth) {
		this.p_birth = p_birth;
	}
	public String getP_yn() {
		return p_yn;
	}
	public void setP_yn(String p_yn) {
		this.p_yn = p_yn;
	}
	public List<MajorVO> getList() {
		return list;
	}
	public void setList(List<MajorVO> list) {
		this.list = list;
	}
}
