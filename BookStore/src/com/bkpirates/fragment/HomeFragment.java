package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.HorizontalListViewAdapter;
import com.bkpirates.adapter.ViewPagerBannerAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BannerEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.DataLoader;
import com.bkpirates.webservice.DataLoaderListener;
import com.bkpirates.widget.HorizontalListView;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class HomeFragment extends Fragment implements DataLoaderListener, OnRefreshListener {

	private HorizontalListView hotBookList, newBookList, favoriteBookList;
	private ArrayList<BookEntity> hotBookArray, newBookArray, favoriteBookArray;

	private ViewPager banner;

	private ArrayList<BannerEntity> bannerArray;

	private int positionBanner;
	private Handler handler;
	private Runnable runnable;
	private final int TIMER = 3000;
	private SwipeRefreshLayout swipeContainer;
	
	private int finishedDownloader =0;

	// private int downloadSuccess = 0;
	// private ProgressDialog loadDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		try {
			if (hotBookArray == null) {
				DataLoader bld1 = new DataLoader();
				// bld1.listener = this;
				hotBookArray = (ArrayList<BookEntity>) bld1.execute(DataLoader.HOT_BOOK_LINK).get();
			}
			if (newBookArray == null) {
				DataLoader bld2 = new DataLoader();
				// bld2.listener = this;
				newBookArray = (ArrayList<BookEntity>) bld2.execute(DataLoader.NEW_BOOK_LINK).get();
			}
			if (favoriteBookArray == null) {
				DataLoader bld3 = new DataLoader();
				// bld3.listener = this;
				favoriteBookArray = (ArrayList<BookEntity>) bld3.execute(DataLoader.TOP_FAVORITE_BOOK_LINK).get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		bannerArray = new ArrayList<BannerEntity>();
		for (int i = 1; i <= 5; i++) {
			bannerArray.add(new BannerEntity(
					getResources().getIdentifier("banner" + i, "drawable", getActivity().getPackageName())));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
		swipeContainer.setOnRefreshListener(HomeFragment.this);
		swipeContainer.setRefreshing(false);

		hotBookList = (HorizontalListView) view.findViewById(R.id.hotbooklist);
		newBookList = (HorizontalListView) view.findViewById(R.id.newbooklist);
		favoriteBookList = (HorizontalListView) view.findViewById(R.id.favoritebooklist);
		banner = (ViewPager) view.findViewById(R.id.banner);

		setAdapter(hotBookList, hotBookArray);
		setAdapter(newBookList, newBookArray);
		setAdapter(favoriteBookList, favoriteBookArray);

		ViewPagerBannerAdapter bannerAdapter = new ViewPagerBannerAdapter(getFragmentManager(), bannerArray);
		banner.setAdapter(bannerAdapter);
		controlBanner(view, banner);
		// auto slide viewpager
		handler = new Handler();
		runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				positionBanner = banner.getCurrentItem();
				if (positionBanner >= 4) {
					positionBanner = 0;
				} else {
					positionBanner = positionBanner + 1;
				}
				banner.setCurrentItem(positionBanner, true);

				handler.postDelayed(runnable, TIMER);
			}
		};

		hotBookList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startBookFragment(hotBookArray.get(position));
			}
		});

		newBookList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startBookFragment(newBookArray.get(position));
			}
		});

		favoriteBookList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startBookFragment(favoriteBookArray.get(position));
			}
		});

		return view;
	}

	private void setAdapter(HorizontalListView listView, ArrayList<BookEntity> array) {
		HorizontalListViewAdapter adapter = new HorizontalListViewAdapter(getContext(), array);
		listView.setAdapter(adapter);
	}

	private void controlBanner(final View view, ViewPager banner) {
		ImageView icon = (ImageView) view.findViewById(R.id.banner_icon0);
		icon.setImageResource(R.drawable.selected_banner);

		banner.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				ImageView icon = (ImageView) view.findViewById(
						getResources().getIdentifier("banner_icon" + position, "id", getContext().getPackageName()));
				icon.setImageResource(R.drawable.selected_banner);
				if (position == 0) {
					icon = (ImageView) view.findViewById(R.id.banner_icon4);
					icon.setImageResource(R.drawable.unselected_banner);
				}
				if (position > 0) {
					icon = (ImageView) view.findViewById(getResources().getIdentifier("banner_icon" + (position - 1),
							"id", getContext().getPackageName()));
					icon.setImageResource(R.drawable.unselected_banner);
				}
				if (position < 4) {
					icon = (ImageView) view.findViewById(getResources().getIdentifier("banner_icon" + (position + 1),
							"id", getContext().getPackageName()));
					icon.setImageResource(R.drawable.unselected_banner);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		if (handler != null) {
			handler.removeCallbacks(runnable);
		}
	}

	@Override
	public void onResume() {
		super.onResume(); // Always call the superclass method first
		handler.postDelayed(runnable, TIMER);
	}

	private void startBookFragment(BookEntity book) {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		// trans.replace(((ViewGroup) getView().getParent()).getId(), new
		// BookFragment(getContext(), book));
		ft.replace(R.id.container, new BookFragment(getContext(), book));
		ft.addToBackStack(null);
		ft.commit();
		fm.executePendingTransactions();
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		finishedDownloader =0;
		try {
			DataLoader bld1 = new DataLoader();
			bld1.listener = this;
			hotBookArray = (ArrayList<BookEntity>) bld1.execute(DataLoader.HOT_BOOK_LINK).get();
			DataLoader bld2 = new DataLoader();
			bld2.listener = this;
			newBookArray = (ArrayList<BookEntity>) bld2.execute(DataLoader.NEW_BOOK_LINK).get();
			DataLoader bld3 = new DataLoader();
			bld3.listener = this;
			favoriteBookArray = (ArrayList<BookEntity>) bld3.execute(DataLoader.TOP_FAVORITE_BOOK_LINK).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		finishedDownloader++;
		if (finishedDownloader ==3){
			setAdapter(hotBookList, hotBookArray);
			setAdapter(newBookList, newBookArray);
			setAdapter(favoriteBookList, favoriteBookArray);
			swipeContainer.setRefreshing(false);
		}
	}

}