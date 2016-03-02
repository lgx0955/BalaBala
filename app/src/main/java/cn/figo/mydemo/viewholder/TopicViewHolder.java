package cn.figo.mydemo.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-02-20
 * Time: 09:58
 * Copyright (c)Ligx All rights reserved.
 */
public class TopicViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {
    private TextView title_tv;
    private ImageView mImg_face;
    private TextView content_tv;


    public TopicViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_topic);
        title_tv = $(R.id.title_tv);
//        content_tv = $(R.id.content_tv);
        mImg_face = $(R.id.iv);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity topicBean){
//        mTv_name.setText(person.getName());
//        mTv_sign.setText(person.getSign());
//        Glide.with(getContext())
//                .load(topicBean.getAvatar())
//                .placeholder(R.drawable.default_image)
//                .bitmapTransform(new CropCircleTransformation(getContext()))
//                .into(mImg_face);
////        title_tv.setText(topicBean.getNickname());
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(AboutActivity.class);
//            }
//        });
    }
}