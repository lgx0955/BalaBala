package cn.figo.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/1/14 17:54.
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean canScroll;

    public void setCanScroll(boolean isCan) {
        canScroll = isCan;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (canScroll) {
            getParent().requestDisallowInterceptTouchEvent(true);

        }
        return super.dispatchTouchEvent(ev);
    }
}
