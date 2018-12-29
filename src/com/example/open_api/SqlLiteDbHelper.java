package com.example.open_api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteDbHelper extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_PATH = "/data/data/com.example.open_api/databases/";
	// Database Name
	private static final String DATABASE_NAME = "ParkInformation.db";
	// Contacts table name
	private static final String TABLE1 = "park";
	private static final String TABLE2 = "facility";
	private static final String TABLE3 = "food";
	private static SQLiteDatabase db;
	// Contacts Table Columns names
	private static final String KEY_P_NAME = "park_name";
	private static final String KEY_P_INFO1 = "park_info1";
	private static final String KEY_P_INFO2 = "park_info2";
	private static final String KEY_P_URL = "park_url";
	private static final String KEY_P_URL_2 = "park_url_2";

	private static final String KEY_P_FACEBOOK = "park_facebook";

	private static final String KEY_F_NAME = "fac_name";
	private static final String KEY_F_LOCAX = "fac_locax";
	private static final String KEY_F_LOCAY = "fac_locay";

	Context ctx;

	public SqlLiteDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		ctx = context;
	}

	
	// When user enters the name of the park, give park information
	public Contact Get_ContactDetails(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE1, new String[] { KEY_P_NAME,
				KEY_P_INFO1, KEY_P_INFO2, KEY_P_URL, KEY_P_URL_2,
				KEY_P_FACEBOOK }, KEY_P_NAME + "=?", new String[] { name },
				null, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			Contact cont = new Contact();
			cont.setPName(cursor.getString(0));
			cont.setPInfo1(cursor.getString(1));
			cont.setPInfo2(cursor.getString(2));
			cont.setPUrl(cursor.getString(3));
			cont.setPUrl2(cursor.getString(4));
			cont.setPFaceBook(cursor.getString(5));
			// return contact
			cursor.close();
			db.close();
			return cont;
		}
		return null;
	}

	public Contact Get_ContactFac(String convenience) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE3, new String[] { KEY_F_NAME,
				KEY_F_LOCAX, KEY_F_LOCAY }, KEY_F_NAME + "=?",
				new String[] { convenience }, null, null, null, null);
		if (cursor != null && cursor.moveToFirst()) {
			Contact cont = new Contact();
			cont.setFName(cursor.getString(0));
			cont.setFx(cursor.getDouble(1));
			cont.setFy(cursor.getDouble(2));
			// return contact
			cursor.close();
			db.close();
			return cont;
		}
		return null;
	}

	// Search park by facility
	public Cursor ParkByFac(String strSearchWord) {
		SQLiteDatabase db = this.getReadableDatabase();
		String aSQL = "select p.park_name" + " from facility f, park p"
				+ " where p.park_id=f.park_id and f.fac_name like ?";

		String[] args = { strSearchWord };

		Cursor outCursor1 = db.rawQuery(aSQL, args);

		return (outCursor1);
	}

	// Search facility by park
	public Cursor FacByPark(String strSearchWord) {
		SQLiteDatabase db = this.getReadableDatabase();
		String aSQL = "select f.fac_name, f.fac_info,f.fac_call, f.fac_locax, f.fac_locay"
				+ " from facility f, park p"
				+ " where p.park_id=f.park_id and p.park_name like ?";

		String[] args = { strSearchWord };

		Cursor outCursor1 = db.rawQuery(aSQL, args);

		return (outCursor1);
	}

	// Search food by park
	public Cursor FoodByPark(String strSearchWord) {
		SQLiteDatabase db = this.getReadableDatabase();
		String aSQL = "select fo.food_category, fo.food_name, fo.food_call"
				+ " from food fo, park p"
				+ " where fo.park_id = p.park_id and p.park_name like ?";

		String[] args = { strSearchWord };

		Cursor outCursor1 = db.rawQuery(aSQL, args);

		return (outCursor1);
	}

	// MapofFac
	public Cursor FacByPark2(String strSearchWord) {
		SQLiteDatabase db = this.getReadableDatabase();
		String aSQL = "select f.fac_name, f.fac_info, f.fac_locax, f.fac_locay"
				+ " from facility f, park p"
				+ " where p.park_id=f.park_id and f.fac_name like ?";

		String[] args = { strSearchWord };

		Cursor outCursor1 = db.rawQuery(aSQL, args);

		return (outCursor1);
	}

	public void CopyDataBaseFromAsset() throws IOException {
		InputStream in = ctx.getAssets().open("ParkInformation.db");
		Log.e("sample", "Starting copying");
		String outputFileName = DATABASE_PATH + DATABASE_NAME;
		File databaseFile = new File(
				"/data/data/com.example.open_api/databases");
		// check if databases folder exists, if not create one and its
		// subfolders
		if (!databaseFile.exists()) {
			databaseFile.mkdir();
		}
		OutputStream out = new FileOutputStream(outputFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		Log.e("sample", "Completed");
		out.flush();
		out.close();
		in.close();
	}

	public void openDataBase() throws SQLException {
		String path = DATABASE_PATH + DATABASE_NAME;
		db = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS
						| SQLiteDatabase.CREATE_IF_NECESSARY);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}