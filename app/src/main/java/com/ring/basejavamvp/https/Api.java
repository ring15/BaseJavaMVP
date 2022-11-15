package com.ring.basejavamvp.https;

import com.ring.basejavamvp.bean.CloseList;
import com.ring.basejavamvp.bean.ConfigBean;
import com.ring.basejavamvp.bean.HttpResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface Api {

    @POST("test/test/test")
    Observable<HttpResult<ConfigBean>> getConfig(@Body RequestBody requestBody);

    /**
     * 获取本人亲密关系列表
     */
    @POST("close/list")
    Observable<HttpResult<CloseList>> getMyInviteList();

}
