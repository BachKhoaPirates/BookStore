package com.bkpirates.fragment;

import java.util.ArrayList;

import com.bkpirates.HorizontalListView.HorizontalListView;
import com.bkpirates.adapter.HorizontalListViewAdapter;
import com.bkpirates.adapter.ViewPagerBannerAdapter;
import com.bkpirates.bookstore.R;
import com.bkpirates.entity.BannerEntity;
import com.bkpirates.entity.BookEntity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends Fragment {

	private HorizontalListView hotBookList, newBookList, favoriteBookList;
	private ViewPager banner;

	private ArrayList<BookEntity> hotBookArray, newBookArray, favoriteBookArray;
	private ArrayList<BannerEntity> bannerArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);

		hotBookList = (HorizontalListView) view.findViewById(R.id.hotbooklist);
		newBookList = (HorizontalListView) view.findViewById(R.id.newbooklist);
		favoriteBookList = (HorizontalListView) view.findViewById(R.id.favoritebooklist);
		hotBookArray = new ArrayList<BookEntity>();
		newBookArray = new ArrayList<BookEntity>();
		favoriteBookArray = new ArrayList<BookEntity>();
		setData(hotBookArray);
		setData(newBookArray);
		setData(favoriteBookArray);
		setAdapter(hotBookList, hotBookArray);
		setAdapter(newBookList, newBookArray);
		setAdapter(favoriteBookList, favoriteBookArray);
		
		banner = (ViewPager) view.findViewById(R.id.banner);
		bannerArray = new ArrayList<BannerEntity>();
		for (int i = 0; i < 5; i++) {
			bannerArray.add(new BannerEntity(R.drawable.banner1));
		}
		ViewPagerBannerAdapter bannerAdapter = new ViewPagerBannerAdapter(getFragmentManager(), bannerArray);
		banner.setAdapter(bannerAdapter);
		controlBanner(view, banner);

		return view;
	}

	private void setAdapter(HorizontalListView listView, ArrayList<BookEntity> array) {
		HorizontalListViewAdapter adapter = new HorizontalListViewAdapter(getContext(), array);
		listView.setAdapter(adapter);
	}

	private void setData(ArrayList<BookEntity> array) {
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				BookEntity data = new BookEntity();
				data.setAuthor("Ho Nam");
				data.setPrice(15000);
				array.add(data);
			} else {
				BookEntity data = new BookEntity();
				data.setAuthor("Cuu Ba Dao");
				data.setPrice(20000);
				array.add(data);
			}
		}
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
				if (position>0){
					icon = (ImageView) view.findViewById(
							getResources().getIdentifier("banner_icon" + (position-1), "id", getContext().getPackageName()));
					icon.setImageResource(R.drawable.unselected_banner);
				}
				if (position<4){
					icon = (ImageView) view.findViewById(
							getResources().getIdentifier("banner_icon" + (position+1), "id", getContext().getPackageName()));
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
}