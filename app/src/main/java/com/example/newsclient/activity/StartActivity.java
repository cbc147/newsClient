package com.example.newsclient.activity;

import android.content.Intent;
import android.os.SystemClock;

import com.example.newsclient.R;



public class StartActivity extends BaseActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    public void initData() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(1500);
                enterGuideActivity();
            }
        }.start();
    }

    private void enterGuideActivity() {
        Intent intent = new Intent(this , GuideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {

    }

}
