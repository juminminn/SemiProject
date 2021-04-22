package dto;

import java.util.Date;


public class Challenge {
	private int chNo; //챌린지 번호
	private int caNo; //카테고리 번호
	private int cecNo; //인증주기 번호
	private int uNo; //유저 번호
	private String chTitle; //챌린지 제목
	private String chContent; //챌린지내용
	private Date chCreateDate;// 작성날짜
	private Date chStartDate; // 시작 날짜
	private Date chEndDate; //끝나는 날짜
	private int chMoney; //참가비
	private String chStartTime; //인증 시작 시간
	private String chEndTime; // 인증 끝나는 시간
	private String chWay; //인증 방법
	private int chCaution; // 신고 횟수
	private int chLikes; //좋아요 개수
	private String chOriginName; // 원본 파일 이름(사진) 
	private String chStoredName; //저장 파일 이름(사진)
	private String chState; // 챌린지 종료상태
	
	@Override
	public String toString() {
		return "Challenge [chNo=" + chNo + ", caNo=" + caNo + ", cecNo=" + cecNo + ", uNo=" + uNo + ", chTitle="
				+ chTitle + ", chContent=" + chContent + ", chCreateDate=" + chCreateDate + ", chStartDate="
				+ chStartDate + ", chEndDate=" + chEndDate + ", chMoney=" + chMoney + ", chStartTime=" + chStartTime
				+ ", chEndTime=" + chEndTime + ", chWay=" + chWay + ", chCaution=" + chCaution + ", chLikes=" + chLikes
				+ ", chOriginName=" + chOriginName + ", chStoredName=" + chStoredName + ", chState=" + chState + "]";
	}

	public final int getChNo() {
		return chNo;
	}

	public final void setChNo(int chNo) {
		this.chNo = chNo;
	}

	public final int getCaNo() {
		return caNo;
	}

	public final void setCaNo(int caNo) {
		this.caNo = caNo;
	}

	public final int getCecNo() {
		return cecNo;
	}

	public final void setCecNo(int cecNo) {
		this.cecNo = cecNo;
	}

	public final int getuNo() {
		return uNo;
	}

	public final void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public final String getChTitle() {
		return chTitle;
	}

	public final void setChTitle(String chTitle) {
		this.chTitle = chTitle;
	}

	public final String getChContent() {
		return chContent;
	}

	public final void setChContent(String chContent) {
		this.chContent = chContent;
	}

	public final Date getChCreateDate() {
		return chCreateDate;
	}

	public final void setChCreateDate(Date chCreateDate) {
		this.chCreateDate = chCreateDate;
	}

	public final Date getChStartDate() {
		return chStartDate;
	}

	public final void setChStartDate(Date chStartDate) {
		this.chStartDate = chStartDate;
	}

	public final Date getChEndDate() {
		return chEndDate;
	}

	public final void setChEndDate(Date chEndDate) {
		this.chEndDate = chEndDate;
	}

	public final int getChMoney() {
		return chMoney;
	}

	public final void setChMoney(int chMoney) {
		this.chMoney = chMoney;
	}

	public final String getChStartTime() {
		return chStartTime;
	}

	public final void setChStartTime(String chStartTime) {
		this.chStartTime = chStartTime;
	}

	public final String getChEndTime() {
		return chEndTime;
	}

	public final void setChEndTime(String chEndTime) {
		this.chEndTime = chEndTime;
	}

	public final String getChWay() {
		return chWay;
	}

	public final void setChWay(String chWay) {
		this.chWay = chWay;
	}

	public final int getChCaution() {
		return chCaution;
	}

	public final void setChCaution(int chCaution) {
		this.chCaution = chCaution;
	}

	public final int getChLikes() {
		return chLikes;
	}

	public final void setChLikes(int chLikes) {
		this.chLikes = chLikes;
	}

	public final String getChOriginName() {
		return chOriginName;
	}

	public final void setChOriginName(String chOriginName) {
		this.chOriginName = chOriginName;
	}

	public final String getChStoredName() {
		return chStoredName;
	}

	public final void setChStoredName(String chStoredName) {
		this.chStoredName = chStoredName;
	}

	public final String getChState() {
		return chState;
	}

	public final void setChState(String chState) {
		this.chState = chState;
	}

}
