package com.bkpirates.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import com.bkpirates.bookstore.MainActivity;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.fragment.BookFragment;
import com.bkpirates.fragment.CartFragment;
import com.bkpirates.fragment.LoginFragment;
import com.bkpirates.webservice.NetWork;
import com.bkpirates.fragment.BookFragment.AddToCartAndFavoriteListAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListCartAdapter extends ArrayAdapter<BookEntity> {

	Context context;
	ArrayList<BookEntity> listBook;
	LayoutInflater inflater;
	NetWork netWork = new NetWork();
	int check = 0;

	private final String DELETE_BOOK = "http://thachpn.name.vn/books/delete_cart_book.php";

	public ListCartAdapter(Context context, ArrayList<BookEntity> list_product) {
		super(context, R.layout.cart_books, list_product);
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
			convertView = inflater.inflate(R.layout.cart_books, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.imageBook);
			holder.name = (TextView) convertView.findViewById(R.id.nameBook);
			holder.author = (TextView) convertView.findViewById(R.id.authorBook);
			holder.price = (TextView) convertView.findViewById(R.id.priceBook);
			holder.deleteButton = (ImageView) convertView.findViewById(R.id.deleteBtn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(listBook.get(position).getLinkImage(), holder.image);

		holder.author.setText(listBook.get(position).getAuthor());
		holder.price.setText(Integer.toString(listBook.get(position).getPrice()));
		holder.name.setText(listBook.get(position).getName());
		holder.deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HttpResponse response = null;
				String content = null;
				netWork.setBookEntity(listBook.get(position));
				Log.d(netWork.getBookEntity().getBid() + "", netWork.getBookEntity().getBid() + "");
				netWork.setPhone(LoginFragment.accEntity.getPhone());
				AddToCartAndFavoriteListAsyncTask add = (AddToCartAndFavoriteListAsyncTask) new AddToCartAndFavoriteListAsyncTask()						
						.execute(DELETE_BOOK);
				CartFragment.arrList.remove(position);
				CartFragment.adapter.notifyDataSetChanged();
				CartFragment.subTotal.setText(CartFragment.total_money() + "");
			}
		});
		return convertView;
	}

	static class ViewHolder {
		ImageView image;
		TextView name;
		TextView author;
		TextView price;
		ImageView deleteButton;
	}

	public class AddToCartAndFavoriteListAsyncTask extends AsyncTask<String, Void, String> {
		ProgressDialog pb;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (s != null) {
				check = netWork.checkForAddCartAndFavoriteList(s);
				if (check == 1)
					Log.d(check + "", check + "");
				else
					Log.d(check + "", check + "");
			} else {
			}

			super.onPostExecute(s);
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			HttpResponse response = null;
			try {
				response = netWork.makeRquestAddToCart(url);
			} catch (IOException e) {
				return null;
			}
			if (response != null) {
				String content = null;
				try {
					content = netWork.processHTTPResponce(response);
					return content;
				} catch (IOException e) {
					return null;
				} catch (ParseException e) {
					return null;
				}
			}

			return null;
		}
	}

}