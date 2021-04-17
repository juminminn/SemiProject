package dto;

import java.util.Date;

public class Payment {
	private int paymNo; //결제 번호
	private int uNo; //사용자 번호
	private int chNo; //챌린지 번호
	private String paymName; //결제 이름
	private Date paymDate; //결제 날짜
	private int paymAmount; //결제금액
	private String paymApplyNum; //카드 승인번호
	private String impUid; //아임포트 번호
	private String merchantUid; // 고유주문번호
	@Override
	public String toString() {
		return "Payment [paymNo=" + paymNo + ", uNo=" + uNo + ", chNo=" + chNo + ", paymName=" + paymName
				+ ", paymDate=" + paymDate + ", paymAmount=" + paymAmount + ", paymApplyNum=" + paymApplyNum
				+ ", impUid=" + impUid + ", merchantUid=" + merchantUid + "]";
	}
	public int getPaymNo() {
		return paymNo;
	}
	public void setPaymNo(int paymNo) {
		this.paymNo = paymNo;
	}
	public int getuNo() {
		return uNo;
	}
	public void setuNo(int uNo) {
		this.uNo = uNo;
	}
	public int getChNo() {
		return chNo;
	}
	public void setChNo(int chNo) {
		this.chNo = chNo;
	}
	public String getPaymName() {
		return paymName;
	}
	public void setPaymName(String paymName) {
		this.paymName = paymName;
	}
	public Date getPaymDate() {
		return paymDate;
	}
	public void setPaymDate(Date paymDate) {
		this.paymDate = paymDate;
	}
	public int getPaymAmount() {
		return paymAmount;
	}
	public void setPaymAmount(int paymAmount) {
		this.paymAmount = paymAmount;
	}
	public String getPaymApplyNum() {
		return paymApplyNum;
	}
	public void setPaymApplyNum(String paymApplyNum) {
		this.paymApplyNum = paymApplyNum;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public String getMerchantUid() {
		return merchantUid;
	}
	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	
	
}
