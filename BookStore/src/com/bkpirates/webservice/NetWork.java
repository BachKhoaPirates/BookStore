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
import org.json.JSONException;
import org.json.JSONObject;

import com.bkpirates.entity.AccountEntity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetWork {
	String phone;
	String pass;
	String name;
	String address;

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
		Log.d("content", content);
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

	public AccountEntity checkAccountForLogin(String result) {
		int success = 0;
		AccountEntity accEntity = new AccountEntity();
		try {
			JSONObject json = new JSONObject(result);
			if (json.has("success")) {
				success = json.getInt("success");
			}
			if (success != 0) {
				if (json.has("name")) {
					accEntity.setName(json.getString("name"));
				}
				if (json.has("address")) {
					accEntity.setAddress(json.getString("address"));
				}
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		accEntity.setPassword(success + ""); // cho success vao password
		return accEntity;

	}

}
