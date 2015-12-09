package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookInOrderAdminAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;
import com.bkpirates.webservice.SendRequest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class OrderFragmentAdmin extends Fragment implements DataLoaderListener {

	private ListView listview;
	private TextView moneyTv, oidTv;
	private ArrayList<BookEntity> bookArray;
	private DataLoader ld;
	private String oid;
	private String totalMoney;
	private ProgressDialog dialog;
	private Button confirmBtn, delBtn;
	private SendRequest rq;

	public OrderFragmentAdmin(String oid, String totalMoney) {
		// TODO Auto-generated constructor stub
		this.oid = oid;
		this.totalMoney = totalMoney;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order_admin, container, false);
		listview = (ListView) view.findViewById(R.id.list_book);
		moneyTv = (TextView) view.findViewById(R.id.subTotal);
		oidTv = (TextView) view.findViewById(R.id.oid);
		confirmBtn = (Button) view.findViewById(R.id.confirm);
		delBtn = (Button) view.findViewById(R.id.delete);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);
		dialog.show();

		rq = new SendRequest();
		ld = new DataLoader();
		ld.listener = this;
		try {
			bookArray = (ArrayList<BookEntity>) ld
					.execute(getString(R.string.ADMIN_GET_LIST_BOOK_IN_ORDER) + "?oid=" + oid).get();
			Log.d("LINKKKKKKL: ", getString(R.string.ADMIN_GET_LIST_BOOK_IN_ORDER) + "?oid=" + oid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		if (bookArray != null) {
			ListBookInOrderAdminAdapter adapter = new ListBookInOrderAdminAdapter(getContext(), bookArray);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					startBookFragment(bookArray.get(position));
				}
			});
		} else {
			Toast.makeText(getContext(), "Lỗi không tải được sách", Toast.LENGTH_LONG).show();
		}

		moneyTv.setText(totalMoney + " VNĐ");
		oidTv.setText("MSĐH: " + oid);
		dialog.dismiss();

		confirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int result = 0;
				try {
					result = rq.execute(getString(R.string.ADMIN_CONFIRM_ORDER) + "?oid=" + oid).get();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (result == 1) {
					Toast.makeText(getContext(), "Gửi yêu cầu thành công!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getContext(), "Có lỗi!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		delBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int result = 0;
				try {
					result = rq.execute(getString(R.string.ADMIN_DELETE_ORDER) + "?oid=" + oid).get();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (result == 1) {
					Toast.makeText(getContext(), "Gửi yêu cầu thành công!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getContext(), "Có lỗi!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void startBookFragment(BookEntity book) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		// trans.replace(((ViewGroup) getView().getParent()).getId(), new
		// BookFragment(getContext(), book));
		ft.replace(R.id.containerAdmin, new BookFragment(getContext(), book));
		ft.addToBackStack(null);
		ft.commit();
		fm.executePendingTransactions();
	}

}
