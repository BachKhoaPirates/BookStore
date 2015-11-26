package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListDistributeAdapter;
import com.bkpirates.app.AppController;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.DistributeBookEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements BookLoaderListener {

	ArrayList<DistributeBookEntity> array = null;
	ListView lView;
	EditText edText;
	ImageView searchButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


		if (AppController.getInstance().getDistributeArray() == null) {
			BookLoader bld = new BookLoader();
			bld.listener = this;
			try {
				array = (ArrayList<DistributeBookEntity>) bld.execute(BookLoader.DISTRIBUTE_LINK).get();
				AppController.getInstance().setDistributeArray(array);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			array = AppController.getInstance().getDistributeArray();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_search, container, false);

		lView = (ListView) view.findViewById(R.id.category);
		edText = (EditText) view.findViewById(R.id.searchText);
		searchButton = (ImageView) view.findViewById(R.id.searchButton);

		setAdapter(lView, array);
		lView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				
				FragmentManager fm = getActivity().getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
//				trans.replace(((ViewGroup) getView().getParent()).getId(), new BookOfDistribute(array.get(position).getPid()));
				ft.replace(R.id.container, new BookOfDistribute(array.get(position).getPid()));
				ft.addToBackStack(null);
				ft.commit();
				fm.executePendingTransactions();
			}
		});

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
	}

	private void setAdapter(ListView lView, ArrayList<DistributeBookEntity> array) {
		ListDistributeAdapter adapter = new ListDistributeAdapter(getContext(), array);
		lView.setAdapter(adapter);
	}
}