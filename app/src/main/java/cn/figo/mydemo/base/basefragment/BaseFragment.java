package cn.figo.mydemo.base.basefragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

import cn.figo.mydemo.R;
import cn.figo.mydemo.utils.SharePreferencesProvider;
import cn.figo.mydemo.utils.ToastHelper;


/**
 * User: Ligx
 * Date: 2015-05-04
 * Time: 15:11
 * Copyright (c)Ligx All rights reserved.
 */
public abstract class BaseFragment extends BaseLazyFragment {

    /**
     * 转跳 没有finish 没有切换效果
     * @param classObj
     */
    public void toActivity(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivity(intent);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     */
    public void overlay(Class<?> classObj) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param params
     */
    public void overlay(Class<?> classObj, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param requestCode
     */
    public void overlayForResult(Class<?> classObj, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
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
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
    }

    /**
     * 转跳 没有finish
     * @param classObj
     * @param requestCode
     * @param params
     */
    public void overlayLeftForResult(Class<?> classObj, int requestCode, Bundle params) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_left_in,R.anim.push_right_out);
    }

    /**
     * 转跳 有finish
     * @param classObj
     */
    public void forward(Class<?> classObj) {
        Intent intent = new Intent();
        intent.setClass(mActivity, classObj);
        this.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
        mActivity.finish();
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
        intent.setClass(mActivity, classObj);
        intent.putExtras(params);
        this.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.push_right_in,R.anim.push_left_out);
        mActivity.finish();
    }

    public final String TAG = this.getClass().getSimpleName();

    Context sharedContext = null;
    private SharedPreferences preferences = null;
    protected void saveP(String key, String value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected void saveP(String key, Boolean value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    protected void saveP(String key, long value) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    protected boolean loadP(String key, Boolean dvalue) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        return preferences.getBoolean(key, dvalue);
    }

    protected String loadP(String key) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        return preferences.getString(key, null);
    }
    protected void clearP(String key) {
        SharedPreferences preferences = SharePreferencesProvider.getMyPreferences(mActivity);
        preferences.edit().remove(key).commit();
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////
    // common classes
    protected boolean isHard = false;
    public ProgressWheel wheel = null;
    private Dialog loadingDialog = null;

    /**
     * 收起waiting dialog
     */
    public void hideLoadBar() {
        if(wheel != null)
            wheel.stopSpinning();
        if(loadingDialog != null)
            loadingDialog.dismiss();
    }

    public void toast(String msg) {
        ToastHelper.showToast(msg, getActivity());
    }

    protected void toast(int msg_id) {
        ToastHelper.showToast(getString(msg_id), getActivity());
    }

    /**
     * 查找view
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
