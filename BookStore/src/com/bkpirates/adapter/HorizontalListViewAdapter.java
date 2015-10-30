package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** An array adapter that knows how to render views when given CustomData classes */
public class HorizontalListViewAdapter extends ArrayAdapter<BookEntity> {
    
    private ArrayList<BookEntity> array;
    private Context context;

    public HorizontalListViewAdapter(Context context, ArrayList<BookEntity> array) {
        super(context, R.layout.horizontal_list_view_data, array);
        this.context = context;
        this.array = array;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            // Inflate the view since it does not exist
        	LayoutInflater inflater = (LayoutInflater) 
        			context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.horizontal_list_view_data, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
          
            holder.text1 = (TextView) convertView.findViewById(R.id.text1);
            holder.text2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (position%3==0){
        	holder.image.setImageResource(R.drawable.book_image);        	
        } else if (position%3==1) {
        	holder.image.setImageResource(R.drawable.book_image2);
        } else {
        	holder.image.setImageResource(R.drawable.bookstore);
        }
        
        holder.text1.setText(array.get(position).getAuthor());
        holder.text2.setText(Integer.toString(array.get(position).getPrice()));
        
        return convertView;
    }

    /** View holder for the views we need access to */
    private static class Holder {
    	public ImageView image;

    	public TextView text1;
    	public TextView text2;
    }
}
