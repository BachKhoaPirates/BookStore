package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;
import com.bkpirates.webservice.SendRequest;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InsertQuantityBookAdminFragment extends Fragment implements DataLoaderListener {

	private BookEntity book;
	private EditText quantityEdit, priceEdit, searchEdit;
	private Button updateBtn;
	private int addQuantity, price;
	private ArrayList<BookEntity> arrayBook = null;
	private ProgressDialog dialog;

	private TextView bidTv, nameTv, authorTv, priceTv, quantityTv;
	private ImageView image;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_insert_quantity_book, container, false);

		bidTv = (TextView) view.findViewById(R.id.bid);
		image = (ImageView) view.findViewById(R.id.imageBook);
		nameTv = (TextView) view.findViewById(R.id.nameBook);
		authorTv = (TextView) view.findViewById(R.id.authorBook);
		priceTv = (TextView) view.findViewById(R.id.priceBook);
		quantityTv = (TextView) view.findViewById(R.id.quantity);

		searchEdit = (EditText) view.findViewById(R.id.searchText);
		quantityEdit = (EditText) view.findViewById(R.id.quantity_add);
		priceEdit = (EditText) view.findViewById(R.id.price_add);
		updateBtn = (Button) view.findViewById(R.id.update);
		ImageView searchBtn = (ImageView) view.findViewById(R.id.searchButton);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);

		updateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (book == null) {
					Toast.makeText(getContext(), "Chưa chọn sách!", Toast.LENGTH_SHORT).show();
				} else if (!checkEditText(quantityEdit) || !checkEditText(priceEdit)) {
					Toast.makeText(getContext(), "Đầu vào không đúng!", Toast.LENGTH_SHORT).show();
				} else {
					addQuantity = Integer.parseInt(quantityEdit.getText().toString().trim());
					price = Integer.parseInt(priceEdit.getText().toString().trim());

					SendRequest rq = new SendRequest();
					int result = 0;
					try {
						result = rq.execute(getString(R.string.ADMIN_UPDATE_QUANTITY) + "?bid=" + book.getBid()
								+ "&price_add=" + price + "&quantity_add=" + addQuantity).get();
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (result == 0) {
						Toast.makeText(getContext(), "Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
						//update view
						book.setQuantity(book.getQuantity() + addQuantity);
						priceTv.setText("Giá nhập vào: "+price + " VNĐ");
						quantityTv.setText("Số lượng: " + book.getQuantity());
						// clear edit text
						quantityEdit.setText("");
						priceEdit.setText("");
					}
				}

			}
		});

		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onSearchButtonClick();
			}
		});

		searchEdit.setFocusableInTouchMode(true);
		searchEdit.requestFocus();
		searchEdit.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

					onSearchButtonClick();
					return true;
				}
				return false;
			}
		});

		return view;
	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		if (arrayBook != null) {
			FragmentManager fm = getActivity().getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.containerAdmin, new BookGridViewAdmin(arrayBook, "INSERT"));
			ft.addToBackStack(null);
			ft.commit();
			fm.executePendingTransactions();

		} else {
			Toast.makeText(getContext(), "Không tìm thấy sách nào!", Toast.LENGTH_LONG).show();
		}
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	private boolean checkEditText(EditText edText) {
		if (edText.getText().toString().trim().length() == 0) {
			return false;
		}
		int n;
		try {
			n = Integer.parseInt(edText.getText().toString().trim());
		} catch (NumberFormatException e) {
			return false;
		}
		if (n <= 0){
			return false;
		}
		return true;
	}

	private void onSearchButtonClick() {
		String ed_text = searchEdit.getText().toString().trim();
		if (ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null) {
			// edit text empty, do nothing
		} else {
			dialog.show();
			DataLoader bld = new DataLoader();
			bld.listener = InsertQuantityBookAdminFragment.this;
			try {
				arrayBook = (ArrayList<BookEntity>) bld.execute(getString(R.string.ADMIN_SEARCH) +"?key="+ ed_text).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}

	public void updateUI() {
		ImageLoader.getInstance().displayImage(book.getLinkImage(), image);
		bidTv.setText("MS :" + book.getBid());
		authorTv.setText(book.getAuthor());
		priceTv.setText("Giá nhập vào: "+Integer.toString(book.getPrice()) + " VNĐ");
		nameTv.setText(book.getName());
		quantityTv.setText("Số lượng: " + book.getQuantity());
	}
}