package com.space.testfuction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by licht on 2019/3/26.
 */

public class SplashActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private TextView mTv;
    private int count = 4;
    private ImageView mIv;
    private Runnable mRunnable;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        hideNavigationBar();
        mTv = findViewById(R.id.tv_splash);
        mIv = findViewById(R.id.iv_splash);
    }

    @Override
    protected void initData() {

        //todo 要修改的activity
        mRunnable = new Runnable() {
            @Override
            public void run() {
                count--;
                if (count <= 0) {
                    mHandler.removeCallbacks(this);
                    //todo 要修改的activity
                    startActivity(new Intent(SplashActivity.this, DragVideoActivity.class));
                    finish();
                    return;
                }
                mTv.setText(count + "秒");
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 1000);
    }

    protected void initListener() {
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(mRunnable);
                //todo 要修改的activity
                startActivity(new Intent(SplashActivity.this, DragVideoActivity.class));
                finish();
            }
        });

        mIv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //todo 下载文件的url
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https:www.baidu.com"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }
        );

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
