package com.bkpirates.fragment;

import com.bkpirates.adapter.AccountAndFunctionAdminAdapter;
import com.bkpirates.bookstore.AdminActivity;
import com.bkpirates.bookstore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Admin_Fragment extends Fragment {
	ListView listview;
	final String[] str = { "Xếp hạng", "Danh sách đơn hàng đã chuyển đến",
			"Danh sách đơn hàng chưa chuyển đến", "Nhập thêm sách", "Thoát" };
	private ArrayAdapter<String> adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_admin, container, false);
		listview = (ListView) view.findViewById(R.id.listFunction);
		adapter = new AccountAndFunctionAdminAdapter(getActivity(), R.layout.item_distribute_book, str);
		listview.setAdapter(adapter);
		listview.setBackgroundResource(R.drawable.admin_background);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
				if (position == 0) {
					FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
							.beginTransaction();
					RankFragment tef = new RankFragment();
					fragmentTransaction.replace(R.id.containerAdmin, tef);
					fragmentTransaction.addToBackStack(null);
					fragmentTransaction.commit();
					getActivity().getSupportFragmentManager().executePendingTransactions();

				} else if (position == 1) {

				} else if (position == 4) {
					AdminActivity.adminActivity.finish();
				}
			}
		});

		return view;
	}

	public static String convertDateToString(int year, int month, int day) {
		String date;
		String monthStr, dayStr;

		if (month < 10) {
			monthStr = "0" + Integer.toString(month);
		} else {
			monthStr = Integer.toString(month);
		}

		if (day < 10) {
			dayStr = "0" + Integer.toString(day);
		} else {
			dayStr = Integer.toString(day);
		}

		date = Integer.toString(year) + monthStr + dayStr;

		return date;
	}

}
