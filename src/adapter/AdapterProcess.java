package adapter;

import java.util.List;

import model.ModelProcess;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;

public class AdapterProcess extends BaseAdapter {

	private List<ModelProcess> mList;
	private Context mContext;
	
	private static class ViewHolder{
//		private TextView tvTargetName;
		private Spinner spTargetName;
//		private DatePicker 
	}
	
	public AdapterProcess(Context pContext, List<ModelProcess> pList){
		this.mList = pList;
		this.mContext = pContext;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
