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

public class RankAdminFragment extends Fragment implements DataLoaderListener {

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
		// spinner.setPrompt(getString(R.string.rank_types_prompt));
		spinner.setAdapter(adapter);

		listview = (ListView) view.findViewById(R.id.lView);
		View header = getActivity().getLayoutInflater().inflate(R.layout.divider, null);
		listview.addHeaderView(header);
		setListView();

		beginBtn = (Button) view.findViewById(R.id.begin_btn);
		endBtn = (Button) view.findViewById(R.id.end_btn);
		getBtn = (Button) view.findViewById(R.id.get_btn);

		beginDateTv = (TextView) view.findViewById(R.id.begin_date);
		if (beginDate != null) {
			beginDateTv.setText(Admin_Fragment.convertStringToDate(beginDate));
		}
		endDateTv = (TextView) view.findViewById(R.id.end_date);
		if (endDate != null) {
			endDateTv.setText(Admin_Fragment.convertStringToDate(endDate));
		}

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
					type = spinner.getSelectedItemPosition();
					String str = "?datein=" + beginDate + "&dateout=" + endDate;

					bld = new DataLoader();
					bld.listener = RankAdminFragment.this;
					try {
						switch (type) {
						case 0:
							arrAcc = (ArrayList<AccountEntity>) bld
									.execute(getString(R.string.ADMIN_GET_TOP_USERS) + str).get();
							break;
						case 1:
							arrBook = (ArrayList<BookEntity>) bld.execute(getString(R.string.ADMIN_GET_TOP_BOOK) + str)
									.get();
							break;
						case 2:
							arrDistribute = (ArrayList<DistributeBookEntity>) bld
									.execute(getString(R.string.ADMIN_GET_TOP_DISTRIBUTE) + str).get();
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
				beginDateTv.setText(Admin_Fragment.convertStringToDate(beginDate));
			}
		};

		endDateCallBack = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				monthOfYear++;
				endDate = Admin_Fragment.convertDateToString(year, monthOfYear, dayOfMonth);
				endDateTv.setText(Admin_Fragment.convertStringToDate(endDate));
			}
		};

	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		resultNotNULL = false;

		setListView();
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

		if (resultNotNULL == false) {
			Toast.makeText(getContext(), "Không tìm thấy tài khoản nào!", Toast.LENGTH_LONG).show();
		}

	}

	private void setListView() {
		switch (type) {
		case 0:
			if (arrAcc != null) {
				resultNotNULL = true;
				TopUsersAdminAdapter adapter = new TopUsersAdminAdapter(getContext(), arrAcc);
				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						position = position - 1;
						AppController.getInstance().initiatePopupWindow(arrAcc.get(position), getActivity());
					}
				});
			}
			break;
		case 1:
			if (arrBook != null) {
				resultNotNULL = true;
				ListBookAdapter adapter = new ListBookAdapter(getContext(), arrBook);
				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						position = position - 1;
						Log.d("POSITION: ", "" + position);
						startBookFragment(arrBook.get(position));
					}
				});
			}
			break;
		case 2:
			if (arrDistribute != null) {
				resultNotNULL = true;
				ListDistributeAdapter adapter = new ListDistributeAdapter(getContext(), arrDistribute);
				listview.setAdapter(adapter);

				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// do nothing
					}
				});
			}
			break;
		}

	}

	private void startBookFragment(BookEntity book) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.containerAdmin, new BookFragment(getContext(), book));
		ft.addToBackStack(null);
		ft.commit();
		fm.executePendingTransactions();
	}
}
