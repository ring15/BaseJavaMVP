package com.ring.basejavamvp.main;

import com.ring.basejavamvp.bean.CloseList;
import com.ring.basejavamvp.bean.ConfigBean;
import com.ring.basejavamvp.https.HttpMethods;
import com.ring.basejavamvp.https.RequestException;
import com.ring.basejavamvp.https.RequestListener;

public class MainPresenter extends MainContract.MyPresenter {
    @Override
    public void getConfig() {
        HttpMethods.getInstance(HttpMethods.HttpMethodsType_1).getConfig("", new RequestListener<ConfigBean>() {
            @Override
            public void onSuccess(ConfigBean configBean) {

            }

            @Override
            public void onError(RequestException e) {

            }
        });
    }

    @Override
    public void getMyInviteList() {
        HttpMethods.getInstance(HttpMethods.HttpMethodsType_1).getMyInviteList(new RequestListener<CloseList>() {
            @Override
            public void onSuccess(CloseList closeList) {

            }

            @Override
            public void onError(RequestException e) {

            }
        });
    }
}
