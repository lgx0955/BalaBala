package cn.figo.mydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Ligx
 * Date: 2015-10-21
 * Time: 09:36
 * Copyright (c)Ligx All rights reserved.
 */
public class Util {
    public static String formatTimeXianshi(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay+"天"+strHour+"时"+strMinute + "分";
    }

    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay+" 天 "+strHour+"  小时 "+strMinute + " 分钟 " + strSecond + " 秒"+"     "+strMilliSecond+"毫秒";
    }

    /**
     * @param @param  mContext
     * @param @return
     * @return File
     * @throws
     * @Title: getCacheFile
     * @Description: 返回应用的缓存位置，默认返回外部SD卡的缓存位置，当SD卡不可用时返回内部存储缓存位置
     */
    public static File getCacheFile(Context mContext) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && mContext.getExternalCacheDir() != null) {
                return mContext.getExternalCacheDir();
            } else {

                return mContext.getCacheDir();
            }
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * @param @param file
     * @return void
     * @throws
     * @Title: creatFlod
     * @Description: 创建目录，如果没有的话递归创建父目录
     */
    public static boolean creatFlod(File file) {
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    creatFlod(file.getParentFile());
                } else {
                    file.mkdir();
                }
                file.mkdir();
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static File getCacheAudioRecordFile(Context mContext) {
        File file = new File(getCacheFile(mContext).getPath() + "/audiorecord");
        if (!file.exists()) {
            creatFlod(file);
        }
        ;
        return file;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
        return px;
    }

    public static float converPixelToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static float convertSpToPixel(float sp, Context context) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static DisplayMetrics getDispalyMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    /**
     * @param @param  timestamp
     * @param @return
     * @return String
     * @throws
     * @Title: getTheFormateSendTime
     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
     */
    public static String getTheFormateSendTime(long timestamp) {
        long javatimestamp;
        String s = String.valueOf(timestamp);
        if (s.length() == 10) {
            s = s + "000";
            javatimestamp = Long.parseLong(s);
        } else {
            javatimestamp = timestamp;
        }

        long currentTimestamp = System.currentTimeMillis();
        int i = Math.abs((int) (currentTimestamp - javatimestamp));
        int fiveMinuteInSecond = 60 * 5;
        int oneHourInSecond = 60 * 60;
        int oneDayInSecond = 60 * 60 * 24;
        int twoDayInSecond = 60 * 60 * 24 * 2;
//        if (i <= fiveMinuteInSecond) {
//            return "刚刚";
//        }
//        if (i > oneHourInSecond && i <= fiveMinuteInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / 60))) + "分钟前";
//        }
//        if (i > fiveMinuteInSecond && i <= oneDayInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / (60 * 60)))) + "小时前";
//        }
//        if (i > oneDayInSecond && i <= twoDayInSecond) {
//            return "昨天";
//        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return simpleDateFormat.format(new Date(javatimestamp));
    }
//    /**
//     * @Title: getTheFormateSendTime
//     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
//     * @param @param timestamp
//     * @param @return
//     * @return String
//     * @throws
//     */
//    public static String  getTheFormateSendTimeWithoutHour(long timestamp){
//        long javatimestamp;
//        String s  = String.valueOf(timestamp);
//        if(s.length()==10){
//            s=s+"000";
//            javatimestamp = Long.parseLong(s);
//        }else{
//            javatimestamp = timestamp;
//        }
//
//        long  currentTimestamp  =  System.currentTimeMillis();
//        int i =  Math.abs((int) (currentTimestamp - javatimestamp));
//        int fiveMinuteInSecond  =  60*5;
//        int oneHourInSecond  = 60*60;
//        int oneDayInSecond  = 60*60*24;
//        int twoDayInSecond  = 60*60*24*2;
//        if(i<=fiveMinuteInSecond){
//            return "刚刚";
//        }
//        if(i>oneHourInSecond&&i<=fiveMinuteInSecond){
//            return   StringUtils.convertToRightFormat(String.valueOf((int)(i/60)))+"分钟前";
//        }
//        if(i>fiveMinuteInSecond&&i<=oneDayInSecond){
//            return   StringUtils.convertToRightFormat(String.valueOf((int)(i/(60*60))))+"小时前";
//        }
//        if(i>oneDayInSecond&&i<=twoDayInSecond){
//            return  "昨天";
//        }
//
//        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
//
//        return   simpleDateFormat.format(new Date(javatimestamp));
//    }

    /**
     * @param @param  timestamp
     * @param @return
     * @return String
     * @throws
     * @Title: getTheFormateSendTime
     * @Description: 格式化发送时间成：几分钟前、几小时前 这样
     */
    public static String getTheFormateSendTimeWithoutHour(long timestamp) {
        long javatimestamp;
        String s = String.valueOf(timestamp);
        if (s.length() == 10) {
            s = s + "000";
            javatimestamp = Long.parseLong(s);
        } else {
            javatimestamp = timestamp;
        }

        long currentTimestamp = System.currentTimeMillis();
        int i = Math.abs((int) (currentTimestamp - javatimestamp));
        int fiveMinuteInSecond = 60 * 5;
        int oneHourInSecond = 60 * 60;
        int oneDayInSecond = 60 * 60 * 24;
        int twoDayInSecond = 60 * 60 * 24 * 2;
//        if (i <= fiveMinuteInSecond) {
//            return "刚刚";
//        }
//        if (i > oneHourInSecond && i <= fiveMinuteInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / 60))) + "分钟前";
//        }
//        if (i > fiveMinuteInSecond && i <= oneDayInSecond) {
//            return StringUtils.convertToRightFormat(String.valueOf((int) (i / (60 * 60)))) + "小时前";
//        }
//        if (i > oneDayInSecond && i <= twoDayInSecond) {
//            return "昨天";
//        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return simpleDateFormat.format(new Date(javatimestamp));
    }

    public static String LeftPad_Tow_Zero(int str) {
        java.text.DecimalFormat format = new java.text.DecimalFormat("00");
        return format.format(str);
    }

    public static long phpTimeStamp2Java(long timestamp) {
        return timestamp * 1000;
    }

    public static long JavaTimeStamp2php(long timestamp) {
        return Math.abs(timestamp / 1000);
    }


    static Gson gson = new Gson();

    //泛型解析gson
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    //泛型解析gson数组
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            lst.add(gson.fromJson(elem, clazz));
        }
        return lst;
    }

    public static boolean isCellPhoneNo(String telephone) {

        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);

        if (matcher.matches()) {
            return true;
        }
        return true;
    }

    public static String saveMyBitmapInRoot(String bitName, Bitmap mBitmap) {
        File f = new File("mnt/sdcard/漫画说/share/" + System.currentTimeMillis() + ".png");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            DebugLog.e("在保存图片时出错：" + e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }


    /**
     * 验证邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            DebugLog.e("验证邮箱地址错误");
            flag = false;
        }

        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            DebugLog.e("验证手机号码错误");
            flag = false;
        }
        return flag;
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     * @return
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
