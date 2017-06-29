package com.example.newsclient;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.newsclient.NewsEntity;
import com.example.newsclient.R;
import com.example.newsclient.URLManager;
import com.example.newsclient.activity.NewsDetailActivity;
import com.example.newsclient.adapter.NewsAdapter;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by yls on 2017/6/27.tt
 */

public class NewsFragment extends BaseFragment{
    private String channelId;
    private ListView mListView;
    private SpringView mSpringView;
    private String newsFirstUrl =
            "http://c.m.163.com/nc/article/headline/" + channelId + "/0-20.html";
    private NewsAdapter newsAdapter;



    public void setChannelId(String channelId){
        this.channelId = channelId;
    }
    @Override
    protected void initData() {
        getDataFromServer();
    }

    @Override
    protected void intiListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity.ResultBean newsBean = (NewsEntity.ResultBean) parent.getItemAtPosition(position);

                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("news", newsBean);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {
        mListView = (ListView) mRoot.findViewById(R.id.lv_news);
        initSpringView();

    }

    private void initSpringView() {
        mSpringView = (SpringView) mRoot.findViewById(R.id.spring_view);

        mSpringView.setHeader(new DefaultHeader(getContext()));
        mSpringView.setFooter(new DefaultFooter(getContext()));

        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                showToast("下拉");
                //请求第一页数据

                //隐藏刷新
                mSpringView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                showToast("上拉");
                //请求下一页数据


                //隐藏刷新
                mSpringView.onFinishFreshAndLoad();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }



    private void getDataFromServer(){
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        String url = URLManager.getUrl(channelId);
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        System.out.println("----服务器返回的json数据:" + json);

                        json = json.replace(channelId , "result");
                        Gson gson = new Gson();
                        NewsEntity newsDatas = gson.fromJson(json, NewsEntity.class);
                        System.out.println("----解析json:" + newsDatas.getResult().size());
                   
                   //显示数据到列表上
                        showDatas(newsDatas);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                    }
                });
    }

    private void showDatas(NewsEntity newsDatas) {
        if (newsDatas == null
                || newsDatas.getResult() == null
                || newsDatas.getResult().size() == 0) {
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }
        //显示轮播图
        final List<NewsEntity.ResultBean.AdsBean> ads = newsDatas.getResult().get(0).getAds();
        //有轮播图广告
        if (ads != null && ads.size() > 0){
            View headerView = View.inflate(mActivity, R.layout.list_header, null);
            SliderLayout sliderLayout = (SliderLayout) headerView.findViewById(R.id.slider_layout);
            for (int i = 0; i < ads.size() ; i++){
                final NewsEntity.ResultBean.AdsBean adsBean = ads.get(i);

                TextSliderView sliderView = new TextSliderView(mActivity);
                sliderView.description(adsBean.getTitle()).image(adsBean.getImgsrc());
                //子界面
                sliderLayout.addSlider(sliderView);
                //点击事件
                sliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast(slider.getDescription());
                       // showToast(slider.getUrl());
                    }
                });
            }
            //添加列表头部布局
            mListView.addHeaderView(headerView);
        }

        newsAdapter = new NewsAdapter(mActivity ,
                newsDatas.getResult());
        mListView.setAdapter(newsAdapter);
    }
}
