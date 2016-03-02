package cn.figo.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import cn.figo.mydemo.bean.CategoryBean;
import cn.figo.mydemo.viewholder.CategoryViewHolder;

/**
 * User: Ligx
 * Date: 2016-02-29
 * Time: 11:42
 * Copyright (c)Ligx All rights reserved.
 */
public class CategoryAdapter extends RecyclerArrayAdapter<CategoryBean> {

    public CategoryAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(parent);
    }
}