package com.silgrid.indicator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class TransitionPagerIndicator extends View implements ViewPager.OnAdapterChangeListener, ViewPager.OnPageChangeListener {

	private int mActivePage;
	private int mPagerAmount;
	private int mIndicatorSize;
	private int mIndicatorsPadding;
	private int mPreviousActivePage = -1;
	private int mPreviousScrollOffset = -1;
	private int mIndicatorColor = Color.WHITE;

	private boolean mAttached;

	private Paint mActivePaint;
	private Paint mInactivePaint;
	private ViewPager mViewPager;

	private RectF mAnimatedRect = new RectF();
	private ValueAnimator mAnimator = new ValueAnimator();

	public TransitionPagerIndicator(Context context) {
		super(context);

		setupParams(null);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		setupParams(attrs);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		setupParams(attrs);
	}

	public TransitionPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);

		setupParams(attrs);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();

		mAttached = true;

		if (mViewPager != null) {
			setupPagerCallbacks(mViewPager);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();

		mAttached = false;

		if (mViewPager != null) {
			removePagerCallbacks(mViewPager);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = mPagerAmount * (mIndicatorSize + mIndicatorsPadding) + getPaddingLeft() + getPaddingRight();
		int height = mIndicatorSize + getPaddingTop() + getPaddingBottom();

		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else if (widthMode == MeasureSpec.AT_MOST) {
			width = Math.min(width, widthSize);
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else if (heightMode == MeasureSpec.AT_MOST) {
			height = Math.min(height, heightSize);
		}

		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int cy = canvas.getHeight() / 2;

		for (int i = 0; i < mPagerAmount; ++i) {
			Paint p = i == mActivePage ? mActivePaint : mInactivePaint;
			canvas.drawCircle(i * (mIndicatorsPadding + mIndicatorSize) + mIndicatorsPadding / 2,
					cy, mIndicatorSize / 2, p);
		}

		if (mAnimatedRect.left != 0 || mAnimatedRect.right != 0) {
			canvas.drawOval(mAnimatedRect, mActivePaint);
		}
	}

	@Override
	public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
		mPagerAmount = newAdapter == null ? 0 : newAdapter.getCount();
		requestLayout();
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (mPreviousScrollOffset == -1) {
			mPreviousScrollOffset = positionOffsetPixels;
			return;
		}

			int fromIndex = positionOffsetPixels > mPreviousScrollOffset
					? position
					: position + 1;
			int toIndex = positionOffsetPixels > mPreviousScrollOffset
					? fromIndex + 1
					: fromIndex - 1;

		if (positionOffsetPixels == 0) {
			mPreviousScrollOffset = -1;
			endTransition(mPreviousActivePage, mActivePage);
			return;
		}

		mAnimator.cancel();
		mAnimatedRect.top = 0;
		mAnimatedRect.bottom = mAnimatedRect.top + mIndicatorSize;
		if (fromIndex < toIndex) {
			mAnimatedRect.left = getLeftSide(fromIndex);
			mAnimatedRect.right = getRightSide(fromIndex) + (getRightSide(toIndex) - getRightSide(fromIndex)) * positionOffset;
		} else {
			mAnimatedRect.left = getLeftSide(fromIndex) - (getLeftSide(fromIndex) - getLeftSide(toIndex)) * (1 - positionOffset);
			mAnimatedRect.right = getRightSide(fromIndex);
		}
		invalidate();
	}

	@Override
	public void onPageSelected(int position) {
		mPreviousActivePage = mActivePage;
		mActivePage = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		if (state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_DRAGGING) {
			mPreviousScrollOffset = -1;
		}
	}

	public void setViewPager(ViewPager viewPager) {
		mViewPager = viewPager;

		if (mAttached) {
			setupPagerCallbacks(viewPager);
		}
	}

	private void setupParams(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
					R.styleable.TransitionPagerIndicator, 0, 0);

			try {
				mIndicatorSize = a.getDimensionPixelSize(R.styleable.TransitionPagerIndicator_indicatorSize,
						(int) (8 * getResources().getDisplayMetrics().density));
				mIndicatorColor = a.getColor(R.styleable.TransitionPagerIndicator_indicatorColor, Color.WHITE);
			} finally {
				a.recycle();
			}
		} else {
			mIndicatorSize = (int) (8 * getResources().getDisplayMetrics().density);
			mIndicatorColor = Color.WHITE;
		}

		mIndicatorsPadding = (int) (15 * getResources().getDisplayMetrics().density);

		mActivePaint = new Paint();
		mActivePaint.setAntiAlias(true);
		mActivePaint.setColor(mIndicatorColor);

		mInactivePaint = new Paint();
		mInactivePaint.setAntiAlias(true);
		mInactivePaint.setColor(mIndicatorColor);
		mInactivePaint.setAlpha(100);
	}

	private void setupPagerCallbacks(ViewPager pager) {
		pager.addOnAdapterChangeListener(this);

		if (pager.getAdapter() != null) {
			onAdapterChanged(pager, null, pager.getAdapter());
		}

		pager.addOnPageChangeListener(this);
	}

	private void removePagerCallbacks(ViewPager pager) {
		pager.removeOnAdapterChangeListener(this);
		pager.removeOnPageChangeListener(this);
	}

	private void endTransition(final int from, final int to) {
		mAnimator.cancel();
		mAnimator.removeAllUpdateListeners();
		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (from < to) {
					mAnimatedRect.left = (float) animation.getAnimatedValue();
				} else {
					mAnimatedRect.right = (float) animation.getAnimatedValue();
				}
				invalidate();
			}
		});

		if (from < to) {
			mAnimator.setFloatValues(getLeftSide(from), getLeftSide(to));
		} else {
			mAnimator.setFloatValues(getRightSide(from), getRightSide(to));
		}

		mAnimator.start();
	}

	private int getLeftSide(int index) {
		return index * (mIndicatorsPadding + mIndicatorSize) + mIndicatorSize / 2;
	}

	private int getRightSide(int index) {
		return index * (mIndicatorsPadding + mIndicatorSize) + mIndicatorSize + mIndicatorSize / 2;
	}

}
