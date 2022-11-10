package com.ring.basejavamvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseView> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        View view = inflater.inflate(getContentView(), container, false);
        initView(view);
        return view;
    }

    protected P mPresenter;

    protected P getPresenter() {
        return null;
    }

    protected abstract int getContentView();

    protected abstract void initView(View view);


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
