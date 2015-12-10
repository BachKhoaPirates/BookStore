package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class ProfitAdminFragment extends Fragment implements DataLoaderListener {

	private String beginDate, endDate;

	private Button beginBtn, endBtn, getBtn;
	private TextView beginDateTv, endDateTv;
	private TextView text;

	private DatePickerDialog.OnDateSetListener beginDateCallBack;
	private DatePickerDialog.OnDateSetListener endDateCallBack;
	private DatePickerDialog dateDialog;

	private ArrayList<String> profit;

	private DataLoader bld;
	private ProgressDialog dialog;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_profit, container, false);

		dialog = new ProgressDialog(getContext());
		dialog.setMessage("Loading...");
		dialog.setCancelable(false);


		beginBtn = (Button) view.findViewById(R.id.begin_btn);
		endBtn = (Button) view.findViewById(R.id.end_btn);
		getBtn = (Button) view.findViewById(R.id.get_btn);
		text =(TextView) view.findViewById(R.id.text);

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
					String str = "?datein=" + beginDate + "&dateout=" + endDate;

					bld = new DataLoader();
					bld.listener = ProfitAdminFragment.this;
					try {
							profit = (ArrayList<String>) bld
									.execute(getString(R.string.ADMIN_PROFIT) + str).get();

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

		if (profit != null) {
			setProfit();
		} else {
			Toast.makeText(getContext(), "Không tìm thấy!", Toast.LENGTH_LONG).show();
		}
	}

	private void setProfit() {
		text.setText("Lợi nhuận: "+profit.get(0)+"VNĐ");
		
	}
}
