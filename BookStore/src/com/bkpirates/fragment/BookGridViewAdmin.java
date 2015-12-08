package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.BookGridViewAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class BookGridViewAdmin extends Fragment{
	
	public interface OnItemSelectedListener {
		public void onItemSelected(BookEntity book);
	}
	
	private GridView gView;
	private ArrayList<BookEntity> arr;
	OnItemSelectedListener listener;
	
	public BookGridViewAdmin(ArrayList<BookEntity> arr){
		this.arr = arr;
	}
	
	
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        try {
	            listener = (OnItemSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnItemSelectedListener");
	        }
	    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_book_of_distribute, container, false);
		
		//hide the virtual key board
		InputMethodManager inputManager = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow((null == getActivity().getCurrentFocus()) ? null
				: getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
		
		gView = (GridView) view.findViewById(R.id.gView);
		gView.setAdapter(new BookGridViewAdapter(getContext(), arr));
		
		gView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				listener.onItemSelected(arr.get(position));
			}
		});
		return view;
	}
	
}
