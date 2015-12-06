package com.bkpirates.webservice;

import java.net.URL;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class SendRequest extends AsyncTask<String, JSONObject, Integer> {

	public DataLoaderListener listener = null;

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Integer doInBackground(String... params) {
		// Lấy URL truy�?n vào
		String url = params[0];
		JSONObject jsonObj;
		int result = 0;
		try {
			jsonObj = JsonReader.readJsonFromInputStream(new URL(url).openStream());
			// xu li Json
			if (jsonObj.has("success") && jsonObj.getString("success").equals("1")) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	@Override
	protected void onProgressUpdate(JSONObject... values) {
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		// super.onPostExecute(result);
		if (result == 0) {
			Log.d("BookLoader:", "Download not success!");
		} else {
			Log.d("BookLoader:", "Download success!");
		}

		if (listener != null) {
			listener.onDownloadSuccess();
		}
	}

}
