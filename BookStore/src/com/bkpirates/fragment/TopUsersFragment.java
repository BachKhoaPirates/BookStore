package com.bkpirates.fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.bkpirates.adapter.TopUsersAdminAdapter;
import com.bkpirates.app.AppController;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;
import com.bkpirates.webservice.NetWorkAdmin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class TopUsersFragment extends Fragment implements BookLoaderListener{
	private ListView listview;
	private NetWorkAdmin netWorkAdmin = new NetWorkAdmin();
	private ArrayList<AccountEntity> accEntity ;
	private final String GET_TOP_USERS = "http://thachpn.name.vn/books/get_top_users.php";
	BookLoader bld1 = new BookLoader();
	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		listview = (ListView) view.findViewById(R.id.lvName);
		bld1.listener = this;
		try {
			accEntity = (ArrayList<AccountEntity>) bld1.execute(GET_TOP_USERS).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TopUsersAdminAdapter adapter = new TopUsersAdminAdapter(getActivity(),R.layout.item_distribute_book, accEntity);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AppController.getInstance().initiatePopupWindow(accEntity.get(position), getActivity());
			}
		});
		return view;
	}
	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		
	}

}
