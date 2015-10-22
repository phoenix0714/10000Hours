package adapter;

import java.util.List;

import model.ModelProcess;
import model.ModelResult;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liu.hours.R;

public class AdapterResult extends BaseAdapter {
	
	private List<ModelResult> mList;
	private Context mContext;
	
	private static class ViewHolder{
		TextView tvTargetName;
		TextView tvFinished;
		ProgressBar pbFinished;
		Button btDetail;
		ListView lvResultDetail;
	}
	
	public AdapterResult(Context pContext, List<ModelResult> pList){
		this.mContext = pContext;
		this.mList = pList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int pCount = 0;
		if(null != mList ){
			pCount = mList.size();
		}
		return pCount;
	}

	@Override
	public ModelResult getItem(int position) {
		// TODO Auto-generated method stub
		ModelResult pModelResult = null;
		if (null != mList){
			pModelResult = mList.get(position);
		}
		return pModelResult;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder pViewHolder = new ViewHolder();
				
		if (null == convertView){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.result_item, null);
			
			pViewHolder.tvTargetName = (TextView)convertView.findViewById(R.id.tvResultTargetName);
			pViewHolder.tvFinished = (TextView)convertView.findViewById(R.id.tvResultTargetFinished);
			pViewHolder.pbFinished = (ProgressBar)convertView.findViewById(R.id.pbResultFinished);
			pViewHolder.btDetail = (Button)convertView.findViewById(R.id.btResultDetail);
			
			convertView.setTag(pViewHolder);
		} else {
			pViewHolder = (ViewHolder) convertView.getTag();
		}
			
		ModelResult pModelResult = getItem(position);

		pViewHolder.tvTargetName.setText(pModelResult.getTargetName() + ":");
		pViewHolder.tvFinished.setText(pModelResult.getTotalHours() + "/10000");
		pViewHolder.pbFinished.setProgress(pModelResult.getTotalHours());
		pViewHolder.btDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<ModelProcess> pList = mList.get(position).getList();
				AlertDialog.Builder pBuilder;
				AlertDialog pAlertDialog;
				
				LayoutInflater pInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View pLayout = pInflater.inflate(R.layout.result_process_show, null);
				ListView pListView = (ListView)pLayout.findViewById(R.id.lvResultDetail);
			
				AdapterResultProcessItem pAdapter = new AdapterResultProcessItem(mContext, mList.get(position).getList());
				pListView.setAdapter(pAdapter);
				pBuilder = new AlertDialog.Builder(mContext);
				pBuilder.setView(pLayout);
				pAlertDialog = pBuilder.create();
				pAlertDialog.show();
				
			}
		});
		
		
		return convertView;
		
	}

}
