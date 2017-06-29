package com.example.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsclient.NewsEntity;
import com.example.newsclient.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by yls on 2017/6/27.
 */

public class NewsAdapter extends BaseAdapter{
    private static final int ITEM_TYPE_WITH_1_IMAGE = 0;
    private static final int ITEM_TYPE_WITH_3_IMAGE = 1;
    private Context mContext;
    private List<NewsEntity.ResultBean> mBeanList;

    public NewsAdapter(Context mContext,
            List<NewsEntity.ResultBean> mBeanList){
        this.mContext = mContext;
        this.mBeanList = mBeanList;
    }

    @Override
    public int getItemViewType(int position) {
        NewsEntity.ResultBean item = getItem(position);
        if (item.getImgextra() == null || item.getImgextra().size() == 0){
            //只有一张图片的item
            return ITEM_TYPE_WITH_1_IMAGE;
        }else{
            return ITEM_TYPE_WITH_3_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return (mBeanList == null) ? 0 : mBeanList.size();
    }

    public NewsEntity.ResultBean getItem(int position){
        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity.ResultBean info = getItem(position);
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_WITH_1_IMAGE) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_news_1, null);
            }
            // 查找列表item中的子控件
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvSource = (TextView) convertView.findViewById(R.id.tv_source);
            TextView tvComment = (TextView) convertView.findViewById(R.id.tv_comment);

            // 显示列表item中的子控件
            tvTitle.setText(info.getTitle());
            tvSource.setText(info.getSource());
            tvComment.setText(info.getReplyCount() + "跟帖");
            Picasso.with(mContext).load(info.getImgsrc()).into(ivIcon);
        }else if (itemViewType == ITEM_TYPE_WITH_3_IMAGE){
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_news_2, null);
            }

            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView  tvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.iv_01);
            ImageView iv02 = (ImageView) convertView.findViewById(R.id.iv_02);
            ImageView iv03 = (ImageView) convertView.findViewById(R.id.iv_03);

            tvTitle.setText(info.getTitle());
            tvComment.setText(info.getReplyCount()+ "跟帖");
            Picasso.with(mContext).load(info.getImgsrc()).into(iv01);
            Picasso.with(mContext).load(info.getImgextra().get(0).getImgsrc())
                    .into(iv02);
            Picasso.with(mContext).load(info.getImgextra().get(1).getImgsrc())
                    .into(iv03);
        }
        return convertView;
    }

}

