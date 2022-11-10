package com.ring.basejavamvp.base;

public class BasePresenter<V extends BaseView> {

    protected V mView;

    public void attachView(V view) {
        mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }
}
