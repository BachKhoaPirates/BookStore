package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
	public static AccountEntity accEntity = new AccountEntity() ;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, null);
		signIn = (Button) view.findViewById(R.id.btnSignIn);
		crtAccount = (Button) view.findViewById(R.id.btnCreateAccount);
		phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
		passWord = (EditText) view.findViewById(R.id.passWord);
		signIn.setEnabled(false);

		phoneNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (phoneNumber.getText().toString().equals("") == false
						&& passWord.getText().toString().equals("") == false) {
					signIn.setEnabled(true);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		passWord.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (phoneNumber.getText().toString().equals("") == false
						&& passWord.getText().toString().equals("") == false) {
					signIn.setEnabled(true);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		signIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				phone = phoneNumber.getText().toString();
				pass = passWord.getText().toString();
				if (phone.equals("123456789") == true && pass.equals("123456789") == true) {
					InputMethodManager imm = (InputMethodManager) getActivity()
							.getSystemService(Activity.INPUT_METHOD_SERVICE);
					imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
					accEntity.setPhone("123456789");
					accEntity.setPassword("123456789");
					AccountFragment fa = new AccountFragment();
					FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, fa);
					ft.addToBackStack(null);
					ft.commit();

				} else {
					AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
					dialog.setTitle("Are you wrong account ?").setCancelable(false)
							.setMessage("Phonenumber of password are incorrect. Please try again.")
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
					dialog.create().show();

				}
			}
		});
		crtAccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				CrtAccountFragment caf = new CrtAccountFragment();
				ft.replace(R.id.container, caf);
				ft.addToBackStack(null);
				ft.commit();
			}
		});

		return view;
	}

//	@Override
//	public void onResume() {
//		SharedPreferences pre = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//		phoneNumber.setText(pre.getString("phone", ""));
//		passWord.setText(pre.getString("pass", ""));
//		super.onResume();
//	}
//
//	@Override
//	public void onPause() {
//		SharedPreferences pre = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//		SharedPreferences.Editor editor = pre.edit();
//		editor.putString("phone", phoneNumber.getText().toString());
//		editor.putString("pass", passWord.getText().toString());
//		editor.commit();
//		super.onPause();
//	}
}
