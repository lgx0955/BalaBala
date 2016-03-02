package cn.figo.mydemo.utils;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gccd on 2014/7/23.
 */
public class MyViewHolder {
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();

        if (viewHolder == null) {

            viewHolder = new SparseArray<View>();

            view.setTag(viewHolder);

        }

        View childView = viewHolder.get(id);

        if (childView == null) {

            childView = view.findViewById(id);

            viewHolder.put(id, childView);

        }
        return (T) childView;

    }


    public static void setText(View view, int id, String text) {
        TextView tv = get(view, id);
        if (tv != null) {
            tv.setText(text);
        }
    }
}