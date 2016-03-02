package cn.figo.mydemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;
import cn.figo.mydemo.viewholder.Bangumi2ViewHolder;
import cn.figo.mydemo.viewholder.LiveViewHolder;
import cn.figo.mydemo.viewholder.RecommendDefalutViewHolder;
import cn.figo.mydemo.viewholder.RecommendViewHolder;
import cn.figo.mydemo.viewholder.RegionViewHolder;

/**
 * User: Ligx
 * Date: 2016-02-17
 * Time: 14:20
 * Copyright (c)Ligx All rights reserved.
 */
public class IndexAdapter extends RecyclerArrayAdapter<RecommendBean.ResultEntity> {

    public static final int TYPE_INVALID = 0;
    public static final int TYPE_NORMAL= 1;
    public static final int TYPE_BANGUMI = 2;
    public static final int TYPE_LIVE = 3;
    public static final int TYPE_PIC = 4;
    public static final int TYPE_REGION = 5;
    public static final int TYPE_RECOMMEND = 6;

    public IndexAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position).getType()==null){
            return TYPE_NORMAL;
        }
        if(getItem(position).getType().equals("recommend")){
            return TYPE_RECOMMEND;
        }else if (getItem(position).getType().equals("bangumi_2")){
            return TYPE_BANGUMI;
        }else if (getItem(position).getType().equals("live")){
            return TYPE_LIVE;
        }else if (getItem(position).getType().equals("region")){
            return TYPE_REGION;
        }else if (getItem(position).getType().equals("pic")){
            return TYPE_PIC;
        }

        return TYPE_NORMAL;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_LIVE:
                view = getViewHolder(R.layout.item_live, parent, true);
                return new LiveViewHolder(view);
            case TYPE_RECOMMEND:
                view = getViewHolder(R.layout.item_recommend, parent, true);
                return new RecommendViewHolder(view);
            case TYPE_BANGUMI:
                view = getViewHolder(R.layout.item_bangumi2, parent, true);
                return new Bangumi2ViewHolder(view);
            case TYPE_REGION:
                view = getViewHolder(R.layout.item_region, parent, true);
                return new RegionViewHolder(view);
            case TYPE_NORMAL:
                view = getViewHolder(R.layout.item_recommend_default, parent, true);
                return new RecommendDefalutViewHolder(view);
        }
        view = getViewHolder(R.layout.item_recommend_default, parent, true);
        return new RecommendDefalutViewHolder(view);
    }

    @NonNull
    private View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }
}