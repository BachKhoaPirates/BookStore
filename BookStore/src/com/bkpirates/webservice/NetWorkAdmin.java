package com.bkpirates.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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

import android.util.Log;

public class NetWorkAdmin {
	private String TAG = "NetWorkAdmin";
	private AccountEntity accEntity;
	private BookEntity bookEntity;
	private String encodedImage;
	private String newPushlier;
	private String newGenre;

	public String getNewPushlier() {
		return newPushlier;
	}

	public void setNewPushlier(String newPushlier) {
		this.newPushlier = newPushlier;
	}

	public String getNewGenre() {
		return newGenre;
	}

	public void setNewGenre(String newGenre) {
		this.newGenre = newGenre;
	}

	public AccountEntity getAccEntity() {
		return accEntity;
	}

	public void setAccEntity(AccountEntity accEntity) {
		this.accEntity = accEntity;
	}

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	public HttpResponse makeRequestUpload(String url) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
		nameValuePairList.add(new BasicNameValuePair("image", encodedImage));
		nameValuePairList.add(new BasicNameValuePair("name", bookEntity.getName()));
		nameValuePairList.add(new BasicNameValuePair("author", bookEntity.getAuthor()));
		nameValuePairList.add(new BasicNameValuePair("price", bookEntity.getPrice() + ""));
		nameValuePairList.add(new BasicNameValuePair("price_add", bookEntity.getPrice_add() + ""));
		nameValuePairList.add(new BasicNameValuePair("content", bookEntity.getContent()));
		nameValuePairList.add(new BasicNameValuePair("quantity", bookEntity.getQuantity() + ""));
		nameValuePairList.add(new BasicNameValuePair("nid", bookEntity.getPulisher()));
		nameValuePairList.add(new BasicNameValuePair("pid", bookEntity.getGenre()));
//		
//		if (newPushlier == null) {
//			Log.d(TAG, bookEntity.getPulisher());
//			nameValuePairList.add(new BasicNameValuePair("nid", bookEntity.getPulisher()));
//
//		} else {
//			// insert new pub and distribute
//			Log.d(TAG + "checked", newPushlier);
//			nameValuePairList.add(new BasicNameValuePair("publisher", newPushlier));
//
//		}
//		if (newGenre == null) {
//			Log.d(TAG, bookEntity.getGenre());
//			nameValuePairList.add(new BasicNameValuePair("pid", bookEntity.getGenre()));
//		} else
//		{
//			Log.d(TAG + "checked", newGenre);
//			nameValuePairList.add(new BasicNameValuePair("distribute", newGenre));
//			
//		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
		httpPost.setEntity(entity);
		return httpClient.execute(httpPost);
	}

	public int check(String result) {
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

	public ArrayList<AccountEntity> GetTopUsers(String result) {

		ArrayList<AccountEntity> array = new ArrayList<AccountEntity>();
		JSONObject jsonObj;
		Log.d("son1", "son1");
		Log.d(result, result);
		try {
			jsonObj = JsonReader.readJsonFromInputStream(new URL(result).openStream());

			Log.d("JSONNNNNNNNNNN", "" + jsonObj);

			Log.d("son", "son");
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				Log.d(TAG, TAG);
				if (jsonObj.has("users")) {
					Log.d(TAG, TAG + TAG);

					String str = jsonObj.getString("users");
					JSONArray jsArr = new JSONArray(str);
					JSONObject js;
					Log.d(jsArr.length() + "", jsArr.length() + "");
					for (int i = 0; i < jsArr.length(); i++) {
						js = new JSONObject(jsArr.getString(i));
						Log.d(jsArr.getString(i) + "", js + "");
						AccountEntity account = new AccountEntity();
						if (js.has("uid")) {
							account.setPhone(js.getString("uid"));
						}
						if (js.has("name")) {
							account.setName(js.getString("name"));
						}
						if (js.has("add")) {
							account.setAddress(js.getString("add"));
						}
						if (js.has("money")) {
							account.setMoney(Integer.parseInt(js.getString("money")));
						}

						array.add(account);
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d(TAG, "dm");
		}
		return array;

	}

}
