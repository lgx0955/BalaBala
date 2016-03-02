package cn.figo.mydemo.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public abstract class NetworkImageViewHolder<T> implements Holder<T> {
    public ImageView imageView;
    @Override
    public View createView(Context mContext) {
        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        imageView = (ImageView)View.inflate(mContext, R.layout.item_ad, null);
        return imageView;
    }
}
