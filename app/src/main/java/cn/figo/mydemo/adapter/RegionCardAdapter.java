package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.RecommendBean;
import cn.figo.mydemo.viewholder.RegionItemViewHolder;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 14:06
 * Copyright (c)Ligx All rights reserved.
 */
public class RegionCardAdapter extends RecyclerArrayAdapter<RecommendBean.ResultEntity.BodyEntity> {

    public RegionCardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RegionItemViewHolder(parent);
    }
}