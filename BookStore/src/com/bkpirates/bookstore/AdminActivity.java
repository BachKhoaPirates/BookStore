package com.bkpirates.bookstore;

import com.bkpirates.entity.BookEntity;
import com.bkpirates.fragment.Admin_Fragment;
import com.bkpirates.fragment.BookGridViewAdmin.OnItemSelectedListener;
import com.bkpirates.fragment.InsertQuantityBookAdminFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class AdminActivity extends FragmentActivity implements OnItemSelectedListener{
	public static Activity adminActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminactivity);
		adminActivity = this;
		//MainActivity.customerActivity.finish();
		
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		Admin_Fragment ef = new Admin_Fragment();
		fragmentTransaction.add(R.id.containerAdmin, ef);
		fragmentTransaction.commit();
		getSupportFragmentManager().executePendingTransactions();
		
	}
	
	@Override
	public void onItemSelected(BookEntity book) {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
	
		fm.popBackStack();
		fm.executePendingTransactions();
		InsertQuantityBookAdminFragment fg = (InsertQuantityBookAdminFragment) fm.findFragmentById(R.id.containerAdmin);
		fg.setBook(book);
		fg.updateUI();
	}

}
