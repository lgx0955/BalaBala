package cn.figo.mydemo.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.LivePartitionsAdapter;
import cn.figo.mydemo.bean.LiveIndexBean;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 15:51
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LivePartitionsViewHolder extends BaseViewHolder<List<LiveIndexBean.DataEntity.PartitionsEntity>> {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

//    LivePartitionsItemAdapter livePartitionsItemAdapter;
    LivePartitionsAdapter livePartitionsAdapter;
    public LivePartitionsViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final List<LiveIndexBean.DataEntity.PartitionsEntity> bodyEntity) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        livePartitionsAdapter = new LivePartitionsAdapter(getContext());
        recyclerView.setAdapter(livePartitionsAdapter);
        livePartitionsAdapter.addAll(bodyEntity);
    }
}