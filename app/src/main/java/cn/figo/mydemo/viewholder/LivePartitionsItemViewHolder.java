package cn.figo.mydemo.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.LivePartitionsItemAdapter;
import cn.figo.mydemo.bean.LiveIndexBean;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 15:59
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LivePartitionsItemViewHolder extends BaseViewHolder<LiveIndexBean.DataEntity.PartitionsEntity> {

    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.header_icon)
    ImageView headerIcon;

    LivePartitionsItemAdapter livePartitionsItemAdapter;

    public LivePartitionsItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_live_partitions_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final LiveIndexBean.DataEntity.PartitionsEntity bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getPartition().getSub_icon().getSrc()).centerCrop().into(headerIcon);
        titleTv.setText(bodyEntity.getPartition().getName());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setNestedScrollingEnabled(false);
        livePartitionsItemAdapter = new LivePartitionsItemAdapter(getContext());
        recyclerView.setAdapter(livePartitionsItemAdapter);
        if (bodyEntity.getLives().size()>4)
            livePartitionsItemAdapter.addAll(bodyEntity.getLives().subList(0,4));
        else
            livePartitionsItemAdapter.addAll(bodyEntity.getLives());

    }
}