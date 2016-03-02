package cn.figo.mydemo.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import cn.figo.mydemo.app.MyApplication;
import cn.figo.mydemo.bean.UserBean;


public class AccountHelper {

    public static UserBean getUser() {

        String s = MyApplication.getSharedPreferences().getString(Const.SharedPreferences.USER, null);
        if (TextUtils.isEmpty(s)) {
            return null;
        } else {
            return new Gson().fromJson(s, UserBean.class);
        }
    }

    public static void setUser(UserBean user) {
//        if (TextUtils.isEmpty(user.user_id)) {
//            UserBean currentUser = getUser();
//            if (currentUser != null) {
//                user.user_id = currentUser.user_id;
//            }
//        }
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString(Const.SharedPreferences.USER, new Gson().toJson(user));
        editor.commit();
    }

    public static void LogOut() {
        UserBean user = getUser();
        if (user != null) {
            SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
            editor.putString(Const.SharedPreferences.USER, null);
            editor.commit();
        }
//        UserBean user = getUser();
//        if (user != null) {
//            user.setToken(null);
//            SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
//            editor.putString(Const.SharedPreferences.USER, new Gson().toJson(user));
//            editor.commit();
//        }
    }

    public static boolean isLogin() {
        UserBean user = getUser();
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void setHotLine(String hotLine) {
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString(Const.HOT_LINE, hotLine);
        editor.commit();
    }

    public static String getHotLine() {
        return MyApplication.getSharedPreferences().getString(Const.HOT_LINE, "020-22016669");
    }

    public static boolean isFistLoading() {
        return MyApplication.getSharedPreferences().getBoolean(Const.First_loading, true);
    }

    public static void setFirstLoading(boolean firstLoading) {
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putBoolean(Const.First_loading, firstLoading);
        editor.commit();
    }

    public static String getAppVersion() {
        return MyApplication.getSharedPreferences().getString(Const.APP_Version, "0.0.0");
    }

    public static void setAppVersion(String appVersion) {
        SharedPreferences.Editor editor = MyApplication.getSharedPreferences().edit();
        editor.putString(Const.APP_Version, appVersion);
        editor.commit();
    }
}
