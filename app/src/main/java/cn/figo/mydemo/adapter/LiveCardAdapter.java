package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.RecommendBean;
import cn.figo.mydemo.viewholder.LiveItemViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 16:07
 * Copyright (c)Ligx All rights reserved.
 */
public class LiveCardAdapter extends RecyclerArrayAdapter<RecommendBean.ResultEntity.BodyEntity> {

    public LiveCardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiveItemViewHolder(parent);
    }
}