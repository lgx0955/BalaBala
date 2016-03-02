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
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;
import cn.figo.mydemo.viewholder.BangumiIndexViewHolder;
import cn.figo.mydemo.viewholder.LastUpdateViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 17:25
 * Copyright (c)Ligx All rights reserved.
 */
public class BangumiIndexAdapter extends RecyclerArrayAdapter<Object> {

    public static final int TYPE_LASTUPDATE = 0;
    public static final int TYPE_NORMAL= 1;
    public static final int TYPE_HEADER = 2;
    public static final int TYPE_FINISH = 3;
    public static final int TYPE_CATEGORY = 4;
    public static final int TYPE_RECOMMENDCATEGORY = 5;

    public BangumiIndexAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if (getItem(position) instanceof BanguminIndexHeaderBean)
            return TYPE_LASTUPDATE;
        return TYPE_NORMAL;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_LASTUPDATE:
                view = getViewHolder(R.layout.item_live_card, parent, true);
                return new LastUpdateViewHolder(view);
//            case TYPE_FINISH:
//                view = getViewHolder(R.layout.item_recommend, parent, true);
//                return new RecommendViewHolder(view);
//            case TYPE_CATEGORY:
//                view = getViewHolder(R.layout.item_bangumi2, parent, true);
//                return new Bangumi2ViewHolder(view);
//            case TYPE_RECOMMENDCATEGORY:
//                view = getViewHolder(R.layout.item_region, parent, true);
//                return new RegionViewHolder(view);
//            case TYPE_NORMAL:
//                view = getViewHolder(R.layout.item_recommend_default, parent, true);
//                return new RecommendDefalutViewHolder(view);
        }
        view = getViewHolder(R.layout.item_home_bangumi, parent, false);
        return new BangumiIndexViewHolder(view);
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