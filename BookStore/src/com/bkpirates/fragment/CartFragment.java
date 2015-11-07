package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListCartAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CartFragment extends Fragment {

	public static TextView subTotal;
	private Button payment;
	private ListView listview;
	int total;
	public static ArrayList<BookEntity> arrList = new ArrayList<BookEntity>();
	public static ListCartAdapter adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		listview = (ListView) view.findViewById(R.id.listBooks);
		subTotal = (TextView) view.findViewById(R.id.subTotal);
		payment = (Button) view.findViewById(R.id.payment);
		
		setData(arrList);
		total = total_money();
		subTotal.setText(total + "");
		adapter = new ListCartAdapter(getContext(), arrList);
		listview.setAdapter(adapter);
		return view;

	}
	public static int total_money(){
		int total = 0;
		for(int i = 0; i< arrList.size(); i++)
			total += arrList.get(i).getPrice();
		return total;
	}
	
	private void setData(ArrayList<BookEntity> array) {
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				BookEntity data = new BookEntity();
				data.setAuthor("Ho Nam");
				data.setPrice(15000);
				data.setName("Sach Hay1");
				array.add(data);
			} else {
				BookEntity data = new BookEntity();
				data.setAuthor("Cuu Ba Dao");
				data.setPrice(20000);
				data.setName("Sach Hay2");
				array.add(data);
			}
		}

	}

}
