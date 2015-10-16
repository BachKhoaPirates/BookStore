package com.bkpirates.bookstore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	private ImageView homeButton;
	private ImageView searchButton;
	private ImageView orderButton;
	private ImageView accButton;

	private ImageView divider1;
	private ImageView divider2;
	private ImageView divider3;
	private ImageView divider4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		divider1 = (ImageView) findViewById(R.id.divider1);
		divider3 = (ImageView) findViewById(R.id.divider3);
		setDivider(divider1);
		// ------------------------------------------------------
		homeButton = (ImageView) findViewById(R.id.home);
		searchButton = (ImageView) findViewById(R.id.search);
		orderButton = (ImageView) findViewById(R.id.order);
		accButton = (ImageView) findViewById(R.id.user);

		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		orderButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		accButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
