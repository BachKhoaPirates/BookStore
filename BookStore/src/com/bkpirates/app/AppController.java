package com.bkpirates.app;

import com.bkpirates.bookstore.R;
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

public class AppController extends Application {

	private static AppController instance;
//	private ProgressDialog progressDialog;

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
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.blank_book)
				.showImageForEmptyUri(R.drawable.blank_book)
				.showImageOnFail(R.drawable.blank_book).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
				

		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(
				context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app
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
	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	// -------------------------------Edit
	// --------------------------------------//
//	public synchronized Tracker getTracker() {
//		if (!AppControl.getInstance().getSharedMem().containsKey("ga_tracker")) {
//			String trackingId = getSystemProperties().getProperty(
//					"ga_tracking_id");
//
//			GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//			analytics.setDryRun(false);
//			// analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
//			analytics.enableAutoActivityReports(this);
//			analytics.setLocalDispatchPeriod(30);
//
//			Tracker tracker = analytics.newTracker(trackingId);
//			tracker.enableAdvertisingIdCollection(true);
//			tracker.enableExceptionReporting(true);
//			tracker.enableAutoActivityTracking(true);
//
//			AppControl.getInstance().getSharedMem().put("ga_tracker", tracker);
//		}
//
//		return (Tracker) AppControl.getInstance().getSharedMem()
//				.get("ga_tracker");
//	}
//
//	public void trackScreen(String screenName) {
//		try {
//			Tracker tracker = getTracker();
//			// Set screen name.
//			tracker.setScreenName(screenName);
//
//			// Send a screen view.
//			tracker.send(new HitBuilders.ScreenViewBuilder().build());
//		} catch (Exception e) {
//			Log.d("DEBUG trackScreen Exception:", e.getMessage());
//		}
//	}
//
//	public void trackError(Exception e) {
//		Tracker tracker = getTracker();
//		tracker.send(new HitBuilders.ExceptionBuilder()
//				.setDescription(Log.getStackTraceString(e.fillInStackTrace()))
//				.setFatal(true).build());
//	}
//
//	public Map<String, Object> getSharedMem() {
//		if (sharedPreference == null) {
//			sharedPreference = new HashMap<String, Object>();
//		}
//		return sharedPreference;
//	}
//
//	public Properties getSystemProperties() {
//		if (properties == null) {
//			properties = AppProperties
//					.load(getApplicationContext().getAssets());
//		}
//		return properties;
//	}

//	public void hideProgressDialog() {
//		if (progressDialog != null) {
//			progressDialog.hide();
//			progressDialog.dismiss();
//			progressDialog = null;
//		}
//	}

}