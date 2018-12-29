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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// Ÿ��Ʋ ���ֱ�
		setContentView(R.layout.fac_main);

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		FacName = (EditText) findViewById(R.id.FacName);
		btnSearch = (Button) this.findViewById(R.id.btnSearch);

		list = (ListView) findViewById(R.id.list); // ����Ʈ�� ��ü ����

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

				// ����Ʈ�信 ����� ����
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

				Intent i = new Intent(Main03_Fac01.this, Main03_Fac02_Map.class); // �Ѿ��
				i.putExtra("pname", curData[0]); // �����͸� intent�� �̿��Ͽ� ����
				i.putExtra("facname", txt); // �����͸� intent�� �̿��Ͽ� ����
				startActivity(i);
			}

		});

	}

	public void HandleCursorData(Cursor outCursor) {
		int recordCount = outCursor.getCount();
		// get column index
		int p_name = outCursor.getColumnIndex("park_name");
		// ������ ������ �����
		Resources res = getResources();
		int a = 0;
		adapter.clear();
		for (int i = 0; i < recordCount; i++) {
			outCursor.moveToNext();
			String pnamecode = outCursor.getString(p_name);

			if (pnamecode.equals("������ ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_knr), "������ ����",
						"�ֿ�ü� : ��� ���¡���� ��������"));
			}
			if (pnamecode.equals("��� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_js), "��� ����",
						"�ֿ�ü� : ��Ǽ��ߺ�, ��Ǽ��ߺ� ������"));
			}
			if (pnamecode.equals("�Ҽ� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ds), "�Ҽ� ����",
						"�ֿ�ü� : �Ҽ� ��õ����(��õ�м�, �ΰ��Ϻ�), ������, ���Ǻм�, X������, ��������"));
			}
			if (pnamecode.equals("��� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_jw), "��� ����", "�ֿ�ü� : �ڿ��н���"));
			}
			if (pnamecode.equals("���� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_bp), "���� ����",
						"�ֿ�ü� : �޺������� �м�, ������"));
			}
			if (pnamecode.equals("���� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ic), "���� ����",
						"�ֿ�ü� : �źϼ�������, û�ҳⱤ��, X������, �ѷ�������Ʈ��"));
			}
			if (pnamecode.equals("���ǵ� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_yed), "���ǵ� ����",
						"�ֿ�ü� : ������, ��Ʈ������, ���� ���°���"));
			}
			if (pnamecode.equals("���� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_mw), "���� ����",
						"�ֿ�ü� : ������, �����̰���"));
			}
			if (pnamecode.equals("���� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_nj), "���� ����",
						"�ֿ�ü� : ķ����, ������������"));
			}
			if (pnamecode.equals("���� ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_ks), "���� ����",
						"�ֿ�ü� : �������� ���°���"));
			}
			if (pnamecode.equals("��ȭ ����")) {
				a = +1;
				adapter.addItem(new IconTextItem(res
						.getDrawable(R.drawable.ic_yh), "��ȭ ����",
						"�ֿ�ü� : ����ǳ�����"));
			}
		}
		if (a == 0) {
			Toast.makeText(getApplicationContext(),
					"���� �ü��Դϴ�. �ٽ� �˻����ֽñ� �ٶ��ϴ�.", 300).show();
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
