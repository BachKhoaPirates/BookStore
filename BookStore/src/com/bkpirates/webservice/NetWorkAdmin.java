package com.bkpirates.webservice;

import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.BookEntity;

import android.util.Log;

public class NetWorkAdmin {
	private String TAG = "NetWorkAdmin";
	private AccountEntity accEntity;
	private BookEntity bookEntity;

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
