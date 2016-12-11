package cn.figo.mydemo.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.base.baseactivity.BaseHeadActivity;
import cn.figo.mydemo.utils.FlowLayout;

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
        getBangumiVideoDetail("");
//        setIvLeftOpt(R.drawable.ic_arrow_back_black);
//        setLeftOptListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    public void getBangumiDetail(String sp_id) {
//        RetrofitClientManager.getSignAsyn(RetrofitClientManager.api.getBangumiDetailBySpId(sp_id, System.currentTimeMillis() + "", "sp"), new MyRetrofitCallBack<BangumiDetailBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR) {
//            @Override
//            public void onDo(BangumiDetailBean response) {
//                super.onDo(response);
//                Glide.with(mContext).load(response.getResult().getCover())
//                        .override(DensityUtils.getDisplayWidth(mContext),DensityUtils.dip2px(mContext,300))
//                        .bitmapTransform(new CropTransformation(mContext,DensityUtils.getDisplayWidth(mContext),DensityUtils.dip2px(mContext,200), CropTransformation.CropType.TOP),new BlurTransformation(mContext,25))
//                        .into(image);
//
//                Glide.with(mContext).load(response.getResult().getCover()).into(ivCover);
//
//                collapsingToolbar.setTitle(response.getResult().getBangumi_title());
//
//                flowLayout.removeAllViews();
//                for (final BangumiDetailBean.ResultEntity.EpisodesEntity episodesEntity:response.getResult().getEpisodes()){
//                    View view = View.inflate(mContext,R.layout.item_bangumidetail_episodes_card,null);
//                    TextView episodes_title = (TextView) view.findViewById(R.id.episodes_title);
//                    episodes_title.setText(episodesEntity.getIndex());
//                    view.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            showLoadingDialog("loading..","解析视频弹幕==");
//                            getBangumiVideoDetail(episodesEntity.getAv_id());
//                        }
//                    });
//                    flowLayout.addView(view);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//            }
//        });
    }
String cid = "";
    public void getBangumiVideoDetail(final String aid){
//        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getVideoPath(aid, "1"), new Callback<AidBean>() {
//            @Override
//            public void onResponse(Response<AidBean> response, Retrofit retrofit) {
//                hideLoadingDialog();
//                Bundle bundle = new Bundle();
////                response.body().toString()+
//                bundle.putString(VideoActivity.EXTRA_AID,aid);
//                overlay(VideoActivity.class,bundle);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//            }
//        });
//        cid = episodesEntity.get();





//        System.out.println("======ttttt");
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://bangumi.bilibili.com")
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .build();
//        retrofit.create(BangumiPlayUrlService.class).getPlayUrl().enqueue(new retrofit2.Callback<AvBean>() {
//            @Override
//            public void onResponse(Call<AvBean> call, retrofit2.Response<AvBean> response) {
////                AvBean.VideoEntity videoEntity = response.body().getVideo();
////                .getDurl().get(0).getBackup_url().getUrl().size()
//                AvBean avBean = response.body();
//                System.out.println("======++++"+avBean.toString());
//
//                hideLoadingDialog();
//                Bundle bundle = new Bundle();
////                response.body().toString()+
//
//                bundle.putString("danmaku_path","http://comment.bilibili.com/"+cid+".xml");
//                bundle.putString("urlpath","concat://:http://cn-gdgz3-cmcc-v-01.acgvideo.com/vg5/0/ed/11472663-1.flv?expires=1480376400&ssig=r66nHhzi-ecpPrSdogKEYw&oi=3085493208&rate=236900&dynamic=1"
//                                                +"|http://cn-gdgz3-cmcc-v-01.acgvideo.com/vg5/0/ed/11472663-2.flv?expires=1480376400&ssig=PDy-op_I5U5WxiouiwIo4g&oi=3085493208&rate=236900&dynamic=1"
//                                                +"|http://cn-gdgz3-cmcc-v-01.acgvideo.com/vg5/0/ed/11472663-3.flv?expires=1480376400&ssig=EEvZ38Qpf7MBcsTqMHLNhA&oi=3085493208&rate=236900&dynamic=1"
//                                                +"|http://cn-gdgz3-cmcc-v-01.acgvideo.com/vg5/0/ed/11472663-4.flv?expires=1480376400&ssig=N44Rweh8gGgAuDLjA4mFzA&oi=3085493208&rate=236900&dynamic=1");
//                overlay(VideoActivity.class,bundle);
//            }
//
//            @Override
//            public void onFailure(Call<AvBean> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }
}
