package com.example.open_api;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
	MyDBHelper mHelper;
	SQLiteDatabase db;
	Cursor cursor;
	MyCursorAdapter myAdapter;

	final static String KEY_ID = "_id";
	final static String KEY_NAME = "name";
	final static String KEY_MEMO = "memo";
	final static String TABLE_NAME = "mytable";

	@SuppressWarnings("deprecation")
	public MyCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView pName = (TextView) view.findViewById(R.id.p_name);
		TextView pMemo = (TextView) view.findViewById(R.id.p_memo);

		String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
		String memo = cursor.getString(cursor.getColumnIndex(KEY_MEMO));

		Log.d("Check String", name + ", " + memo);

		pName.setText(name);
		pMemo.setText(memo);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.list_item, parent, false);
		return v;
	}

}
