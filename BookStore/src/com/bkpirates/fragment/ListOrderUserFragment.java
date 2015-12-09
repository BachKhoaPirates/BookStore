package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.OrderAdminAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.OrderEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListOrderUserFragment extends Fragment {
	public static ListView listview;
	private static ArrayList<OrderEntity> arrayOrder = new ArrayList<OrderEntity>();
 	
	public static ArrayList<OrderEntity> getArrayOrder() {
		return arrayOrder;
	}

	public static void setArrayOrder(ArrayList<OrderEntity> arrayOrder) {
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
				FragmentManager fm = getActivity().getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(((ViewGroup) getView().getParent()).getId(), new OrderFragmentAccount(arrayOrder.get(position).getOid(),
						arrayOrder.get(position).getTotalMoney()));
				ft.addToBackStack(null);
				ft.commit();
				fm.executePendingTransactions();
			}
		});
		return view;
	}


}
