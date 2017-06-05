package com.silgrid.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class CircleDrawer extends Drawer {

	public CircleDrawer(int pagesAmount, int indicatorColor, int indicatorSize, Callback callback) {
		super(pagesAmount, indicatorColor, indicatorSize, callback);
	}

	@Override
	void draw(Canvas canvas) {
		int cy = canvas.getHeight() / 2;

		for (int i = 0; i < mPagesAmount; ++i) {
			Paint p = i == mCallback.getActivePage() ? mActivePaint : mInactivePaint;
			canvas.drawCircle(mCallback.getLeftSide(i) + mIndicatorSize / 2, cy, mIndicatorSize / 2, p);
		}

		RectF rect = mCallback.getAnimatedRect();
		if (rect.left != 0 || rect.right != 0) {
			canvas.drawRoundRect(rect, mIndicatorSize / 2, mIndicatorSize / 2, mActivePaint);
		}
	}
}
