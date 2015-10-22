package adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import model.ModelProcess;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liu.hours.R;

public class AdapterResultProcessItem extends BaseAdapter {

	private List<ModelProcess> mList;
	private Context mContext;
	
	public AdapterResultProcessItem(Context pContext, List<ModelProcess> pList){
		this.mContext = pContext;
		this.mList = pList;
	}
	
	private static class ViewHolder{
		TextView tvProcessDetail;
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
	public ModelProcess getItem(int position) {
		// TODO Auto-generated method stub
		ModelProcess pModelProcess = null;
		if(null != mList && position < mList.size()){
			pModelProcess = mList.get(position);
		}
		return pModelProcess;
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
		String pDate = null;
		String pContent = null;

		if (null == convertView){
			pViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.result_process_item, null);
			
			pViewHolder.tvProcessDetail = (TextView)convertView.findViewById(R.id.tvResultProcessItem);
			
			convertView.setTag(pViewHolder);
		} else {
			pViewHolder = (ViewHolder) convertView.getTag();
		}
		
		ModelProcess pModelProcess = getItem(position);
		
		if(null != pModelProcess){
			DateFormat pDF = new SimpleDateFormat("yyyy/MM/dd");
			pDate = pDF.format(pModelProcess.getDate());
			pContent = pDate + "   "
					+ pModelProcess.getTargetName() + "   "
					+ String.valueOf(pModelProcess.getHours()) + "   "
					+ pModelProcess.getRemark();
		}
		
		pViewHolder.tvProcessDetail.setText(pContent);
				
		return convertView;
	}

}
