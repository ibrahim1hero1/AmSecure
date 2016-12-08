package com.cross.amsecure;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
   @author to  Ibrahim Hanna @2016
               ibrahim.seniore@gmail.com  
*/


public class DBHelper extends SQLiteOpenHelper {

	   public static final String DATABASE_NAME = "AmSecure.db"; 
	   public static final String SITES_TABLE_NAME = "Sites"; 
	   public static final String SITES_COLUMN_ID = "ID"; 
	   public static final String SITES_COLUMN_NAME = "Name"; 
	   public static final String SITES_COLUMN_PASSWORD = "Password"; 
	   public static final int DATABASE_VERSION=1;
	
	   private HashMap sitesMap;
	   
	DBHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		 db.execSQL( 
			      "CREATE TABLE " +SITES_TABLE_NAME+ 
			      " (ID integer primary key, Name text,Password text)" 
			      );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS Sites"); 
	     onCreate(db); 
	}

	
	public boolean inserSite(String name){
	
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		contentValues.put("Name", name);
		contentValues.put("Password", "sadasdfhjkkl");
		 db.insert(SITES_TABLE_NAME, null, contentValues);
		 return true;
		
	}
	
	public boolean insertSite(String name, String passworsd){
		
		
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		contentValues.put("Name", name);
		contentValues.put("Password",passworsd);
		 db.insert(SITES_TABLE_NAME, null, contentValues);
		 return true;
		
	}
	
	public Cursor getSites(){
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(SITES_TABLE_NAME, new String[]{"Name"},
				null, null, null, null, null);
		return cursor;
	}
	
     public Cursor getSiteRow(String name){
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from Sites where Name="+name, null);
		return cursor;
	}
	
	
}
