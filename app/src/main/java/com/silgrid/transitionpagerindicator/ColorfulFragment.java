package com.silgrid.transitionpagerindicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorfulFragment extends Fragment {

	private int mColor;

	public static Fragment newInstance(int color) {
		Bundle bundle = new Bundle();
		bundle.putInt("color", color);

		Fragment f = new ColorfulFragment();
		f.setArguments(bundle);
		return f;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mColor = getArguments().getInt("color");
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = new View(container.getContext());
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		view.setLayoutParams(params);
		view.setBackgroundColor(mColor);

		return view;
	}
}
