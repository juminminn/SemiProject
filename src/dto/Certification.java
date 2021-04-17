package dto;

import java.util.Date;

public class Certification {
	private int ceNo; //인증 번호
	private int paNo; //참여 챌린지 번호
	private String ceOriginName; // 인증 사진 원본 이름
	private String ceStoredName; // 인증 사진 저장 이름
	private Date ceCreateDate; // 인증 날짜
	private Date ceUpdateDate; // 인증 수정 날짜
	private String ceIsSuccess; // 인증 성공 여부
	@Override
	public String toString() {
		return "Certification [ceNo=" + ceNo + ", paNo=" + paNo + ", ceOriginName=" + ceOriginName + ", ceStoredName="
				+ ceStoredName + ", ceCreateDate=" + ceCreateDate + ", ceUpdateDate=" + ceUpdateDate + ", ceIsSuccess="
				+ ceIsSuccess + "]";
	}
	public int getCeNo() {
		return ceNo;
	}
	public void setCeNo(int ceNo) {
		this.ceNo = ceNo;
	}
	public int getPaNo() {
		return paNo;
	}
	public void setPaNo(int paNo) {
		this.paNo = paNo;
	}
	public String getCeOriginName() {
		return ceOriginName;
	}
	public void setCeOriginName(String ceOriginName) {
		this.ceOriginName = ceOriginName;
	}
	public String getCeStoredName() {
		return ceStoredName;
	}
	public void setCeStoredName(String ceStoredName) {
		this.ceStoredName = ceStoredName;
	}
	public Date getCeCreateDate() {
		return ceCreateDate;
	}
	public void setCeCreateDate(Date ceCreateDate) {
		this.ceCreateDate = ceCreateDate;
	}
	public Date getCeUpdateDate() {
		return ceUpdateDate;
	}
	public void setCeUpdateDate(Date ceUpdateDate) {
		this.ceUpdateDate = ceUpdateDate;
	}
	public String getCeIsSuccess() {
		return ceIsSuccess;
	}
	public void setCeIsSuccess(String ceIsSuccess) {
		this.ceIsSuccess = ceIsSuccess;
	}
	
	
}
