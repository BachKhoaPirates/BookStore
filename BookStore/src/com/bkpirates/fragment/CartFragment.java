package com.bkpirates.fragment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.adapter.ListCartAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWork;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CartFragment extends Fragment {

	public static TextView subTotal;
	private Button payment;
	private ListView listview;
	int total = 0;
	private NetWork netWork = new NetWork();
	public static ArrayList<BookEntity> arrList = new ArrayList<BookEntity>();
	public static ListCartAdapter adapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cart, container, false);
		listview = (ListView) view.findViewById(R.id.listBooks);
		subTotal = (TextView) view.findViewById(R.id.subTotal);
		payment = (Button) view.findViewById(R.id.payment);

		if(netWork.checkInternetConnect(getActivity())){
			netWork.setPhone(LoginFragment.accEntity.getPhone());
			GetFromCartAsyncTask addCart = (GetFromCartAsyncTask) new GetFromCartAsyncTask().execute("http://thachpn.name.vn/books/get_cart.php");
			
			
		}
		
		

		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getActivity(), position + "", Toast.LENGTH_LONG).show();
				startBookFragment(arrList.get(position));

			}
		});

		payment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0;i < arrList.size(); i ++){
					AccountFragment.orderArrayBooks.add(arrList.get(i));
					arrList.remove(i);
				}

			}
		});
		return view;

	}

	public static int total_money() {
		int total = 0;
		for (int i = 0; i < arrList.size(); i++)
			total += arrList.get(i).getPrice() * arrList.get(i).getQuantity();
		return total;
	}

	public class GetFromCartAsyncTask extends AsyncTask<String, Void, String> {
		ProgressDialog pb;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (s != null) {
					// Log.d(s, s);
					//Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
					arrList = netWork.checkResultForGetUserBooks(s);
					if (arrList.size() == 0) {
						 Toast.makeText(getActivity(), "Not found anything in cart", Toast.LENGTH_LONG ).show();

					} else {
						for (int i = 0; i < arrList.size(); i++)
						{
							Log.d(arrList.get(i).getPrice() + "", arrList.get(i).getQuantity() + "");
						}
						total = total_money();
						subTotal.setText(total + "");
						adapter = new ListCartAdapter(getContext(), arrList);
						listview.setAdapter(adapter);
					}
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Fail");
				builder.setMessage("Check for Connect Server");
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
	private void startBookFragment(BookEntity book) {
		FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
		trans.replace(((ViewGroup) getView().getParent()).getId(), new BookFragment(getContext(), book));
		trans.addToBackStack(null);
		trans.commit();
	}


}