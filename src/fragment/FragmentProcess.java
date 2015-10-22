package fragment;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ModelProcess;
import model.ModelTarget;
import adapter.AdapterTarget;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.liu.hours.R;

import database.DAOProcess;
import database.DAOTarget;

@SuppressLint("SimpleDateFormat") public class FragmentProcess extends Fragment {

	// selected values from views
	private String mTargetName;
	private int mHours;
	private Date mDate;
	private String mRemark;
	
	// related views
	private Spinner mSPTargetName;
	private Spinner mSPHours;
	private DatePicker mDPDate;
	private EditText mETRemark;
	
	// related buttons
	private Button mBTAdd;
	private Button mBTUpdate;
	private Button mBTDelete;
	private Button mBTSelect;
	
	//// target spinner
	private AdapterTarget mAdapterTarget;
	private List<ModelTarget> mListTarget;
	
	//// hours spinner
	private ArrayAdapter<String> mAdapterHours;
	private ArrayList<String> mListHours = new ArrayList<String>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_process, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		// views
		mSPTargetName = (Spinner)getActivity().findViewById(R.id.spProcessTarget);
		mSPHours = (Spinner)getActivity().findViewById(R.id.spProcessHour);
		mDPDate = (DatePicker)getActivity().findViewById(R.id.dpProcessDate);
		mETRemark = (EditText)getActivity().findViewById(R.id.etProcessRemark);
		
		mBTAdd = (Button)getActivity().findViewById(R.id.btProcessAdd);
		mBTUpdate = (Button)getActivity().findViewById(R.id.btProcessUpdate);
		mBTDelete = (Button)getActivity().findViewById(R.id.btProcessDelete);
		mBTSelect = (Button)getActivity().findViewById(R.id.btProcessSelect);
		
		
		// display of spinners
		//// setting of spinner target
		DAOTarget pDAOTarget  = new DAOTarget(getActivity());
		mListTarget = new ArrayList<ModelTarget>(pDAOTarget.getTarget("and 1=1"));
		mAdapterTarget = new AdapterTarget(getActivity(), mListTarget);
		
		mSPTargetName.setAdapter(mAdapterTarget);
		

		//// setting of spinner hours
		for (int i = 1; i <= 24; i++){
			mListHours.add(String.valueOf(i));
		}
		mAdapterHours = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, mListHours);
		mAdapterHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mSPHours.setAdapter(mAdapterHours);
		
		// spinner on item click listener
		mSPTargetName.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mTargetName = mListTarget.get(position).getTargetName();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mSPHours.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mHours = Integer.parseInt(mListHours.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// button listeners
		mBTAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// value of mDate
				getSelectedDate();
				
				// value of mRemark
				mRemark = mETRemark.getText().toString();
				
				// new a process model
				ModelProcess pProcess = new ModelProcess();
				pProcess.setDate(mDate);
				pProcess.setHours(mHours);
				pProcess.setTargetName(mTargetName);
				pProcess.setRemark(mRemark);
				
				// insert process model into db
				DAOProcess pDAOProcess = new DAOProcess(getActivity().getApplicationContext());
				pDAOProcess.insertProcess(pProcess);
				
				Toast.makeText(getActivity(), "Process Added Successfully.", Toast.LENGTH_SHORT).show();
			}
		});

		mBTUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// value of mDate
				getSelectedDate();
				
				// value of mRemark
				mRemark = mETRemark.getText().toString();
				
				// new a process model
				ModelProcess pProcess = new ModelProcess();
				pProcess.setDate(mDate);
				pProcess.setHours(mHours);
				pProcess.setTargetName(mTargetName);
				pProcess.setRemark(mRemark);
				
				// update process model into db
				DAOProcess pDAOProcess = new DAOProcess(getActivity().getApplicationContext());
				pDAOProcess.updateProcess(pProcess);
				
				Toast.makeText(getActivity(), "Updated Successfully.", Toast.LENGTH_SHORT).show();
			}
		});
		
		mBTDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// value of mDate
				getSelectedDate();
				
				// new a process model
				ModelProcess pProcess = new ModelProcess();
				pProcess.setDate(mDate);
//				pProcess.setHours(mHours);
				pProcess.setTargetName(mTargetName);
//				pProcess.setRemark(mRemark);
				
				DAOProcess pDAOProcess = new DAOProcess(getActivity().getApplicationContext());
				pDAOProcess.deleteProcess(pProcess);
				
				Toast.makeText(getActivity(), "Deleted Successfully.", Toast.LENGTH_SHORT).show();
			}
		});
		
		// bug in spinner.setSelection
		// solved
		mBTSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSelectedDate();
				
				int pHours;
				String pRemark;
				DAOProcess pDAOProcess = new DAOProcess(getActivity().getApplicationContext());
				ModelProcess pProcess = pDAOProcess.getSelectedProcess(mDate, mTargetName);
				
				pHours = pProcess.getHours() - 1;
				pRemark = pProcess.getRemark();
				
				mSPHours.setSelection(pHours, false);
				mETRemark.setText(pRemark);
			}
		});
		
	}
	
	private void getSelectedDate(){
		int pDateYear = mDPDate.getYear();
		int pDateMonth = mDPDate.getMonth();
		int pDateDay = mDPDate.getDayOfMonth();
		
		String pStrDate = pDateYear + "-" + (pDateMonth + 1) + "-" + pDateDay; 
		SimpleDateFormat pDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			mDate = pDateFormat.parse(pStrDate);
		} catch (ParseException e){
			// TODO Auto-generated catch block
			Toast.makeText(getActivity(), "Date transformation exception", Toast.LENGTH_SHORT).show();
			e.printStackTrace();					
		}

	}
	
}
