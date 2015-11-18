package com.bkpirates.bookstore;

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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private ImageView homeButton, searchButton, cartButton, userButton;
	private TextView topBar;

	private static final String HomeTag = "Home";
	private static final String SearchTag = "Search";
	private static final String CartTag = "Cart";
	private static final String AccountTag = "Account";

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
		HomeFragment home = new HomeFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.container, home, HomeTag).commit();
		getSupportFragmentManager().executePendingTransactions();

		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				setBottomBar();
				homeButton.setImageResource(R.drawable.home_select);

				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				ft.remove(fm.findFragmentById(R.id.container)).commit();
				if (fm.getBackStackEntryCount() > 0) {
					fm.popBackStackImmediate(HomeTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
				Log.d("BACKSTACK", "" + fm.getBackStackEntryCount());
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				setBottomBar();
				searchButton.setImageResource(R.drawable.search_select);

				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				SearchFragment search = (SearchFragment) fm.findFragmentByTag(SearchTag);

				if (search == null || !search.isVisible()) {
					while (fm.getBackStackEntryCount() > 1) {
						fm.popBackStackImmediate();
					}
					search = new SearchFragment();
					ft.replace(R.id.container, search, SearchTag);
					if (fm.getBackStackEntryCount() == 0) {
						ft.addToBackStack(HomeTag);
					}
					ft.commit();
					fm.executePendingTransactions();
				}
				Log.d("BACKSTACK", "" + fm.getBackStackEntryCount());
			}

		});

		cartButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				setBottomBar();
				cartButton.setImageResource(R.drawable.cart_select);

				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				if (LoginFragment.accEntity.getPassword() == null) {
					while (fm.getBackStackEntryCount() > 1) {
						fm.popBackStackImmediate();
					}
					ft.replace(R.id.container, new LoginFragment());
					if (fm.getBackStackEntryCount() == 0) {
						ft.addToBackStack(HomeTag);
					}
					ft.commit();
					fm.executePendingTransactions();
				} else {

					CartFragment cart = (CartFragment) fm.findFragmentByTag(CartTag);

					if (cart == null || !cart.isVisible()) {
						while (fm.getBackStackEntryCount() > 1) {
							fm.popBackStackImmediate();
						}
						cart = new CartFragment();
						ft.replace(R.id.container, cart, CartTag);
						if (fm.getBackStackEntryCount() == 0) {
							ft.addToBackStack(HomeTag);
						}
						ft.commit();
						fm.executePendingTransactions();
					}

				}

				Log.d("BACKSTACK", "" + fm.getBackStackEntryCount());
			}

		});

		userButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				setBottomBar();
				userButton.setImageResource(R.drawable.user_select);

				FragmentManager fm = getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();

				if (LoginFragment.accEntity.getPassword() == null) {
					while (fm.getBackStackEntryCount() > 1) {
						fm.popBackStackImmediate();
					}
					ft.replace(R.id.container, new LoginFragment());
					if (fm.getBackStackEntryCount() == 0) {
						ft.addToBackStack(HomeTag);
					}
					ft.commit();
					fm.executePendingTransactions();
				} else {

					AccountFragment acc = (AccountFragment) fm.findFragmentByTag(AccountTag);

					if (acc == null || !acc.isVisible()) {
						while (fm.getBackStackEntryCount() > 1) {
							fm.popBackStackImmediate();
						}
						acc = new AccountFragment();
						ft.replace(R.id.container, acc, AccountTag);
						if (fm.getBackStackEntryCount() == 0) {
							ft.addToBackStack(HomeTag);
						}
						ft.commit();
						fm.executePendingTransactions();
					}
				}

				Log.d("BACKSTACK", "" + fm.getBackStackEntryCount());
			}
		});
	}

	private void setTopBar(TextView topBar) {
		topBar = (TextView) findViewById(R.id.top_bar);
		Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
		topBar.setTypeface(face);
	}

	private void setBottomBar() {
		homeButton.setImageResource(R.drawable.home_unselect);
		searchButton.setImageResource(R.drawable.search_unselect);
		cartButton.setImageResource(R.drawable.cart_unselect);
		userButton.setImageResource(R.drawable.user_unselect);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		if (fm.getBackStackEntryCount() == 0) {
			finish();
		}
		if (fm.getBackStackEntryCount() == 1) {
			setBottomBar();
			homeButton.setImageResource(R.drawable.home_select);
		}
		fm.beginTransaction().remove(fm.findFragmentById(R.id.container)).commit();
		fm.popBackStackImmediate();
	}

}