package com.example.newsclient;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newsclient.R;
import com.example.newsclient.URLManager;
import com.example.newsclient.VideoEntity;
import com.example.newsclient.adapter.VideoAdapter;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;





/**
 * Created by yls on 2017/6/27.
 */

public class MainFragment2 extends BaseFragment{
    @Override
    protected void initData() {
        getVideoDatas();

    }

    private void getVideoDatas() {
        new HttpUtils().send(HttpRequest.HttpMethod.GET, URLManager.VideoURL
                , new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        System.out.println("-----视频数据：" + json);
                        json =  json.replace("V9LG4B3A0", "result");
                        Gson gson = new Gson();
                        VideoEntity newsDatas = gson.fromJson(json, VideoEntity.class);
                        System.out.println("----解析视频json:"
                                + newsDatas.getResult().size());
                        
                        showDatas(newsDatas);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                    }
                });
    }

    private void showDatas(VideoEntity newsDatas) {
        RecyclerView recyclerView = (RecyclerView) mRoot.findViewById(R.id.recycler_view);
        // 设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        // 设置适配器，显示列表
        recyclerView.setAdapter(new VideoAdapter(mActivity, newsDatas.getResult()));
    }

    @Override
    protected void intiListener() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_02;
    }


}
