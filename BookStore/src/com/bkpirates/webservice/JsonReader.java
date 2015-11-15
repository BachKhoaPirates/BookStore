package com.bkpirates.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {
	public static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Ham tra ve JSONObject
	 * 
	 * @param url
	 *            - Truyen link URL muon nhan ve JSON
	 * @return - tra ve JSONOBject
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject readJsonFromInputStream(InputStream is) throws IOException, JSONException {
//		InputStream is = new URL(url).openStream();
		
		try {
			// đ�?c nội dung với Unicode:
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
