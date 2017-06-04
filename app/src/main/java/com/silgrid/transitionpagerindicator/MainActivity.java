package com.silgrid.transitionpagerindicator;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.silgrid.indicator.TransitionPagerIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Adapter adapter = new Adapter(getSupportFragmentManager());
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TransitionPagerIndicator indicator = (TransitionPagerIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}

	private static class Adapter extends FragmentStatePagerAdapter {

		private List<Fragment> mFragments = new ArrayList<>();

		Adapter(FragmentManager fm) {
			super(fm);

			mFragments.add(ColorfulFragment.newInstance(Color.RED));
			mFragments.add(ColorfulFragment.newInstance(Color.GREEN));
			mFragments.add(ColorfulFragment.newInstance(Color.BLUE));
			mFragments.add(ColorfulFragment.newInstance(Color.GRAY));
		}

		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}
	}
}
