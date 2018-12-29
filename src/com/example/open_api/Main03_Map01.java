package com.example.open_api;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

public class Main03_Map01 extends NMapActivity implements
		OnMapStateChangeListener, OnMapViewTouchEventListener,
		OnCalloutOverlayListener, LocationListener {

	public static final String API_KEY = "4451c139d13ef141c9c077bda6434ee3 ";
	protected static final int REQUEST_CODE_ANOTHER = 10;
	NMapView mMapView;
	double latPoint = 0;
	double lngPoint = 0;
	NMapController mMapController = null;
	LinearLayout MapContainer;
	private NMapViewerResourceProvider mMapViewerResourceProvider;
	private NMapOverlayManager mOverlayManager;
	private NMapCalloutOverlay mCalloutOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.map_main);

		AddMap();
	}

	private void AddMap() {
		// TODO Auto-generated method stub
		RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.map);
		Button bt = new Button(this);
		bt.setBackgroundResource(R.drawable.img);
		mMapView = new NMapView(this);
		// set a registered API key for Open MapViewer Library
		mMapView.setApiKey(API_KEY);// set the activity content to the map view

		layout1.addView(mMapView);
		layout1.addView(bt);

		mMapView.setClickable(true);// register listener for map state changes
		mMapView.setOnMapStateChangeListener(this);
		mMapView.setOnMapViewTouchEventListener(this);
		mMapView.setBuiltInZoomControls(true, null);
		
		// create overlay manager
		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
		mOverlayManager = new NMapOverlayManager(this, mMapView,
				mMapViewerResourceProvider);

		mCalloutOverlay = NMapCalloutOverlay(mCalloutOverlay, null, null);
		// Starting point, Ending point, Overlay

		int markerId = NMapPOIflagType.PIN;

		final NMapPOIdata poiData = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData.beginPOIdata(1);

		final NMapPOIdata poiData1 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData1.beginPOIdata(1);

		final NMapPOIdata poiData2 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData2.beginPOIdata(1);

		final NMapPOIdata poiData3 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData3.beginPOIdata(1);

		final NMapPOIdata poiData4 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData4.beginPOIdata(1);

		final NMapPOIdata poiData5 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData5.beginPOIdata(1);

		final NMapPOIdata poiData6 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData6.beginPOIdata(1);

		final NMapPOIdata poiData7 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData7.beginPOIdata(1);

		final NMapPOIdata poiData8 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData8.beginPOIdata(1);

		final NMapPOIdata poiData9 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData9.beginPOIdata(1);

		final NMapPOIdata poiData10 = new NMapPOIdata(1,
				mMapViewerResourceProvider);
		poiData10.beginPOIdata(1);

		poiData.addPOIitem(126.8925002, 37.5446259, "양화 공원", markerId, 0);
        poiData1.addPOIitem(126.9348268, 37.5263232, "여의도 공원", markerId, 0);
        poiData2.addPOIitem(126.8744075, 37.5672574, "난지 공원", markerId, 0);
        poiData3.addPOIitem(126.9946625, 37.5094917, "반포 공원", markerId, 0);
        poiData4.addPOIitem(127.0900268, 37.5188864, "잠실 공원", markerId, 0);
        poiData5.addPOIitem(127.0709209, 37.5285120, "뚝섬 공원", markerId, 0);
        poiData6.addPOIitem(127.0130935, 37.5225934, "잠원 공원", markerId, 0);
        poiData7.addPOIitem(126.8952798, 37.5553018, "망원 공원", markerId, 0);
        poiData8.addPOIitem(126.9652322, 37.5191003, "이촌 공원", markerId, 0);
        poiData9.addPOIitem(126.8151551, 37.5879036, "강서 공원", markerId, 0);
        poiData10.addPOIitem(127.1245699, 37.5583388, "광나루 공원", markerId, 0);

		NMapPOIdataOverlay poiDataOverlay = mOverlayManager
				.createPOIdataOverlay(poiData, null);

		poiDataOverlay.setOnStateChangeListener(new OnStateChangeListener() {

			@Override
			public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Main03_Map01.this,
						Main03_Park02_Inform.class);

				i.putExtra("pname", "양화 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay1 = mOverlayManager
                .createPOIdataOverlay(poiData1, null);

        poiDataOverlay1.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "여의도 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay2 = mOverlayManager
                .createPOIdataOverlay(poiData2, null);
        poiDataOverlay2.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "난지 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay3 = mOverlayManager
                .createPOIdataOverlay(poiData3, null);
        poiDataOverlay3.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "반포 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay4 = mOverlayManager
                .createPOIdataOverlay(poiData4, null);
        poiDataOverlay4.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "잠실 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay5 = mOverlayManager
                .createPOIdataOverlay(poiData5, null);
        poiDataOverlay5.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "뚝섬 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay6 = mOverlayManager
                .createPOIdataOverlay(poiData6, null);
        poiDataOverlay6.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "잠원 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay7 = mOverlayManager
                .createPOIdataOverlay(poiData7, null);
        poiDataOverlay7.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "망원 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay8 = mOverlayManager
                .createPOIdataOverlay(poiData8, null);
        poiDataOverlay8.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "이촌 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay9 = mOverlayManager
                .createPOIdataOverlay(poiData9, null);
        poiDataOverlay9.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "강서 공원");
                startActivity(i);
            }

            @Override
            public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub

            }
        });

        NMapPOIdataOverlay poiDataOverlay10 = mOverlayManager
                .createPOIdataOverlay(poiData10, null);
        poiDataOverlay10.setOnStateChangeListener(new OnStateChangeListener() {

            @Override
            public void onCalloutClick(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Main03_Map01.this,
                        Main03_Park02_Inform.class);

                i.putExtra("pname", "광나루 공원");
                startActivity(i);
			}

			@Override
			public void onFocusChanged(NMapPOIdataOverlay arg0, NMapPOIitem arg1) {
				// TODO Auto-generated method stub

			}
		});
		Button btn = (Button) findViewById(R.id.button);
		Button btn2 = (Button) findViewById(R.id.button2);
		Button btn3 = (Button) findViewById(R.id.button3);
		Button btn4 = (Button) findViewById(R.id.button4);
		Button btn5 = (Button) findViewById(R.id.button5);
		Button btn6 = (Button) findViewById(R.id.button6);
		Button btn7 = (Button) findViewById(R.id.button7);
		Button btn8 = (Button) findViewById(R.id.button8);
		Button btn9 = (Button) findViewById(R.id.button9);
		Button btn10 = (Button) findViewById(R.id.button10);
		Button btn11 = (Button) findViewById(R.id.button11);

		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint gwang = new NGeoPoint(127.1245699, 37.5583388);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(gwang);
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint jamsil = new NGeoPoint(127.0900268, 37.5188864);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(jamsil);
			}
		});

		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint ddook = new NGeoPoint(127.0709209, 37.5285120);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(ddook);
			}
		});
		btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint jamwon = new NGeoPoint(127.0130935, 37.5225934);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(jamwon);
			}
		});

		btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint banpo = new NGeoPoint(126.9946625, 37.5094917);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(banpo);
			}
		});
		btn6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint ichon = new NGeoPoint(126.9652322, 37.5191003);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(ichon);
			}
		});
		btn7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint yuedo = new NGeoPoint(126.9348268, 37.5263232);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(yuedo);
			}
		});
		btn8.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint mangwon = new NGeoPoint(126.8952798, 37.5553018);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(mangwon);
			}
		});
		btn9.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint nanji = new NGeoPoint(126.8744075, 37.5672574);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(nanji);
			}
		});
		btn10.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint gangseo = new NGeoPoint(126.8151551, 37.5879036);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(gangseo);
			}
		});
		btn11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				NGeoPoint yanghwa = new NGeoPoint(126.8925002, 37.5446259);

				NMapController mc = mMapView.getMapController();
				mc.animateTo(yanghwa);
			}
		});

		bt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CurrentLocation();
			}
		});
		// Starting Point

	}

	private NMapCalloutOverlay NMapCalloutOverlay(NMapOverlay itemizedOverlay,
			NMapOverlayItem item, Rect itemBounds) {
		// TODO Auto-generated method stub
		return null;
	}

	private void CurrentLocation() {
		// TODO Auto-generated method stub
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0,
				(LocationListener) this);
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000,
				0, (LocationListener) this);
	}

	public void voidonMapInitHandler(NMapView mapview, NMapError errorInfo) {
		if (errorInfo == null) {

		} else {
			// fail
			android.util.Log.e("NMAP",
					"onMapInitHandler: error=" + errorInfo.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	@Override
	public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay arg0,
			NMapOverlayItem arg1, Rect arg2) {

		// TODO Auto-generated method stub
		return null;
	}

	private void showMyLocation(double latitude, double longitude) {
		NMapViewerResourceProvider mMapViewerResourceProvider = null;
		NMapOverlayManager mOverlayManager;

		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
		mOverlayManager = new NMapOverlayManager(this, mMapView,
				mMapViewerResourceProvider);

		NGeoPoint myPoint = new NGeoPoint(longitude, latitude);

		int startId = NMapPOIflagType.FROM;

		NMapPOIdata poiData = new NMapPOIdata(1, mMapViewerResourceProvider);
		poiData.beginPOIdata(1);
        poiData.addPOIitem(myPoint, "현재 위치", startId, 0);

		poiData.endPOIdata();
		NMapPOIdataOverlay poiDataOverlay = mOverlayManager
				.createPOIdataOverlay(poiData, null);

		NMapController controller = mMapView.getMapController();
		controller.animateTo(myPoint);
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();

		showMyLocation(latitude, longitude);
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

}
