package cn.figo.mydemo.viewholder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.BangumiIndexBean;
import cn.figo.mydemo.ui.activity.BangumiDetailActivity;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.utils.LPhone;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 17:34
 * Copyright (c)Ligx All rights reserved.
 */
public class BangumiIndexViewHolder extends BaseViewHolder<BangumiIndexBean.ListEntity> {

    @Bind(R.id.bangumi2_icon)
    ImageView bangumi2Icon;
    @Bind(R.id.bangumi2_title)
    TextView bangumi2Title;
    @Bind(R.id.bangumi2_container)
    LinearLayout bangumi2Container;

    public BangumiIndexViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final BangumiIndexBean.ListEntity listEntity) {
        int screenWidth = LPhone.getScreenWidth(getContext());
        int myWidth = (screenWidth - DensityUtils.dip2px(getContext(),12)) / 2;
        float ratio = Float.parseFloat(listEntity.getWidth()+"")/Float.parseFloat(listEntity.getHeight()+"");
        int height = (int) (myWidth / ratio +0.5);
        bangumi2Icon.setLayoutParams(new LinearLayout.LayoutParams(myWidth, height));
        Glide.with(getContext()).load(listEntity.getImageurl()).crossFade(0).into(bangumi2Icon);

        bangumi2Title.setText(listEntity.getTitle());

        bangumi2Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(BangumiDetailActivity.BANGUMI_SPID_EXTRA,listEntity.getSpid()+"");
                overlay(BangumiDetailActivity.class,bundle);
            }
        });
    }
}
