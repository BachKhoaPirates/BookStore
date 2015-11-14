package com.bkpirates.webservice;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bkpirates.entity.BookEntity;

import android.os.AsyncTask;
import android.util.Log;

public class BookLoader extends AsyncTask<String, JSONObject, ArrayList<?>> {

	ArrayList<BookEntity> array;
	public BookLoaderListener listener;

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected ArrayList<?> doInBackground(String... params) {
		// Láº¥y URL truyá»?n vÃ o
		String url = params[0];
		JSONObject jsonObj;
		try {
			// Ä‘á»?c vÃ  chuyá»ƒn vá»? JSONObject
			jsonObj = JsonReader.readJsonFromUrl(url);
			Log.d("JSONNNNNNNNNN", ""+jsonObj);
			// xu li Json
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				if (jsonObj.has("books")) {
					array = new ArrayList<BookEntity>();

					String str = jsonObj.getString("books");
					JSONArray jsArr = new JSONArray(str);
					JSONObject js;

					for (int i = 0; i < jsArr.length(); i++) {
						js = new JSONObject(jsArr.getString(i));
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
						if (js.has("author")){
							book.setAuthor(js.getString("author"));
						}
						if (js.has("pulisher")){
							book.setPulisher(js.getString("pulisher"));
						}
						if (js.has("content")){
							book.setContent(js.getString("content"));
						}
						if (js.has("link")){
							book.setLinkImage(js.getString("link"));
						}
						array.add(book);
					}

				} else {
					array = null;
				}
			} else {
				array = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

	@Override
	protected void onProgressUpdate(JSONObject... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(ArrayList<?> result) {
		// TODO Auto-generated method stub
		// super.onPostExecute(result);
		if (result == null) {
			Log.d("BookLoader:", "Download not success!");
		} else
			Log.d("BookLoader:", "Download success!");
		listener.onDownloadSuccess();
	}

}
