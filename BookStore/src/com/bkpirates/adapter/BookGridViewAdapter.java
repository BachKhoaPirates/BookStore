package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookGridViewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<BookEntity> arr;

	public BookGridViewAdapter(Context context, ArrayList<BookEntity> arr) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.arr = arr;
	}

	public int getCount() {
		return arr.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
        	LayoutInflater inflater = (LayoutInflater) 
        			context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_view_book_item, parent, false);
            
            holder = new Holder();
        	holder.image = (ImageView) convertView.findViewById(R.id.image);
        	holder.text1 = (TextView) convertView.findViewById(R.id.text1);
        	holder.text2 = (TextView) convertView.findViewById(R.id.text2);
        	convertView.setTag(holder);
        } else {
        	holder = (Holder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(arr.get(position).getLinkImage(), holder.image);
        
        holder.text1.setText(arr.get(position).getAuthor());
        holder.text2.setText(Integer.toString(arr.get(position).getPrice())+" Đ");
        return convertView;
	}
	
	private static class Holder {
		public ImageView image;
		public TextView text1;
		public TextView text2;
	}

}
