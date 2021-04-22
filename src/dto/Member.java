package dto;

import java.util.Date;

public class Member {
	private int uno; //번호
	private String uid; //아이디
	private String upassword; //비밀번호
	private String username; //이름
	private String email;//이메일
	private String nick; //별명
	private String phone;//휴대전화
	private String gender; //성별
	private Date birth;//생일
	private Date singup; //가입일
	private String account; //계좌
	private String bank; //은행
	private String ugrade; //등급
	private String post; //우편번호
	private int caution; //경고
	private String challenge; //도전하고 싶은 챌린져
	private String address; //주소
	
	//to String
	@Override
	public String toString() {
		return "Member [uno=" + uno + ", uid=" + uid + ", upassword=" + upassword + ", username=" + username + ", email="
				+ email + ", nick=" + nick + ", phone=" + phone + ", gender=" + gender + ", birth=" + birth + ", singup="
				+ singup + ", account=" + account + ", bank=" + bank + ", ugrade=" + ugrade + ", post=" + post
				+ ", caution=" + caution + ", challenge=" + challenge + ", address=" + address + "]";
	}
	
	//getter setter
	public final int getUno() {
		return uno;
	}
	public final void setUno(int uno) {
		this.uno = uno;
	}
	public final String getUid() {
		return uid;
	}
	public final void setUid(String uid) {
		this.uid = uid;
	}
	public final String getUpassword() {
		return upassword;
	}
	public final void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final String getNick() {
		return nick;
	}
	public final void setNick(String nick) {
		this.nick = nick;
	}
	public final String getPhone() {
		return phone;
	}
	public final void setPhone(String phone) {
		this.phone = phone;
	}
	public final String getGender() {
		return gender;
	}
	public final void setGender(String gender) {
		this.gender = gender;
	}
	public final Date getBirth() {
		return birth;
	}
	public final void setBirth(Date birth) {
		this.birth = birth;
	}
	public final Date getSingup() {
		return singup;
	}
	public final void setSingup(Date singup) {
		this.singup = singup;
	}
	public final String getAccount() {
		return account;
	}
	public final void setAccount(String account) {
		this.account = account;
	}
	public final String getBank() {
		return bank;
	}
	public final void setBank(String bank) {
		this.bank = bank;
	}
	public final String getUgrade() {
		return ugrade;
	}
	public final void setUgrade(String ugrade) {
		this.ugrade = ugrade;
	}
	public final String getPost() {
		return post;
	}
	public final void setPost(String post) {
		this.post = post;
	}
	public final int getCaution() {
		return caution;
	}
	public final void setCaution(int caution) {
		this.caution = caution;
	}
	public final String getChallenge() {
		return challenge;
	}
	public final void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public final String getAddress() {
		return address;
	}
	public final void setAddress(String address) {
		this.address = address;
	}





}