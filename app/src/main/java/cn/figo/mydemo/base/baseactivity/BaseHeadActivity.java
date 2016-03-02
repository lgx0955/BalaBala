package cn.figo.mydemo.base.baseactivity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import cn.figo.mydemo.R;


/**
 * User: Ligx
 * Date: 2015-04-20
 * Time: 16:18
 * Copyright (c)Ligx All rights reserved.
 */
public abstract class BaseHeadActivity extends BaseActivity {

    @Bind(R.id.iv_left_opt)
    public ImageView ivLeftOpt;
    @Bind(R.id.tv_left_opt)
    public TextView tvLeftOpt;
    @Bind(R.id.btn_left_opt)
    public FrameLayout btnLeftOpt;
    @Bind(R.id.iv_opt)
    public ImageView ivOpt;
    @Bind(R.id.tv_opt)
    public TextView tvOpt;
    @Bind(R.id.btn_opt)
    public FrameLayout btnOpt;
    @Bind(R.id.iv_title)
    public ImageView ivTitle;
    @Bind(R.id.tv_title)
    public TextView tvTitle;
    @Bind(R.id.btn_title)
    public FrameLayout btnTitle;
    @Bind(R.id.rl_title_1)
    public RelativeLayout rlTitle1;

    /**
     * 设置标题
     */
    protected void setTitle(String title) {
        tvTitle.setText(title);
        tvTitle.setVisibility(View.VISIBLE);
    }

    /**
     * 设置标题颜色
     */
    protected void setTitleColor(int textColor, int backgroud) {
        if (tvTitle != null) {
            if (backgroud != -1) {
                rlTitle1.setBackgroundColor(backgroud);
            }
            if (textColor != -1) {
                tvTitle.setTextColor(textColor);
            }
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题颜色
     */
    protected void setTitleColorRes(int textColorRes, int backgroudRes) {
        if (tvTitle != null) {
            if (backgroudRes != -1) {
                rlTitle1.setBackgroundColor(getResources().getColor(backgroudRes));
            }
            if (textColorRes != -1) {
                tvTitle.setTextColor(getResources().getColor(textColorRes));
            }
            tvTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右操作图片
     *
     * @param resId
     */
    protected void setIvOpt(int resId) {
        ivOpt.setImageResource(resId);
        ivOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右操作文字
     *
     * @param opt
     */
    protected void setTvOpt(String opt) {
        tvOpt.setText(opt);
        tvOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右操作文字
     *
     * @param opt
     */
    protected void setTvOpt(String opt, int textColor) {
        tvOpt.setText(opt);
        tvOpt.setTextColor(textColor);
        tvOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右操作事件
     *
     * @param optListener
     */
    protected void setOptListener(View.OnClickListener optListener) {
        btnOpt.setOnClickListener(optListener);
        btnOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作图片
     *
     * @param resId
     */
    protected void setIvLeftOpt(int resId) {
        ivLeftOpt.setImageResource(resId);
        ivLeftOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作文字
     *
     * @param opt
     */
    protected void setTvLeftOpt(String opt) {
        tvLeftOpt.setText(opt);
        tvLeftOpt.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左操作事件
     *
     * @param optListener
     */
    protected void setLeftOptListener(View.OnClickListener optListener) {
        btnLeftOpt.setOnClickListener(optListener);
        btnLeftOpt.setVisibility(View.VISIBLE);
    }
}
