package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListBookFragment extends Fragment {

	public static TextView nameList;
	public static ListView listview;
	private static ArrayList<BookEntity> arrBooks = new ArrayList<BookEntity>();
 	public static ArrayList<BookEntity> getArrBooks() {
		return arrBooks;
	}
	public static void setArrBooks(ArrayList<BookEntity> arrBooks) {
		ListBookFragment.arrBooks = arrBooks;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_listbooks, container, false);
		nameList = (TextView )view.findViewById(R.id.nameList);
		listview = (ListView) view.findViewById(R.id.listBooks);
		ListBookAdapter adapter = new ListBookAdapter(getContext(), arrBooks);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startBookFragment(arrBooks.get(position));
			}
		});
		return view;
	}
	private void startBookFragment(BookEntity book){
		FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
		trans.replace(((ViewGroup)getView().getParent()).getId(),
				new BookFragment(getContext(), book));
		trans.addToBackStack(null);
		trans.commit();
	}
	
}
