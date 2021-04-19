package dto;

public class Mypage {
	private int mNo;
	private String mOriginname;
	private String mStoredname;
	private int mLikes;
	private int mComment;
	private int mPost;
	private int mAcchall;
	private int mFail;
	private int mCpoint;
	private int mApoint;
	
	
	//to String
	@Override
	public String toString() {
		return "Mypage [mNo=" + mNo + ", mOriginname=" + mOriginname + ", mStoredname=" + mStoredname + ", mLikes="
				+ mLikes + ", mComment=" + mComment + ", mPost=" + mPost + ", mAcchall=" + mAcchall + ", mFail=" + mFail
				+ ", mCpoint=" + mCpoint + ", mApoint=" + mApoint + "]";
	}
	
	//getter setter
	public int getmNo() {
		return mNo;
	}
	public void setmNo(int mNo) {
		this.mNo = mNo;
	}
	public String getmOriginname() {
		return mOriginname;
	}
	public void setmOriginname(String mOriginname) {
		this.mOriginname = mOriginname;
	}
	public String getmStoredname() {
		return mStoredname;
	}
	public void setmStoredname(String mStoredname) {
		this.mStoredname = mStoredname;
	}
	public int getmLikes() {
		return mLikes;
	}
	public void setmLikes(int mLikes) {
		this.mLikes = mLikes;
	}
	public int getmComment() {
		return mComment;
	}
	public void setmComment(int mComment) {
		this.mComment = mComment;
	}
	public int getmPost() {
		return mPost;
	}
	public void setmPost(int mPost) {
		this.mPost = mPost;
	}
	public int getmAcchall() {
		return mAcchall;
	}
	public void setmAcchall(int mAcchall) {
		this.mAcchall = mAcchall;
	}
	public int getmFail() {
		return mFail;
	}
	public void setmFail(int mFail) {
		this.mFail = mFail;
	}
	public int getmCpoint() {
		return mCpoint;
	}
	public void setmCpoint(int mCpoint) {
		this.mCpoint = mCpoint;
	}
	public int getmApoint() {
		return mApoint;
	}
	public void setmApoint(int mApoint) {
		this.mApoint = mApoint;
	}

}
