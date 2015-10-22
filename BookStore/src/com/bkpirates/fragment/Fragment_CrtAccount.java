package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;

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
	
	private String phone;
	private String name;
	private String address;
	private String pass;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_crtaccount, null);
		phoneNumberCrt = (EditText) view.findViewById(R.id.phoneNumberCrt);
		nameCrt = (EditText) view.findViewById(R.id.nameCrt);
		addressCrt = (EditText) view.findViewById(R.id.addressCrt);
		passWordCrt = (EditText) view.findViewById(R.id.passWordCrt);
		register = (Button) view.findViewById(R.id.btnRegister);
		
		
		return view;
	}

}
