package com.example.open_api;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Main03_Park03_FacDetail extends Activity {
	TextView txtMsg;
	Contact c_park;
	TextView info;
	TextView call;
	TextView pname;
	String txtb;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fac_detail_main);

		pname = (TextView) findViewById(R.id.txt);

		Intent send = getIntent();
		String txta = send.getStringExtra("pname");
		txtb = send.getStringExtra("fname");
		pname.setText(txta + "of " + txtb);

		info = (TextView) findViewById(R.id.txt2);
		call = (TextView) findViewById(R.id.txt4);

		SqlLiteDbHelper dbHelper = new SqlLiteDbHelper(this);

		try {
			dbHelper.CopyDataBaseFromAsset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.openDataBase();

		Cursor cursor = dbHelper.FacByPark(txta);
		HandleCursorData(cursor);

	}

	private void HandleCursorData(Cursor outCursor) {
		// TODO Auto-generated method stub
		int recordCount = outCursor.getCount();
		// get column index
		int fnameCol = outCursor.getColumnIndex("fac_name");
		int finfoCol = outCursor.getColumnIndex("fac_info");
		int fcallCol = outCursor.getColumnIndex("fac_call");

		for (int i = 0; i < recordCount; i++) {

			outCursor.moveToNext();
			String fname = outCursor.getString(fnameCol);
			String finfo = outCursor.getString(finfoCol);
			String fcall = outCursor.getString(fcallCol);

			if (fname.equals(txtb)) {
				info.setText(finfo + "\n\n");
				call.setText(fcall);
			}

		}
	}

}
