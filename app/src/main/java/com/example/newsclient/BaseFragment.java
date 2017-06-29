package com.example.newsclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment{
    protected View mRoot;
    protected Activity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(mRoot == null){
         mRoot = LayoutInflater.from(mActivity)
                 .inflate(getLayoutRes() , container , false);
            initView();
            intiListener();
            initData();
        }
        return mRoot;
    }

    /** 初始化数据*/
    protected abstract void initData();
    /**设置控件的监听 */
    protected abstract void intiListener();
    /** 查找布局中的控件*/
    protected abstract void initView();

    /** 返回一个Fragment要显示的布局界面*/
    public abstract int getLayoutRes();

    private Toast mToast;
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
