package util;

import java.util.HashMap;
import java.util.Map;

public class BankCode {
	private String bank; //은행이름
	private String code; //kg이니시스 은행 코드
	private Map<String, String> codeTable = new HashMap<>();
	
	//생성자
	public BankCode(String bank) {
		this.bank = bank;
		codeTable.put("KB국민은행", "04");
		codeTable.put("SC제일은행", "23");
		codeTable.put("경남은행", "39");
		codeTable.put("광주은행", "34");
		codeTable.put("기업은행", "03");
		codeTable.put("농협", "11");
		codeTable.put("대구은행", "31");
		codeTable.put("부산은행", "32");
		codeTable.put("산업은행", "02");
		codeTable.put("새마을금고", "45");
		codeTable.put("수협", "07");
		codeTable.put("신한은행", "88");
		codeTable.put("외환은행", "05");
		codeTable.put("우리은행", "20");
		codeTable.put("우체국", "71");
		codeTable.put("전북은행", "37");
		codeTable.put("축협", "16");
		codeTable.put("카카오뱅크", "90");
		codeTable.put("카카오", "90");
		codeTable.put("케이뱅크", "89");
		codeTable.put("하나은행(서울은행)", "81");
		codeTable.put("한국씨티은행(한미은행)", "53");
		this.code = codeTable.get(bank);
	}
	
	@Override
	public String toString() {
		return "BankCode [bank=" + bank + ", code=" + code + ", codeTable=" + codeTable + "]";
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Map<String, String> getCodeTable() {
		return codeTable;
	}
	public void setCodeTable(Map<String, String> codeTable) {
		this.codeTable = codeTable;
	}
	
	
	
	
	
}
