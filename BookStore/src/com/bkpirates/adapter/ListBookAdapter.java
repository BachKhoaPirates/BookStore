package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.MainActivity;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.fragment.CartFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListBookAdapter extends ArrayAdapter<BookEntity> {

	Context context;
	ArrayList<BookEntity> listBook;
	LayoutInflater inflater;

	public ListBookAdapter(Context context, ArrayList<BookEntity> list_product) {
		super(context, R.layout.item_books, list_product);
		this.context = context;
		this.listBook = list_product;
	}

	@Override
	public int getCount() {
		return listBook.size();
	}

	@Override
	public BookEntity getItem(int position) {

		return listBook.get(position);
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
			convertView = inflater.inflate(R.layout.item_books, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.imageBook);
			holder.name = (TextView) convertView.findViewById(R.id.nameBook);
			holder.author = (TextView) convertView.findViewById(R.id.authorBook);
			holder.price = (TextView) convertView.findViewById(R.id.priceBook);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(listBook.get(position).getLinkImage(), holder.image);
        Log.d("LinkUrl", ""+listBook.get(position).getLinkImage());
		holder.author.setText(listBook.get(position).getAuthor());
		holder.price.setText(Integer.toString(listBook.get(position).getPrice())+" VNĐ");
		holder.name.setText(listBook.get(position).getName());
		return convertView;
	}

	static class ViewHolder {
		ImageView image;
		TextView name;
		TextView author;
		TextView price;
	}

}