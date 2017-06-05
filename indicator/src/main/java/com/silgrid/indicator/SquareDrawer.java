package com.silgrid.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class SquareDrawer extends Drawer {

	public SquareDrawer(int pagesAmount, int indicatorColor, int indicatorSize, Callback callback) {
		super(pagesAmount, indicatorColor, indicatorSize, callback);
	}

	@Override
	void draw(Canvas canvas) {

		for (int i = 0; i < mPagesAmount; ++i) {
			Paint p = i == mCallback.getActivePage() ? mActivePaint : mInactivePaint;
			int left = mCallback.getLeftSide(i);
			int top = canvas.getHeight() / 2 - mIndicatorSize / 2;
			canvas.drawRect(left, top, left + mIndicatorSize, top + mIndicatorSize, p);
		}

		RectF rect = mCallback.getAnimatedRect();
		if (rect.left != 0 || rect.right != 0) {
			canvas.drawRect(rect, mActivePaint);
		}
	}
}
