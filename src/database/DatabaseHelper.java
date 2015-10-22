package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "HoursDatabase";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context pContext, String pName, CursorFactory pFactory, int pVersion){
		super(pContext, pName, pFactory, pVersion);
	}
	
	public DatabaseHelper(Context pContext){
		super(pContext, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// need to be improved
		// TODO Auto-generated method stub
//		StringBuilder pCreateTarget = new StringBuilder();
//		StringBuilder pCreateProcess = new StringBuilder();
//		
//		pCreateTarget.append("	 Create Table IF NOT EXISTS Target(");
//		pCreateTarget.append("			[TargetID] integer PRIMARY KEY AUTOINCREMENT");
//		pCreateTarget.append("			,[TargetName] varchar(50)");
//		pCreateTarget.append("			);");
//		
//		pCreateProcess.append("	 Create Table IF NOT EXISTS Process(");
//		pCreateProcess.append("			[ProcessID] integer PRIMARY KEY AUTOINCREMENT");
//		pCreateProcess.append("			,[TargetName] varchar(50)");
//		pCreateProcess.append("			,[Date] date");
//		pCreateProcess.append("			,[Hours] integer");
//		pCreateProcess.append("			,[Remark] text");
//		pCreateProcess.append("			);");
//		
//		try{
//			db.execSQL(pCreateTarget.toString());
//			db.execSQL(pCreateProcess.toString());
//
//		} catch(Exception e){
//			e.printStackTrace();
//		}

		// a method to create multiple tables in one db.exec
		String pSQLTarget = "Create Table IF NOT EXISTS Target(" +
				"[TargetID] integer PRIMARY KEY AUTOINCREMENT" +
				",[TargetName] varchar(50)" +
				");";
		String pSQLProcess = "Create Table IF NOT EXISTS Process(" +
				"[ProcessID] integer PRIMARY KEY AUTOINCREMENT" +
				",[TargetName] varchar(50)" +
				",[Date] date" +
				",[Hours] integer" +
				",[Remark] text" +
				");";
		
		String[] pStatement = new String[]{pSQLTarget, pSQLProcess};
		for(String pSQL : pStatement){
			try{
				db.execSQL(pSQL);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("Drop Table if Exists Target;");
		db.execSQL("Drop Table if Exists Process;");
		onCreate(db);
		
	}

}
