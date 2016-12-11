package cn.figo.mydemo.ui.fragment;

import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.adapter.BangumiIndexAdapter;
import cn.figo.mydemo.base.basefragment.BaseFragment;
import cn.figo.mydemo.bean.BanguminIndexHeaderBean;
import cn.figo.mydemo.bean.RecommendBannerBean;
import cn.figo.mydemo.utils.DensityUtils;

/**
 * User: Ligx
 * Date: 2016-03-01
 * Time: 16:39
 * Copyright (c)Ligx All rights reserved.
 */
public class BangumiFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    BangumiIndexAdapter bangumiIndexAdapter;
    ConvenientBanner convenientBanner;

    List<RecommendBannerBean.DataEntity> bannerBeans = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_bangumi;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        bangumiIndexAdapter = new BangumiIndexAdapter(mContext);

        recyclerView.setAdapter(bangumiIndexAdapter);

        recyclerView.setEmptyView(R.layout.view_empty);

        bangumiIndexAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
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
                getBangumiHeader();
            }
        });

        bangumiIndexAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bangumiIndexAdapter.stopMore();
                    }
                },3000);
            }
        });
        bangumiIndexAdapter.setNoMore(R.layout.view_nomore);

        bangumiIndexAdapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {

            }

            @Override
            public void onErrorClick() {
                bangumiIndexAdapter.resumeMore();
            }
        });

        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, -20, 100);

//        getBangumi();
        getRecommendBanner();
        getBangumiHeader();
    }

    public void getBangumi(final BanguminIndexHeaderBean banguminIndexHeaderBean){
//        RetrofitClientManager.getAsyn(RetrofitClientManager.api.updateBangumi(),new MyRetrofitCallBack<BangumiIndexBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR){
//            @Override
//            public void onDo(BangumiIndexBean response) {
//                super.onDo(response);
//                bangumiIndexAdapter.clear();
//
////                bangumiIndexAdapter.add(banguminIndexHeaderBean.getResult().getLatestUpdate());
////                bangumiIndexAdapter.add(banguminIndexHeaderBean.getResult().getRecommendCategory());
////                bangumiIndexAdapter.add(banguminIndexHeaderBean.getResult().getCategories());
//
//                bangumiIndexAdapter.addAll(response.getList());
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setRefreshing(false);
//                    }
//                });
//            }
//        });
    }

    public void getBangumiHeader(){
//        RetrofitClientManager.getAsyn(RetrofitClientManager.api.updateBangumiIndexHeader(),new MyRetrofitCallBack<BanguminIndexHeaderBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR){
//            @Override
//            public void onDo(BanguminIndexHeaderBean response) {
//                super.onDo(response);
//                getBangumi(response);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setRefreshing(false);
//                    }
//                });
//            }
//        });
    }

    public void getRecommendBanner(){
//        RetrofitClientManager.getAsyn(RetrofitClientManager.api.updateRecommendBanner(),new MyRetrofitCallBack<RecommendBannerBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR){
//            @Override
//            public void onDo(RecommendBannerBean response) {
//                super.onDo(response);
//                bannerBeans.clear();
//                bannerBeans.addAll(response.getData());
//                try {
//                    convenientBanner.setPages(new CBViewHolderCreator<NetworkImageViewHolder>() {
//                        @Override
//                        public NetworkImageViewHolder createHolder() {
//                            return new NetworkImageViewHolder<RecommendBannerBean.DataEntity>() {
//                                @Override
//                                public void UpdateUI(Context context, int position, final RecommendBannerBean.DataEntity data) {
//                                    Glide.with(mContext).load(data.getImage()).into(imageView);
//                                }
//                            };
//                        }
//                    }, bannerBeans).setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
//                }catch (Exception e){e.printStackTrace();}
//
//                convenientBanner.startTurning(5000);
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                recyclerView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setRefreshing(false);
//                    }
//                });
//            }
//        });
    }
}