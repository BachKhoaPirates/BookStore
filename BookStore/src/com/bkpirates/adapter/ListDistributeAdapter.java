package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.adapter.ListBookAdapter.ViewHolder;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.DistributeBookEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListDistributeAdapter extends ArrayAdapter<DistributeBookEntity> {

	private Context context;
	private ArrayList<DistributeBookEntity> arr;

	public ListDistributeAdapter(Context context, ArrayList<DistributeBookEntity> arr) {
		super(context, R.layout.item_distribute_book, arr);
		this.context = context;
		this.arr = arr;
	}

	@Override
	public int getCount() {
		return arr.size();
	}

	@Override
	public DistributeBookEntity getItem(int position) {
		return arr.get(position);
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
		
		holder.name.setText(arr.get(position).getName());

		return convertView;
	}

	static class ViewHolder {
		TextView name;
	}
}