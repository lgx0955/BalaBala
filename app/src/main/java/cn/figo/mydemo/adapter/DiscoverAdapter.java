package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.viewholder.DiscoverBottomViewHolder;
import cn.figo.mydemo.viewholder.DiscoverCenterViewHolder;
import cn.figo.mydemo.viewholder.DiscoverTopViewHolder;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 15:56
 * Copyright (c)Ligx All rights reserved.
 */
public class DiscoverAdapter extends RecyclerArrayAdapter<String> {

    private static final int TYPE_TOP_SEARCH = 0;
    private static final int TYPE_CENTER = 1;
    private static final int TYPE_BOTTOM = 2;

    public DiscoverAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if (position == 0) {
            return TYPE_TOP_SEARCH;
        } else if (position == 1) {
            return TYPE_CENTER;
        } else if (position == 2) {
            return TYPE_BOTTOM;
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TOP_SEARCH:
                return new DiscoverTopViewHolder(parent);
            case TYPE_CENTER:
                return new DiscoverCenterViewHolder(parent);
            case TYPE_BOTTOM:
                return new DiscoverBottomViewHolder(parent);
        }
        return null;
    }
}