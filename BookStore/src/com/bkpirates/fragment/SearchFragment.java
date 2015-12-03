package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListDistributeAdapter;
import com.bkpirates.app.AppController;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.DistributeBookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements DataLoaderListener {

	private ArrayList<DistributeBookEntity> arrayDistribute = null;
	private ListView lView;
	private EditText edText;
	private ImageView searchButton;
	private ProgressDialog dialog;
	private final int LIMIT = 10;
	private ArrayList<BookEntity> arrayBook = null;
	private int check = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if (AppController.getInstance().getDistributeArray() == null) {
			DataLoader bld = new DataLoader();
			bld.listener = this;
			try {
				arrayDistribute = (ArrayList<DistributeBookEntity>) bld.execute(DataLoader.DISTRIBUTE_LINK).get();
				AppController.getInstance().setDistributeArray(arrayDistribute);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			arrayDistribute = AppController.getInstance().getDistributeArray();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_search, container, false);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Đang tải...");
		dialog.setCancelable(false);

		lView = (ListView) view.findViewById(R.id.category);
		edText = (EditText) view.findViewById(R.id.searchText);
		searchButton = (ImageView) view.findViewById(R.id.searchButton);

		setAdapter(lView, arrayDistribute);
		lView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				dialog.show();
				check = 1;
				DataLoader bld = new DataLoader();
				bld.listener = SearchFragment.this;
				try {
					arrayBook = (ArrayList<BookEntity>) bld.execute(DataLoader.LIST_BOOK_LINK + "?pid="
							+ arrayDistribute.get(position).getPid() + "&offset=" + LIMIT).get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearchButtonClick();
			}
		});

		edText.setFocusableInTouchMode(true);
		edText.requestFocus();
		edText.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

					onSearchButtonClick();
					return true;
				}
				return false;
			}
		});

		return view;
	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		if (check == 1) {
			if (arrayBook != null){
				FragmentManager fm = getActivity().getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.container, new BookOfDistribute(arrayBook));
				ft.addToBackStack(null);
				ft.commit();
				fm.executePendingTransactions();
				if (dialog.isShowing()){
					dialog.dismiss();
				}
			} else {
				Toast.makeText(getContext(), "Không tìm thấy sách nào!", Toast.LENGTH_LONG).show();
			}
		}

	}

	private void setAdapter(ListView lView, ArrayList<DistributeBookEntity> array) {
		ListDistributeAdapter adapter = new ListDistributeAdapter(getContext(), array);
		lView.setAdapter(adapter);
	}

	private void onSearchButtonClick() {
		String ed_text = edText.getText().toString().trim();
		if (ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
			// edit text empty, do nothing
		} else {
			check = 1;
			DataLoader bld = new DataLoader();
			bld.listener = SearchFragment.this;
			try {
				arrayBook = (ArrayList<BookEntity>) bld.execute(DataLoader.SEARCH_LINK + ed_text).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}