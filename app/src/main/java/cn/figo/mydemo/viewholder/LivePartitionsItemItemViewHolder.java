package cn.figo.mydemo.viewholder;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.LiveIndexBean;
import cn.figo.mydemo.ui.activity.LiveDetailActivity;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.utils.LPhone;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 23:30
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LivePartitionsItemItemViewHolder extends BaseViewHolder<LiveIndexBean.DataEntity.PartitionsEntity.LivesEntity> {

    @Bind(R.id.live_icon)
    ImageView liveIcon;
    @Bind(R.id.live_title)
    TextView liveTitle;
    @Bind(R.id.live_owner_face_icon)
    ImageView liveOwnerFaceIcon;
    @Bind(R.id.live_container)
    LinearLayout liveContainer;

    public LivePartitionsItemItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_live_partitions_card_item_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final LiveIndexBean.DataEntity.PartitionsEntity.LivesEntity bodyEntity) {
        int width = bodyEntity.getCover().getWidth();
        int height = bodyEntity.getCover().getHeight();
        int screenWidth = LPhone.getScreenWidth(getContext());
        int myWidth = (screenWidth - DensityUtils.dip2px(getContext(), 24)) / 2;
        float ratio = Float.parseFloat(width + "") / Float.parseFloat(height + "");
        int myheight = (int) (myWidth / ratio + 0.5);
        liveIcon.setLayoutParams(new LinearLayout.LayoutParams(myWidth, myheight));
        Glide.with(getContext()).load(bodyEntity.getCover().getSrc()).centerCrop().into(liveIcon);
        liveTitle.setText(bodyEntity.getTitle());
        Glide.with(getContext()).load(bodyEntity.getOwner().getFace()).centerCrop().into(liveOwnerFaceIcon);

        liveContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(LiveDetailActivity.EXTRA_ROOMID,bodyEntity.getRoom_id()+"");
                overlay(LiveDetailActivity.class,bundle);
            }
        });
    }
}