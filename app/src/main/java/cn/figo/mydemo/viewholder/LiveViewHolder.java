package cn.figo.mydemo.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.LiveCardAdapter;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 15:22
 * Copyright (c)Ligx All rights reserved.
 */
public class LiveViewHolder extends BaseViewHolder<RecommendBean.ResultEntity> {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.header_icon)
    ImageView headerIcon;

    LiveCardAdapter liveCardAdapter;

    public LiveViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity resultEntity) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setNestedScrollingEnabled(false);

        liveCardAdapter = new LiveCardAdapter(getContext());
        recyclerView.setAdapter(liveCardAdapter);
        liveCardAdapter.addAll(resultEntity.getBody());

        titleTv.setText(resultEntity.getHead().getTitle());

    }
}