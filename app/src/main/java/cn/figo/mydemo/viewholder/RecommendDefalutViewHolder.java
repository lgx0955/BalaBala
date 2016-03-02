package cn.figo.mydemo.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 15:32
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendDefalutViewHolder extends BaseViewHolder<RecommendBean.ResultEntity> {
    @Bind(R.id.icon_iv)
    ImageView iconIv;

    public RecommendDefalutViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity newsBean) {
        Glide.with(getContext())
                .load(newsBean.getBody().get(0).getCover())
                .into(iconIv);
    }
}