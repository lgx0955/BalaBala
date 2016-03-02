package cn.figo.mydemo.base.baseactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import cn.figo.mydemo.R;
import cn.figo.mydemo.utils.DeviceUtil;
import cn.figo.mydemo.utils.ToastHelper;


/**
 * User: Ligx
 * Date: 2015-04-20
 * Time: 16:12
 * Copyright (c)Ligx All rights reserved.
 */
public abstract class BaseActivity extends BaseAppCompatActivity {
    public final String TAG = this.getClass().getSimpleName();

    Context sharedContext = null;
    private SharedPreferences preferences = null;
    public SharedPreferences getMyPreferences() {
        // TODO Auto-generated method stub
        if (preferences != null)
            return preferences;
        try {
            sharedContext = createPackageContext(DeviceUtil.getPackageName(this), Context.CONTEXT_IGNORE_SECURITY);
            preferences = sharedContext.getSharedPreferences("config", Context.MODE_PRIVATE);
        } catch (PackageManager.NameNotFoundException e) {
//            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return preferences;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }

    protected void saveP(String key, String value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    protected void saveP(String key, Long value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    protected void saveP(String key, Boolean value) {
        SharedPreferences preferences = getMyPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected boolean loadP(String key, Boolean dvalue) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getBoolean(key, dvalue);
    }
    protected long loadP(String key, Long dvalue) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getLong(key, dvalue);
    }

    protected String loadP(String key) {
        SharedPreferences preferences = getMyPreferences();
        return preferences.getString(key, null);
    }
    protected void clearP(String key) {
        SharedPreferences preferences = getMyPreferences();
        preferences.edit().remove(key).commit();
    }

    public void toast(String msg) {
        ToastHelper.showToast(msg, getApplicationContext());
    }

    protected void toast(int msg_id) {
        ToastHelper.showToast(getString(msg_id), this);
    }

    protected void showSnackBar(String content) {
        ViewGroup view = (ViewGroup)getWindow().getDecorView();
        Snackbar.make(view.getChildAt(0), content, Snackbar.LENGTH_LONG)
                .setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "Toast comes out", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    /**
     * 转跳 没有finish 没有切换效果
     * @param classObj
     */
    public void toActivity(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivity(intent);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     */
    public void overlay(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param params
     */
    public void overlay(Class<?> classObj, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayLeftForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param requestCode
     */
    public void overlayForResult(Class<?> classObj, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }
    // //////////////////////////////////////////////////////////////////////////////////////////////
    // logic method

    /**
     * 转跳 没有finish
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(this, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 有finish
     * @param classObj
     */
    public void forward(Class<?> classObj) {
        Intent intent = new Intent();
        intent.setClass(this, classObj);
        this.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
        this.finish();
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////
    // debug method

    /**
     * 转跳 有finish
     * @param classObj
     * @param params
     */
    public void forward(Class<?> classObj, Bundle params) {
        Intent intent = new Intent();
        intent.setClass(this, classObj);
        intent.putExtras(params);
        this.startActivity(intent);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
        this.finish();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        onFinish();
//        scrollToFinishActivity();
    }

    public void onFinish(){
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }



    // //////////////////////////////////////////////////////////////////////////////////////////////
    // common classes
    public MaterialDialog materialDialog;

    public void showLoadingDialog(){
        if (materialDialog==null) {
            materialDialog = new MaterialDialog.Builder(this)
                    .title(R.string.progress_dialog)
                    .content(R.string.please_wait)
                    .progress(true, 0)
                    .progressIndeterminateStyle(false).build();
            materialDialog.show();
        }else{
            materialDialog.show();
        }
    }
    public void showLoadingDialog(String title,String content){
        if (materialDialog != null) {
            materialDialog.dismiss();
        }
        materialDialog = new MaterialDialog.Builder(mContext)
                .title(title)
                .content(content)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
    }

    public void hideLoadingDialog(){
        if (materialDialog!=null) {
            materialDialog.dismiss();
        }
    }
}
