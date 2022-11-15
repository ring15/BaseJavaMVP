package com.ring.basejavamvp.main;

import com.ring.basejavamvp.base.BasePresenter;
import com.ring.basejavamvp.base.BaseView;

public interface MainContract {

    interface MyView extends BaseView {
    }

    abstract class MyPresenter extends BasePresenter<MyView> {
        public abstract void getConfig();
        public abstract void getMyInviteList();
    }
}
