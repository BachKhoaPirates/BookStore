package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.HorizontalListView.HorizontalListView;
import com.bkpirates.HorizontalListView.HorizontalListViewAdapter;
import com.bkpirates.HorizontalListView.HorizontalListViewData;
import com.bkpirates.bookstore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment{
	
	private HorizontalListView listView;
	private ArrayList<HorizontalListViewData> array;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		listView = (HorizontalListView) view.findViewById(R.id.list1);
		setData(array);
		setAdapter(listView, array);
		return view;
	}
	
	private void setAdapter(HorizontalListView listView, ArrayList<HorizontalListViewData> array){
		HorizontalListViewAdapter adapter = new HorizontalListViewAdapter(getContext(), array);
		listView.setAdapter(adapter);
	}
	
	private void setData(ArrayList<HorizontalListViewData> array){
		array = new ArrayList<HorizontalListViewData>();
		for (int i=0; i<10; i++){
			if (i%2==0){
				HorizontalListViewData data = new HorizontalListViewData();
				data.setAuthor("tac gia 1");
				data.setName("sach 1");
				data.setPrice(15000);
				array.add(data);
			} else {
				HorizontalListViewData data = new HorizontalListViewData();
				data.setAuthor("tac gia 2");
				data.setName("sach 2");
				data.setPrice(20000);
				array.add(data);
			}
		}
		
		Log.d("KKKKKKKKK", "KKKKKKKKKKKK");
		Log.d("KKKKKKKKKKKKKK", ""+array.size());
	}
}