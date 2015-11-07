package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BookFragment extends Fragment {

	private Context context;
	private BookEntity book;
	private int numberBookToBuy = 1;

	TextView increase_button, number, decrease_button;

	public BookFragment(Context context, BookEntity book) {
		this.context = context;
		this.book = book;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_book, container, false);

		increase_button = (TextView) view.findViewById(R.id.increase_button);
		number = (TextView) view.findViewById(R.id.number);
		decrease_button = (TextView) view.findViewById(R.id.decrease_button);

		number.setText(Integer.toString(numberBookToBuy));
		increase_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				number.setText(Integer.toString(++numberBookToBuy));
			}
		});
		decrease_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (numberBookToBuy > 1)
					number.setText(Integer.toString(--numberBookToBuy));
			}
		});

		ImageView image = (ImageView) view.findViewById(R.id.image);

		image.setImageResource(R.drawable.book_image);

		return view;
	}
}
