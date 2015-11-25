package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.BookGridViewAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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
		return view;
	}
	
	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		
	}
}
