/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.figo.mydemo.base.basefragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.google.gson.Gson;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Author:  Tau.Chen
 * Email:   1076559197@qq.com | tauchen1990@gmail.com
 * Date:    2015/4/13.
 * Description:
 */
public abstract class BaseLazyFragment extends Fragment {

    /**
     * Log tag
     */
    protected static String TAG_LOG = null;
    protected Context mContext;
    protected Activity mActivity;
    public Gson gson = new Gson();
    public AQuery aq;
    private int mTaskId;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;


    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG_LOG = this.getClass().getSimpleName();
//        AppService.getInstance().getBus().register(this);
        mTaskId=getActivity().getTaskId();
    }


    View parent;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutRes() != 0) {
            this.parent = inflater.inflate(getLayoutRes(), container, false);
            this.inflater = inflater;

            aq = new AQuery(parent);
            try {
                ButterKnife.bind(this,parent);
            }catch (Exception e){e.printStackTrace();}

            init();
            return parent;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

//        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        AppService.getInstance().getBus().unregister(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected void onFirstUserVisible(){};

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected void onUserVisible(){};

    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected void onUserInvisible(){};

    /**
     * init all views and add events
     */
    protected abstract void init();

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getLayoutRes();

    /**
     * get the support fragment manager
     *
     * @return
     */
    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }


    protected View findViewById(int id){
        return parent.findViewById(id);
    }

    /**
     * 查找view
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

}
