package cn.figo.mydemo.ui.fragment;

import android.content.Context;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.LiveIndexAdapter;
import cn.figo.mydemo.base.basefragment.BaseFragment;
import cn.figo.mydemo.bean.LiveIndexBean;
import cn.figo.mydemo.http.CachePolicy;
import cn.figo.mydemo.http.MyRetrofitCallBack;
import cn.figo.mydemo.http.RetrofitClientManager;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.viewholder.NetworkImageViewHolder;

/**
 * User: Ligx
 * Date: 2016-03-08
 * Time: 13:22
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    LiveIndexAdapter liveIndexAdapter;
    ConvenientBanner convenientBanner;

    List<LiveIndexBean.DataEntity.BannerEntity> bannerBeans = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_live;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        liveIndexAdapter = new LiveIndexAdapter(mContext);

        recyclerView.setAdapter(liveIndexAdapter);

        recyclerView.setEmptyView(R.layout.view_empty);

        liveIndexAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                convenientBanner = new ConvenientBanner(mContext);
                convenientBanner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DensityUtils.dip2px(mContext, 128)));
                return convenientBanner;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setRefreshing(true);
            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLiveIndex();
            }
        });

        liveIndexAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        liveIndexAdapter.stopMore();
                    }
                },3000);
            }
        });
        liveIndexAdapter.setNoMore(R.layout.view_nomore);

        liveIndexAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveIndexAdapter.resumeMore();
            }
        });

        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, -20, 100);

        getLiveIndex();
    }

    public void getLiveIndex(){
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.updateLiveIndex(),new MyRetrofitCallBack<LiveIndexBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR){
            @Override
            public void onDo(LiveIndexBean response) {
                super.onDo(response);

                liveIndexAdapter.clear();

                response.getData().getEntranceIcons().add(new LiveIndexBean.DataEntity.EntranceIconsEntity(-1,"全部直播",null));

                liveIndexAdapter.add(response.getData().getEntranceIcons());
                liveIndexAdapter.add(response.getData().getPartitions());


                bannerBeans.clear();
                bannerBeans.addAll(response.getData().getBanner());
                try {
                    convenientBanner.setPages(new CBViewHolderCreator<NetworkImageViewHolder>() {
                        @Override
                        public NetworkImageViewHolder createHolder() {
                            return new NetworkImageViewHolder<LiveIndexBean.DataEntity.BannerEntity>() {
                                @Override
                                public void UpdateUI(Context context, int position, final LiveIndexBean.DataEntity.BannerEntity data) {
                                    Glide.with(mContext).load(data.getImg()).into(imageView);
                                }
                            };
                        }
                    }, bannerBeans).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                }catch (Exception e){e.printStackTrace();}

                convenientBanner.startTurning(5000);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setRefreshing(false);
                    }
                });
            }
        });
    }
}