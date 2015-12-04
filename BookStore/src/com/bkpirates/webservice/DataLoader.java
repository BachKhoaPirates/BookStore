package com.bkpirates.webservice;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.DistributeBookEntity;
import com.bkpirates.entity.OrderAdminEntity;

import android.os.AsyncTask;
import android.util.Log;

public class DataLoader extends AsyncTask<String, JSONObject, ArrayList<?>> {

	private ArrayList<BookEntity> bookArray;
	private ArrayList<DistributeBookEntity> distributeArray;
	private ArrayList<AccountEntity> accountArray;
	private ArrayList<OrderAdminEntity> orderArray;
	
	public DataLoaderListener listener = null;

	public static final String NEW_BOOK_LINK = "http://thachpn.name.vn/books/get_new_books.php";
	public static final String TOP_FAVORITE_BOOK_LINK = "http://thachpn.name.vn/books/get_top_favorite_boooks.php";
	public static final String HOT_BOOK_LINK = "http://thachpn.name.vn/books/get_hot_books.php";

	public static final String DISTRIBUTE_LINK = "http://thachpn.name.vn/books/get_distribute.php";

	public static final String SEARCH_LINK = "http://thachpn.name.vn/books/search.php?key=";
	public static final String LIST_BOOK_LINK = "http://thachpn.name.vn/books/get_list_books.php";

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected ArrayList<?> doInBackground(String... params) {
		// Lấy URL truy�?n vào
		String url = params[0];
		JSONObject jsonObj;
		try {
			jsonObj = JsonReader.readJsonFromInputStream(new URL(url).openStream());
			// xu li Json
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				if (jsonObj.has("books")) {
					bookArray = new ArrayList<BookEntity>();

					JSONArray jsArr = new JSONArray(jsonObj.getString("books"));
					JSONObject js;
					Log.d("JSON", ""+jsArr);
					for (int i = 0; i < jsArr.length(); i++) {
						js = jsArr.getJSONObject(i);
						BookEntity book = new BookEntity();
						if (js.has("bid")) {
							book.setBid(js.getString("bid"));
						}
						if (js.has("name")) {
							book.setName(js.getString("name"));
						}
						if (js.has("price")) {
							book.setPrice(Integer.parseInt(js.getString("price")));
						}
						if (js.has("quantity")) {
							book.setQuantity(Integer.parseInt(js.getString("quantity")));
						}
						if (js.has("author")) {
							book.setAuthor(js.getString("author"));
						}
						if (js.has("publisher")) {
							book.setPulisher(js.getString("pulisher"));
						}
						if (js.has("content")) {
							book.setContent(js.getString("content"));
						}
						if (js.has("link")) {
							book.setLinkImage(js.getString("link"));
						}
						bookArray.add(book);
					}

				} else if (jsonObj.has("users")) {
					accountArray = new ArrayList<AccountEntity>();

					JSONArray jsArr = new JSONArray(jsonObj.getString("users"));
					JSONObject js;
					Log.d("JSON", ""+jsArr);
					for (int i = 0; i < jsArr.length(); i++) {
						js = jsArr.getJSONObject(i);
						AccountEntity account = new AccountEntity();
						if (js.has("uid")) {
							account.setPhone(js.getString("uid"));
						}
						if (js.has("name")) {
							account.setName(js.getString("name"));
						}
						if (js.has("money")) {
							account.setMoney(Integer.parseInt(js.getString("money")));
						}

						if (js.has("add")) {
							account.setAddress(js.getString("add"));
						}
						accountArray.add(account);
					}

				} else if (jsonObj.has("orders")) {
					orderArray = new ArrayList<OrderAdminEntity>();
					JSONArray jsArr = new JSONArray(jsonObj.getString("orders"));
					JSONObject js;
					Log.d("JSON", ""+jsArr);
					for (int i = 0; i < jsArr.length(); i++) {
						js = jsArr.getJSONObject(i);
						OrderAdminEntity order = new OrderAdminEntity();
						if (js.has("oid")) {
							order.setOid(js.getString("oid"));
						}
						if (js.has("date")) {
							order.setDate(js.getString("date"));
						}
						if (js.has("payment")){
							order.setTotalMoney(js.getString("payment"));
						}
						if (js.has("name")){
							order.setOrderPerson(js.getString("name"));
						}
						if (js.has("address")){
							order.setOrderPersonAddress(js.getString("address"));
						}
						orderArray.add(order);
					}
				} else if (jsonObj.has("distributes")) {
					distributeArray = new ArrayList<DistributeBookEntity>();

					JSONArray jsArr = new JSONArray(jsonObj.getString("distributes"));
					JSONObject js;
					Log.d("JSON", ""+jsArr);
					for (int i = 0; i < jsArr.length(); i++) {
						js = jsArr.getJSONObject(i);
						DistributeBookEntity distributeBook = new DistributeBookEntity();
						if (js.has("pid")) {
							distributeBook.setPid(js.getString("pid"));
						}
						if (js.has("distribute")) {
							distributeBook.setName(js.getString("distribute"));
						}
						distributeArray.add(distributeBook);
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (accountArray != null) {
			return accountArray;
		} else if (bookArray != null) {
			return bookArray;
		} else if (distributeArray != null) {
			return distributeArray;
		} else if (orderArray != null){
			return orderArray;
		} else {
			return null;
		}

	}

	@Override
	protected void onProgressUpdate(JSONObject... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(ArrayList<?> result) {
		// TODO Auto-generated method stub
		if (result == null) {
			Log.d("BookLoader:", "Download not success!");
		} else
			Log.d("BookLoader:", "Download success!");

		if (listener != null) {
			listener.onDownloadSuccess();
		}
		// super.onPostExecute(result);
	}

}
