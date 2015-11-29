package com.bkpirates.fragment;

import com.bkpirates.adapter.AccountAndFunctionAdminAdapter;
import com.bkpirates.bookstore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Admin_Fragment extends Fragment {
	ListView listview;
	final String[] str = { "Tài khoản mua nhiều nhất", "Sách mua nhiều nhất trong tháng",
			"Nhà xuất bản được mua nhiều nhất", "Thể loại sách được mua nhiều nhất", "Nhập thêm sách", "Thoát" };
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
					FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
					TopUsersFragment tef = new TopUsersFragment();
					fragmentTransaction.replace(R.id.containerAdmin, tef);
					fragmentTransaction.commit();
					getActivity().getSupportFragmentManager().executePendingTransactions();

				} else if (position == 1) {
					Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
				}
			}
		});

		return view;
	}

}
