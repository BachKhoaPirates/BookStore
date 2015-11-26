package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.BookGridViewAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class BookOfDistribute extends Fragment implements BookLoaderListener {
	
	private String pid;
	private GridView gView;
	private ArrayList<BookEntity> arr;
	private final String URL = "http://thachpn.name.vn/books/get_list_books.php";
	private final int LIMIT = 10;
	
	public BookOfDistribute(String pid){
		this.pid = pid;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (arr == null){
			BookLoader bld = new BookLoader();
			bld.listener = this;
			try {
				arr = (ArrayList<BookEntity>) bld.execute(URL+"?pid="+pid+"&offset="+LIMIT).get();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_book_of_distribute, container, false);
		gView = (GridView) view.findViewById(R.id.gView);
		gView.setAdapter(new BookGridViewAdapter(getContext(), arr));
		
		gView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				FragmentManager fm = getActivity().getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
//				trans.replace(((ViewGroup) getView().getParent()).getId(), new BookOfDistribute(array.get(position).getPid()));
				ft.replace(R.id.container, new BookFragment(getContext(), arr.get(position)));
				ft.addToBackStack(null);
				ft.commit();
				fm.executePendingTransactions();
			}
		});
		return view;
	}
	
	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		
	}
}
