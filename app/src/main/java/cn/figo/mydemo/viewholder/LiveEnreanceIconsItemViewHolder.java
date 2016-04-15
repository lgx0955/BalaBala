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
import cn.figo.mydemo.bean.LiveIndexBean;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 14:11
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveEnreanceIconsItemViewHolder extends BaseViewHolder<LiveIndexBean.DataEntity.EntranceIconsEntity> {


    @Bind(R.id.live_icon)
    ImageView liveIcon;
    @Bind(R.id.live_title)
    TextView liveTitle;

    public LiveEnreanceIconsItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_live_entranceicons_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final LiveIndexBean.DataEntity.EntranceIconsEntity bodyEntity) {
        if (bodyEntity.getEntrance_icon()!=null)
            Glide.with(getContext()).load(bodyEntity.getEntrance_icon().getSrc()).override(Integer.parseInt(bodyEntity.getEntrance_icon().getWidth()),Integer.parseInt(bodyEntity.getEntrance_icon().getHeight())).fitCenter().into(liveIcon);
        else
            liveIcon.setImageResource(R.mipmap.ic_launcher);
        liveTitle.setText(bodyEntity.getName());
    }
}