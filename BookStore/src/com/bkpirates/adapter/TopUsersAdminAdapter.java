package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TopUsersAdminAdapter extends ArrayAdapter<AccountEntity> {

	private Context context;
	private ArrayList<AccountEntity> arrAccount;

	public TopUsersAdminAdapter(Context context, ArrayList<AccountEntity> arr) {
		super(context, R.layout.item_distribute_book, arr);
		this.context = context;
		this.arrAccount = arr;
	}

	@Override
	public int getCount() {
		return arrAccount.size();
	}

	@Override
	public AccountEntity getItem(int position) {
		return arrAccount.get(position);
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
			convertView = inflater.inflate(R.layout.item_distribute_book, parent, false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText((position+1)+". "+arrAccount.get(position).getPhone() + "--" + arrAccount.get(position).getName());
		return convertView;
	}

	static class ViewHolder {
		TextView name;
	}

}
