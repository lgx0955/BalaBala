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
 * Time: 16:08
 * Copyright (c)Ligx All rights reserved.
 */
public class LiveItemViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {


    @Bind(R.id.live_icon)
    ImageView liveIcon;
    @Bind(R.id.live_title)
    TextView liveTitle;
    @Bind(R.id.live_number)
    TextView liveNumber;
    @Bind(R.id.live_container)
    LinearLayout liveContainer;

    public LiveItemViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_live_card);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getCover()).centerCrop().into(liveIcon);
        liveTitle.setText(bodyEntity.getTitle());
        liveNumber.setText(bodyEntity.getDanmaku());

//        liveContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(FileListActivity.class);
//            }
//        });
    }
}
