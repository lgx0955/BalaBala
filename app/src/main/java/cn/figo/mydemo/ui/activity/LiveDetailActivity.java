package cn.figo.mydemo.ui.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import cn.figo.mydemo.R;
import cn.figo.mydemo.base.baseactivity.BaseActivity;
import cn.figo.mydemo.bean.LiveVideoSourceBean;
import cn.figo.mydemo.danmaku.BiliDanmakuClient;
import cn.figo.mydemo.danmaku.DanmakuBean;
import cn.figo.mydemo.danmaku.IIncomingDanmakuCallback;
import cn.figo.mydemo.http.CachePolicy;
import cn.figo.mydemo.http.MyRetrofitCallBack;
import cn.figo.mydemo.http.RetrofitClientManager;
import cn.figo.mydemo.utils.DensityUtils;
import cn.figo.mydemo.widget.media.AndroidMediaController;
import cn.figo.mydemo.widget.media.IjkVideoView;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Danmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.danmaku.util.IOUtils;
import master.flame.danmaku.ui.widget.DanmakuView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/**
 * User: Ligx
 * Date: 2016-03-09
 * Time: 01:34
 * Copyright (c) 2015年 广州火鹰信息科技有限公司. All rights reserved.
 */
public class LiveDetailActivity extends BaseActivity {

    public static String EXTRA_ROOMID = "extra_roomID";
    private String roomID;


    @Bind(R.id.video_view)
    IjkVideoView videoView;
    IDanmakuView svDanmaku;

    private AndroidMediaController mMediaController;
    private BiliDanmakuClient danmakuClient;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_live_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        roomID = getIntent().getStringExtra(EXTRA_ROOMID);
        svDanmaku = (IDanmakuView) findViewById(R.id.sv_danmaku);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        showLoadingDialog();
        getLiveVideoSource(roomID);

        danmakuContext = DanmakuContext.create();
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示3行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);

        mMediaController = new AndroidMediaController(this, false);
        videoView.setMediaController(mMediaController);

        if (svDanmaku != null) {
            mParser = createParser(getResources().openRawResource(R.raw.comments));
            svDanmaku.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
//                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    svDanmaku.start();
//                    addDanmaku(true);
                }
            });
            svDanmaku.prepare(mParser, danmakuContext);
            svDanmaku.showFPS(false);
        }
    }


    public void getLiveVideoSource(String roomID) {
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getLiveVideoSource(roomID), new MyRetrofitCallBack<LiveVideoSourceBean>(mContext, CachePolicy.POLICY_ON_NET_ERROR) {
            @Override
            public void onDo(LiveVideoSourceBean response) {
                super.onDo(response);
//                videoView.toggleAspectRatio();
                videoView.setVideoPath(response.getData());
                videoView.start();

                initDanmaku();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                hideLoadingDialog();
            }
        });
    }

    private void initDanmaku() {
        svDanmaku.showFPS(true);
        danmakuClient = new BiliDanmakuClient(Integer.parseInt(roomID), new IIncomingDanmakuCallback() {
            @Override
            public void incomingDanmaku(String danmaku) {
                addDanmaku(danmaku);
            }

            @Override
            public void incomingDanmakus(List<BaseDanmaku> danmakus) {
//                dsv_danmaku.addDanmakus(danmakus);
            }
        });

        danmakuClient.start();
    }
    DanmakuContext danmakuContext;
    BaseDanmakuParser mParser;

    private void addDanmaku(String s) {
        //{"info":[[0,1,25,16777215,1457530865,"1457530846",0,"c5c472fa",0],"B克拉收好",[5771796,"尛鑫鑫",0,0,0],[1,"姬控","请叫我毒姬",44592,9953448],[4,958754],[]],"cmd":"DANMU_MSG"}

        DanmakuBean danmakuBean=null;
        JSONArray style = null;
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getString("cmd").equals("DANMU_MSG")){
                JSONArray jsonArray = jsonObject.getJSONArray("info");
                danmakuBean = new DanmakuBean();
                style = jsonArray.getJSONArray(0);
                danmakuBean.setMsg(jsonArray.get(1).toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            danmakuBean=null;
        }

        if (danmakuBean!=null) {
            BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL, danmakuContext);
            if (danmaku == null || svDanmaku == null) {
                return;
            }
            try {
                if (style!=null){
                    long time = (long) (Float.parseFloat(style.getString(0)) * 1000); // 出现时间
                    int type = Integer.parseInt(style.getString(1)); // 弹幕类型
                    float textSize = Float.parseFloat(style.getString(2)); // 字体大小
                    int color = Integer.parseInt(style.getString(3)) | 0xFF000000; // 颜色
                    danmaku.time = svDanmaku.getCurrentTime() + 1000;
                    danmaku.isLive = true;
                    danmaku.textSize = textSize * (danmakuContext.getDisplayer().getDensity() - 0.6f);
                    danmaku.textColor = color;
                    danmaku.textShadowColor = color <= Color.BLACK ? Color.WHITE : Color.BLACK;
                    danmaku.text = danmakuBean.getMsg();
                    svDanmaku.addDanmaku(danmaku);
                }
            }catch (Exception e){
                e.printStackTrace();
            };
//            danmaku.padding = 5;
//            danmaku.priority = 100;  // 可能会被各种过滤器过滤并隐藏显示
//            danmaku.isLive = false;
//            danmaku.time = svDanmaku.getCurrentTime() + 1000;
//            danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
//            danmaku.textColor = Color.RED;
//            danmaku.textShadowColor = Color.WHITE;
//            danmaku.underlineColor = Color.GREEN;
//            danmaku.borderColor = Color.GREEN;

//            0,1,25,16777215,1457530865,"1457530846",0,"c5c472fa",0
            //70.021003723145,1,25,0,1422268748,0,3e854d49,757731945
            //[0,1,25,16777215,1457664552,"1457656313",0,"b33c8d34",0]
//            [0,1,25,16777215,1457664845,"1457660175",0,"8fbd2bec",0]上方普通白色弹幕
//            [[0,1,25,16777215,1457665046,"1457664182",0,"b3f9217e",0]上方普通白色弹幕
//            [0,1,25,6737151,1457665060,"1457664985",0,"f0ec7c01",0]浅蓝色
//            0,1,25,16777215,1457665146,-520139034,0,"6da491ae",0 下划线

        }
    }
    private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {

        private Drawable mDrawable;

        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                new Thread() {

                    @Override
                    public void run() {
                        String url = "http://www.bilibili.com/favicon.ico";
                        InputStream inputStream = null;
                        Drawable drawable = mDrawable;
                        if(drawable == null) {
                            try {
                                URLConnection urlConnection = new URL(url).openConnection();
                                inputStream = urlConnection.getInputStream();
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
                                mDrawable = drawable;
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(inputStream);
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100);
                            SpannableStringBuilder spannable = createSpannable(drawable);
                            danmaku.text = spannable;
                            if(svDanmaku != null) {
                                svDanmaku.invalidateDanmaku(danmaku, false);
                            }
                            return;
                        }
                    }
                }.start();
            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    };
    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }

    private boolean mBackPressed;
    @Override
    protected void onStop() {
        super.onStop();
        if (mBackPressed || !videoView.isBackgroundPlayEnabled()) {
            videoView.stopPlayback();
            videoView.release(true);
            videoView.stopBackgroundPlay();
        } else {
            videoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();

        danmakuClient.stop();
    }
    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }
}