package com.example.open_api;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class Main03_Park02_Inform extends Activity {

	Button b1;
	Button b2;
	WebView wv1;
	WebView wv2;
	WebView wv3;
	TextView contact;
	SqlLiteDbHelper dbHelper = new SqlLiteDbHelper(this);
	Contact c_park;
	Contact c_food;
	Cursor cursor;

	Cursor cursor2;
	ListView listView;
	IconTextListAdapter adapter;

	IconTextListAdapter adapter2;
	InputMethodManager imm;

	SQLiteDatabase db;
	String sql;
	String txta;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.park_main);

		TextView pname;
		pname = (TextView) findViewById(R.id.txt);

		Intent send = getIntent();
		txta = send.getStringExtra("pname");
		pname.setText(txta);

		wv1 = (WebView) findViewById(R.id.wv1);
		wv2 = (WebView) findViewById(R.id.wv2);

		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();

		dbHelper = new SqlLiteDbHelper(this); // db
		try {
			dbHelper.CopyDataBaseFromAsset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbHelper.openDataBase();

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		// Adapter
		adapter = new IconTextListAdapter(this);
		
		// Tab 1
        TabSpec ts1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1)
                .setIndicator("공원 소개");
        tabHost.addTab(ts1);

        TextView pintro = (TextView) findViewById(R.id.txt1_1);
        TextView pinform = (TextView) findViewById(R.id.txt1_2);

        c_park = new Contact();
        c_park = dbHelper.Get_ContactDetails(txta);

        pintro.setText(c_park.getPInfo1());
        pinform.setText(c_park.getPInfo2());

        // Tab 2
        TabSpec ts2 = tabHost.newTabSpec("Tab2").setContent(R.id.tab2)
                .setIndicator("오시는 길");
        tabHost.addTab(ts2);
        wv1.loadUrl(c_park.getPUrl());

        // Tab 3
        TabSpec ts3 = tabHost.newTabSpec("Tab3").setContent(R.id.tab3)
                .setIndicator("시설 예약");
        tabHost.addTab(ts3);
        wv2.loadUrl(c_park.getPUrl2());

        // Tab 4
        TabSpec ts4 = tabHost.newTabSpec("Tab4").setContent(R.id.tab4)
                .setIndicator("편의 시설");
        tabHost.addTab(ts4);
        final Button b3_1 = (Button) findViewById(R.id.btn3_1);
        final Button b3_2 = (Button) findViewById(R.id.btn3_2);
        b3_1.setEnabled(true);
        b3_2.setEnabled(true);
        b3_1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                b3_1.setEnabled(false);
                b3_2.setEnabled(true);
                ChooseFacCategory();
            }
        });

        b3_2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                adapter.clear();
                adapter.notifyDataSetChanged();
                b3_1.setEnabled(true);
                b3_2.setEnabled(false);
                ChooseFoodCatecory();

            };
        });

        // Tab 5
        TabSpec ts5 = tabHost.newTabSpec("Tab5").setContent(R.id.tab5)
                .setIndicator("SNS로 보내기");
        tabHost.addTab(ts5);
        final String txtb = send.getStringExtra("pname");
        pname.setText(txtb);
        ListView list = (ListView) findViewById(R.id.list2); // ListView object
        adapter2 = new IconTextListAdapter(this);

        // Make item data
        Resources res = getResources();
        adapter2.addItem(new IconTextItem(res.getDrawable(R.drawable.i2),
                "페이스북", "한강공원 페이스북으로 이동"));
        adapter2.addItem(new IconTextItem(res.getDrawable(R.drawable.i1),
                "트위터", "한강공원 트위터로 이동"));
        list.setAdapter(adapter2);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
                // TODO Auto-generated method stub

                WebView wv4 = (WebView) findViewById(R.id.wv4);
                WebSettings set = wv4.getSettings();

                IconTextItem curItem = (IconTextItem) adapter2
                        .getItem(position);
                String[] curData = curItem.getData();
                if (curData[0].equals("페이스북")) {
                    set.setJavaScriptEnabled(true); // Enable JavaScript
                    wv4.loadUrl(c_park.getPFacebook());
                }

                if (curData[0].equals("트위터")) {
                    set.setJavaScriptEnabled(true); // Enable JavaScript
                    wv4.loadUrl("https://mobile.twitter.com/seoulhangang");
                }

            }

        });

    }

    protected void ChooseFacCategory() {
        Button chicken = (Button) findViewById(R.id.chicken);
        Button pizza = (Button) findViewById(R.id.pizza);
        Button jjajang = (Button) findViewById(R.id.jjajang);
        chicken.setVisibility(View.GONE);
        pizza.setVisibility(View.GONE);
        jjajang.setVisibility(View.GONE);

        TextView pname;
        pname = (TextView) findViewById(R.id.txt);
        Intent send = getIntent();

        final String txta = send.getStringExtra("pname");
        pname.setText(txta);
        Cursor cursor1 = dbHelper.FacByPark(txta);
        HandleCursorData3_1(cursor1);

    }

    protected void ChooseFoodCatecory() {
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        Button chicken = (Button) findViewById(R.id.chicken);
        Button pizza = (Button) findViewById(R.id.pizza);
        Button jjajang = (Button) findViewById(R.id.jjajang);

        chicken.setVisibility(View.VISIBLE);
        pizza.setVisibility(View.VISIBLE);
        jjajang.setVisibility(View.VISIBLE);
        TextView pname = (TextView) findViewById(R.id.txt);
        Intent send = getIntent();

        final String txta = send.getStringExtra("pname");
        pname.setText(txta);
        // TODO Auto-generated method stub

        chicken.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Cursor cursor2 = dbHelper.FoodByPark(txta);
                HandleCursorData3_2(cursor2);

            }
        });

        jjajang.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Cursor cursor3 = dbHelper.FoodByPark(txta);
                HandleCursorData3_3(cursor3);
            }
        });

        pizza.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Cursor cursor4 = dbHelper.FoodByPark(txta);
                HandleCursorData3_4(cursor4);
            }
        });

    }

    public void HandleCursorData3_1(Cursor outCursor) {
        Toast.makeText(getApplicationContext(), "리스트 클릭 시 더 큰 화면에서 볼 수 있습니다.",
                Toast.LENGTH_LONG).show();
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        int recordCount = outCursor.getCount();

        // get column index
        int fnameCol = outCursor.getColumnIndex("fac_name");

        Resources res = getResources();
        adapter.clear();
        for (int i = 0; i < recordCount; i++) {
            outCursor.moveToNext();
            String fname = outCursor.getString(fnameCol);

            adapter.addItem(new IconTextItem(res
                    .getDrawable(R.drawable.pin_ballon_arrow), fname, ""));
            list.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                        int position, long id) {
                    // TODO Auto-generated method stub
                    IconTextItem curItem = (IconTextItem) adapter
                            .getItem(position);
                    String[] curData = curItem.getData();
                    Intent i = new Intent(Main03_Park02_Inform.this,
                            Main03_Park03_FacDetail.class); // Move from 03 to 04
                    i.putExtra("fname", curData[0]); // Send data through intent
                    i.putExtra("pname", txta);
                    startActivity(i);
                }
            });
        }
        outCursor.close();
    }

    public void HandleCursorData3_2(Cursor outCursor) {

        Toast.makeText(getApplicationContext(), "리스트 클릭 시 전화로 바로 연결됩니다.",
                Toast.LENGTH_LONG).show();
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.clearChoices();
        int recordCount = outCursor.getCount();

        // get column index
        int aCol = outCursor.getColumnIndex("food_category");
        int bCol = outCursor.getColumnIndex("food_name");
        int dCol = outCursor.getColumnIndex("food_call");

        Resources res = getResources();
        adapter.clear();

        for (int i = 0; i < recordCount; i++) {
            outCursor.moveToNext();
            final String fcal = outCursor.getString(dCol);
            String fca = outCursor.getString(aCol);
            String fna = outCursor.getString(bCol);

            if (fca.equals("치킨")) {
                adapter.addItem(new IconTextItem(res
                        .getDrawable(R.drawable.chicken2), fna, fcal));
                list.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {
                        // TODO Auto-generated method stub
                        IconTextItem curItem = (IconTextItem) adapter
                                .getItem(position);
                        String[] curData = curItem.getData();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + curData[1]));
                        startActivity(intent);

                    }

                });
            }
        }
        outCursor.close();

    }

    public void HandleCursorData3_3(Cursor outCursor) {
        Toast.makeText(getApplicationContext(), "리스트 클릭 시 전화로 바로 연결됩니다.",
                Toast.LENGTH_LONG).show();

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.clearChoices();
        int recordCount = outCursor.getCount();

        // get column index
        int aCol = outCursor.getColumnIndex("food_category");
        int bCol = outCursor.getColumnIndex("food_name");
        int dCol = outCursor.getColumnIndex("food_call");
        Resources res = getResources();
        adapter.clear();
        for (int i = 0; i < recordCount; i++) {
            outCursor.moveToNext();
            String fca = outCursor.getString(aCol);
            String fna = outCursor.getString(bCol);
            String fcal = outCursor.getString(dCol);
            if (fca.equals("짜장면")) {
                adapter.addItem(new IconTextItem(res
                        .getDrawable(R.drawable.jjajang), fna, fcal));
                list.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {
                        // TODO Auto-generated method stub
                        IconTextItem curItem = (IconTextItem) adapter
                                .getItem(position);
                        String[] curData = curItem.getData();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + curData[1]));
                        startActivity(intent);

                    }

                });

            }
        }

        outCursor.close();
    }

    public void HandleCursorData3_4(Cursor outCursor) {

        Toast.makeText(getApplicationContext(), "리스트 클릭 시 전화로 바로 연결됩니다.",
                Toast.LENGTH_LONG).show();

        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.clearChoices();
        int recordCount = outCursor.getCount();

        // get column index
        int aCol = outCursor.getColumnIndex("food_category");
        int bCol = outCursor.getColumnIndex("food_name");
        int dCol = outCursor.getColumnIndex("food_call");

        Resources res = getResources();
        adapter.clear();
        for (int i = 0; i < recordCount; i++) {
            outCursor.moveToNext();
            String fca = outCursor.getString(aCol);
            String fna = outCursor.getString(bCol);
            String fcal = outCursor.getString(dCol);
            if (fca.equals("피자")) {
                adapter.addItem(new IconTextItem(res
                        .getDrawable(R.drawable.pizza), fna, fcal));
                list.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                            int position, long id) {
                        // TODO Auto-generated method stub
                        IconTextItem curItem = (IconTextItem) adapter
                                .getItem(position);
                        String[] curData = curItem.getData();

                        Intent intent = new Intent(Intent.ACTION_DIAL);

                        intent.setData(Uri.parse("tel:" + curData[1]));
                        startActivity(intent);

                    }

                });

            }
        }

        outCursor.close();
    }

}
