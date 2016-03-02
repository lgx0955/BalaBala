package cn.figo.mydemo.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.DiscoverAdapter;
import cn.figo.mydemo.base.basefragment.BaseFragment;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 13:30
 * Copyright (c)Ligx All rights reserved.
 */
public class DiscoverFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    DiscoverAdapter discoverAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        discoverAdapter = new DiscoverAdapter(mContext);
        discoverAdapter.add("");
        discoverAdapter.add("");
        discoverAdapter.add("");
        recyclerView.setAdapter(discoverAdapter);

    }
}
