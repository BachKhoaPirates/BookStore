package com.bkpirates.bookstore;

import com.bkpirates.app.AppController;
import com.bkpirates.fragment.AccountFragment;
import com.bkpirates.fragment.CartFragment;
import com.bkpirates.fragment.HomeFragment;
import com.bkpirates.fragment.LoginFragment;
import com.bkpirates.fragment.SearchFragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private ImageView homeButton, searchButton, cartButton, userButton;
	private TextView topBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTopBar(topBar);
		
		homeButton = (ImageView) findViewById(R.id.home_button);
		searchButton = (ImageView) findViewById(R.id.search_button);
		cartButton = (ImageView) findViewById(R.id.cart_button);
		userButton = (ImageView) findViewById(R.id.user_button);

		setBottomBar();
		homeButton.setImageResource(R.drawable.home_select);
		getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment(), "Home").commit();

		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				setBottomBar();
				homeButton.setImageResource(R.drawable.home_select);
				
				FragmentManager fm = getSupportFragmentManager();
				fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				FragmentTransaction trans = fm.beginTransaction();
				
				HomeFragment home = (HomeFragment) getSupportFragmentManager().findFragmentByTag("Home");
				if (home == null) {
					home = new HomeFragment();
					trans.replace(R.id.container, home, "Home");
					trans.commit();
				} else if (!home.isVisible()) {
					trans.replace(R.id.container, home);
					trans.commit();
				}
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				setBottomBar();
				searchButton.setImageResource(R.drawable.search_select);
				
				FragmentManager fm = getSupportFragmentManager();
				fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				FragmentTransaction trans = fm.beginTransaction();
				
				SearchFragment search = (SearchFragment) getSupportFragmentManager().findFragmentByTag("Search");
				if (search == null) {
					search = new SearchFragment();
					trans.replace(R.id.container, search, "Search");
					trans.commit();
				} else if (!search.isVisible()) {
					trans.replace(R.id.container, search);
					trans.commit();
				}
			}

		});

		cartButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				setBottomBar();
				cartButton.setImageResource(R.drawable.cart_select);
				
				if (LoginFragment.accEntity.getPassword() == null) {
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment())
							.commit();
				} else {

					getSupportFragmentManager().beginTransaction().replace(R.id.container, new CartFragment()).commit();
				}
			}

		});

		userButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				setBottomBar();
				userButton.setImageResource(R.drawable.user_select);
				
				if (LoginFragment.accEntity.getPassword() == null) {
					getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment())
							.commit();
				} else {

					getSupportFragmentManager().beginTransaction().replace(R.id.container, new AccountFragment())
							.commit();
				}
			}
		});
	}

	private void setTopBar(TextView topBar) {
		topBar = (TextView) findViewById(R.id.top_bar);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
		topBar.setTypeface(face);
	}
	
	private void setBottomBar(){
		homeButton.setImageResource(R.drawable.home_unselect);
		searchButton.setImageResource(R.drawable.search_unselect);
		cartButton.setImageResource(R.drawable.cart_unselect);
		userButton.setImageResource(R.drawable.user_unselect);
	}
}