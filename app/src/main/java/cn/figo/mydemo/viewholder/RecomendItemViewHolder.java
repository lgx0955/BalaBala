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
 * Time: 13:28
 * Copyright (c)Ligx All rights reserved.
 */
public class RecomendItemViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {


    @Bind(R.id.recommend_icon)
    ImageView recommendIcon;
    @Bind(R.id.recommend_title)
    TextView recommendTitle;
    @Bind(R.id.recommend_number)
    TextView recommendNumber;
    @Bind(R.id.recommend_container)
    LinearLayout recommendContainer;

    public RecomendItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_recommend_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getCover()).centerCrop().into(recommendIcon);
        recommendTitle.setText(bodyEntity.getTitle());
        recommendNumber.setText(bodyEntity.getDanmaku());

//        recommendContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(FileListActivity.class);
//            }
//        });
    }
}
