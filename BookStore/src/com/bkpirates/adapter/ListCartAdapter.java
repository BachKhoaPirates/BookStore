package com.bkpirates.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.fragment.CartFragment;
import com.bkpirates.fragment.LoginFragment;
import com.bkpirates.webservice.NetWork;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.R.integer;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ListCartAdapter extends ArrayAdapter<BookEntity> {

	Context context;
	ArrayList<BookEntity> listBook;
	LayoutInflater inflater;
	NetWork netWork = new NetWork();
	int check = 0;
	private final int DEFINE_QUANTITY = 50;
	Integer[] arrQuantity = new Integer[DEFINE_QUANTITY];
	private final String DELETE_BOOK = "http://thachpn.name.vn/books/delete_cart_book.php";
	private final String ADD_CART = "http://thachpn.name.vn/books/add_cart.php";
	private ArrayAdapter<Integer> adapter = null;

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
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.cart_books, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.imageBook);
			holder.name = (TextView) convertView.findViewById(R.id.nameBook);
			holder.author = (TextView) convertView.findViewById(R.id.authorBook);
			holder.price = (TextView) convertView.findViewById(R.id.priceBook);
			holder.deleteButton = (ImageView) convertView.findViewById(R.id.deleteBtn);
			holder.numberToBuy = (TextView) convertView.findViewById(R.id.numberToBuy);
			holder.spinner = (Spinner) convertView.findViewById(R.id.spinner1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// load image
		ImageLoader.getInstance().displayImage(listBook.get(position).getLinkImage(), holder.image);
		Log.d(listBook.get(position).getNumberBookToBuy() + "", listBook.get(position).getNumberBookToBuy() + "");
		holder.numberToBuy.setText("Quantity:" + listBook.get(position).getNumberBookToBuy() + "");
		holder.name.setText(listBook.get(position).getName());
		holder.author.setText(listBook.get(position).getAuthor());
		holder.price.setText(Integer.toString(listBook.get(position).getPrice()));
		final int tempQuantity = listBook.get(position).getQuantity();
		for (int i = 0; i < DEFINE_QUANTITY; i++)
			arrQuantity[i] = i + 1;
		adapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, arrQuantity);
		adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		holder.spinner.setAdapter(adapter);
		
		holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
				if(arrQuantity[position1] > tempQuantity){
					AlertDialog.Builder dialog = new AlertDialog.Builder(context);
					dialog.setTitle("");
					dialog.setMessage("Sorry because not enough quantity");
					dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					dialog.setCancelable(false);
					dialog.create();
					dialog.show();
						
				}else if(tempQuantity >= arrQuantity[position1] && arrQuantity[position1]!=1){
					listBook.get(position).setNumberBookToBuy(arrQuantity[position1]);
					holder.numberToBuy.setText("Quantity:"+ arrQuantity[position1]);
					CartFragment.subTotal.setText(CartFragment.total_money() + "");
					netWork.setBookEntity(listBook.get(position));
					netWork.setPhone(LoginFragment.accEntity.getPhone());
					netWork.setNumberBookToBuy(arrQuantity[position1]);
					AddToCartAndFavoriteListAsyncTask add = (AddToCartAndFavoriteListAsyncTask) new AddToCartAndFavoriteListAsyncTask()
							.execute(ADD_CART);
					
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		holder.deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HttpResponse response = null;
				String content = null;
				netWork.setBookEntity(listBook.get(position));
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
		TextView numberToBuy;
		ImageView deleteButton;
		// TextView increase;
		// TextView decrease;
		Spinner spinner;
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