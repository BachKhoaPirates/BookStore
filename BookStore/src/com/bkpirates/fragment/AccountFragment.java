package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AccountFragment extends Fragment {

	private Button favoriteBook;
	private Button listOrder;
	private Button infoAccount;
	private Button logOut;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_account, null);

		favoriteBook = (Button) view.findViewById(R.id.favoriteBook);
		listOrder = (Button) view.findViewById(R.id.listOrder);
		infoAccount = (Button) view.findViewById(R.id.infoAccount);
		logOut = (Button) view.findViewById(R.id.logOut);

		favoriteBook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		listOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		infoAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});
		logOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		return view;
	}
}
