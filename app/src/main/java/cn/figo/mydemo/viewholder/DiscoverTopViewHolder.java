package cn.figo.mydemo.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.app.AppService;
import cn.figo.mydemo.event.RequestSearchEvent;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 16:00
 * Copyright (c)Ligx All rights reserved.
 */
public class DiscoverTopViewHolder extends BaseViewHolder {

    @Bind(R.id.discover_top_search)
    TextView discoverTopSearch;
    @Bind(R.id.discover_top_scan)
    ImageView discoverTopScan;

    public DiscoverTopViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_discover_top_search);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppService.getBus().post(new RequestSearchEvent());
            }
        });
    }
}