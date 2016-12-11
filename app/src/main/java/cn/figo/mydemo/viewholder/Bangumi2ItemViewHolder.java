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
 * Date: 2016-02-29
 * Time: 15:48
 * Copyright (c)Ligx All rights reserved.
 */
public class Bangumi2ItemViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {

    @Bind(R.id.bangumi2_icon)
    ImageView bangumi2Icon;
    @Bind(R.id.bangumi2_title)
    TextView bangumi2Title;
    @Bind(R.id.bangumi2_number)
    TextView bangumi2Number;
    @Bind(R.id.bangumi2_container)
    LinearLayout bangumi2Container;

    public Bangumi2ItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_bangumi2_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getCover()).centerCrop().into(bangumi2Icon);
        bangumi2Title.setText(bodyEntity.getTitle());
        bangumi2Number.setText(bodyEntity.getDanmaku());

//        bangumi2Container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(FileListActivity.class);
//            }
//        });
    }
}
