package com.ring.basejavamvp.main;

import com.ring.basejavamvp.base.BaseModel;
import com.ring.basejavamvp.base.BasePresenter;
import com.ring.basejavamvp.base.BaseView;

public interface MainContract {

    interface MyModel extends BaseModel {
    }

    interface MyView extends BaseView {
    }

    abstract class MyPresenter extends BasePresenter<MyView> {
    }
}
