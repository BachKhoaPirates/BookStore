package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookAdapter;
import com.bkpirates.adapter.ListDistributeAdapter;
import com.bkpirates.adapter.TopUsersAdminAdapter;
import com.bkpirates.app.AppController;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.DistributeBookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RankFragment extends Fragment implements DataLoaderListener {

	private String beginDate, endDate;

	private ListView listview;
	private Spinner spinner;
	private Button beginBtn, endBtn, getBtn;
	private TextView beginDateTv, endDateTv;

	private DatePickerDialog.OnDateSetListener beginDateCallBack;
	private DatePickerDialog.OnDateSetListener endDateCallBack;
	private DatePickerDialog dateDialog;

	private ArrayList<AccountEntity> arrAcc;
	private ArrayList<BookEntity> arrBook;
	private ArrayList<DistributeBookEntity> arrDistribute;
	private final String GET_TOP_USERS = "http://thachpn.name.vn/books/admin_get_top_users.php";
	private final String GET_TOP_BOOK = "http://thachpn.name.vn/books/admin_get_top_books.php";
	private final String GET_TOP_DISTRIBUTE = "http://thachpn.name.vn/books/admin_get_top_distribute.php";

	private DataLoader bld;
	private ProgressDialog dialog;

	private int type;
	private boolean resultNotNULL;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_admin_rank, container, false);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);

		spinner = (Spinner) view.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.rank_types_arrays,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setPrompt(getString(R.string.rank_types_prompt));
		spinner.setAdapter(adapter);

		listview = (ListView) view.findViewById(R.id.lView);

		beginBtn = (Button) view.findViewById(R.id.begin_btn);
		endBtn = (Button) view.findViewById(R.id.end_btn);
		getBtn = (Button) view.findViewById(R.id.get_btn);

		beginDateTv = (TextView) view.findViewById(R.id.begin_date);
		endDateTv = (TextView) view.findViewById(R.id.end_date);

		setCallBack();

		beginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Creating an object of DatePickerDialog in context
				// dateCallback is called which defined below
				dateDialog = new DatePickerDialog(getContext(), beginDateCallBack, 2014, 12, 12);
				// Showing the DatePickerDialog
				dateDialog.show();
			}
		});

		endBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Creating an object of DatePickerDialog in context
				// dateCallback is called which defined below
				dateDialog = new DatePickerDialog(getContext(), endDateCallBack, 2014, 12, 12);
				// Showing the DatePickerDialog
				dateDialog.show();
			}
		});

		getBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (beginDate == null) {
					Toast.makeText(getContext(), "Chưa nhập ngày bắt đầu!", Toast.LENGTH_LONG).show();
				} else if (endDate == null) {
					Toast.makeText(getContext(), "Chưa nhập ngày kết thúc!", Toast.LENGTH_LONG).show();
				} else {
					dialog.show();
					type = spinner.getSelectedItemPosition() + 1;
					String str = "?datein=" + beginDate + "&dateout=" + endDate;

					bld = new DataLoader();
					bld.listener = RankFragment.this;
					try {
						switch (type) {
						case 1:
							arrAcc = (ArrayList<AccountEntity>) bld.execute(GET_TOP_USERS + str).get();
							break;
						case 2:
							arrBook = (ArrayList<BookEntity>) bld.execute(GET_TOP_BOOK + str).get();
							break;
						case 3:
							arrDistribute = (ArrayList<DistributeBookEntity>) bld.execute(GET_TOP_DISTRIBUTE + str)
									.get();
							break;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});

		return view;
	}

	private void setCallBack() {
		beginDateCallBack = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				monthOfYear++;
				beginDate = Admin_Fragment.convertDateToString(year, monthOfYear, dayOfMonth);
				beginDateTv.setText(Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear) + "/"
						+ Integer.toString(year));
			}
		};

		endDateCallBack = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				monthOfYear++;
				endDate = Admin_Fragment.convertDateToString(year, monthOfYear, dayOfMonth);
				endDateTv.setText(Integer.toString(dayOfMonth) + "/" + Integer.toString(monthOfYear) + "/"
						+ Integer.toString(year));
			}
		};

	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

		resultNotNULL = false;

		switch (type) {
		case 1:
			if (arrAcc != null) {
				resultNotNULL = true;
				TopUsersAdminAdapter adapter = new TopUsersAdminAdapter(getContext(), arrAcc);
				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						AppController.getInstance().initiatePopupWindow(arrAcc.get(position), getActivity());
					}
				});
			}
			break;
		case 2:
			if (arrBook != null) {
				resultNotNULL = true;
				ListBookAdapter adapter = new ListBookAdapter(getContext(), arrBook);
				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Log.d("POSITION: ", ""+position); 
						startBookFragment(arrBook.get(position));
//						Log.d("NAME: ", ""+arrBook.get(position).getName());
					}
				});
			}
			break;
		case 3:
			if (arrDistribute != null) {
				resultNotNULL = true;
				ListDistributeAdapter adapter = new ListDistributeAdapter(getContext(), arrDistribute);
				listview.setAdapter(adapter);
				
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						//do nothing
					}
				});
			}
			break;
		}

		if (resultNotNULL == true) {
			View header = getActivity().getLayoutInflater().inflate(R.layout.divider, null);
			listview.addHeaderView(header);
		} else {
			Toast.makeText(getContext(), "Không tìm thấy tài khoản nào!", Toast.LENGTH_LONG).show();
		}
	}

	private void startBookFragment(BookEntity book) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.container, new BookFragment(getContext(), book));
		ft.addToBackStack(null);
		ft.commit();
		fm.executePendingTransactions();
	}
}
