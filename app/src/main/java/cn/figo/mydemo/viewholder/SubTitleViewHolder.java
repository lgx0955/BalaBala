package cn.figo.mydemo.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.RecommendBean;


/**
 * User: Ligx
 * Date: 2016-02-20
 * Time: 09:58
 * Copyright (c)Ligx All rights reserved.
 */
public class SubTitleViewHolder extends BaseViewHolder<RecommendBean.ResultEntity.BodyEntity> {
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.more_iv1)
    ImageView moreIv1;
    @Bind(R.id.wenzhang_rv)
    RelativeLayout wenzhangRv;

    public SubTitleViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_subtitle);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(final RecommendBean.ResultEntity.BodyEntity subTitleBean) {
        titleTv.setText(subTitleBean.getTitle());

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                overlay(AboutActivity.class);
////                Intent intent = new Intent(getContext(), AboutActivity.class);
////                ActivityOptionsCompat options =
////                        ActivityOptionsCompat.makeScaleUpAnimation(itemView, //The View that the new activity is animating from
////                                (int) itemView.getWidth() / 2, (int) itemView.getHeight() / 2, //拉伸开始的坐标
////                                0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
////
////                ActivityCompat.startActivity((Activity) getContext(), intent, options.toBundle());
//            }
//        });
    }
}
