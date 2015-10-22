package adapter;

import java.util.List;

import com.liu.hours.R;

import model.ModelTarget;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterTarget extends BaseAdapter {

	private List<ModelTarget> mList;
	private Context mContext;
	
	private static class ViewHolder{
		TextView tvTargetName;
	}
	
	public void setList(List<ModelTarget> pList){
		this.mList = pList;
	}
	
	public AdapterTarget(Context pContext, List<ModelTarget> pList){
		this.mContext = pContext;
		this.mList = pList;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int pCount = 0;
		if(null != mList){
			pCount = mList.size();
		}
		return pCount;
	}

	@Override
	public ModelTarget getItem(int position) {
		// TODO Auto-generated method stub
		ModelTarget pItem = null;
		if(null != mList){
			pItem = mList.get(position);
		}
		return pItem;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder pViewHolder = null;
		
//		if (convertView == null){
//			pViewHolder = new ViewHolder();
//			convertView = LayoutInflater.from(mContext).inflate(com.liu.hours.R.layout.target_item, null);
//
//			convertView.setTag(pViewHolder);
//		}else{
//			pViewHolder = (ViewHolder)convertView.getTag();
//		}
		if (null == convertView){
			pViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.target_item, null);
			
			pViewHolder.tvTargetName = (TextView)convertView.findViewById(R.id.tvTargetItem);
			
			convertView.setTag(pViewHolder);
		} else {
			pViewHolder = (ViewHolder) convertView.getTag();
		}
//		convertView = LayoutInflater.from(mContext).inflate(R.layout.target_item, null);
//		
//		pViewHolder.tvTargetName = (TextView)convertView.findViewById(R.id.tvTargetItem);
		
		ModelTarget pModelTarget = getItem(position);
		if(null != pModelTarget){
			pViewHolder.tvTargetName.setText(pModelTarget.getTargetName());
		}
				
		return convertView;
	}

}
