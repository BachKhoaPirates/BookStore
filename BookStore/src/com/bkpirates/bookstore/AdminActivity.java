package com.bkpirates.bookstore;

import com.bkpirates.fragment.Admin_Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class AdminActivity extends FragmentActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminactivity);
		MainActivity.customerActivity.finish();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		
		Admin_Fragment ef = new Admin_Fragment();
		fragmentTransaction.add(R.id.containerAdmin, ef);
		fragmentTransaction.commit();
		getSupportFragmentManager().executePendingTransactions();
	}

}
