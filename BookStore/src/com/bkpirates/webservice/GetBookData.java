package com.bkpirates.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.bkpirates.entity.BookEntity;

import android.util.Log;

public class GetBookData {

	private final String URL = "http://thachpn.name.vn/books/get_book.php?bid=";
	public GetBookDataListener listener;

	public interface GetBookDataListener{
		public void onDownloadSuccess();
	}
	
	public GetBookData() {
		
	}

	public void loadBook(BookEntity book){
		try {
			HttpClient client = new DefaultHttpClient();
			String getURL = URL + book.getBid();
			HttpGet get = new HttpGet(getURL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null) {
//				Log.d("resEntityGet", "NOT NULL");
//				JSONObject js = new JSONObject(resEntityGet.toString());
//				if (js.has("quantity")) {
//					book.setQuantity(Integer.parseInt(js.getString("quantity")));
//				}
//				if (js.has("pulisher")){
//					book.setPulisher(js.getString("pulisher"));
//				}
//				if (js.has("content")){
//					book.setContent(js.getString("content"));
//				}
//				listener.onDownloadSuccess();
			}else{
				Log.d("NULLLLLLLLLL", "NULLLLLLLLL");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listener.onDownloadSuccess();
	}
}
