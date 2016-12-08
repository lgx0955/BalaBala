package cn.figo.mydemo.base.baseactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.androidquery.AQuery;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import cn.figo.mydemo.base.BaseAppManager;


/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/3/9.
 * Description:
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    public AQuery aq;
    public Gson gson = new Gson();
    /**
     * Log tag
     */
    protected static String TAG_LOG = null;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * context
     */
    protected Context mContext = null;


    /**
     * 查找view
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        TAG_LOG = this.getClass().getSimpleName();
        BaseAppManager.getInstance().addActivity(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        if (getLayoutRes() != 0) {
            setContentView(getLayoutRes());
            aq = new AQuery(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        try {
            ButterKnife.bind(this);
        }catch (Exception e){e.printStackTrace();}


        init();
    }

    @Override
    public void finish() {
        super.finish();
        BaseAppManager.getInstance().removeActivity(this);
    }

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getLayoutRes();


    /**
     * init all views and add events
     */
    protected abstract void init();

}