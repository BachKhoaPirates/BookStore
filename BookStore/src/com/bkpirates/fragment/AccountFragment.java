package com.bkpirates.fragment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.adapter.AccountAdapter;
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

		listview = (ListView) view.findViewById(R.id.listview);
		adapter = new AccountAdapter(getActivity(), R.layout.item_distribute_book, str);
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
					initiatePopupWindow();
					
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
					View mView = mInflater.inflate(R.layout.fragment_listbooks, null);
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

	private void initiatePopupWindow() {
		Button btnOkPopup;
		Button btnNotOkPopup;
		final PopupWindow pwindo;
		int width;
		int height;
		TextView phoneText;
		TextView passText;
		TextView addressText;
		TextView nameText;

		width = (int) convertDpToPixel(320, getActivity());
		height = (int) convertDpToPixel(150, getActivity());

		try {

			LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.popup, null, false);
			pwindo = new PopupWindow(layout, width, height, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
			btnOkPopup = (Button) layout.findViewById(R.id.btn_ok_popup);
			nameText = (TextView) layout.findViewById(R.id.txtView1);
			addressText = (TextView) layout.findViewById(R.id.txtView2);
			phoneText = (TextView) layout.findViewById(R.id.txtView3);
			passText = (TextView) layout.findViewById(R.id.txtView4);

			nameText.setText("Name        : " + accEntity.getName());
			addressText.setText("Address    : " + accEntity.getAddress());
			phoneText.setText("Phone       : " + accEntity.getPhone());
			passText.setText("PassWord : " + accEntity.getPassword());
			btnOkPopup.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					pwindo.dismiss();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

}