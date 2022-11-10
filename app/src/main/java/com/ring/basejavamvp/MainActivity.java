package com.ring.basejavamvp;

import com.ring.basejavamvp.base.BaseActivity;
import com.ring.basejavamvp.base.BasePresenter;
import com.ring.basejavamvp.base.BaseView;

public class MainActivity extends BaseActivity<BasePresenter<MainActivity>, MainActivity> implements BaseView {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }
}