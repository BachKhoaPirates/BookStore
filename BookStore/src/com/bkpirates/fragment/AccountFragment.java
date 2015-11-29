package com.bkpirates.fragment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.adapter.AccountAndFunctionAdminAdapter;
import com.bkpirates.app.AppController;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class AccountFragment extends Fragment {

	private String[] str = {"Favorite Books","Ordered Books", "List Order","Information","Logout"};
	private ListView listview;
	private TextView txtNameUsers;
	private NetWork netWork = new NetWork();
	private ArrayList<BookEntity> favoriteArrayBooks = new ArrayList<BookEntity>();
	public static ArrayList<BookEntity> orderArrayBooks = new ArrayList<BookEntity>();
	private AccountEntity accEntity = new AccountEntity();
	private ArrayAdapter<String> adapter = null;

	public AccountEntity getAccEntity() {
		return accEntity;
	}

	public void setAccEntity(AccountEntity accEntity) {
		this.accEntity = accEntity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_account, null);
		
		setAccEntity(LoginFragment.accEntity);
		txtNameUsers = (TextView) view.findViewById(R.id.nameUser);
		listview = (ListView) view.findViewById(R.id.listview);
		
		txtNameUsers.setText(accEntity.getName());
		adapter = new AccountAndFunctionAdminAdapter(getActivity(), R.layout.item_distribute_book, str);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				if(position == 0){ // sach thich

					netWork.setPhone(accEntity.getPhone());
					GetUserBooksAsyncTask nw = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask()
							.execute("http://thachpn.name.vn/books/get_user_favorite_books.php");		
				}else if(position == 1) // sach da mua
				{
					netWork.setPhone(accEntity.getPhone());
					GetUserBooksAsyncTask nw = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask()
							.execute("http://thachpn.name.vn/books/get_bought_books.php");	
				}else if(position == 2){
					
				}else if(position == 3){ // thong tin ng dung
					AppController.getInstance().initiatePopupWindow(accEntity, getActivity());
					
				}else if(position == 4){ //logout
					accEntity.setPhone(null);
					accEntity.setPassword(null);
					LoginFragment.checkLogin = 0;
					// clear phone and password
					SharedPreferences pre = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = pre.edit();
					editor.clear();
					editor.putString("checkLogin", 0 + "");
					editor.commit();
					FragmentManager fm = getFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					LoginFragment lg = new LoginFragment();
					ft.replace(R.id.container, lg);
					ft.commit();
					fm.executePendingTransactions();
				}
			}
			
		});


		return view;
	}

	private class GetUserBooksAsyncTask extends AsyncTask<String, Void, String> {
		ProgressDialog pb;

		@Override
		protected void onPreExecute() {
			pb = new ProgressDialog(getActivity());
			pb.setMessage("Loading...");
			pb.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (pb != null) {
				pb.dismiss();
			}
			if (s != null) {
				
				favoriteArrayBooks = netWork.checkResultForGetUserBooks(s);
				if (favoriteArrayBooks.size() == 0) {
					// Toast.makeText(getActivity(), "Not found anything in
					// cart", Toast.LENGTH_LONG).show();

				} else {
					ListBookFragment listBookFragment = new ListBookFragment();
					listBookFragment.setArrBooks(favoriteArrayBooks);
					LayoutInflater mInflater = LayoutInflater.from(getActivity());
					View mView = mInflater.inflate(R.layout.fragment_list, null);
					FragmentManager fm = getFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.container, listBookFragment);
					ft.addToBackStack(null);
					ft.commit();
					fm.executePendingTransactions();
				}
			}

			super.onPostExecute(s);
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			HttpResponse response = null;

			try {
				Log.d("respone", url);
				Log.d("respone", url);

				response = netWork.makeRquestGetUserBooks(url);
			} catch (IOException e) {
				return null;
			}
			if (response != null) {

				String content = null;
				try {
					content = netWork.processHTTPResponce(response);

					return content;
				} catch (IOException e) {
					return null;
				} catch (ParseException e) {
					return null;
				}
			}
			return null;

		}
	}


}