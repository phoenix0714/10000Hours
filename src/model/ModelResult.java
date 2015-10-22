package model;

import java.util.List;

public class ModelResult {
	private String mTargetName;
	private int mTotalHours;
	private List<ModelProcess> mList;
	
	public String getTargetName() {
		return mTargetName;
	}
	public void setTargetName(String mTargetName) {
		this.mTargetName = mTargetName;
	}
	public int getTotalHours() {
		return mTotalHours;
	}
	public void setTotalHours(int mTotalHours) {
		this.mTotalHours = mTotalHours;
	}
	public List<ModelProcess> getList() {
		return mList;
	}
	public void setList(List<ModelProcess> mList) {
		this.mList = mList;
	}
}
