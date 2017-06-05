package com.silgrid.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

abstract class Drawer {

	interface Callback {
		int getActivePage();
		int getLeftSide(int index);
		int getRightSide(int index);
		RectF getAnimatedRect();
	}

	protected int mPagesAmount;
	protected int mIndicatorSize;

	protected Paint mActivePaint;
	protected Paint mInactivePaint;
	protected Callback mCallback;

	public Drawer(int pagesAmount, int indicatorColor, int indicatorSize, Callback callback) {
		mIndicatorSize = indicatorSize;
		mPagesAmount = pagesAmount;
		mCallback = callback;

		mActivePaint = new Paint();
		mActivePaint.setAntiAlias(true);
		mActivePaint.setColor(indicatorColor);

		mInactivePaint = new Paint();
		mInactivePaint.setAntiAlias(true);
		mInactivePaint.setColor(indicatorColor);
		mInactivePaint.setAlpha(100);
	}

	public void setPagesAmount(int pagesAmount) {
		mPagesAmount = pagesAmount;
	}

	abstract void draw(Canvas canvas);
}
