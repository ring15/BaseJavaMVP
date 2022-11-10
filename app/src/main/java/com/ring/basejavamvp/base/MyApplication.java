package com.ring.basejavamvp.base;

import android.app.Application;

import com.ring.basejavamvp.utils.LogUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO:日志开关，正式环境需关闭
        LogUtil.init(true);
    }
}
