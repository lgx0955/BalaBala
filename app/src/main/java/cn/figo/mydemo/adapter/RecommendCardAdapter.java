package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.RecommendBean;
import cn.figo.mydemo.viewholder.RecomendItemViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 16:21
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendCardAdapter extends RecyclerArrayAdapter<RecommendBean.ResultEntity.BodyEntity> {

    public RecommendCardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecomendItemViewHolder(parent);
    }
}