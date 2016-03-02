package cn.figo.mydemo.viewholder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.figo.mydemo.R;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.view.FlowLayout;
import cn.figo.mydemo.view.MyScrollView;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 16:00
 * Copyright (c)Ligx All rights reserved.
 */
public class DiscoverCenterViewHolder extends BaseViewHolder {


    @Bind(R.id.discover_center_wiki)
    MyScrollView discoverCenterWiki;
    @Bind(R.id.discover_bottom_arrow)
    ImageView discoverBottomArrow;
    @Bind(R.id.discover_center_tv)
    TextView discoverCenterTv;
    @Bind(R.id.discover_more)
    RelativeLayout discoverMore;
    @Bind(R.id.discover_center_container)
    LinearLayout discoverCenterContainer;
    private boolean isOpened;
    private final String[] mDatas = {"太子妃升职记", "奶酪陷阱", "拜托了冰箱", "吃货天下", "请和废材的我谈恋爱", "暴走大事件", "了不起的挑战",
            "太子妃升职记", "奶酪陷阱", "拜托了冰箱", "吃货天下", "请和废材的我谈恋爱", "暴走大事件", "了不起的挑战",
            "太子妃升职记", "奶酪陷阱", "拜托了冰箱", "吃货天下", "请和废材的我谈恋爱", "暴走大事件", "了不起的挑战"};

    public DiscoverCenterViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_discover_center);
        ButterKnife.bind(this, itemView);
        initData();
    }

    private void initData() {
        if (mDatas == null) {
            discoverCenterContainer.setVisibility(View.GONE);
            return;
        }
        discoverCenterContainer.setVisibility(View.VISIBLE);

        FlowLayout mLayout = new FlowLayout(getContext());
        discoverCenterWiki.addView(mLayout);

        ViewGroup.LayoutParams layoutParams = discoverCenterWiki.getLayoutParams();
        layoutParams.height = DensityUtils.dip2px(getContext(), 80);
        discoverCenterWiki.setLayoutParams(layoutParams);

        mLayout.setSpace(DensityUtils.dip2px(getContext(), 12), DensityUtils.dip2px(getContext(), 12));

        for (int i = 0; i < mDatas.length; i++) {

            TextView tv = new TextView(getContext());
            tv.setText(mDatas[i]);
            tv.setPadding(DensityUtils.dip2px(getContext(), 5), DensityUtils.dip2px(getContext(), 5), DensityUtils.dip2px(getContext(), 5), DensityUtils.dip2px(getContext(), 5));
            //设置字体颜色的选择器
            ColorStateList colorSateList = (ColorStateList) getContext().getResources().getColorStateList(R.color.discover_text_selector);
            tv.setTextColor(colorSateList);

            GradientDrawable normal = new GradientDrawable();
            normal.setShape(GradientDrawable.RECTANGLE);
            normal.setCornerRadius(DensityUtils.dip2px(getContext(), 3));
            normal.setColor(Color.parseColor("#ffffff"));

            GradientDrawable pressed = new GradientDrawable();
            pressed.setShape(GradientDrawable.RECTANGLE);
            pressed.setCornerRadius(DensityUtils.dip2px(getContext(), 3));
            pressed.setColor(Color.parseColor("#97445C"));

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressed);
            selector.addState(new int[]{}, normal);

            tv.setBackgroundDrawable(selector);
            final int index = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "" + mDatas[index], Toast.LENGTH_SHORT).show();
                }
            });
            mLayout.addView(tv);
        }
    }

    @OnClick(R.id.discover_more)
    public void onCenterTvClick(View v) {
        if (!isOpened) {
            ObjectAnimator.ofFloat(discoverBottomArrow, "rotation", 0, 180).start();
            animate(DensityUtils.dip2px(getContext(), 80), DensityUtils.dip2px(getContext(), 200));
            discoverCenterTv.setText("收起");
            //设置可滑动
            discoverCenterWiki.setCanScroll(true);
        } else {
            ObjectAnimator.ofFloat(discoverBottomArrow, "rotation", -180, 0).start();
            animate(DensityUtils.dip2px(getContext(), 200), DensityUtils.dip2px(getContext(), 80));
            discoverCenterTv.setText("查看更多");
            //设置不可滑动
            discoverCenterWiki.setCanScroll(false);
        }
        isOpened = !isOpened;
    }

    private void animate(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = discoverCenterWiki.getLayoutParams();
                layoutParams.height = value;
                discoverCenterWiki.setLayoutParams(layoutParams);
            }
        });
        animator.start();
    }
}