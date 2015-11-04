package com.bkpirates.bookstore;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bkpirates.fragment.*;

public class MainActivity extends FragmentActivity {

	private ImageView bt1;
	private ImageView bt2, bt3, bt4;
	private TextView topBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTopBar(topBar);

		bt1 = (ImageView) findViewById(R.id.bt1);
		bt2 = (ImageView) findViewById(R.id.bt2);
		bt3 = (ImageView) findViewById(R.id.bt3);
		bt4 = (ImageView) findViewById(R.id.bt4);
		// FragmentManager fm = getSupportFragmentManager();
		// FragmentTransaction ft = fm.beginTransaction();
		final Home fg1 = new Home();
		final Search fg2 = new Search();
		getSupportFragmentManager().beginTransaction().add(R.id.container, fg1).commit();

		bt1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// TODO Auto-generated method stub
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, new Home()).commit();
			}
		});

		bt2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, new Search()).commit();
			}

		});

		bt3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, new CartFragment()).commit();
			}
		});

		bt4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(LoginFragment.accEntity.getPhone() == null){

					getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new LoginFragment()).commit();	
				} else{

					getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new AccountFragment()).commit();	
				}
			}
		});
	}

	private void setTopBar(TextView topBar) {
		topBar = (TextView) findViewById(R.id.top_bar);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
		topBar.setTypeface(face);
	}
}