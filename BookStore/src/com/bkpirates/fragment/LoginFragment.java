package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi")
public class LoginFragment extends Fragment {

	private Button signIn;
	private Button crtAccount;
	private EditText phoneNumber;
	private EditText passWord;
	private String phone;
	private String pass;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, null);
		signIn = (Button) view.findViewById(R.id.btnSignIn);
		crtAccount = (Button) view.findViewById(R.id.btnCreateAccount);
		phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
		passWord = (EditText) view.findViewById(R.id.passWord);
		signIn.setEnabled(false);

		signIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phone = phoneNumber.getText().toString();
				pass = passWord.getText().toString();
				if(phone.equals("123456789") == true && pass.equals("123456789") == true)
				{
					InputMethodManager imm = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,
							0);
					AccountFragment fa = new AccountFragment();
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, fa);
					ft.addToBackStack(null);
					ft.commit();
					
				}
			}
		});
		crtAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				CrtAccountFragment fca = new CrtAccountFragment();
				ft.commit();
			}
		});
		return view;
	}
}
