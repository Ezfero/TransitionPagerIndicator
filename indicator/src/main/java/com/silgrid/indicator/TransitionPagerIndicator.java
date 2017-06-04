package com.silgrid.indicator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class TransitionPagerIndicator extends View {
	private ViewPager mViewPager;

	public TransitionPagerIndicator(Context context) {
		super(context);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void setViewPager(ViewPager viewPager) {
		mViewPager = viewPager;
	}
}
