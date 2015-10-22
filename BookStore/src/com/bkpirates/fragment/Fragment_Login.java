package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi")
public class Fragment_Login extends Fragment {

	private Button signIn;
	private Button crtAccount;
	private EditText phoneNumber;
	private EditText passWord;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, null);
		signIn = (Button) view.findViewById(R.id.btnSignIn);
		crtAccount = (Button) view.findViewById(R.id.btnCreateAccount);
		phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
		passWord = (EditText) view.findViewById(R.id.passWord);
		signIn.setEnabled(false);

		crtAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment_CrtAccount fca = new Fragment_CrtAccount();
				ft.commit();
			}
		});
		return view;
	}
}
