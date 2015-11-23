package com.bkpirates.adapter;

import java.util.ArrayList;

import com.bkpirates.entity.BannerEntity;
import com.bkpirates.fragment.BannerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerBannerAdapter extends FragmentStatePagerAdapter {
	private ArrayList<BannerEntity> arr = null;

	public ViewPagerBannerAdapter(FragmentManager fm, ArrayList<BannerEntity> arr) {
		super(fm);
		this.arr = arr;
	}

	@Override
	public Fragment getItem(int position) {
		return new BannerFragment(arr.get(position).getUrl());
	}

	@Override
	public int getCount() {
		// return NUM_PAGES;
		return arr.size();
	}
	
}
