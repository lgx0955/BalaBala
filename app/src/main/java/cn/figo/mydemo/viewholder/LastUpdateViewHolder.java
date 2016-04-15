package cn.figo.mydemo.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.figo.mydemo.R;
import cn.figo.mydemo.bean.AidBean;
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;
import cn.figo.mydemo.bean.VideoDetailBean;
import cn.figo.mydemo.http.RetrofitClientManager;
import cn.figo.mydemo.ui.activity.VideoActivity;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * User: Ligx
 * Date: 2016-03-02
 * Time: 12:36
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LastUpdateViewHolder extends BaseViewHolder<BanguminIndexHeaderBean> {


    @Bind(R.id.live_icon)
    ImageView liveIcon;
    @Bind(R.id.live_title)
    TextView liveTitle;
    @Bind(R.id.live_number)
    TextView liveNumber;
    @Bind(R.id.live_container)
    LinearLayout liveContainer;

    AidBean aidBean;

    public LastUpdateViewHolder(View parent) {
        super(parent);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void setData(final BanguminIndexHeaderBean bodyEntity) {
        Glide.with(getContext()).load(bodyEntity.getResult().getLatestUpdate().getList().get(0).getCover()).centerCrop().into(liveIcon);
        liveTitle.setText(bodyEntity.getResult().getLatestUpdate().getList().get(0).getBangumi_title());
//        liveNumber.setText(bodyEntity.getDanmaku());




        liveContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getVideoList(bodyEntity.getResult().getLatestUpdate().getList().get(0).getSeason_id());
//                VideoActivity.intentTo(getContext(),);
                System.out.println("Gggghhhhaa");
                getVideoPathByAid("3538470");
            }
        });
    }


    public void getVideoList(String session_id){
        System.out.println("aaaaa"+session_id);
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getBangumiDetail(), new Callback() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                VideoDetailBean videoDetailBean = new Gson().fromJson(response.body().toString(),VideoDetailBean.class);
                System.out.print("aaaaaaaa"+response.body().toString());
                getVideoPathByAid(videoDetailBean.getResult().getEpisodes().get(0).getAv_id());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void getVideoPathByAid(String aid){
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getVideoPath(aid,"1"), new Callback<AidBean>() {
            @Override
            public void onResponse(Response<AidBean> response, Retrofit retrofit) {
                aidBean = response.body();
                System.out.println("开启视频");
                VideoActivity.intentTo(getContext(),aidBean.getSrc(),"aaaa");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

}