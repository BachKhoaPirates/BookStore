package com.bkpirates.fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;

import org.apache.http.HttpResponse;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.NetWorkAdmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class InsertNewBooksFragment extends AppCompatActivity implements OnClickListener {

	public static final String UPLOAD_URL = "http://thachpn.name.vn/books/test.php";
	public static final String UPLOAD_KEY = "image";
	public static final String TAG = "MY MESSAGE";

	private int PICK_IMAGE_REQUEST = 1;
	EditText editName, editQuantity, editAuthor, editPushlier, editGenre, editContent, editPrice;
	ImageView image;
	Button btnChoose, btnUpload;
	BookEntity book = new BookEntity();

	private Bitmap bitmap;

	int check = 0;

	private Uri filePath;

	private NetWorkAdmin netWorkAdmin = new NetWorkAdmin();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_insert_new_book);
//		setWidgets();
//		book.setAuthor(editAuthor.getText() + "");
//		book.setContent(editContent.getText() + "");
//		book.setName(editName.getText() + "");
//		book.setPrice(Integer.parseInt(editPrice.getText() + ""));
//		book.setPulisher(editPushlier.getText() + "");
//		book.setQuantity(Integer.parseInt(editQuantity.getText() + ""));
//		book.setGenre(editGenre.getText() + "");
//		netWorkAdmin.setBookEntity(book);
//		btnChoose.setOnClickListener(this);
//		btnUpload.setOnClickListener(this);
		
	}

//	private void setWidgets() {
//		editName = (EditText) findViewById(R.id.editName);
//		editAuthor = (EditText) findViewById(R.id.editAuthor);
//		editContent = (EditText) findViewById(R.id.editContent);
//		editPushlier = (EditText) findViewById(R.id.editPushlier);
//		editQuantity = (EditText) findViewById(R.id.editQuantity);
//		editPrice = (EditText) findViewById(R.id.editPrice);
//		editGenre = (EditText) findViewById(R.id.editGenre);
//
//		btnChoose = (Button) findViewById(R.id.btnChoose);
//		btnUpload = (Button) findViewById(R.id.btnUpload);
//
//		image = (ImageView) findViewById(R.id.imageView);
//	}

	@Override
	public void onClick(View v) {
		if (v == btnChoose) {
			showFileChooser();
		}
		if (v == btnUpload) {
			uploadImage();
		}
	}

	private void showFileChooser() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
	}

	private void uploadImage() {
		String uploadImage = getStringImage(bitmap);
		netWorkAdmin.setEncodedImage(uploadImage);
		Log.d(TAG + " Son ", netWorkAdmin.getEncodedImage());
		GetUserBooksAsyncTask order = (GetUserBooksAsyncTask) new GetUserBooksAsyncTask()
					.execute(UPLOAD_URL);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

			filePath = data.getData();
			try {
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				image.setImageBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getStringImage(Bitmap bmp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] imageBytes = baos.toByteArray();
		String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
		return encodedImage;
	}

	private class GetUserBooksAsyncTask extends AsyncTask<String, Void, String> {
		ProgressDialog pb;

		@Override
		protected void onPreExecute() {
			// pb = new ProgressDialog(MainActivity.this);
			// pb.setMessage("Loading...");
			// pb.show();
			// super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			if (pb != null) {
				// pb.dismiss();
			}
			if (s != null) {
				// check = netWorkAdmin.check(s);
				// Log.d(TAG, check + "");
				// Toast.makeText(, check + "", Toast.LENGTH_LONG).show();
			}
			super.onPostExecute(s);
		}

		@Override
		protected String doInBackground(String... params) {
			String url = params[0];
			HttpResponse response = null;

			try {

				response = netWorkAdmin.makeRequestUpload(url);
			} catch (IOException e) {
				return null;
			}
			if (response != null) {

				String content = null;
				try {
					content = netWorkAdmin.processHTTPResponce(response);

					return content;
				} catch (IOException e) {
					return null;
				} catch (ParseException e) {
					return null;
				}
			}
			return null;

		}

	}

}
