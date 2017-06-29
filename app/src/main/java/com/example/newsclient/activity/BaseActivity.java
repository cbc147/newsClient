package com.example.newsclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        initView();
        initListener();
        initData();
        }

    public abstract  int getLayoutRes();

    public abstract void initData();


    public abstract void initListener();

    public abstract void initView();

    private Toast mToast;
    public void showToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(this , "" , Toast.LENGTH_LONG);
        }
        mToast.setText(msg);
        mToast.show();
    }

}
