package cn.figo.mydemo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;

/**
 * User: Ligx
 * Date: 2016-03-02
 * Time: 12:36
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LastUpdateViewHolder extends BaseViewHolder<BanguminIndexHeaderBean> {


    @Bind(R.id.live_icon)
    ImageView liveIcon;
    @Bind(R.id.live_title)
    TextView liveTitle;
    @Bind(R.id.live_number)
    TextView liveNumber;
    @Bind(R.id.live_container)
    LinearLayout liveContainer;

    public LastUpdateViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final BanguminIndexHeaderBean bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getResult().getLatestUpdate().getList().get(0).getCover()).centerCrop().into(liveIcon);
//        liveTitle.setText(bodyEntity.getTitle());
//        liveNumber.setText(bodyEntity.getDanmaku());
//
//        liveContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(FileListActivity.class);
//            }
//        });
    }
}