package com.bkpirates.bookstore;

import com.bkpirates.webservice.NetWork;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends Activity {

	// Splash screen timer
	private static final int SPLASH_TIME_OUT = 600;
	private static final int TIME_TO_CHECK_INTERNET = 3000;

	private TextView tView;
	private NetWork nw;
	private AlertDialog myAlertDialog;
	private Handler handler;
	private Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		tView = (TextView) findViewById(R.id.app_name);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
		tView.setTypeface(face);

		// creat dialog
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("Lỗi").setCancelable(false).setMessage("Vui lòng kết nối Internet!").setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		myAlertDialog = dialog.create();

		// check Internet
		nw = new NetWork();
		handler = new Handler();
		runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				changeActivity();
				handler.postDelayed(runnable, TIME_TO_CHECK_INTERNET);
			}
		};
		handler.postDelayed(runnable, SPLASH_TIME_OUT);

	}

	private void changeActivity() {
		if (!nw.checkInternetConnect(this)) {
			if (!myAlertDialog.isShowing()) {
				myAlertDialog.show();
			}
		} else {
			Intent i = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(i);
			// close this activity
			finish();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (handler != null) {
			handler.removeCallbacks(runnable);
		}
		super.onPause();
	}
}