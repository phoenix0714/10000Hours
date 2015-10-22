package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelProcess {
	private String mTargetName;
	private Date mDate;
	private int mHours;
	private String mRemark;
	
	public ModelProcess(){
		setTargetName(null);
		setDate(new Date());
		setHours(0);
		setRemark(null);
	}
	
	public String getTargetName() {
		return mTargetName;
	}
	public void setTargetName(String mTargetName) {
		this.mTargetName = mTargetName;
	}
	public Date getDate() {
		return mDate;
	}
	public void setDate(Date mDate) {
		this.mDate = mDate;
	}
	public void setDate(String pString) {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			mDate = df.parse(pString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getHours() {
		return mHours;
	}
	public void setHours(int mHours) {
		this.mHours = mHours;
	}
	public String getRemark() {
		return mRemark;
	}
	public void setRemark(String mRemark) {
		this.mRemark = mRemark;
	}
}
