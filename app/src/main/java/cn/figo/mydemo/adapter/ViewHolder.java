package cn.figo.mydemo.adapter;

import android.util.SparseArray;
import android.view.View;

import cn.figo.mydemo.R;

/**
 * 比较规范独立的的ViewHolder.
 *
 * @author Jack Tony
 * @date 2015/4/28
 * @see "http://www.cnblogs.com/tianzhijiexian/p/4157889.html"
 */
public class ViewHolder {

    private SparseArray<View> viewHolderSparseArr;

    private View mView;

    private ViewHolder(View view) {
        mView = view;
        viewHolderSparseArr = (SparseArray<View>) mView.getTag(R.id.tag_SparseArray);
        if (viewHolderSparseArr == null) {
            viewHolderSparseArr = new SparseArray<>();
            mView.setTag(R.id.tag_SparseArray, viewHolderSparseArr);
        }
    }

    public static ViewHolder getInstance(View view) {
        ViewHolder instance = (ViewHolder) view.getTag(R.id.tag_viewHolder);
        if (instance == null) {
            instance = new ViewHolder(view);
            view.setTag(R.id.tag_viewHolder, instance);
        }
        return instance;
    }

    public <T extends View> T getView(int id) {
        View childView = viewHolderSparseArr.get(id);
        if (childView == null) {
            childView = mView.findViewById(id);
            viewHolderSparseArr.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return mView;
    }
}