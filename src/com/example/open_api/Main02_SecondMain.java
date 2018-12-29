package com.example.open_api; 

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * The Main shown 1.5 sec after the first.
 * 
 * @author UOS
 *
 */
public class Main02_SecondMain extends Activity {

	public static final int REQUEST_CODE_ANOTHER = 1001;
	public static String PREF_ID = "Pref01";
	public static final int actMode = Activity.MODE_PRIVATE;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_main);

		Button cl = (Button) findViewById(R.id.location); // ������ġ ��ư
		cl.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent0 = new Intent(getBaseContext(),
						Main03_Map01.class);
				startActivityForResult(intent0, REQUEST_CODE_ANOTHER);
			}
		});

		Button ss = (Button) findViewById(R.id.si); // �ü� ��ư
		ss.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent1 = new Intent(getBaseContext(),
						Main03_Fac01.class);
				startActivityForResult(intent1, REQUEST_CODE_ANOTHER);
			}
		});
		Button kw = (Button) findViewById(R.id.park);
		kw.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent2 = new Intent(getBaseContext(),
						Main03_Park01_List.class);
				startActivityForResult(intent2, REQUEST_CODE_ANOTHER);
			}
		});

		Button like = (Button) findViewById(R.id.like);
		like.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent3 = new Intent(getBaseContext(),
						Main03_Like01.class);
				startActivityForResult(intent3, REQUEST_CODE_ANOTHER);
			}
		});

	}
}
