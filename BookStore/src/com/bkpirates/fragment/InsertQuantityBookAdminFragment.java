package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertQuantityBookAdminFragment extends Fragment {

	private BookEntity book;
	private EditText quantityEdit, priceEdit;
	private Button updateBtn;
	private int quantity, price;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_insert_quantity_book, container, false);
		quantityEdit = (EditText) view.findViewById(R.id.quantity_add);
		priceEdit = (EditText) view.findViewById(R.id.price_add);
		updateBtn = (Button) view.findViewById(R.id.update);
		
		
		updateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (book != null){
					Toast.makeText(getContext(), "Chưa chọn sách!", Toast.LENGTH_SHORT).show();
				} else if (checkEditText(quantityEdit) || checkEditText(priceEdit)){
					Toast.makeText(getContext(), "Đầu vào không đúng!", Toast.LENGTH_SHORT).show();
				} else {
					quantity = Integer.parseInt(quantityEdit.getText().toString().trim());
					price = Integer.parseInt(priceEdit.getText().toString().trim());
					Log.d("quantity", ""+quantity);
					Log.d("price", ""+price);
				}
				
				
			}
		});

		return view;
	}
	
	private boolean checkEditText(EditText edText){
		if (edText.getText().toString().trim().length() == 0){
			return false;
		}
		
		try {
			Integer.parseInt(edText.getText().toString().trim());
		} catch (NumberFormatException e){
			e.printStackTrace();
//			return false;
		}
		return true;
	}

	
	public void setBook(BookEntity book){
		this.book = book;
	}
}