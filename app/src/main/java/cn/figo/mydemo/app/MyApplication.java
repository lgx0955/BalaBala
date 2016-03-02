package cn.figo.mydemo.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by SilenceDut on 2015/11/28.
 */
public class MyApplication extends Application {
    private static Context mContext;
    private RefWatcher mRefWatcher;
    public static DataBase liteOrmDb;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        mRefWatcher = LeakCanary.install(this);
        mContext = getApplicationContext();
        AppService.getInstance().initService();

        liteOrmDb = LiteOrm.newCascadeInstance(mContext, "liteorm.db");

    }

    public static Context getContext() {
        return mContext;
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = mContext.getSharedPreferences("httpcccaaaccchhheeeeeee", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    @Override
    public void onLowMemory() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onLowMemory();
    }

    public void exitApp() {
//        BaseAppManager.getInstance().clear();
        System.gc();
//        MobclickAgent.onKillProcess(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
