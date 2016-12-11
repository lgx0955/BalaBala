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
import cn.figo.mydemo.adapter.IndexAdapter;
import cn.figo.mydemo.base.basefragment.BaseFragment;
import cn.figo.mydemo.bean.RecommendBannerBean;
import cn.figo.mydemo.utils.DensityUtils;

public class RecommendFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView recyclerView;

    IndexAdapter indexAdapter;
    ConvenientBanner convenientBanner;

    List<RecommendBannerBean.DataEntity> bannerBeans = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        indexAdapter = new IndexAdapter(mContext);

        recyclerView.setAdapter(indexAdapter);

        recyclerView.setEmptyView(R.layout.view_empty);

        indexAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
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
                getRecommend();
                getRecommendBanner();
            }
        });

        indexAdapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        indexAdapter.stopMore();
                    }
                },3000);
            }
        });
        indexAdapter.setNoMore(R.layout.view_nomore);

//        indexAdapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                indexAdapter.resumeMore();
//            }
//        });

        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, -20, 100);

        getRecommend();
        getRecommendBanner();
    }

    public void getRecommend(){
//        RetrofitClientManager.getAsyn(RetrofitClientManager.api.updateRecommend(),new MyRetrofitCallBack<RecommendBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR){
//            @Override
//            public void onDo(RecommendBean response) {
//                super.onDo(response);
//                indexAdapter.clear();
//                indexAdapter.addAll(response.getResult());
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
