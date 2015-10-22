package database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ModelProcess;
import model.ModelTarget;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("SimpleDateFormat") public class DAOProcess {

private DatabaseHelper mSQLiteHelper = null;
	
	public DAOProcess(Context pContext){
		mSQLiteHelper = new DatabaseHelper(pContext);
	}
	
	public void insertProcess(ModelProcess pModelProcess){
		DateFormat pDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = pDateFormat.format(pModelProcess.getDate());
		String pTargetName = pModelProcess.getTargetName();
		int pHours = pModelProcess.getHours();
		String pRemark = pModelProcess.getRemark();
		String sql = "insert into Process (TargetName, Date, Hours, Remark) values ('" + pTargetName + "', '" + pDate + "', '" + pHours + "', '" + pRemark + "');";
		
		try{
			SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
			pDB.execSQL(sql);	
		}catch (Exception e){
			System.out.println("Error:" + e.toString());
			e.printStackTrace();
		}
		
	}
	
	public List<ModelProcess> getProcess(String pCondition, Context pContext){
		String pSqlTarget = "Select * From Target";
		SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
		Cursor pCursor = pDB.rawQuery(pSqlTarget, null);
		List<ModelProcess> pList = new ArrayList<ModelProcess>();
		pCursor.moveToFirst();
		for(int i = 0; i < pCursor.getCount(); i++){;
			List<ModelProcess> pListTarget = getProcessByTarget(pCursor.getString(1));
			for(int j = 0; j < pListTarget.size(); j ++){
				pList.add(pListTarget.get(j));
				pCursor.moveToNext();
			}
		}
		
		pCursor.close();
		return pList;
		
	}
	
	public List<ModelProcess> getProcessByTarget(ModelTarget pTarget){
		return getProcessByTarget(pTarget.getTargetName());
	}
	
	public List<ModelProcess> getProcessByTarget(String pTargetName){
		String pSql = "Select * From Process Where TargetName = '" + pTargetName + "' Order By Date";
		SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
		Cursor pCursor = pDB.rawQuery(pSql, null);
		List<ModelProcess> pList = new ArrayList<ModelProcess>();
		for(int i = 0; i < pCursor.getCount(); i++){
			ModelProcess pModelProcess = new ModelProcess();
			pModelProcess.setTargetName(pCursor.getString(1));
			pModelProcess.setDate(pCursor.getString(2));
//			int pHours = Integer.valueOf(pCursor.getShort(3)).intValue();
			pModelProcess.setHours(Integer.valueOf(pCursor.getShort(3)).intValue());
			pModelProcess.setRemark(pCursor.getString(4));
			
			pList.add(pModelProcess);
			pCursor.moveToNext();
		}
		
		pCursor.close();
		return pList;
	}
	
	public ModelProcess getSelectedProcess(Date pDate, String pTargetName){
		DateFormat pDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDateString = pDateFormat.format(pDate);
		
		return getSelectedProcess(pDateString, pTargetName);
	}
	
	public ModelProcess getSelectedProcess(String pStrDate, String pTargetName){
		String pSql = "Select * From Process Where TargetName = '" + pTargetName + "' and Date = '" + pStrDate + "';";
		SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
		Cursor pCursor = pDB.rawQuery(pSql, null);

		ModelProcess pModelProcess = new ModelProcess();
		pCursor.moveToFirst();
		
		pModelProcess.setTargetName(pCursor.getString(1));
		pModelProcess.setDate(pCursor.getString(2));
		pModelProcess.setHours(Integer.valueOf(pCursor.getShort(3)).intValue());
		pModelProcess.setRemark(pCursor.getString(4));
		
		pCursor.close();
		
		return pModelProcess;
	}
	
	public void updateProcess(ModelProcess pModelProcess){
		DateFormat pDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDate = pDateFormat.format(pModelProcess.getDate());
		String pTargetName = pModelProcess.getTargetName();
		int pHours = pModelProcess.getHours();
		String pRemark = pModelProcess.getRemark();
		
		String pSQL = "Update Process SET Hours = '" + pHours + "', Remark = '" + pRemark + "' WHERE Date = '" + pDate + "' and TargetName = '" + pTargetName + "';";
		
		try{
			SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
			pDB.execSQL(pSQL);
			
		}catch (Exception e){
			System.out.println("Error:" + e.toString());
			e.printStackTrace();
		}
	}
	
	public void deleteProcess(ModelProcess pModelProcess){
		DateFormat pDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pDateString = pDateFormat.format(pModelProcess.getDate());
		String pTargetName = pModelProcess.getTargetName();
		String pSQL = "Delete FROM Process WHERE Date = '" + pDateString + "' and TargetName = '" + pTargetName + "';";
				
		try{
			SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
			pDB.execSQL(pSQL);
			
		}catch (Exception e){
			System.out.println("Error:" + e.toString());
			e.printStackTrace();
		}
	}
	
}
