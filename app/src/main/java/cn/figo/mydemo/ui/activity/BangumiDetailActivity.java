package cn.figo.mydemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.base.baseactivity.BaseHeadActivity;
import cn.figo.mydemo.bean.AidBean;
import cn.figo.mydemo.bean.BangumiDetailBean;
import cn.figo.mydemo.http.CachePolicy;
import cn.figo.mydemo.http.MyRetrofitCallBack;
import cn.figo.mydemo.http.RetrofitClientManager;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.utils.FlowLayout;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class BangumiDetailActivity extends BaseHeadActivity {

    public static String BANGUMI_SPID_EXTRA = "bangumi_spid_extra";
    public static String sp_id;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.flowlayout)
    FlowLayout flowLayout;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bangumi_detail;
    }

    @Override
    protected void init() {
        sp_id = getIntent().getStringExtra(BANGUMI_SPID_EXTRA);

        getBangumiDetail(sp_id);

        setIvLeftOpt(R.drawable.ic_arrow_back_black);
        setLeftOptListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getBangumiDetail(String sp_id) {
        RetrofitClientManager.getSignAsyn(RetrofitClientManager.api.getBangumiDetailBySpId(sp_id, System.currentTimeMillis() + "", "sp"), new MyRetrofitCallBack<BangumiDetailBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR) {
            @Override
            public void onDo(BangumiDetailBean response) {
                super.onDo(response);
                Glide.with(mContext).load(response.getResult().getCover())
                        .override(DensityUtils.getDisplayWidth(mContext),DensityUtils.dip2px(mContext,300))
                        .bitmapTransform(new CropTransformation(mContext,DensityUtils.getDisplayWidth(mContext),DensityUtils.dip2px(mContext,200), CropTransformation.CropType.TOP),new BlurTransformation(mContext,25))
                        .into(image);

                Glide.with(mContext).load(response.getResult().getCover()).into(ivCover);

                collapsingToolbar.setTitle(response.getResult().getBangumi_title());

                flowLayout.removeAllViews();
                for (final BangumiDetailBean.ResultEntity.EpisodesEntity episodesEntity:response.getResult().getEpisodes()){
                    View view = View.inflate(mContext,R.layout.item_bangumidetail_episodes_card,null);
                    TextView episodes_title = (TextView) view.findViewById(R.id.episodes_title);
                    episodes_title.setText(episodesEntity.getIndex());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showLoadingDialog("loading..","解析视频弹幕==");
                            getBangumiVideoDetail(episodesEntity.getAv_id());
                        }
                    });
                    flowLayout.addView(view);
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    public void getBangumiVideoDetail(String aid){
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getVideoPath(aid, "1"), new Callback<AidBean>() {
            @Override
            public void onResponse(Response<AidBean> response, Retrofit retrofit) {
                hideLoadingDialog();
                Bundle bundle = new Bundle();
                bundle.putString(VideoActivity.EXTRA_AID,response.body().toString());
                overlay(VideoActivity.class,bundle);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
