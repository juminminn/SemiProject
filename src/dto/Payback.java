package dto;

import java.util.Date;

public class Payback {
	private int paybNo;
	private int uNo;
	private int chNo;
	private int paybAmount;
	private int paybTaxFree;
	private int paybChecksum;
	private Date paybDate;
	private String paybReason;
	private String paybRefundHolder;
	private String paybRefundBank;
	private String paybReFundAccount;
	private String impUid;
	private String merchantUid;
	
	//to String
	@Override
	public String toString() {
		return "Payback [paybNo=" + paybNo + ", uNo=" + uNo + ", chNo=" + chNo + ", paybAmount=" + paybAmount
				+ ", paybTaxFree=" + paybTaxFree + ", paybChecksum=" + paybChecksum + ", paybDate=" + paybDate
				+ ", paybReason=" + paybReason + ", paybRefundHolder=" + paybRefundHolder + ", paybRefundBank="
				+ paybRefundBank + ", paybReFundAccount=" + paybReFundAccount + ", impUid=" + impUid + ", merchantUid="
				+ merchantUid + "]";
	}

	//getter setter
	public final int getPaybNo() {
		return paybNo;
	}

	public final void setPaybNo(int paybNo) {
		this.paybNo = paybNo;
	}

	public final int getuNo() {
		return uNo;
	}

	public final void setuNo(int uNo) {
		this.uNo = uNo;
	}

	public final int getChNo() {
		return chNo;
	}

	public final void setChNo(int chNo) {
		this.chNo = chNo;
	}

	public final int getPaybAmount() {
		return paybAmount;
	}

	public final void setPaybAmount(int paybAmount) {
		this.paybAmount = paybAmount;
	}

	public final int getPaybTaxFree() {
		return paybTaxFree;
	}

	public final void setPaybTaxFree(int paybTaxFree) {
		this.paybTaxFree = paybTaxFree;
	}

	public final int getPaybChecksum() {
		return paybChecksum;
	}

	public final void setPaybChecksum(int paybChecksum) {
		this.paybChecksum = paybChecksum;
	}

	public final Date getPaybDate() {
		return paybDate;
	}

	public final void setPaybDate(Date paybDate) {
		this.paybDate = paybDate;
	}

	public final String getPaybReason() {
		return paybReason;
	}

	public final void setPaybReason(String paybReason) {
		this.paybReason = paybReason;
	}

	public final String getPaybRefundHolder() {
		return paybRefundHolder;
	}

	public final void setPaybRefundHolder(String paybRefundHolder) {
		this.paybRefundHolder = paybRefundHolder;
	}

	public final String getPaybRefundBank() {
		return paybRefundBank;
	}

	public final void setPaybRefundBank(String paybRefundBank) {
		this.paybRefundBank = paybRefundBank;
	}

	public final String getPaybReFundAccount() {
		return paybReFundAccount;
	}

	public final void setPaybReFundAccount(String paybReFundAccount) {
		this.paybReFundAccount = paybReFundAccount;
	}

	public final String getImpUid() {
		return impUid;
	}

	public final void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public final String getMerchantUid() {
		return merchantUid;
	}

	public final void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	
	
	
}
