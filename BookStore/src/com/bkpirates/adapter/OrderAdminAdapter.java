package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.OrderEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrderAdminAdapter extends ArrayAdapter<OrderEntity> {

	private Context context;
	private ArrayList<OrderEntity> orderArray;

	public OrderAdminAdapter(Context context, ArrayList<OrderEntity> arr) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.item_distribute_book, arr);
		this.context = context;
		this.orderArray = arr;
	}

	@Override
	public int getCount() {
		return orderArray.size();
	}

	@Override
	public OrderEntity getItem(int position) {
		return orderArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.order_item, parent, false);
			holder = new ViewHolder();

			holder.oid = (TextView) convertView.findViewById(R.id.oid);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			holder.totalMoney = (TextView) convertView.findViewById(R.id.total_money);
			holder.orderPerson = (TextView) convertView.findViewById(R.id.order_person);
			holder.orderPersonAddress = (TextView) convertView.findViewById(R.id.order_person_address);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(orderArray.get(position).getCheckOrder() == 1) // set color of confirm order of users
		{
			holder.oid.setTextColor(context.getResources().getColor(R.color.green));
			holder.date.setTextColor(context.getResources().getColor(R.color.green));
		}
		holder.oid.setText("MSĐH: " + orderArray.get(position).getOid());
		holder.date.setText("Ngày: " + orderArray.get(position).getDate());
		holder.totalMoney.setText("Tổng tiền: " + orderArray.get(position).getTotalMoney()+" VNĐ");
		holder.orderPerson.setText("Người nhận: " + orderArray.get(position).getOrderPerson());
		if (orderArray.get(position).getOrderPersonAddress() != null) {
			holder.orderPersonAddress.setText("Địa chỉ: " + orderArray.get(position).getOrderPersonAddress());
		}
		return convertView;
	}

	static class ViewHolder {
		TextView oid;
		TextView date;
		TextView totalMoney;
		TextView orderPerson;
		TextView orderPersonAddress;
	}

}
