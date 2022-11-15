package com.ring.basejavamvp.utils;

import android.util.Log;

public class LogUtil {

    private static String TAG = "srh";
    private static boolean LOG;

    public static void init(boolean log) {
        LOG = log;
    }

    //logd
    public static void d(Object str) {
        d(TAG, str);
    }

    //logd
    public static void d(Object cls, Object str) {
        d(cls.getClass().getSimpleName(), str);
    }

    //logd
    public static void d(String tag, Object str) {
        if (LOG) {
            String msg = str.toString();
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - TAG.length();
            //大于max_str_length时
            while (msg.length() > max_str_length) {
                Log.d(tag, " -->: " + msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.d(tag, " -->: " + msg);
        }
    }

    //loge
    public static void e(Object str) {
        e(TAG, str);
    }

    //loge
    public static void e(Object cls, Object str) {
        e(cls.getClass().getSimpleName(), str);
    }

    //loge
    public static void e(String tag, Object str) {
        if (LOG) {
            String msg = str.toString();
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - TAG.length();
            //大于max_str_length时
            while (msg.length() > max_str_length) {
                Log.e(tag, " -->: " + msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.e(tag, " -->: " + msg);
        }
    }

    //logi
    public static void i(Object str) {
        i(TAG, str);
    }

    //logi
    public static void i(Object cls, Object str) {
        i(cls.getClass().getSimpleName(), str);
    }

    //logi
    public static void i(String tag, Object str) {
        if (LOG) {
            String msg = str.toString();
            //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
            //  把4*1024的MAX字节打印长度改为2001字符数
            int max_str_length = 2001 - TAG.length();
            //大于max_str_length时
            while (msg.length() > max_str_length) {
                Log.i(tag, " -->: " + msg.substring(0, max_str_length));
                msg = msg.substring(max_str_length);
            }
            //剩余部分
            Log.i(tag, " -->: " + msg);
        }
    }
}
