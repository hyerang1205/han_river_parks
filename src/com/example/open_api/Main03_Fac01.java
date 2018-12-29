package com.example.open_api;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main03_Fac01 extends Activity {
	public static Activity ctx;
	private EditText FacName;
	private Button btnSearch;

	SqlLiteDbHelper dbHelper = new SqlLiteDbHelper(this);
	Contact c_park;
	ListView list;
	String txt;

	InputMethodManager imm;
	IconTextListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 타이틀 없애기
		setContentView(R.layout.fac_main);

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		FacName = (EditText) findViewById(R.id.FacName);
		btnSearch = (Button) this.findViewById(R.id.btnSearch);

		list = (ListView) findViewById(R.id.list); // 리스트뷰 객체 참조

		adapter = new IconTextListAdapter(this);

		dbHelper = new SqlLiteDbHelper(this);
		try {
			dbHelper.CopyDataBaseFromAsset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.openDataBase();

		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txt = FacName.getText().toString();
				Cursor cursor = dbHelper.ParkByFac(txt);
				HandleCursorData(cursor);

				// 리스트뷰에 어댑터 설정
				list.setAdapter(adapter);
				// hide soft keypad
				imm.hideSoftInputFromWindow(FacName.getWindowToken(), 0);
				FacName.setText("");
			}
		});

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);
				String[] curData = curItem.getData();

				Intent i = new Intent(Main03_Fac01.this, Main03_Fac02_Map.class); // 넘어가자
				i.putExtra("pname", curData[0]); // 데이터를 intent를 이용하여 전달
				i.putExtra("facname", txt); // 데이터를 intent를 이용하여 전달
				startActivity(i);
			}

		});

	}

	public void HandleCursorData(Cursor outCursor) {
		int recordCount = outCursor.getCount();
		// get column index
		int p_name = outCursor.getColumnIndex("park_name");
		// 아이템 데이터 만들기
		Resources res = getResources();
		int a = 0;
		adapter.clear();
		for (int i = 0; i < recordCount; i++) {
			outCursor.moveToNext();
			String pnamecode = outCursor.getString(p_name);

			if (pnamecode.equals("광나루 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_knr), "광나루 공원",
						"주요시설 : 고덕 생태·경관 보전지역"));
			}
			if (pnamecode.equals("잠실 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_js), "잠실 공원",
						"주요시설 : 잠실수중보, 잠실수중보 물고기길"));
			}
			if (pnamecode.equals("뚝섬 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ds), "뚝섬 공원",
						"주요시설 : 뚝섬 벽천마당(벽천분수, 인공암벽), 수영장, 음악분수, X게임장, 수변무대"));
			}
			if (pnamecode.equals("잠원 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_jw), "잠원 공원", "주요시설 : 자연학습장"));
			}
			if (pnamecode.equals("반포 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_bp), "반포 공원",
						"주요시설 : 달빛무지개 분수, 서래섬"));
			}
			if (pnamecode.equals("이촌 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ic), "이촌 공원",
						"주요시설 : 거북선나루터, 청소년광장, X게임장, 롤러스케이트장"));
			}
			if (pnamecode.equals("여의도 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_yed), "여의도 공원",
						"주요시설 : 수영장, 요트마리나, 샛강 생태공원"));
			}
			if (pnamecode.equals("망원 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_mw), "망원 공원",
						"주요시설 : 수영장, 물놀이공간"));
			}
			if (pnamecode.equals("난지 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_nj), "난지 공원",
						"주요시설 : 캠핑장, 강변물놀이장"));
			}
			if (pnamecode.equals("강서 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ks), "강서 공원",
						"주요시설 : 강서습지 생태공원"));
			}
			if (pnamecode.equals("양화 공원")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_yh), "양화 공원",
						"주요시설 : 전원풍경단지"));
			}
		}
		if (a == 0) {
			Toast.makeText(getApplicationContext(),
					"없는 시설입니다. 다시 검색해주시기 바랍니다.", 300).show();
		}
		outCursor.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
