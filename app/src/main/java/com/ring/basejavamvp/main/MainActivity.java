package com.ring.basejavamvp.main;

import com.ring.basejavamvp.R;
import com.ring.basejavamvp.base.BaseActivity;
import com.ring.basejavamvp.base.BasePresenter;
import com.ring.basejavamvp.base.BaseView;

public class MainActivity extends BaseActivity<MainContract.MyPresenter, MainContract.MyView> implements MainContract.MyView {

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

    @Override
    protected MainContract.MyPresenter getPresenter() {
        return new MainPresenter();
    }
}