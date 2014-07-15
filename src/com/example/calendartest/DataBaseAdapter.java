package com.example.calendartest;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DataBaseAdapter {
	
	private static final String TAG = "DataBaseAdapter";
	private static final String DB_NAME = "Task_DB";
	private static final String TABLE_NAME = "table_main";
	private static final int DB_VERSION = 10;
	
	public static final String KEY_ID = "num";
	public static final String KEY_TASK = "task";
	public static final String KEY_FOLDER = "folder";
	public static final String KEY_PRIORITY = "priority";
	public static final String KEY_TIME = "time";
	public static final String KEY_NUM = "numer";
	
	private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("+ KEY_ID+ " INTEGER PRIMARY KEY," + KEY_TASK + " TEXT," + KEY_FOLDER + " TEXT," + KEY_PRIORITY + " TEXT," + KEY_TIME + " TEXT,"+ KEY_NUM + " INTEGER)";
	
	private SQLiteDatabase database = null ;
	private CustomSQLiteOpenHelper helper = null;
	private Context mContext;
	
	private static class CustomSQLiteOpenHelper extends SQLiteOpenHelper{
		//默认构造器
		 CustomSQLiteOpenHelper(Context context){
			 super(context,DB_NAME,null,DB_VERSION);
		 }
		 //创建数据表
		 @Override
		 public void onCreate(SQLiteDatabase db){
			 db.execSQL(DB_CREATE);
		 }
		 //升级数据库
		 @Override
		 public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
			 db.execSQL("DROP TABLE IF EXISTS notes");
			 onCreate(db);
		 }
	}
	
	public DataBaseAdapter(Context context){		
		 mContext = context;	 
	}
	
	public void open() throws SQLException{
		helper = new CustomSQLiteOpenHelper(mContext);
		database = helper.getWritableDatabase();
	}
	
	public void close(){
		helper.close();
	}
	
	public long insertData(String task,String folder,String priority,String time ,int num){
		ContentValues cv = new ContentValues();
		cv.put(KEY_TASK, task);
		cv.put(KEY_FOLDER, folder);
		cv.put(KEY_PRIORITY, priority);
		cv.put(KEY_TIME, time);
		cv.put(KEY_NUM, num);
		return database.insert(TABLE_NAME, KEY_ID, cv);	
	}
	
	public boolean deleteData(int id){
		return database.delete(TABLE_NAME, KEY_NUM+"="+id, null)>0;
	}
	
	public boolean updateData(String task,String folder,String priority,String time,int id){
		ContentValues cv = new ContentValues();
		cv.put(KEY_TASK, task);
		cv.put(KEY_FOLDER, folder);
		cv.put(KEY_PRIORITY, priority);
		cv.put(KEY_TIME, time);
		return database.update(TABLE_NAME, cv, KEY_NUM+"="+id, null)>0;
	}
	
	public Cursor fetchData(int id) throws SQLException{
		Cursor mCursor = database.query( TABLE_NAME, new String[]{KEY_TASK,KEY_FOLDER,KEY_PRIORITY,KEY_TIME,KEY_NUM}, KEY_NUM+"="+id, null, null, null, null, null);
		if(mCursor!=null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public Cursor fetchAllData() throws SQLException{
		Cursor mCursor = database.query( TABLE_NAME, new String[]{KEY_TASK,KEY_FOLDER,KEY_PRIORITY,KEY_TIME,KEY_NUM}, null, null, null, null, null);
		return mCursor;
	}
	
	
}
