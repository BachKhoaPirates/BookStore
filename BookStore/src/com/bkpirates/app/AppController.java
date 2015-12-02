package com.bkpirates.app;

import java.util.ArrayList;

import com.bkpirates.bookstore.R;
import com.bkpirates.entity.AccountEntity;
import com.bkpirates.entity.DistributeBookEntity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class AppController extends Application {

	private static AppController instance;

	private ArrayList<DistributeBookEntity> distributeArray = null;

	@Override
	public void onCreate() {
		super.onCreate();

		initImageLoader(getApplicationContext());
		instance = this;

	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.blank_book)
				.showImageForEmptyUri(R.drawable.blank_book).showImageOnFail(R.drawable.blank_book).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();

		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		// config.writeDebugLogs(); // Remove for release app
		config.defaultDisplayImageOptions(defaultOptions);

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}

	public static AppController getInstance() {
		if (instance == null) {
			instance = new AppController();

		}
		return instance;
	}
	public static void initiatePopupWindow(AccountEntity accEntity,Context context) {
		Button btnOkPopup;
		Button btnNotOkPopup;
		final PopupWindow pwindo;
		int width;
		int height;
		TextView phoneText;
		TextView passText;
		TextView addressText;
		TextView nameText;
		TextView moneyText;

		width = (int) convertDpToPixel(320, context);
		height = (int) convertDpToPixel(175, context);

		try {

			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.popup, null, false);
			pwindo = new PopupWindow(layout, width, height, true);
			pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
			btnOkPopup = (Button) layout.findViewById(R.id.btn_ok_popup);
			nameText = (TextView) layout.findViewById(R.id.txtView1);
			addressText = (TextView) layout.findViewById(R.id.txtView2);
			phoneText = (TextView) layout.findViewById(R.id.txtView3);
			passText = (TextView) layout.findViewById(R.id.txtView4);
			moneyText = (TextView) layout.findViewById(R.id.txtView5);

			nameText.setText(   "Tên         : " + accEntity.getName());
			addressText.setText("Địa chỉ     : " + accEntity.getAddress());
			phoneText.setText(  "SĐT         : " + accEntity.getPhone());
			passText.setText(   "Mật khẩu    : " + accEntity.getPassword());
			moneyText.setText(  "Tiền đã mua : " + accEntity.getMoney());
			btnOkPopup.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					pwindo.dismiss();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}
	
	public ArrayList<DistributeBookEntity> getDistributeArray(){
		return distributeArray;
	}
	
	public void setDistributeArray(ArrayList<DistributeBookEntity> arr){
		this.distributeArray = arr;
	}

	// -------------------------------Edit
	// --------------------------------------//
	// public synchronized Tracker getTracker() {
	// if (!AppControl.getInstance().getSharedMem().containsKey("ga_tracker")) {
	// String trackingId = getSystemProperties().getProperty(
	// "ga_tracking_id");
	//
	// GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
	// analytics.setDryRun(false);
	// // analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
	// analytics.enableAutoActivityReports(this);
	// analytics.setLocalDispatchPeriod(30);
	//
	// Tracker tracker = analytics.newTracker(trackingId);
	// tracker.enableAdvertisingIdCollection(true);
	// tracker.enableExceptionReporting(true);
	// tracker.enableAutoActivityTracking(true);
	//
	// AppControl.getInstance().getSharedMem().put("ga_tracker", tracker);
	// }
	//
	// return (Tracker) AppControl.getInstance().getSharedMem()
	// .get("ga_tracker");
	// }
	//
	// public void trackScreen(String screenName) {
	// try {
	// Tracker tracker = getTracker();
	// // Set screen name.
	// tracker.setScreenName(screenName);
	//
	// // Send a screen view.
	// tracker.send(new HitBuilders.ScreenViewBuilder().build());
	// } catch (Exception e) {
	// Log.d("DEBUG trackScreen Exception:", e.getMessage());
	// }
	// }
	//
	// public void trackError(Exception e) {
	// Tracker tracker = getTracker();
	// tracker.send(new HitBuilders.ExceptionBuilder()
	// .setDescription(Log.getStackTraceString(e.fillInStackTrace()))
	// .setFatal(true).build());
	// }
	//
	// public Map<String, Object> getSharedMem() {
	// if (sharedPreference == null) {
	// sharedPreference = new HashMap<String, Object>();
	// }
	// return sharedPreference;
	// }
	//
	// public Properties getSystemProperties() {
	// if (properties == null) {
	// properties = AppProperties
	// .load(getApplicationContext().getAssets());
	// }
	// return properties;
	// }

	// public void hideProgressDialog() {
	// if (progressDialog != null) {
	// progressDialog.hide();
	// progressDialog.dismiss();
	// progressDialog = null;
	// }
	// }

}