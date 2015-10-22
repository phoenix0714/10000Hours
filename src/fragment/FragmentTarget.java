package fragment;

import java.util.ArrayList;
import java.util.List;

import model.ModelTarget;
import adapter.AdapterTarget;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.hours.R;

import database.DAOTarget;

public class FragmentTarget extends Fragment {
		
		// list view
		private ListView mListView;
		private List<ModelTarget> mList;
		private AdapterTarget mAdapterTarget;
		
		// buttons
		private Button mAdd, mUpdate, mDelete;
		
		private String mSelectedTargetName;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub

			View pView = inflater.inflate(R.layout.fragment_target, container, false);
			mAdd = (Button)pView.findViewById(R.id.btTargetAdd);
			mUpdate = (Button)pView.findViewById(R.id.btTargetUpdate);
			mDelete = (Button)pView.findViewById(R.id.btTargetDelete);
			mListView = (ListView)pView.findViewById(R.id.lvTargetContent);
			
			// value of mSelectedTargetName
			mListView.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					View pView = mListView.getChildAt(position);
					TextView pTextView = (TextView)(pView.findViewById(R.id.tvTargetItem));
					mSelectedTargetName = pTextView.getText().toString();
					
				}
			});
			
			return pView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			// display targets in SQLite
			DAOTarget pDAOTarget = new DAOTarget(getActivity());
			mList = new ArrayList<ModelTarget>(pDAOTarget.getTarget("and 1=1"));
			
//			mListView.setDividerHeight(0);
			mAdapterTarget = new AdapterTarget(getActivity(), mList);
			mListView.setAdapter(mAdapterTarget);
			
			mAdd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					final EditText pEdit = new EditText(getActivity());
					new AlertDialog.Builder(getActivity()).setTitle("Input a new Target name:")
						.setView(pEdit)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								String pName = pEdit.getText().toString();
								DAOTarget pDAOTarget = new DAOTarget(getActivity());
								pDAOTarget.insertTarget(pName);
								Toast.makeText(getActivity(), pName + " Inserted Successfully.", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("Cancel", null).show();
				}
			});
			
			mUpdate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					final EditText pEdit = new EditText(getActivity());
					pEdit.setText(mSelectedTargetName);

					new AlertDialog.Builder(getActivity()).setTitle("Update the Target name:")
						.setView(pEdit)
						.setPositiveButton("Update", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								String pNameOld = mSelectedTargetName;
								String pNameNew = pEdit.getText().toString();
								DAOTarget pDAOTarget = new DAOTarget(getActivity());
								pDAOTarget.updateTarget(pNameOld, pNameNew);
								Toast.makeText(getActivity(), "Target name updated Successfully from " + pNameOld + " to " + pNameNew + ".", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("Cancel", null).show();
					
				}
			});
			
			mDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String pMessage = "Are you sure to delete " + mSelectedTargetName + "?";
					new AlertDialog.Builder(getActivity()).setTitle("WARNING!")
						.setMessage(pMessage)
						.setPositiveButton("YES", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								DAOTarget pTarget = new DAOTarget(getActivity());
								pTarget.deleteTarget(mSelectedTargetName);
								Toast.makeText(getActivity(), mSelectedTargetName + " deleted successfully.", Toast.LENGTH_SHORT).show();
							}
						})
						.setNegativeButton("NO", null).show();
				}
			});
			
 		}
}
