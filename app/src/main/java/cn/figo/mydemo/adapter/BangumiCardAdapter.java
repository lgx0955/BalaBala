package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.RecommendBean;
import cn.figo.mydemo.viewholder.Bangumi2ItemViewHolder;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 15:53
 * Copyright (c)Ligx All rights reserved.
 */
public class BangumiCardAdapter extends RecyclerArrayAdapter<RecommendBean.ResultEntity.BodyEntity> {

    public BangumiCardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new Bangumi2ItemViewHolder(parent);
    }
}