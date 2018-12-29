package com.example.open_api;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay.OnStateChangeListener;

public class Main03_Fac02_Map extends NMapActivity implements
		OnMapStateChangeListener, OnMapViewTouchEventListener,
		OnCalloutOverlayListener, LocationListener {

	public static final String API_KEY = "4451c139d13ef141c9c077bda6434ee3 ";
	NMapView mMapView;
	double latPoint = 0;
	double lngPoint = 0;
	NMapController mMapController = null;
	LinearLayout MapContainer;
	private NMapViewerResourceProvider mMapViewerResourceProvider;
	private NMapOverlayManager mOverlayManager;
	SqlLiteDbHelper dbHelper = new SqlLiteDbHelper(this);
	Contact m_fac;
	String txta;
	String txtb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// No title

		setContentView(R.layout.map_main_2);
		TextView pname = (TextView) findViewById(R.id.txt);
		Button bt = new Button(this);
		bt.setBackgroundResource(R.drawable.img);
		Intent send = getIntent();

		txta = send.getStringExtra("pname");
		txtb = send.getStringExtra("facname");
		pname.setText(txta);

		LinearLayout ll = (LinearLayout) findViewById(R.id.map);

		// set a registered API key for Open MapViewer Library
		mMapView = new NMapView(this);
		mMapView.setApiKey(API_KEY); // set the activity content to the map view

		ll.addView(mMapView);

		mMapView.setClickable(true); // register listener for map state changes
		mMapView.setOnMapStateChangeListener(this);
		mMapView.setOnMapViewTouchEventListener(this);
		mMapView.setBuiltInZoomControls(true, null);

		bt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CurrentLocation();
			}
		});

		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
		mOverlayManager = new NMapOverlayManager(this, mMapView,
				mMapViewerResourceProvider);

		dbHelper = new SqlLiteDbHelper(this);
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
		int facxCol = outCursor.getColumnIndex("fac_locax");
		int facyCol = outCursor.getColumnIndex("fac_locay");

		int markerId = NMapPOIflagType.PIN;
		for (int i = 0; i < recordCount; i++) {

			outCursor.moveToNext();
			String fname = outCursor.getString(fnameCol);
			Double facx = outCursor.getDouble((int) facxCol);
			Double facy = outCursor.getDouble((int) facyCol);

			if (fname.equals(txtb)) {

				NGeoPoint loca = new NGeoPoint(facx, facy);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(loca);

				final NMapPOIdata poiData = new NMapPOIdata(1,
						mMapViewerResourceProvider);
				poiData.beginPOIdata(1);
				poiData.addPOIitem(loca, txta + "of " + fname, markerId, 0);

				NMapPOIdataOverlay poiDataOverlay = mOverlayManager
						.createPOIdataOverlay(poiData, null);
				poiDataOverlay
						.setOnStateChangeListener(new OnStateChangeListener() {

							@Override
							public void onCalloutClick(NMapPOIdataOverlay arg0,
									NMapPOIitem arg1) {
								// TODO Auto-generated method stub
								Intent i = new Intent(Main03_Fac02_Map.this,
										Main03_Park02_Inform.class);

								i.putExtra("pname", txta);
								startActivity(i);
							}

							@Override
							public void onFocusChanged(NMapPOIdataOverlay arg0,
									NMapPOIitem arg1) {
							}
						});
				poiDataOverlay.showAllPOIdata(0);
			}
		}

	}

	private void CurrentLocation() {
		// TODO Auto-generated method stub
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,
				(LocationListener) this);
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,
				0, (LocationListener) this);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay arg0,
			NMapOverlayItem arg1, Rect arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLongPress(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongPressCanceled(NMapView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(NMapView arg0, MotionEvent arg1, MotionEvent arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSingleTapUp(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchDown(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchUp(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapCenterChange(NMapView arg0, NGeoPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapCenterChangeFine(NMapView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapInitHandler(NMapView arg0, NMapError arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onZoomLevelChange(NMapView arg0, int arg1) {
		// TODO Auto-generated method stub

	}
}
