package com.bkpirates.fragment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.adapter.ListCartAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWork;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartFragment extends Fragment {

	public static TextView subTotal;
	private final String TAG =  "CartFragment";
	private ImageView payment;
	private ListView listview;
	private final String PAYMENT = "http://thachpn.name.vn/books/add_order_book.php";
	private final String GET_CART = "http://thachpn.name.vn/books/get_cart.php";
	
	int total = 0;
	int checkPayment = 0;
	private NetWork netWork = new NetWork();
	public static ArrayList<BookEntity> arrList = new ArrayList<BookEntity>();
	public static ListCartAdapter adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		listview = (ListView) view.findViewById(R.id.listBooks);
		subTotal = (TextView) view.findViewById(R.id.subTotal);
		payment = (ImageView) view.findViewById(R.id.payment);

		if(netWork.checkInternetConnect(getActivity())){
			netWork.setPhone(LoginFragment.accEntity.getPhone());
			Log.d(TAG, LoginFragment.accEntity.getPhone());
			GetFromCartAsyncTask addCart = (GetFromCartAsyncTask) new GetFromCartAsyncTask().execute(GET_CART);
			
			
		}
		else {
			Toast.makeText(getActivity(), "Vui lòng kiểm tra kết nối mạng!", Toast.LENGTH_LONG).show();
		}
		
		

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startBookFragment(arrList.get(position));
			}
		});
		

		payment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				netWork.setPayment(total);
				Log.d(TAG, netWork.getPayment() + "");
				PaymentAsyncTask pat = (PaymentAsyncTask) new PaymentAsyncTask().execute(PAYMENT);
				int n = arrList.size();
				for (int i = n-1;i >= 0 ;i --){
					//result :success
					//request : uid, payment;
					arrList.remove(i);
				}
				subTotal.setText("");
				adapter.notifyDataSetChanged();

			}
		});
		return view;

	}

	public static int total_money() {
		int total = 0;
		for (int i = 0; i < arrList.size(); i++)
			total += arrList.get(i).getPrice() * arrList.get(i).getNumberBookToBuy();
		return total;
	}

	public class PaymentAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (s != null) {
				Log.d(TAG, s);
				checkPayment = netWork.checkForAddCartAndFavoriteList(s);
				Log.d(TAG, checkPayment + "");
				if(checkPayment == 1){
					Toast.makeText(getActivity(), "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(getActivity(), "Có lỗi khi đặt hàng!", Toast.LENGTH_LONG).show();
				}
										
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Lỗi");
				builder.setMessage("Vui lòng kiểm tra kết nối mạng!");
				builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.setCancelable(false);
				dialog.show();
			}

			super.onPostExecute(s);
		}

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		HttpResponse response = null;
		try {
			response = netWork.makeRquestPayment(url);
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

public class GetFromCartAsyncTask extends AsyncTask<String, Void, String> {

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String s) {
		if (s != null) {
			arrList = netWork.checkResultForGetUserBooks(s);
			if (arrList.size() == 0) {
				Toast.makeText(getActivity(), "Không có sách nào trong giỏ hàng!", Toast.LENGTH_LONG).show();

			} else {
				total = total_money();
				Log.d(TAG, arrList.get(0).getName());
				subTotal.setText("Tổng: "+total + " VNĐ");
				adapter = new ListCartAdapter(getContext(), R.layout.cart_books, arrList);
				if( adapter == null ){
					Log.d(TAG, "size of cart :" +arrList.size() + "");
					
				}
				listview.setAdapter(adapter);
				
			}
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Lỗi");
			builder.setMessage("Vui lòng kiểm tra kết nối mạng!");
			builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			AlertDialog dialog = builder.create();
			dialog.setCancelable(false);
			dialog.show();
		}

		super.onPostExecute(s);
	}

	@Override
	protected String doInBackground(String... params) {
		String url = params[0];
		HttpResponse response = null;
		try {
			response = netWork.makeRquestGetUserBooksAndGetOrders(url);
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

	private void startBookFragment(BookEntity book) {	
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
//		trans.replace(((ViewGroup) getView().getParent()).getId(), new BookFragment(getContext(), book));
		ft.replace(R.id.container, new BookFragment(getContext(), book));
		ft.addToBackStack(null);
		ft.commit();
		fm.executePendingTransactions();
	}

}