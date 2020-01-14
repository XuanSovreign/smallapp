package com.space.testfuction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.util.List;

/**
 * Created by licht on 2019/10/19.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mUrls;
    private OnOwnItemClickListener mListener;

    public VideoAdapter(Context context, List<String> urls) {
        mContext = context;
        mUrls = urls;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_video, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (mUrls == null) {
            return;
        }
        holder.mVideoView.setVideoPath(mUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return mUrls == null ? 0 : mUrls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public VideoView mVideoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mVideoView = itemView.findViewById(R.id.vw_source);
        }
    }

    public void setOnOwnItemClickListener(OnOwnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnOwnItemClickListener {
        void onItemClick(int position, View view, ViewGroup parent);
    }

}
