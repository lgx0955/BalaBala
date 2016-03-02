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
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.utils.LPhone;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 14:07
 * Copyright (c)Ligx All rights reserved.
 */
public class RegionItemViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {


    @Bind(R.id.region_icon)
    ImageView regionIcon;
    @Bind(R.id.region_title)
    TextView regionTitle;
    @Bind(R.id.region_number)
    TextView regionNumber;

    public RegionItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_region_card);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity bodyEntity) {
        int screenWidth = LPhone.getScreenWidth(getContext());
        int myWidth = (screenWidth - DensityUtils.dip2px(getContext(),24)) / 2;
        float ratio = Float.parseFloat(bodyEntity.getWidth()+"")/Float.parseFloat(bodyEntity.getHeight()+"");
        int height = (int) (myWidth / ratio +0.5);
        regionIcon.setLayoutParams(new LinearLayout.LayoutParams(myWidth, height));
        Glide.with(getContext()).load(bodyEntity.getCover()).into(regionIcon);
        regionTitle.setText(bodyEntity.getTitle());
        regionNumber.setText(bodyEntity.getDanmaku());

    }
}