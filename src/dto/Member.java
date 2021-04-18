package dto;

public class Member {
	private int uno; //사용자 번호
	private String uid; //아이디
	private String upassword; //비밀번호
	private String ugrade; // 등급 
	
	@Override
	public String toString() {
		return "Member [uno=" + uno + ", uid=" + uid + ", upassword=" + upassword + ", ugrade=" + ugrade + "]";
	}
	
	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUgrade() {
		return ugrade;
	}
	public void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}
	
	
}
