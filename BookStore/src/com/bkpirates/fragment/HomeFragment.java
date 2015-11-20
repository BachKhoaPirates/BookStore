package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.adapter.HorizontalListViewAdapter;
import com.bkpirates.adapter.ViewPagerBannerAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BannerEntity;
import com.bkpirates.entity.BookEntity;
import com.bkpirates.webservice.BookLoader;
import com.bkpirates.webservice.BookLoaderListener;
import com.bkpirates.widget.HorizontalListView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

public class HomeFragment extends Fragment implements BookLoaderListener {

	private HorizontalListView hotBookList, newBookList, favoriteBookList;
	private ArrayList<BookEntity> hotBookArray, newBookArray, favoriteBookArray;

	private ViewPager banner;

	private ArrayList<BannerEntity> bannerArray;
	private int downloadSuccess = 0;
	private ProgressDialog loadDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		hotBookList = (HorizontalListView) view.findViewById(R.id.hotbooklist);
		newBookList = (HorizontalListView) view.findViewById(R.id.newbooklist);
		favoriteBookList = (HorizontalListView) view.findViewById(R.id.favoritebooklist);

		if (downloadSuccess == 0) {
			// on the first time open app
			loadDialog = new ProgressDialog(getActivity());
			loadDialog.setMessage("Loading... Please wait!");
			loadDialog.setCancelable(false);
			loadDialog.show();

			BookLoader bld1 = new BookLoader();
			BookLoader bld2 = new BookLoader();
			BookLoader bld3 = new BookLoader();
			bld1.listener = bld2.listener = bld3.listener = this;
			try {
				// if (hotBookArray == null){
				hotBookArray = (ArrayList<BookEntity>) bld1.execute(BookLoader.HOT_BOOK_LINK).get();
				// }
				// if (newBookArray == null){
				newBookArray = (ArrayList<BookEntity>) bld2.execute(BookLoader.NEW_BOOK_LINK).get();
				// }
				// if (favoriteBookArray == null){
				favoriteBookArray = (ArrayList<BookEntity>) bld3.execute(BookLoader.TOP_FAVORITE_BOOK_LINK).get();
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// data haved been load before
			setAdapter(hotBookList, hotBookArray);
			setAdapter(newBookList, newBookArray);
			setAdapter(favoriteBookList, favoriteBookArray);
		}

		banner = (ViewPager) view.findViewById(R.id.banner);
		bannerArray = new ArrayList<BannerEntity>();
		for (int i = 0; i < 5; i++) {
			bannerArray.add(new BannerEntity(R.drawable.banner1));
		}
		ViewPagerBannerAdapter bannerAdapter = new ViewPagerBannerAdapter(getFragmentManager(), bannerArray);
		banner.setAdapter(bannerAdapter);
		controlBanner(view, banner);

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
	public void onDownloadSuccess() {
		// TODO Auto-generated method stub
		downloadSuccess++;
		if (downloadSuccess == 3) {
			setAdapter(hotBookList, hotBookArray);
			setAdapter(newBookList, newBookArray);
			setAdapter(favoriteBookList, favoriteBookArray);
			loadDialog.dismiss();
		}
	}

	private void startBookFragment(BookEntity book) {
		FragmentTransaction trans = getActivity().getSupportFragmentManager().beginTransaction();
		trans.replace(((ViewGroup) getView().getParent()).getId(), new BookFragment(getContext(), book));
		trans.addToBackStack(null);
		trans.commit();
	}

}