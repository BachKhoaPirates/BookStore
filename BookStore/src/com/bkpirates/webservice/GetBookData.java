package com.bkpirates.webservice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bkpirates.entity.BookEntity;
import com.bkpirates.fragment.LoginFragment;

import android.os.AsyncTask;
import android.util.Log;

public class GetBookData extends AsyncTask<BookEntity, Void, Void> {

	private final String URL = "http://thachpn.name.vn/books/get_book.php?bid=";
	public GetBookDataListener listener;
	private final String TAG  = "GetBookData";
	public interface GetBookDataListener {
		public void onDownloadSuccess();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(BookEntity... params) {
		// TODO Auto-generated method stub

		BookEntity book = params[0];
		
		String getURL = URL + book.getBid();
		if (LoginFragment.checkLogin == 1){
			getURL = getURL + "&uid=" + LoginFragment.accEntity.getPhone();
		}

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(getURL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null) {
				JSONObject jsResponse = JsonReader.readJsonFromInputStream(resEntityGet.getContent());
				Log.d("JSONNNNNNNNNNN", ""+jsResponse);
				if (jsResponse.has("success") && jsResponse.getString("success").equals("1")) {
					JSONArray jsArray = new JSONArray(jsResponse.getString("books"));
					JSONObject js = jsArray.getJSONObject(0);
					if (js.has("quantity")) {
						book.setQuantity(Integer.parseInt(js.getString("quantity")));
					}
					if (js.has("publisher")) {
						book.setPulisher(js.getString("publisher"));
					}
					if (js.has("content")) {
						book.setContent(js.getString("content"));
					}
					if (js.has("like")){
						book.setLike(Integer.parseInt(js.getString("like")));
						Log.d(TAG, book.getLike() + "");
					}
					if (js.has("clike")){
						book.setLikedPersonNumber(Integer.parseInt(js.getString("clike")));
					}
				}
				Log.d("GetBookData:", "Download success!");

			} else {
				Log.d("GetBookData:", "Download fail!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		if (listener != null){
			listener.onDownloadSuccess();
		}
	}
}
