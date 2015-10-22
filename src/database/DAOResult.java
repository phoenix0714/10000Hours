package database;

import java.util.ArrayList;
import java.util.List;

import model.ModelProcess;
import model.ModelResult;
import model.ModelTarget;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOResult {

	private DatabaseHelper mSQLiteHelper = null;
	
	public DAOResult(Context pContext){
		mSQLiteHelper = new DatabaseHelper(pContext);
	}
	
	public List<ModelResult> getAllResult(){
		List<ModelTarget> pTargetList = getAllTargetName(" and 1=1");
		List<ModelResult> pResultList = new ArrayList<ModelResult>();
		
		for (int i = 0; i < pTargetList.size(); i ++){
			ModelResult pModelResult = new ModelResult();
			String pTargetName = pTargetList.get(i).getTargetName();
			int pHours = getTotalHoursByTargetName(pTargetName);
			
			pModelResult.setTargetName(pTargetName);
			pModelResult.setTotalHours(pHours);
			if(pHours == 0){
				pModelResult.setList(null);
			}else{
				pModelResult.setList(getProcessByTarget(pTargetName));
			}
			
			pResultList.add(pModelResult);
			
		}
		return pResultList;
	}
	
	public List<ModelProcess> getProcessByTarget(String pTargetName){
		String pSql = "Select * From Process Where TargetName = '" + pTargetName + "' Order By Date" + ";";
		SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
		Cursor pCursor = null;// = pDB.rawQuery(pSql, null);
		List<ModelProcess> pList = null;// = new ArrayList();
		try{
			pCursor = pDB.rawQuery(pSql, null);
			pList = new ArrayList<ModelProcess>();
			pCursor.moveToFirst();
		}catch(Exception e){
			System.out.println("Error[DAOTarget]:" + e.toString());
			e.printStackTrace();
		}
		for(int i = 0; i < pCursor.getCount(); i++){
			ModelProcess pModelProcess = new ModelProcess();
			pModelProcess.setTargetName(pCursor.getString(1));
			pModelProcess.setDate(pCursor.getString(2));
			pModelProcess.setHours(Integer.valueOf(pCursor.getString(3)).intValue());
			pModelProcess.setRemark(pCursor.getString(4));
			
			pList.add(pModelProcess);
			pCursor.moveToNext();
		}
		
		pCursor.close();
		return pList;
	}
	
	public List<ModelTarget> getAllTargetName(String pCondition){
		
		if(null == pCondition){
			return null;
		}else {
			String pSql = "Select * From Target Where 1=1" + pCondition;
			List<ModelTarget> pList = new ArrayList<ModelTarget>();
			
			SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
			Cursor pCursor = null;
			
			try{
				pCursor = pDB.rawQuery(pSql, null);
			}
			catch(Exception e){
				System.out.println("Error[DAOTarget]:" + e.toString());
				e.printStackTrace();
			}
			
			pCursor.moveToFirst();
			for(int i = 0; i < pCursor.getCount(); i++){
				ModelTarget pModelTarget = new ModelTarget();
				pModelTarget.setTargetName(pCursor.getString(1));
				pList.add(pModelTarget);
				pCursor.moveToNext();
			}

			pCursor.close();
			
			return pList;
		}
	}
	
	public int getTotalHoursByTargetName(String pTargetName){
		int pHours = 0;
		String pSql = "Select SUM(Hours) From Process Where TargetName = '" + pTargetName + "';";
		SQLiteDatabase pDB;
		Cursor pCursor;
		try{
			pDB = mSQLiteHelper.getReadableDatabase();
			pCursor = pDB.rawQuery(pSql, null);
			if(pCursor.getCount() != 0){
				pCursor.moveToFirst();
				pHours = pCursor.getInt(pCursor.getColumnIndex("SUM(Hours)"));
			}
		}catch (Exception e){
			System.out.println("Error:DAOResult" + e.toString());
			e.printStackTrace();
		}
		
		return pHours;
	}
}
