package dto;

import java.sql.Date;

public class Post {
	private int Pno;
	private int Uno;
	private int Bno;
	private String P_Title;
	private String P_Content;
	private Date P_Create_date;
	private Date P_Update_date;
	private int P_views;
	private String U_nick;
	private String u_id;
	private String p_origin_name;
	private String p_stored_name;
	
	public Post() {}
	
	@Override
	public String toString() {
		return "Post [Pno=" + Pno + ", Uno=" + Uno + ", Bno=" + Bno + ", P_Title=" + P_Title + ", P_Content="
				+ P_Content + ", P_Create_date=" + P_Create_date + ", P_Update_date=" + P_Update_date + ", P_views="
				+ P_views + ", U_nick=" + U_nick + ", u_id=" + u_id + ", p_origin_name=" + p_origin_name
				+ ", p_stored_name=" + p_stored_name + "]";
	}

	public String getP_origin_name() {
		return p_origin_name;
	}
	public void setP_origin_name(String p_origin_name) {
		this.p_origin_name = p_origin_name;
	}
	public String getP_stored_name() {
		return p_stored_name;
	}
	public void setP_stored_name(String p_stored_name) {
		this.p_stored_name = p_stored_name;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_nick() {
		return U_nick;
	}

	public void setU_nick(String u_nick) {
		U_nick = u_nick;
	}

	public int getPno() {
		return Pno;
	}
	public void setPno(int pno) {
		Pno = pno;
	}
	public int getUno() {
		return Uno;
	}
	public void setUno(int uno) {
		Uno = uno;
	}
	public int getBno() {
		return Bno;
	}
	public void setBno(int bno) {
		Bno = bno;
	}
	public String getP_Title() {
		return P_Title;
	}
	public void setP_Title(String p_Title) {
		P_Title = p_Title;
	}
	public String getP_Content() {
		return P_Content;
	}
	public void setP_Content(String p_Content) {
		P_Content = p_Content;
	}
	public Date getP_Create_date() {
		return P_Create_date;
	}
	public void setP_Create_date(Date p_Create_date) {
		P_Create_date = p_Create_date;
	}
	public Date getP_Update_date() {
		return P_Update_date;
	}
	public void setP_Update_date(Date p_Update_date) {
		P_Update_date = p_Update_date;
	}
	public int getP_views() {
		return P_views;
	}
	public void setP_views(int p_views) {
		P_views = p_views;
	}
	
}
