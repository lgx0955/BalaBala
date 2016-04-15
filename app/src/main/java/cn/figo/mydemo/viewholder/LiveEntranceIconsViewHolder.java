package cn.figo.mydemo.viewholder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.LiveEntranceIconsAdapter;
import cn.figo.mydemo.bean.LiveIndexBean;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 13:48
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveEntranceIconsViewHolder extends BaseViewHolder<List<LiveIndexBean.DataEntity.EntranceIconsEntity>> {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    LiveEntranceIconsAdapter liveEntranceIconsAdapter;

    public LiveEntranceIconsViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(List<LiveIndexBean.DataEntity.EntranceIconsEntity> bodyEntity) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        recyclerView.setNestedScrollingEnabled(false);
        liveEntranceIconsAdapter = new LiveEntranceIconsAdapter(getContext());
        recyclerView.setAdapter(liveEntranceIconsAdapter);
        liveEntranceIconsAdapter.addAll(bodyEntity);
    }
}