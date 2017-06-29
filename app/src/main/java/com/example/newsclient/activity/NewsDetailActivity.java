package com.example.newsclient.activity;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.newsclient.NewsEntity;
import com.example.newsclient.R;



/**
 * Created by yls on 2017/6/28.
 */

public class NewsDetailActivity extends BaseActivity{
    private WebView mWebView;
    private ProgressBar mProgressBar;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData() {
        NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean) getIntent().getSerializableExtra("news");
        mWebView.loadUrl(newsBean.getUrl());

    //   显示标题栏左上角的返回图标
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 显示标题栏
          getSupportActionBar().setTitle(newsBean.getTitle());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();       // 标题栏左上角的返回按钮，退出当前界面
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.pb_01);
        initWebView();
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        //禁止使用其他浏览器打开
         mWebView.setWebViewClient(new WebViewClient());
        //支持javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        //加载网页时显示进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){//加载完成,隐藏进度条
                    mProgressBar.setVisibility(View.GONE);
                }else{// 显示加载进度
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                    System.out.println("---------percent: " + newProgress);
                }
            }
        });
    }
}
