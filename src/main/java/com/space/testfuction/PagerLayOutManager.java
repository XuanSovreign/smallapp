package com.space.testfuction;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by licht on 2019/10/18.
 */

public class PagerLayOutManager extends LinearLayoutManager {

    private PagerSnapHelper mPagerSnapHelper;
    private RecyclerView mRecyclerView;
    private OnOwnViewPagerListener mOnOwnViewPagerListener;
    private int mDrift = 0;

    public PagerLayOutManager(Context context, int orientation) {
        this(context, orientation, false);
    }

    public PagerLayOutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    private void init() {
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mPagerSnapHelper.attachToRecyclerView(view);
        mRecyclerView = view;
        mRecyclerView.addOnChildAttachStateChangeListener(mchildListener);
    }

    public void setOnOwnPagerListener(OnOwnViewPagerListener listener) {
        mOnOwnViewPagerListener = listener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View snapView = mPagerSnapHelper.findSnapView(this);
                if (snapView != null) {
                    int position = getPosition(snapView);
                    if (mOnOwnViewPagerListener != null && getChildCount() == 1) {
                        mOnOwnViewPagerListener.onPagerSelected(position, position == getItemCount(), snapView);
                    }
                }
                Log.e("onScrollStateChanged", "onScrollStateChanged: IDLE");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                Log.e("onScrollStateChanged", "onScrollStateChanged: DRAGGING");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                Log.e("onScrollStateChanged", "onScrollStateChanged: SETTLING");
                break;
        }
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    RecyclerView.OnChildAttachStateChangeListener mchildListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnOwnViewPagerListener != null && getChildCount() == 1) {
                mOnOwnViewPagerListener.onInitCompleted(view);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            if (mOnOwnViewPagerListener == null) {
                return;
            }
            if (mDrift >= 0) {
                mOnOwnViewPagerListener.onPagerRelease(true, getPosition(view), view);
            } else {
                mOnOwnViewPagerListener.onPagerRelease(false, getPosition(view), view);
            }

        }
    };

}
