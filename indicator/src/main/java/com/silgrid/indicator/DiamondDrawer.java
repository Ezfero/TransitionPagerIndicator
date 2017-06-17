package com.silgrid.indicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class DiamondDrawer extends Drawer {

	private Path mIndicatorPath = new Path();
	private Path mAnimationPath = new Path();

	public DiamondDrawer(int pagesAmount, int indicatorColor, int indicatorSize, Callback callback) {
		super(pagesAmount, indicatorColor, indicatorSize, callback);
	}

	@Override
	void draw(Canvas canvas) {
		for (int i = 0; i < mPagesAmount; ++i) {
			Paint p = i == mCallback.getActivePage() ? mActivePaint : mInactivePaint;
			int left = mCallback.getLeftSide(i);
			int top = canvas.getHeight() / 2 - mIndicatorSize / 2;
			mIndicatorPath.reset();
			mIndicatorPath.moveTo(left, top + mIndicatorSize / 2);
			mIndicatorPath.lineTo(left + mIndicatorSize / 2, top);
			mIndicatorPath.lineTo(left + mIndicatorSize, top + mIndicatorSize / 2);
			mIndicatorPath.lineTo(left + mIndicatorSize / 2, top + mIndicatorSize);
			canvas.drawPath(mIndicatorPath, p);
		}

		RectF rect = mCallback.getAnimatedRect();
		if (rect.left != 0 || rect.right != 0) {
			mAnimationPath.reset();
			mAnimationPath.moveTo(rect.left, rect.top + mIndicatorSize / 2);
			mAnimationPath.lineTo(rect.left + mIndicatorSize / 2, rect.top);
			mAnimationPath.lineTo(rect.right - mIndicatorSize / 2, rect.top);
			mAnimationPath.lineTo(rect.right, rect.top + mIndicatorSize / 2);
			mAnimationPath.lineTo(rect.right - mIndicatorSize / 2, rect.bottom);
			mAnimationPath.lineTo(rect.left + mIndicatorSize / 2, rect.bottom);
			mAnimationPath.lineTo(rect.left, rect.top + mIndicatorSize / 2);
			canvas.drawPath(mAnimationPath, mActivePaint);
		}
	}
}
