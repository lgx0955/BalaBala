package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.LiveIndexBean;
import cn.figo.mydemo.viewholder.LivePartitionsItemViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 15:50
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LivePartitionsAdapter extends RecyclerArrayAdapter<LiveIndexBean.DataEntity.PartitionsEntity> {

    public LivePartitionsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LivePartitionsItemViewHolder(parent);
    }
}