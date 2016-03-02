package cn.figo.mydemo.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-02-17
 * Time: 14:48
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendHolder extends BaseViewHolder<RecommendBean.ResultEntity> {
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.zanNum)
    TextView zanNum;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.detail_tv)
    TextView detailTv;


    public RecommendHolder(ViewGroup parent) {
        super(parent, R.layout.item_news);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity newsBean) {
        Glide.with(getContext())
                .load(newsBean.getBody().get(0).getCover())
                .into(iv);
        titleTv.setText(newsBean.getBody().get(0).getDesc1());

    }
}