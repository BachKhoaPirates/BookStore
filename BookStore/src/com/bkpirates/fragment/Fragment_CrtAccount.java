package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi")
public class Fragment_CrtAccount extends Fragment {

	private EditText phoneNumberCrt;
	private EditText nameCrt;
	private EditText addressCrt;
	private EditText passWordCrt;
	private Button register;
	
	AccountEntity account = new AccountEntity();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_crtaccount, null);
		phoneNumberCrt = (EditText) view.findViewById(R.id.phoneNumberCrt);
		nameCrt = (EditText) view.findViewById(R.id.nameCrt);
		addressCrt = (EditText) view.findViewById(R.id.addressCrt);
		passWordCrt = (EditText) view.findViewById(R.id.passWordCrt);
		register = (Button) view.findViewById(R.id.btnRegister);
		
		account.setPhone(phoneNumberCrt.getText().toString());
		account.setPassword(passWordCrt.getText().toString());
		account.setName(nameCrt.getText().toString());
		account.setAddress(addressCrt.getText().toString());
		
		return view;
	}

}
