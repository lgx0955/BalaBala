/*
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.figo.mydemo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.zip.DataFormatException;

import cn.figo.mydemo.R;
import cn.figo.mydemo.app.Settings;
import cn.figo.mydemo.bean.AidBean;
import cn.figo.mydemo.content.RecentMediaStorage;
import cn.figo.mydemo.http.RetrofitClientManager;
import cn.figo.mydemo.ui.fragment.TracksFragment;
import cn.figo.mydemo.widget.media.AndroidMediaController;
import cn.figo.mydemo.widget.media.IjkVideoView;
import cn.figo.mydemo.widget.media.MeasureHelper;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.danmaku.util.IOUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;


public class VideoActivity extends AppCompatActivity implements TracksFragment.ITrackHolder {
    private static final String TAG = "VideoActivity";

    public static final String EXTRA_URL = "videoPath";
    public static final String EXTRA_AID = "aidbean";
    AidBean aidBean;

    private String mVideoPath;
    private Uri    mVideoUri;

    private AndroidMediaController mMediaController;
    private IjkVideoView mVideoView;
    private TextView mToastTextView;
    private TableLayout mHudView;
    private DrawerLayout mDrawerLayout;
    private ViewGroup mRightDrawer;

    private Settings mSettings;
    private boolean mBackPressed;

    ImageView biliAnim;
    AnimationDrawable anim;

    String danmaku_path;

    public static Intent newIntent(Context context, String videoPath, String videoTitle) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("videoPath", videoPath);
        intent.putExtra("videoTitle", videoTitle);
        return intent;
    }

    public static void intentTo(Context context, String videoPath, String videoTitle) {
        context.startActivity(newIntent(context, videoPath, videoTitle));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mSettings = new Settings(this);
        biliAnim = (ImageView) findViewById(R.id.bili_anim);
        anim = (AnimationDrawable) biliAnim.getBackground();
        anim.start();
        try{
            aidBean = new Gson().fromJson(getIntent().getStringExtra(EXTRA_AID),AidBean.class);
        }catch (Exception e){e.printStackTrace();}

        if (aidBean!=null){
            mVideoPath = aidBean.getSrc();
            danmaku_path = aidBean.getCid();
        }

//      handle arguments
//        mVideoPath = getIntent().getStringExtra(EXTRA_URL);
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//            String scheme = mVideoUri.getScheme();
//            if (TextUtils.isEmpty(scheme)) {
//                Log.e(TAG, "Null unknown ccheme\n");
//                finish();
//                return;
//            }
//            if (scheme.equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
//                mVideoPath = mVideoUri.getPath();
//            } else if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
//                Log.e(TAG, "Can not resolve content below Android-ICS\n");
//                finish();
//                return;
//            } else {
//                Log.e(TAG, "Unknown scheme " + scheme + "\n");
//                finish();
//                return;
//            }
//        }

//        Intent intent = getIntent();
//        String intentAction = intent.getAction();
//        if (!TextUtils.isEmpty(intentAction)) {
//            if (intentAction.equals(Intent.ACTION_VIEW)) {
//                mVideoPath = intent.getDataString();
//            } else if (intentAction.equals(Intent.ACTION_SEND)) {
//                mVideoUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
//                    String scheme = mVideoUri.getScheme();
//                    if (TextUtils.isEmpty(scheme)) {
//                        Log.e(TAG, "Null unknown ccheme\n");
//                        finish();
//                        return;
//                    }
//                    if (scheme.equals(ContentResolver.SCHEME_ANDROID_RESOURCE)) {
//                        mVideoPath = mVideoUri.getPath();
//                    } else if (scheme.equals(ContentResolver.SCHEME_CONTENT)) {
//                        Log.e(TAG, "Can not resolve content below Android-ICS\n");
//                        finish();
//                        return;
//                    } else {
//                        Log.e(TAG, "Unknown scheme " + scheme + "\n");
//                        finish();
//                        return;
//                    }
//                }
//            }
//        }

        if (!TextUtils.isEmpty(mVideoPath)) {
            new RecentMediaStorage(this).saveUrlAsync(mVideoPath);
        }

        // init UI
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        mMediaController = new AndroidMediaController(this, false);
        mMediaController.setSupportActionBar(actionBar);

        mToastTextView = (TextView) findViewById(R.id.toast_text_view);
        mHudView = (TableLayout) findViewById(R.id.hud_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRightDrawer = (ViewGroup) findViewById(R.id.right_drawer);

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);

        new RecentMediaStorage(this).saveUrlAsync(mVideoPath);

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setHudView(mHudView);

        mVideoView.toggleAspectRatio();
        //用webview加载http://live.bilibili.com/148  从返回的html数据中通过正则找到直播地址
//        mVideoPath = "http://wshls.acgvideo.com/live/live_682508_3859786/playlist.m3u8";
//        mVideoPath = "http://hdl3a.douyutv.com/live/56040rC6zZixldgm.flv";
//        mVideoPath = "http://cn-gdfs12-dx.acgvideo.com/vg4/e/c8/5635675.mp4?expires=1457698500&ssig=2kGcD42pv29rg0_2QchdlQ&oi=1903027322&internal=1&rate=0";
//        3538470
        mVideoView.setVideoPath(mVideoPath);
//        if (mVideoPath != null)
//            mVideoView.setVideoPath(mVideoPath);
//        else if (mVideoUri != null)
//            mVideoView.setVideoURI(mVideoUri);
//        else {
//            Log.e(TAG, "Null Data Source\n");
//            finish();
//            return;
//        }
        initDanmaku();

    }
    public void initDanmaku() {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示3行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);

        if (mDanmakuView != null) {
            if (aidBean!=null)
                getVideoDanmakuPath(aidBean.getCid());
            else
                getVideoDanmaku("5635675");
        }


//        mVideoView.start();
//        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(IMediaPlayer mp) {
//
//            }
//        });
//        mVideoView.getmMediaPlayer().setOnBufferingUpdateListener(new IMediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
//                initDanmaku();
//            }
//        });
//        mMediaController.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mToastTextView.setText("Aaaa");
//                mToastTextView.setVisibility(View.VISIBLE);
//                mMediaController.show(10000);
//            }
//        },2000);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle_ratio) {
            int aspectRatio = mVideoView.toggleAspectRatio();
            String aspectRatioText = MeasureHelper.getAspectRatioText(this, aspectRatio);
            mToastTextView.setText(aspectRatioText);
            mMediaController.showOnce(mToastTextView);
            return true;
        } else if (id == R.id.action_toggle_player) {
            int player = mVideoView.togglePlayer();
            String playerText = IjkVideoView.getPlayerText(this, player);
            mToastTextView.setText(playerText);
            mMediaController.showOnce(mToastTextView);
            return true;
        } else if (id == R.id.action_toggle_render) {
            int render = mVideoView.toggleRender();
            String renderText = IjkVideoView.getRenderText(this, render);
            mToastTextView.setText(renderText);
            mMediaController.showOnce(mToastTextView);
            return true;
        } else if (id == R.id.action_show_info) {
            mVideoView.showMediaInfo();
        } else if (id == R.id.action_show_tracks) {
            if (mDrawerLayout.isDrawerOpen(mRightDrawer)) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.right_drawer);
                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.remove(f);
                    transaction.commit();
                }
                mDrawerLayout.closeDrawer(mRightDrawer);
            } else {
                Fragment f = TracksFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.right_drawer, f);
                transaction.commit();
                mDrawerLayout.openDrawer(mRightDrawer);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ITrackInfo[] getTrackInfo() {
        if (mVideoView == null)
            return null;

        return mVideoView.getTrackInfo();
    }

    @Override
    public void selectTrack(int stream) {
        mVideoView.selectTrack(stream);
    }

    @Override
    public void deselectTrack(int stream) {
        mVideoView.deselectTrack(stream);
    }

    @Override
    public int getSelectedTrack(int trackType) {
        if (mVideoView == null)
            return -1;

        return mVideoView.getSelectedTrack(trackType);
    }
    @Override
    public void onBackPressed() {
        mBackPressed = true;

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBackPressed || !mVideoView.isBackgroundPlayEnabled()) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }

    //Dannmaku
    private BaseDanmakuParser mParser;
    private DanmakuContext danmakuContext;
    private IDanmakuView mDanmakuView;
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
                            if(mDanmakuView != null) {
                                mDanmakuView.invalidateDanmaku(danmaku, false);
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

    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }


    public void getVideoDanmaku(String danmaku_id) {
        RetrofitClientManager.getAsyn(RetrofitClientManager.api.getDanmaku(danmaku_id), new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                InputStream stream = null;
                try {
                    stream = new ByteArrayInputStream(CompressionTools.decompressXML(response.body().bytes()));
                } catch (DataFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mParser = createParser(stream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                    @Override
                    public void updateTimer(DanmakuTimer timer) {
                    }

                    @Override
                    public void drawingFinished() {

                    }

                    @Override
                    public void danmakuShown(BaseDanmaku danmaku) {
                        Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                    }

                    @Override
                    public void prepared() {
                        mVideoView.start();
                        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(IMediaPlayer mp) {
                                mDanmakuView.start();
                                findViewById(R.id.video_start).setVisibility(View.GONE);
                            }
                        });
                    }
                });
                mDanmakuView.prepare(mParser, danmakuContext);
                mDanmakuView.showFPS(false);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getVideoDanmakuPath(String danmaku_path) {
        Request request = new Request.Builder()
                .url(danmaku_path)
                .get()
                .build();
        RetrofitClientManager.httpClient.newCall(request).enqueue(new com.squareup.okhttp.Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(com.squareup.okhttp.Response response) throws IOException {
                InputStream stream = null;
                try {
                    stream = new ByteArrayInputStream(CompressionTools.decompressXML(response.body().bytes()));
                } catch (DataFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mParser = createParser(stream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                    @Override
                    public void updateTimer(DanmakuTimer timer) {
                    }

                    @Override
                    public void drawingFinished() {

                    }

                    @Override
                    public void danmakuShown(BaseDanmaku danmaku) {
                        Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                    }

                    @Override
                    public void prepared() {
                        mVideoView.start();
                        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(IMediaPlayer mp) {
                                mDanmakuView.start();
                                findViewById(R.id.video_start).setVisibility(View.GONE);
                            }
                        });
                    }
                });
                mDanmakuView.prepare(mParser, danmakuContext);
                mDanmakuView.showFPS(false);


                new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mVideoView.getmMediaPlayer().setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
                            @Override
                            public void onSeekComplete(IMediaPlayer mp) {

                                mDanmakuView.seekTo(mp.getCurrentPosition());
                            }
                        });
                    }
                },5000);
            }
        });
    }
}