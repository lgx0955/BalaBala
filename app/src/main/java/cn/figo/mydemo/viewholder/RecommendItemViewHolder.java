package cn.figo.mydemo.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 16:21
 * Copyright (c)Ligx All rights reserved.
 */
public class RecommendItemViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {


    @Bind(R.id.recommend_icon)
    ImageView recommendIcon;
    @Bind(R.id.recommend_title)
    TextView recommendTitle;
    @Bind(R.id.recommend_number)
    TextView recommendNumber;
    @Bind(R.id.recommend_container)
    LinearLayout recommendContainer;

    public RecommendItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_recommend_card);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getCover()).override(bodyEntity.getWidth(), bodyEntity.getHeight()).centerCrop().into(recommendIcon);
        recommendTitle.setText(bodyEntity.getTitle());
        recommendNumber.setText(bodyEntity.getDanmaku());

    }
}