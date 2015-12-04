package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.OrderAdminAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.OrderAdminEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

	private ArrayList<OrderAdminEntity> orderArray;

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
					type = spinner.getSelectedItemPosition();
					String str = "?datein=" + beginDate + "&dateout=" + endDate;

					bld = new DataLoader();
					bld.listener = ListOrderAdminFragment.this;
					try {
						switch (type) {
						case 0:
							orderArray = (ArrayList<OrderAdminEntity>) bld.execute(getString(R.string.ADMIN_GET_LIST_BOUGHT) +str).get();
							break;
						case 1:
							orderArray = (ArrayList<OrderAdminEntity>) bld.execute(getString(R.string.ADMIN_GET_LIST_ORDER) +str).get();
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

		if (orderArray != null){
			OrderAdminAdapter adapter = new OrderAdminAdapter(getContext(), orderArray);
			listview.setAdapter(adapter);
			
			
			switch (type) {
			case 0:
				
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					}
				});
				break;
			case 1:
				listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					}
				});
				break;
			}
		} else {
			Toast.makeText(getContext(), "Không tìm thấy đơn hàng nào!", Toast.LENGTH_LONG).show();
		}
	}

//	private void startBookFragment(BookEntity book) {
//		FragmentManager fm = getActivity().getSupportFragmentManager();
//		FragmentTransaction ft = fm.beginTransaction();
//		ft.replace(R.id.containerAdmin, new BookFragment(getContext(), book));
//		ft.addToBackStack(null);
//		ft.commit();
//		fm.executePendingTransactions();
//	}
}
