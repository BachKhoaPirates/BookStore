package com.bkpirates.HorizontalListView;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** An array adapter that knows how to render views when given CustomData classes */
public class HorizontalListViewAdapter extends ArrayAdapter<HorizontalListViewData> {
    private LayoutInflater mInflater;
    private ArrayList<HorizontalListViewData> array;

    public HorizontalListViewAdapter(Context context, ArrayList<HorizontalListViewData> array) {
        super(context, R.layout.horizontal_list_view_data, array);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.array = array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.horizontal_list_view_data, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            holder.text3 = (TextView) convertView.findViewById(R.id.text3);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        
        holder.image.setImageResource(R.drawable.book_image);
        
        holder.text1.setText(array.get(position).getName());
        holder.text2.setText(array.get(position).getAuthor());
        holder.text3.setText(Integer.toString(array.get(position).getPrice()));
        
        return convertView;
    }

    /** View holder for the views we need access to */
    private static class Holder {
    	public ImageView image;
    	public TextView text1;
    	public TextView text2;
    	public TextView text3;
    }
}
