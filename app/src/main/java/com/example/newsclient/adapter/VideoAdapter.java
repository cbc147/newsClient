package com.example.newsclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsclient.R;
import com.example.newsclient.VideoEntity;
import com.example.newsclient.activity.VideoPlayActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by yls on 2017/6/29.
 */

public class VideoAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<VideoEntity.ResultBean> listDatas;

        public VideoAdapter(Context mContext,
               List<VideoEntity.ResultBean> listDatas){
            this.mContext = mContext;
            this.listDatas = listDatas;
        }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;

        final VideoEntity.ResultBean video = listDatas.get(position);

        Log.d("--------"  , video.getMp4_url());
        holder.mJCVideoPlayer.setUp(video.getMp4_url() , video.getTitle());

        // 预加载缩略图
        Picasso.with(mContext).load(video.getCover()).into(holder.mJCVideoPlayer.ivThumb);

        // 显示标题
        holder.tvVideoTitle.setText(listDatas.get(position).getTitle());
        // 显示视频播放时长
        System.out.println("-----duration: " + video.getLength());
        String durationStr = DateFormat.format("mm:ss", video.getLength() * 1000).toString();
        holder.tvVideoDuration.setText(durationStr);
        // 显示播放次数
        holder.tvPlayCount.setText(video.getPlayCount() + "");

        // 点击列表项时，跳转进入视频播放详情界面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoPlayActivity.class);
                intent.putExtra("video_url", video.getMp4_url());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatas == null ? 0 : listDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

//        private ImageView ivVideoImage;
        private TextView tvVideoTitle;
        private TextView tvVideoDuration;
        private TextView tvPlayCount;
        private JCVideoPlayerStandard mJCVideoPlayer;

        public MyViewHolder(View itemView){
            super(itemView);

//            ivVideoImage = (ImageView) itemView.findViewById(R.id.iv_video_image);
            tvVideoTitle = (TextView) itemView.findViewById(R.id.tv_video_title);
            tvVideoDuration = (TextView) itemView.findViewById(R.id.tv_video_duration);
            tvPlayCount = (TextView) itemView.findViewById(R.id.tv_play_count);
            mJCVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }
}
