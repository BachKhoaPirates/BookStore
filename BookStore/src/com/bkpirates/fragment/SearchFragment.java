package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListDistributeAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.DistributeBookEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements BookLoaderListener{
	
	ArrayList<DistributeBookEntity> array;
	ListView lView;
	EditText edText;
	ImageView searchButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_search,  container, false);
		
		lView = (ListView) view.findViewById(R.id.category);
		edText = (EditText) view.findViewById(R.id.searchText);
		searchButton = (ImageView) view.findViewById(R.id.searchButton);
		
		BookLoader bld = new BookLoader();
		bld.listener=this;
		
		if (array == null) {
			try {
				array = (ArrayList<DistributeBookEntity>) bld.execute(BookLoader.DISTRIBUTE_LINK).get();
			} catch (Exception e){
				e.printStackTrace();
			}
		} else {
			setAdapter(lView, array);
		}
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "Just click search button", Toast.LENGTH_SHORT).show();
			}
		});
		
		return view;
	}
	
	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		setAdapter(lView, array);
		Log.d("siseeeeeeeeeeee", array.size()+"");
	}
	
	private void setAdapter(ListView lView, ArrayList<DistributeBookEntity> array){
		ListDistributeAdapter adapter = new ListDistributeAdapter(getContext(), array);
		lView.setAdapter(adapter);
	}
}