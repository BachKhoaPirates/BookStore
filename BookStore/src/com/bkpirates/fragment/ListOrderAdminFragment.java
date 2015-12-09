package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.OrderAdminAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.OrderEntity;
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

public class ListOrderAdminFragment extends Fragment implements DataLoaderListener {

	private String beginDate, endDate;

	private ListView listview;
	private Spinner spinner;
	private Button beginBtn, endBtn, getBtn;
	private TextView beginDateTv, endDateTv;

	private DatePickerDialog.OnDateSetListener beginDateCallBack;
	private DatePickerDialog.OnDateSetListener endDateCallBack;
	private DatePickerDialog dateDialog;

	private ArrayList<OrderEntity> orderArray;

	private DataLoader bld;
	private ProgressDialog dialog;

	private int type;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_admin_rank, container, false);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);

		spinner = (Spinner) view.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.order_types_arrays,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		listview = (ListView) view.findViewById(R.id.lView);
		View header = getActivity().getLayoutInflater().inflate(R.layout.divider, null);
		listview.addHeaderView(header);
		if (orderArray != null) {
			setListView();
		}

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
					bld.listener = ListOrderAdminFragment.this;
					try {
						switch (type) {
						case 0:
							orderArray = (ArrayList<OrderEntity>) bld
									.execute(getString(R.string.ADMIN_GET_LIST_BOUGHT) + str).get();
							break;
						case 1:
							orderArray = (ArrayList<OrderEntity>) bld
									.execute(getString(R.string.ADMIN_GET_LIST_ORDER) + str).get();
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
		if (dialog.isShowing()) {
			dialog.dismiss();
		}

		if (orderArray != null) {
			setListView();
		} else {
			Toast.makeText(getContext(), "Không tìm thấy đơn hàng nào!", Toast.LENGTH_LONG).show();
		}
	}

	private void setListView() {
		OrderAdminAdapter adapter = new OrderAdminAdapter(getContext(), orderArray);
		listview.setAdapter(adapter);

		switch (type) {
		case 0:

			listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					position = position - 1;
					FragmentManager fm = getActivity().getSupportFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.containerAdmin, new OrderFragmentAccount(orderArray.get(position).getOid(),
							orderArray.get(position).getTotalMoney()));
					ft.addToBackStack(null);
					ft.commit();
					fm.executePendingTransactions();
				}
			});
			break;
		case 1:
			listview.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					position = position - 1;
					FragmentManager fm = getActivity().getSupportFragmentManager();
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.containerAdmin, new OrderFragmentAdmin(orderArray.get(position).getOid(),
							orderArray.get(position).getTotalMoney()));
					ft.addToBackStack(null);
					ft.commit();
					fm.executePendingTransactions();
				}
			});
			break;
		}
	}
}
