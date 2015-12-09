package com.bkpirates.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.entity.OrderEntity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetWork {
	private final String TAG = "NetWork";
	String phone;
	String pass;
	String name;
	String address;
	int numberBookToBuy;
	int payment;

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getNumberBookToBuy() {
		return numberBookToBuy;
	}

	public void setNumberBookToBuy(int numberBookToBuy) {
		this.numberBookToBuy = numberBookToBuy;
	}

	BookEntity bookEntity = new BookEntity();

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean checkInternetConnect(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;
	}

	public HttpResponse makeRquestCreateAccount(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		nameValuePairList.add(new BasicNameValuePair("pass", pass));
		nameValuePairList.add(new BasicNameValuePair("name", name));
		nameValuePairList.add(new BasicNameValuePair("add", address));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	public HttpResponse makeRquestLogin(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		nameValuePairList.add(new BasicNameValuePair("pass", pass));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	public HttpResponse makeRquestGetUserBooksAndGetOrders(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	public HttpResponse makeRquestAddToCart(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		nameValuePairList.add(new BasicNameValuePair("bid", bookEntity.getBid()));
		nameValuePairList.add(new BasicNameValuePair("total", numberBookToBuy + ""));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	// Add_Favorite_List and Delete_Book, and Delete_favorite in Cart are same
	// request so you can use this function for both
	public HttpResponse makeRquestAddFavoriteList(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		nameValuePairList.add(new BasicNameValuePair("bid", bookEntity.getBid()));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	public HttpResponse makeRquestPayment(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("uid", phone));
		nameValuePairList.add(new BasicNameValuePair("payment", payment + ""));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	static InputStream is = null;

	public String processHTTPResponce(HttpResponse response) throws ParseException, IOException {
		String content = "";
		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		if (statusCode < 400) {
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
					content = reader.readLine();
					is.close();
				} catch (Exception e) {
					//
				}
			}
		}
		// Log.d("content", content);
		return content;
	}

	public int checkAccountForCreateAccount(String result) {
		int success = 0;
		try {
			JSONObject json = new JSONObject(result);
			if (json.has("success")) {
				success = json.getInt("success");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return success;
	}

	public int checkForAddCartAndFavoriteList(String result) {
		int success = 0;
		try {
			JSONObject json = new JSONObject(result);
			if (json.has("success")) {
				success = json.getInt("success");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return success;
	}

	public AccountEntity checkAccountForLogin(String result) {
		int success = 0;
		AccountEntity accEntity = new AccountEntity();
		try {
			JSONObject json = new JSONObject(result);
			if (json.has("success")) {
				success = json.getInt("success");
			}
			Log.d(TAG, "success Login :" + success);
			if (success != 0) {
				if (json.has("name")) {
					accEntity.setName(json.getString("name"));
				}
				if (json.has("address")) {
					accEntity.setAddress(json.getString("address"));
				}
				if (json.has("money")) {
					accEntity.setMoney(Integer.parseInt(json.getString("money")));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		accEntity.setPassword(success + ""); // cho success vao password
		return accEntity;

	}

	public ArrayList<OrderEntity> getOrdersUsers(String result) {
		ArrayList<OrderEntity> array = new ArrayList<OrderEntity>();
		try {
			JSONObject jsonObj = new JSONObject(result);
			Log.d(TAG, TAG);
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				if (jsonObj.has("orders")) {

					String str = jsonObj.getString("orders");
					JSONArray jsArr = new JSONArray(str);
					JSONObject js;
					for (int i = 0; i < jsArr.length(); i++) {
						Log.d(i + "", jsArr.length() + "");
						js = new JSONObject(jsArr.getString(i));
						OrderEntity order = new OrderEntity();
						if (js.has("oid")) {
							order.setOid(js.getString("oid"));
						}
						if (js.has("payment")) {
							order.setTotalMoney(js.getString("payment"));
						}
						if (js.has("date")) {
							order.setDate(js.getString("date"));
						}
						if (js.has("confirm")) {
							order.setCheckOrder(Integer.parseInt(js.getString("confirm")));
						}
						array.add(order);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;

	}

	public ArrayList<BookEntity> checkResultForGetUserBooks(String result) {
		ArrayList<BookEntity> array = new ArrayList<BookEntity>();
		try {
			JSONObject jsonObj = new JSONObject(result);
			Log.d(TAG, TAG);
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				if (jsonObj.has("books")) {

					String str = jsonObj.getString("books");
					JSONArray jsArr = new JSONArray(str);
					JSONObject js;
					for (int i = 0; i < jsArr.length(); i++) {
						Log.d(i + "", jsArr.length() + "");
						js = new JSONObject(jsArr.getString(i));
						BookEntity book = new BookEntity();
						if (js.has("bid")) {
							book.setBid(js.getString("bid"));
						}
						if (js.has("name")) {
							book.setName(js.getString("name"));
						}
						if (js.has("author")) {
							book.setAuthor(js.getString("author"));
						}
						if (js.has("price")) {
							book.setPrice(Integer.parseInt(js.getString("price")));
						}
						if (js.has("link")) {
							book.setLinkImage(js.getString("link"));
						}
						if (js.has("quantity")) {
							book.setQuantity(Integer.parseInt(js.getString("quantity")));
						}
						if (js.has("total")) { // so luon sach mua cho vao cart
							book.setNumberBookToBuy(Integer.parseInt(js.getString("total")));
						}
						if (js.has("like")) {
							book.setLike(Integer.parseInt(js.getString("like")));
						}
						if (js.has("clike")) {
							book.setLikedPersonNumber(Integer.parseInt(js.getString("clike")));
						}
						array.add(book);
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;

	}

}