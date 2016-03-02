package cn.figo.mydemo.viewholder;

import android.graphics.drawable.AnimationDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 16:00
 * Copyright (c)Ligx All rights reserved.
 */
public class DiscoverBottomViewHolder extends BaseViewHolder {


    @Bind(R.id.discover_bottom_discovery_new)
    ImageView discoverBottomDiscoveryNew;
    @Bind(R.id.discover_bottom_quanzi)
    RelativeLayout discoverBottomQuanzi;
    @Bind(R.id.discover_bottom_rank_original)
    RelativeLayout discoverBottomRankOriginal;
    @Bind(R.id.discover_bottom_rank_all)
    RelativeLayout discoverBottomRankAll;
    @Bind(R.id.discover_bottom_game)
    RelativeLayout discoverBottomGame;
    @Bind(R.id.discover_bottom_biliyoo)
    RelativeLayout discoverBottomBiliyoo;

    public DiscoverBottomViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_discover_bottom);
        ButterKnife.bind(this, itemView);

        //设置动画
        AnimationDrawable ad = (AnimationDrawable) discoverBottomDiscoveryNew.getBackground();
        ad.start();
    }
}
