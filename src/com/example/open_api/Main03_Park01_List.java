package com.example.open_api;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Main03_Park01_List extends Activity {

	ListView list;

	IconTextListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// To get rid of title
		setContentView(R.layout.listpark_main);
		list = (ListView) findViewById(R.id.listview); //List View
		
		// To make item adapters
		adapter = new IconTextListAdapter(this);
		Resources res = getResources();

		adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_knr),
                "광나루 공원", "주요시설 : 고덕 생태·경관 보전지역"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_js),
                "잠실 공원", "주요시설 : 잠실수중보, 잠실수중보 물고기길"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_ds),
                "뚝섬 공원", "주요시설 : 뚝섬 벽천마당(벽천분수, 인공암벽), 수영장, 음악분수, X게임장, 수변무대"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_jw),
                "잠원 공원", "주요시설 : 자연학습장"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_bp),
                "반포 공원", "주요시설 : 달빛무지개 분수, 서래섬"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_ic),
                "이촌 공원", "주요시설 : 거북선나루터, 청소년광장, X게임장, 롤러스케이트장"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_yed),
                "여의도 공원", "주요시설 : 수영장, 요트마리나, 샛강 생태공원"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_mw),
                "망원 공원", "주요시설 : 수영장, 물놀이공간"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_nj),
                "난지 공원", "주요시설 : 캠핑장, 강변물놀이장"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_ks),
                "강서 공원", "주요시설 : 강서습지 생태공원"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.ic_yh),
                "양화 공원", "주요시설 : 전원풍경단지"));

		// To set adapters in the list view
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);
				String[] curData = curItem.getData();

				Intent i = new Intent(Main03_Park01_List.this,
						Main03_Park02_Inform.class); // Move to the information screen
				i.putExtra("pname", curData[0]); // Send data by using intent
				startActivity(i);
			}

		});

	}

}
