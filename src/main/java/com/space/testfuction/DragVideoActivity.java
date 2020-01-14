package com.space.testfuction;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by licht on 2019/10/18.
 */

public class DragVideoActivity extends BaseActivity {

    private RecyclerView mRvVideo;
    private PagerLayOutManager mLayOutManager;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_drag_video;
    }

    @Override
    protected void initView() {
        mRvVideo = findViewById(R.id.rv_video);
    }

    @Override
    protected void initData() {
        List<String> videoUrls = new ArrayList<>();
        VideoAdapter adapter = new VideoAdapter(this, videoUrls);
        mLayOutManager = new PagerLayOutManager(this, OrientationHelper.VERTICAL);
        mRvVideo.setLayoutManager(mLayOutManager);
        mRvVideo.setAdapter(adapter);

    }

    @Override
    protected void initListener() {
        mLayOutManager.setOnOwnPagerListener(new OnOwnViewPagerListener() {
            @Override
            public void onInitCompleted(View view) {
                playVideo(0, view);
            }

            @Override
            public void onPagerRelease(boolean isNex, int position, View view) {
                releaseVideo(position, view);
            }

            @Override
            public void onPagerSelected(int position, boolean isBottom, View view) {
                playVideo(position, view);
            }
        });
    }

    private void releaseVideo(int position, View view) {
        RecyclerView.ViewHolder holder = mRvVideo.getChildViewHolder(view);
        VideoAdapter.MyViewHolder viewHolder = (VideoAdapter.MyViewHolder) holder;
        viewHolder.mVideoView.stopPlayback();
    }

    private void playVideo(int i, View view) {
        RecyclerView.ViewHolder holder = mRvVideo.getChildViewHolder(view);
        VideoAdapter.MyViewHolder viewHolder = (VideoAdapter.MyViewHolder) holder;
        viewHolder.mVideoView.start();
    }
}
