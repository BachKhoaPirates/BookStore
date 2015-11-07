package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ListBookFragment extends Fragment {

	public static TextView nameList;
	public static ListView listview;
	public static ArrayList<BookEntity> arrBooks = new ArrayList<BookEntity>();
 	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_listbooks, container, false);
		nameList = (TextView )view.findViewById(R.id.nameList);
		listview = (ListView) view.findViewById(R.id.listBooks);
		ListBookAdapter adapter = new ListBookAdapter(getContext(), arrBooks);
		listview.setAdapter(adapter);
		return view;
	}
}
