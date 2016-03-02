package cn.figo.mydemo.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * @Package cn.figo.f2f.unit
 * @User kenny
 * @DATE 2014/5/22 15:01
 * @Description:
 */
public class ToastHelper {
    public  static Toast mToast;

    public static  void showToast(final int resId,final Context context){
        new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                String text =  context.getString(resId);
                if(mToast==null){
                    mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                }else{
                    mToast.setText(text);
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
                if (text==null||text.trim().length()==0)
                    return;
                mToast.show();
            }
        });

    }

    public static  void showToast(final String text,final Context context){
        new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(text);
                    mToast.setDuration(Toast.LENGTH_SHORT);
                }
                if (text==null||text.trim().length()==0)
                    return;
                mToast.show();
            }
        });
    }
}
