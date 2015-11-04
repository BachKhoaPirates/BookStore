package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
//				for (int i = 0; i < 20; i++) {
//					ListBookFragment.arrBooks.remove(i);
//				}
//				if (ListBookFragment.arrBooks == null) {
//					Toast.makeText(getContext(), "sai", Toast.LENGTH_LONG).show();
//				}
				ListBookFragment.nameList.setText("Favorite Books");
				// FragmentTransaction ft =
				// getFragmentManager().beginTransaction();
				// ft.replace(R.id.container, new ListBookFragment());
				// ft.commit();
			}

		});
		listOrder.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				for (int i = 0; i < 20; i++) {
					if (i % 2 == 0) {
						BookEntity data = new BookEntity();
						data.setAuthor("Ho Nam");
						data.setPrice(15000);
						data.setName("Sach Hay1");
						ListBookFragment.arrBooks.add(data);
					} else {
						BookEntity data = new BookEntity();
						data.setAuthor("Cuu Ba Dao");
						data.setPrice(20000);
						data.setName("Sach Hay2");
						ListBookFragment.arrBooks.add(data);
					}
				}
				ListBookFragment.nameList.setText("Order books");
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.replace(R.id.container, new ListBookFragment());
				ft.commit();

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
				LoginFragment.accEntity.setPhone(null);
				LoginFragment.accEntity.setPassword(null);
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				LoginFragment lg = new LoginFragment();
				ft.replace(R.id.container, lg);
				ft.commit();

			}
		});

		return view;
	}
}
