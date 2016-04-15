package cn.figo.mydemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.R;
import cn.figo.mydemo.viewholder.LiveEntranceIconsViewHolder;
import cn.figo.mydemo.viewholder.LivePartitionsViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 13:32
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveIndexAdapter extends RecyclerArrayAdapter<Object> {

    public static final int TYPE_ENTRANCE = 0;
    public static final int TYPE_NORMAL= 1;
    public static final int TYPE_PARTITIONS = 2;

    public LiveIndexAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if (position == 0)
            return TYPE_ENTRANCE;
        if (position == 1)
            return TYPE_PARTITIONS;
        return TYPE_ENTRANCE;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_ENTRANCE:
                view = getViewHolder(R.layout.item_live_entranceicons, parent, false);
                return new LiveEntranceIconsViewHolder(view);
            case TYPE_PARTITIONS:
                view = getViewHolder(R.layout.item_live_partitions, parent, false);
                return new LivePartitionsViewHolder(view);
        }
        return null;
    }

    @NonNull
    private View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
//        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
//        layoutParams.setFullSpan(isFullSpan);
//        view.setLayoutParams(layoutParams);
        return view;
    }
}