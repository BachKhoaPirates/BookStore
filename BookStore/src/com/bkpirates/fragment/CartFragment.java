package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartFragment extends Fragment {
	
	private TextView subTotal;
	private Button payment;
	private ListView listview;
	private ArrayAdapter<BookEntity> adapter = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, null);
		
		subTotal = (TextView) view.findViewById(R.id.subTotal);
		payment = (Button) view.findViewById(R.id.payment);
		listview = (ListView) view.findViewById(R.id.listviewProduct);
		//adapter = new ArrayAdapter<BookEntity>(this, android.R.layout.simple_list_item_1, listview);
		return view;

	}
}
