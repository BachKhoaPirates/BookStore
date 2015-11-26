package com.bkpirates.fragment;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.webservice.NetWork;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

@SuppressLint("NewApi")
public class CrtAccountFragment extends Fragment {

	private EditText phoneNumberCrt;
	private EditText nameCrt;
	private EditText addressCrt;
	private EditText passWordCrt;

	private String phone;
	private String name;
	private String address;
	private String pass;
	private Button register;
	static int check = 0;
	private NetWork netWork = new NetWork();

	public static AccountEntity account = new AccountEntity();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_crtaccount, null);
		phoneNumberCrt = (EditText) view.findViewById(R.id.phoneNumberCrt);
		nameCrt = (EditText) view.findViewById(R.id.nameCrt);
		addressCrt = (EditText) view.findViewById(R.id.addressCrt);
		passWordCrt = (EditText) view.findViewById(R.id.passWordCrt);
		register = (Button) view.findViewById(R.id.btnRegister);

		register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				phone = phoneNumberCrt.getText().toString();
				account.setPhone(phone);
				pass = passWordCrt.getText().toString();
				account.setPassword(pass);
				name = nameCrt.getText().toString();
				account.setName(name);
				address = addressCrt.getText().toString();
				account.setAddress(address);
				netWork.setAddress(address);
				netWork.setName(name);
				netWork.setPass(pass);
				netWork.setPhone(phone);
				NetWorkAsyncTask nw = (NetWorkAsyncTask) new NetWorkAsyncTask()
						.execute("http://thachpn.name.vn/books/create_account.php");

			}
		});

		return view;
	}

	public class NetWorkAsyncTask extends AsyncTask<String, Void, String> {
		ProgressDialog pb;

		@Override
		protected void onPreExecute() {
			pb = new ProgressDialog(getActivity());
			pb.setMessage("Creating...");
			pb.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (pb != null) {
				pb.dismiss();
			}
			if (s != null) {
				check = netWork.checkAccountForCreateAccount(s);
				Toast.makeText(getActivity(), check + "", Toast.LENGTH_LONG).show();
				if (check == 1) {
					SharedPreferences pre = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = pre.edit();
					editor.putString("checkLogin", 1 + "");
					editor.putString("phone", phone);
					editor.putString("pass", pass);
					editor.commit();
					getActivity().getSupportFragmentManager().popBackStack();
					getActivity().getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, new LoginFragment()).commit();
				} else {
					Toast.makeText(getActivity(), netWork.getPhone() + netWork.getAddress() + netWork.getName() + netWork.getPass(), Toast.LENGTH_LONG).show();
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setTitle("Fail");
					builder.setMessage("Account existed");
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
			}
			super.onPostExecute(s);
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			HttpResponse response = null;
			try {
				response = netWork.makeRquestCreateAccount(url);
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
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;

		}
	}
}
