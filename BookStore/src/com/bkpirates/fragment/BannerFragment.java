package com.bkpirates.fragment;

import com.bkpirates.bookstore.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BannerFragment extends Fragment {

	private int url;
//	private int position;

	public BannerFragment(int url) {
		this.url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.banner, container, false);

		ImageView image = (ImageView) view.findViewById(R.id.image);

		image.setImageResource(url);

		return view;
	}
}
