package com.bkpirates.fragment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.webkit.WebView.FindListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InflateParams")
public class AccountFragment extends Fragment {

	private Button favoriteBook;
	private Button listOrder;
	private Button infoAccount;
	private Button logOut;

	private NetWork netWork = new NetWork();
	private ArrayList<BookEntity> favoriteArrayBooks = new ArrayList<BookEntity>();
	public static ArrayList<BookEntity> orderArrayBooks = new ArrayList<BookEntity>();
	private AccountEntity accEntity = new AccountEntity();

	public AccountEntity getAccEntity() {
		return accEntity;
	}

	public void setAccEntity(AccountEntity accEntity) {
		this.accEntity = accEntity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_account, null);

		favoriteBook = (Button) view.findViewById(R.id.favoriteBook);
		listOrder = (Button) view.findViewById(R.id.listOrder);
		infoAccount = (Button) view.findViewById(R.id.infoAccount);
		logOut = (Button) view.findViewById(R.id.logOut);
		
		setAccEntity(LoginFragment.accEntity);
		
		favoriteBook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// if(netWork.checkInternetConnect(getActivity())){
				Log.d("Favorite", "Favorite");
				netWork.setPhone(accEntity.getPhone());
				GetUserBooksAsyncTask nw = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask()
						.execute("http://thachpn.name.vn/books/get_user_favorite_books.php");
				// }
			}

		});
		listOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("listOrder", "listOrder");
				netWork.setPhone(accEntity.getPhone());
				GetUserBooksAsyncTask nw = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask()
						.execute("http://thachpn.name.vn/books/get_bought_books.php");
			}

		});
		infoAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				initiatePopupWindow();

			}
		});
		logOut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				accEntity.setPhone(null);
				accEntity.setPassword(null);
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				LoginFragment lg = new LoginFragment();
				ft.replace(R.id.container, lg);
				ft.commit();

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
				// Log.d(s, s);
				Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
				favoriteArrayBooks = netWork.checkResultForGetUserBooks(s);
				Log.d("Dieu hieu sao luon lan 2", favoriteArrayBooks.size() + "");
				if (favoriteArrayBooks.size() == 0) {
					// Toast.makeText(getActivity(), "Not found anything in
					// cart", Toast.LENGTH_LONG).show();

				} else {
					ListBookFragment listBookFragment = new ListBookFragment();
					listBookFragment.setArrBooks(favoriteArrayBooks);
					LayoutInflater mInflater = LayoutInflater.from(getActivity());
					View mView = mInflater.inflate(R.layout.fragment_listbooks, null);
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, listBookFragment);
					ft.addToBackStack(null);
					ft.commit();
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

	// private class GetUserOrderBooksAsyncTask extends AsyncTask<String, Void,
	// String> {
	// ProgressDialog pb;
	//
	// @Override
	// protected void onPreExecute() {
	// pb = new ProgressDialog(getActivity());
	// pb.setMessage("Loading...");
	// pb.show();
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected void onPostExecute(String s) {
	// if (pb != null) {
	// pb.dismiss();
	// }
	// if (s != null) {
	// //Log.d(s, s);
	// Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
	// orderArrayBooks = netWork.checkResultForGetUserBooks(s);
	// Log.d("Dieu hieu sao luon lan 2", favoriteArrayBooks.size() + "");
	// if(favoriteArrayBooks.size() == 0){
	// // Toast.makeText(getActivity(), "Not found anything in cart",
	// Toast.LENGTH_LONG).show();
	//
	// }else{
	// ListBookFragment listBookFragment = new ListBookFragment();
	// listBookFragment.setArrBooks(orderArrayBooks);
	// LayoutInflater mInflater = LayoutInflater.from(getActivity());
	// View mView = mInflater.inflate(R.layout.fragment_listbooks, null);
	// FragmentTransaction ft = getFragmentManager().beginTransaction();
	// ft.replace(R.id.container, listBookFragment);
	// ft.addToBackStack(null);
	// ft.commit();
	// }
	// }
	//
	//
	//
	// super.onPostExecute(s);
	// }
	//
	// @Override
	// protected String doInBackground(String... params) {
	// String url = params[0];
	// HttpResponse response = null;
	//
	// try {
	// Log.d("respone", url);
	// Log.d("respone", url);
	//
	// response = netWork.makeRquestGetUserFavoriteBooks(url);
	// } catch (IOException e) {
	// return null;
	// }
	// if (response != null) {
	//
	//
	// String content = null;
	// try {
	// content = netWork.processHTTPResponce(response);
	//
	// return content;
	// } catch (IOException e) {
	// return null;
	// } catch (ParseException e) {
	// return null;
	// }
	// }
	// return null;
	//
	// }
	// }
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

		width = (int) convertDpToPixel(340, getActivity());
		height = (int) convertDpToPixel(180, getActivity());

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
		   phoneText.setText("Phone       : " +accEntity.getPhone());
			passText.setText("PassWord : " +accEntity.getPassword());
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