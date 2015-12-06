package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBookInOrderAdminAdapter extends ArrayAdapter<BookEntity> {

	Context context;
	ArrayList<BookEntity> bookArray;
	LayoutInflater inflater;

	public ListBookInOrderAdminAdapter(Context context, ArrayList<BookEntity> arr) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.book_in_order_item, arr);
		this.context = context;
		this.bookArray = arr;
	}

	@Override
	public int getCount() {
		return bookArray.size();
	}

	@Override
	public BookEntity getItem(int position) {

		return bookArray.get(position);
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
			convertView = inflater.inflate(R.layout.book_in_order_item, parent, false);
			holder = new ViewHolder();
			holder.bid = (TextView) convertView.findViewById(R.id.bid);
			holder.image = (ImageView) convertView.findViewById(R.id.imageBook);
			holder.name = (TextView) convertView.findViewById(R.id.nameBook);
			holder.author = (TextView) convertView.findViewById(R.id.authorBook);
			holder.price = (TextView) convertView.findViewById(R.id.priceBook);
			holder.quantity = (TextView) convertView.findViewById(R.id.quantity);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(bookArray.get(position).getLinkImage(), holder.image);
		holder.bid.setText("MS :" + bookArray.get(position).getBid());
		holder.author.setText(bookArray.get(position).getAuthor());
		holder.price.setText(Integer.toString(bookArray.get(position).getPrice()) + " VNĐ");
		holder.name.setText(bookArray.get(position).getName());
		holder.quantity.setText("Số lượng: " + bookArray.get(position).getQuantity());
		return convertView;
	}

	static class ViewHolder {
		ImageView image;
		TextView bid;
		TextView name;
		TextView author;
		TextView price;
		TextView quantity;
	}

}