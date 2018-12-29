package com.example.open_api;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_PATH = "/data/data/com.example.open_api/databases/";
	// Database Name
	private static final String DATABASE_NAME = "ParkInformation.db";
	MyDBHelper mHelper;
	SQLiteDatabase db;
	Cursor cursor;
	MyCursorAdapter myAdapter;

	final static String KEY_ID = "_id";
	final static String KEY_NAME = "name";
	final static String KEY_MEMO = "memo";
	final static String TABLE_NAME = "mytable";

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, 2);
	}

    // Set Primary key with AUTOINCREMENT
	public void onCreate(SQLiteDatabase db) {
		String query = String.format("CREATE TABLE %s ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s TEXT, "
				+ "%s TEXT );", TABLE_NAME, KEY_NAME, KEY_MEMO);
		db.execSQL(query);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
		db.execSQL(query);
		onCreate(db);
	}

}