package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookAdapter;
import com.bkpirates.adapter.OrderAdminAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.OrderAdminEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListOrderUserFragment extends Fragment {
	public static ListView listview;
	private static ArrayList<OrderAdminEntity> arrayOrder = new ArrayList<OrderAdminEntity>();
 	
	public static ArrayList<OrderAdminEntity> getArrayOrder() {
		return arrayOrder;
	}

	public static void setArrayOrder(ArrayList<OrderAdminEntity> arrayOrder) {
		ListOrderUserFragment.arrayOrder = arrayOrder;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		listview = (ListView) view.findViewById(R.id.lvName);
		OrderAdminAdapter adapter = new OrderAdminAdapter(getContext(), arrayOrder);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), position + "",Toast.LENGTH_LONG  ).show();
			}
		});
		return view;
	}


}
