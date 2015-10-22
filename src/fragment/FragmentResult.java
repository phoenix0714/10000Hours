package fragment;

import java.util.ArrayList;
import java.util.List;

import model.ModelResult;
import adapter.AdapterResult;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liu.hours.R;

import database.DAOResult;

public class FragmentResult extends Fragment {
	
	// views
	private ListView mListView;
	private AdapterResult mAdapterResult;
	private List<ModelResult> mList;
	
	private TextView mTVTargetName, mTVFinishedHours;
	private ProgressBar mPBFinishedHours;
	private Button mBTResultDetail;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_result, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		mTVTargetName = (TextView)getActivity().findViewById(R.id.tvResultTargetName);
		mTVFinishedHours = (TextView)getActivity().findViewById(R.id.tvResultTargetFinished);
		mPBFinishedHours = (ProgressBar)getActivity().findViewById(R.id.pbResultFinished);
		mBTResultDetail = (Button)getActivity().findViewById(R.id.btResultDetail);
		mListView = (ListView)getActivity().findViewById(R.id.lvResultContainer);
		
		
		DAOResult pDAOResult = new DAOResult(getActivity());
		mList = new ArrayList<ModelResult>(pDAOResult.getAllResult());
		
		mAdapterResult = new AdapterResult(getActivity(), mList);
		
		mListView.setAdapter(mAdapterResult);
	}
}
