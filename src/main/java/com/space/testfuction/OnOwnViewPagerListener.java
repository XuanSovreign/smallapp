package com.space.testfuction;

import android.view.View;

/**
 * Created by licht on 2019/10/18.
 */

public interface OnOwnViewPagerListener {
    void onInitCompleted(View view);
    void onPagerRelease(boolean isNext,int position,View view);
    void onPagerSelected(int position,boolean isBottom,View view);
}
