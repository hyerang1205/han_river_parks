package com.example.open_api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main03_Like01 extends Activity {
	MyDBHelper mHelper;
	SQLiteDatabase db;
	Cursor cursor;
	MyCursorAdapter myAdapter;
	ListView list;

	final static String KEY_ID = "_id";
	final static String KEY_NAME = "name";
	final static String KEY_MEMO = "memo";
	final static String TABLE_NAME = "mytable";

	final static String querySelectAll = String.format("SELECT * FROM %s",
			TABLE_NAME);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.like_main);

		list = (ListView) findViewById(R.id.lv_name_age);

		mHelper = new MyDBHelper(this);
		db = mHelper.getWritableDatabase();

		cursor = db.rawQuery(querySelectAll, null);
		myAdapter = new MyCursorAdapter(this, cursor);

		list.setAdapter(myAdapter);
		list.setOnItemLongClickListener(onItemLongClickEvent);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				String move_name = ((Cursor) myAdapter.getItem(arg2))
						.getString(1);
				Intent i = new Intent(Main03_Like01.this,
						Main03_Park02_Inform.class); // Move from 03 to  04
				i.putExtra("pname", move_name); // send data through intent
				startActivity(i);
			}

		});

	}

	public void mOnClick(View v) {
		EditText eName = (EditText) findViewById(R.id.et_name);
		EditText eMemo = (EditText) findViewById(R.id.et_memo);

		final String name = eName.getText().toString();

		String memo = eMemo.getText().toString();

		String query = String.format(
				"INSERT INTO %s VALUES ( null, '%s', '%s' );", TABLE_NAME,
				name, memo);
		db.execSQL(query);

		cursor = db.rawQuery(querySelectAll, null);
		myAdapter.changeCursor(cursor);

		eName.setText("");
		eMemo.setText("");

		// Hide keyboard after save
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(eMemo.getWindowToken(), 0);

	}

	// ListView event for long click
	OnItemLongClickListener onItemLongClickEvent = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// Show dialog after long click event happens
			ShowMenu(myAdapter.getItem(arg2));
			return true;
		}
	};

	// Long click -> Pop-up to ask if user wants deleting 
	private void ShowMenu(final Object object) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(((Cursor) object).getString(1));
		alert.setMessage("Do you want to delete the memo?");
		alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String query = String.format("DELETE FROM " + TABLE_NAME
						+ " WHERE " + "name" + " =  " + "'"
						+ ((Cursor) object).getString(1) + "'");
				db.execSQL(query);

				cursor = db.rawQuery(querySelectAll, null);
				myAdapter.changeCursor(cursor);
			}
		});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		alert.show();
	}
}