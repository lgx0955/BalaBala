package cn.figo.mydemo.adapter;

/**
 * User: Ligx
 * Date: 2015-08-18
 * Time: 11:06
 * Copyright (c)Ligx All rights reserved.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

/**
 * @author Jack Tony
 * @date 2015/5/17
 */
public abstract class CommonRcvAdapter<T extends AdapterModel> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //瀑布流最后1项铺满
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == getItemCount()-1) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 内部接口回调方法
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position, Object object);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position, Object object);
    }

    protected final String TAG = getClass().getSimpleName();
    private List<T> mData;
    private int mPosition;
    private OnItemClickListener listener;
    private OnItemLongClickListener listener4long;

    protected CommonRcvAdapter(List<T> data) {
        mData = data;
    }

    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new RcvAdapterItem(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RcvAdapterItem(parent.getContext(), initItemView(mData.get(mPosition).getDataType()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RcvAdapterItem) holder).setViews(getItemByType(mData.get(position).getDataType()), mData.get(position), position);
    }

    protected abstract AdapterItem<T> initItemView(Object type);

    // (type - item) = (key - value)
    private HashMap<Object, AdapterItem<T>> mItemMap = new HashMap<>();

    /**
     * 根据相应的类型得到item对象
     *
     * @param type item的类型
     */
    private AdapterItem<T> getItemByType(Object type) {
        AdapterItem<T> item = mItemMap.get(type);
        if (item == null) {
            item = initItemView(type);
            mItemMap.put(type, item);
        }
        return item;
    }

    private SparseArray<Object> mItemTypeSparseArr = new SparseArray<>();

    private int getRealType(Object type) {
        int realType = mItemTypeSparseArr.indexOfValue(type);
        if (realType == -1) {
            mItemTypeSparseArr.put(mItemTypeSparseArr.size() - 1, type);
            realType = mItemTypeSparseArr.size() - 1;
        }
        return realType;
    }


    /**
     * 可以被复写用于单条刷新等
     */
    public void updateData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    private class RcvAdapterItem extends RecyclerView.ViewHolder {

        public RcvAdapterItem(Context context, AdapterItem item, ViewGroup root) {
            super(LayoutInflater.from(context).inflate(item.getLayoutResId(), root, false));
        }

        public RcvAdapterItem(View view) {
            super(view);
        }
        /**
         * 设置Item内部view的方法
         *
         * @param model     数据对象
         * @param position 当前item的position
         */
        public void setViews(AdapterItem<T> item, final T model, final int position) {
            try {
                if (listener != null) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onItemClick(v, position, model);
                        }
                    });
                }
                if (listener4long != null) {
                    itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            listener4long.onItemLongClick(view, position, model);
                            return false;
                        }
                    });
                }
                item.initViews(ViewHolder.getInstance(itemView), model, position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.listener4long = listener;
    }

    public void insert(T bean, int position) {
        insert(mData, bean, position);
    }

    public void remove(int position) {
        remove(mData, position);
    }

    public void clear() {
        clear(mData);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * instead by getData().get(position).getDataType()
     */
    @Deprecated
    @Override
    public int getItemViewType(int position) {
        return getRealType(mData.get(position).getDataType());
    }

    /**
     * Insert a item to the list of the adapter
     *
     * @param list     data list
     * @param object   object T
     * @param position position
     * @param <T>      in T
     */
    public <T> void insert(List<T> list, T object, int position) {
        list.add(position, object);
        notifyItemInserted(position);
    }

    /**
     * Remove a item of  the list of the adapter
     *
     * @param list     data list
     * @param position position
     */
    public void remove(List<?> list, int position) {
        if (list.size() > 0) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * Clear the list of the adapter
     *
     * @param list data list
     */
    public void clear(List<?> list) {
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }
}