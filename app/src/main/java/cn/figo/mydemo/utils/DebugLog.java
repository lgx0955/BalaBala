package cn.figo.mydemo.utils;

import android.util.Log;

/**
 * Created by kenny on 2014/4/28.
 */
public class DebugLog {
    static String className = "AiPeiHuClient";
    static String methodName;


    public static boolean isDebuggable() {

        return Const.DEBUG_LOG;
    }

    private static String createLog(String log) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        buffer.append(methodName);
        buffer.append("]");
        buffer.append(log);

        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
    }

    public static void e(String message) {
        if (!isDebuggable()) return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void i(String message) {
        if (!isDebuggable()) return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!isDebuggable()) return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!isDebuggable()) return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!isDebuggable()) return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable()) return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLog(message));
    }
}
