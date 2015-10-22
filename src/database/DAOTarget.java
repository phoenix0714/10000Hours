package database;

import java.util.ArrayList;
import java.util.List;

import model.ModelTarget;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOTarget {
	
	private DatabaseHelper mSQLiteHelper = null;
	
	public DAOTarget(Context pContext){
		mSQLiteHelper = new DatabaseHelper(pContext);
	}
	
	public void insertTarget(ModelTarget pModelTarget){
		String pTargetName = pModelTarget.getTargetName();
		insertTarget(pTargetName);
	}
	
	public void insertTarget(String pTargetName){
		String sql = null;
		if(null != pTargetName){
			
			sql = "insert into Target (TargetName) values ('" + pTargetName + "');";
			
			try{
				SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
				pDB.execSQL(sql);
			}catch (Exception e){
				System.out.println("Error[DAOTarget]:" + e.toString());
				e.printStackTrace();
			}
		}
	}
	
	public List<ModelTarget> getTarget(String pCondition){
		
		if(null != pCondition){
			
			String pSql = "Select * From Target Where 1=1 " + pCondition;
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
		
		else{
			return null;
		}
	}
	
	public void updateTarget(ModelTarget pOldTarget, ModelTarget pNewTarget){
		String pOldTargetName = pOldTarget.getTargetName();
		String pNewTargetName = pNewTarget.getTargetName();
		
		updateTarget(pOldTargetName, pNewTargetName);
	}
	
	public void updateTarget(String pOldName, String pNewName){
		
		if(null != pOldName && null != pNewName){
			String pSQL = "Update Target SET TargetName = '" + pNewName + "' WHERE TargetName = '" + pOldName + "' ;";	
			try{
				SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
				pDB.execSQL(pSQL);
			}catch (Exception e){
				System.out.println("Error[DAOTarget]:" + e.toString());
				e.printStackTrace();
			}
		}
	}
	
	public void deleteTarget(ModelTarget pModelTarget){
		deleteTarget(pModelTarget.getTargetName());
	}
	
	public void deleteTarget(String pName){
		
		if(null != pName){
			String pSQL = "Delete FROM Target WHERE TargetName = '" + pName + "';";
			try{
				SQLiteDatabase pDB = mSQLiteHelper.getReadableDatabase();
				pDB.execSQL(pSQL);
			}catch (Exception e){
				System.out.println("Error[DAOTarget]:" + e.toString());
				e.printStackTrace();
			}
		}
	}
}
