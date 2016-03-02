package cn.figo.mydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送短信,检查网络...
 *
 * @author gccd
 *         2013-12-13
 */
public class DeviceUtil {

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }

    /**
     * 检查网络是否可用
     *
     * @return
     */
    public static boolean checkNetWork(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            for (NetworkInfo info : connectivity.getAllNetworkInfo()) {
                if (info.isConnected()) {
                    Log.i("checkNetWork", "the " + info.getTypeName() + " is on;");
                    return true;
                } else {
                    Log.i("checkNetWork", "the " + info.getTypeName() + " is off;");
                }
            }
        }
        return false;
    }

    /**
     * 是否开启WIFI
     *
     * @param context
     * @return
     */
    public static boolean isWifiCon(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            // Do whatever
            return true;
        }
        return false;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 获取imei
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }

    /**
     * 获取mac地址
     *
     * @param context
     * @return
     */
    public static String getMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param number
     */
    public static void dial(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * 呼叫电话
     *
     * @param context
     * @param number
     */
    public static void call(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        context.startActivity(intent);
    }

    /**
     * 发送短信
     *
     * @param context
     * @param number  目的电话
     * @param content 内容
     */
    public static void sendSms(Context context, String number, String content) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 关闭输入法
     *
     * @param acitivity
     */
    public static void hideSoftInput(Activity acitivity) {
        if (acitivity != null && acitivity.getWindow() != null && acitivity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) acitivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(acitivity.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Rect frame = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /**
     * 分享
     *
     * @param context
     * @param uri
     * @param content
     */
    public static void sendShare(Context context, Uri uri, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        if (uri != null) {
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.putExtra("sms_body", content);
        } else {
            intent.setType("text/plain");
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(Intent.createChooser(intent, "分享"));
        context.startActivity(intent);
    }


    /**
     * 获取耗费内存
     *
     * @return 已用内存
     */
    public static long getUsedMemory() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return total - free;
    }

    private void abortTM(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        tm.getCallState();//int

        tm.getCellLocation();//CellLocation

        tm.getDeviceId();//String

        tm.getDeviceSoftwareVersion();//String

        tm.getLine1Number();//String

        tm.getNeighboringCellInfo();//List<NeighboringCellInfo>

        tm.getNetworkCountryIso();//String

        tm.getNetworkOperator();//String

        tm.getNetworkOperatorName();//String

        tm.getNetworkType();//int

        tm.getPhoneType();//int

        tm.getSimCountryIso();//String

        tm.getSimOperator();//String

        tm.getSimOperatorName();//String

        tm.getSimSerialNumber();//String

        tm.getSimState();//int

        tm.getSubscriberId();//String

        tm.getVoiceMailAlphaTag();//String

        tm.getVoiceMailNumber();//String

        tm.hasIccCard();//boolean

        tm.isNetworkRoaming();//
    }


    /**
     * 打开输入法
     *
     * @param context
     */
    public static void openInput(Context context, EditText submitBt) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(submitBt, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * 获取包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        try {
            String pkName = context.getPackageName();
            String versionName = context.getPackageManager().getPackageInfo(
                    pkName, 0).versionName;
            int versionCode = context.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
            return pkName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
