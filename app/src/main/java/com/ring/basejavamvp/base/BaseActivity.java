package com.ring.basejavamvp.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ring.basejavamvp.utils.UIUtils;

import java.lang.ref.WeakReference;

public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView> extends AppCompatActivity {

    private static final String EXIT_ACTION = "action.exit";

    private ExitReceiver exitReceiver;//用来退出所有应用

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        setContentView(getContentView());
        initView();
        //导航栏暗模式
        UIUtils.setDarkStatusIcon(this, true);
        //设置全面屏
        if (isFullScreen() && Build.VERSION.SDK_INT >= 28) {
            //使用全面屏且SDK版本大于等于28时，需要特殊配置使用刘海区域
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(attributes);
        }

        exitReceiver = new ExitReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(EXIT_ACTION);
        registerReceiver(exitReceiver, filter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //设置全屏沉浸
        if (isFullScreen() && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility((View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY));
        }
    }

    protected P mPresenter;

    protected P getPresenter() {
        return null;
    }

    protected abstract int getContentView();

    protected abstract void initView();

    protected boolean isFullScreen() {
        return false;//默认设置为非全面屏显示
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //广播必须解除注册，否则会内存泄漏
        unregisterReceiver(exitReceiver);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    static class ExitReceiver extends BroadcastReceiver {

        private WeakReference<BaseActivity> mWeakReference;

        public ExitReceiver(BaseActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mWeakReference != null && mWeakReference.get() != null) {
                mWeakReference.get().finish();
            }
        }
    }
}
