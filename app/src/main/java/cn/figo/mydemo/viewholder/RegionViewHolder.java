package cn.figo.mydemo.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.RegionCardAdapter;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 13:26
 * Copyright (c)Ligx All rights reserved.
 */
public class RegionViewHolder extends BaseViewHolder<RecommendBean.ResultEntity> {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.header_icon)
    ImageView headerIcon;

    RegionCardAdapter regionCardAdapter;

    public RegionViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity resultEntity) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        regionCardAdapter = new RegionCardAdapter(getContext());
        recyclerView.setAdapter(regionCardAdapter);
        regionCardAdapter.addAll(resultEntity.getBody());

        titleTv.setText(resultEntity.getHead().getTitle());

        switch (resultEntity.getHead().getTitle()){
            case "动画区":
                Glide.with(getContext()).load(R.drawable.ic_header_hot).into(headerIcon);
                break;
            case "音乐区":
                Glide.with(getContext()).load(R.drawable.ic_header_ding).into(headerIcon);
                break;
            default:
                Glide.with(getContext()).load(R.drawable.ic_header_new).into(headerIcon);
        }
    }
}