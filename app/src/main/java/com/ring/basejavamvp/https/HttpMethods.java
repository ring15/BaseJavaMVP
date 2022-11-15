package com.ring.basejavamvp.https;

import com.ring.basejavamvp.bean.CloseList;
import com.ring.basejavamvp.bean.ConfigBean;
import com.ring.basejavamvp.bean.HttpResult;
import com.ring.basejavamvp.utils.Config;
import com.ring.basejavamvp.utils.LogUtil;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpMethods {

    private Api api;

    private HttpMethods(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor(s -> LogUtil.d("HttpLog", s)));
        httpClient.addInterceptor(new HttpInterceptor());
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url);
        Retrofit retrofit = builder.build();
        api = retrofit.create(Api.class);
    }

    //饿汉式单例类不能实现延迟加载，不管将来用不用始终占据内存,懒汉式单例类线程安全控制烦琐，而且性能受影响
    //静态内部类实现单例模式
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE1 = new HttpMethods(Config.API_URL);//api域名
        private static final HttpMethods INSTANCE2 = new HttpMethods(Config.LOG_URL);//日志域名
    }

    public static final int HttpMethodsType_1 = 1;//api业务
    public static final int HttpMethodsType_2 = 2;//日志业务

    public static HttpMethods getInstance(int type) {
        HttpMethods httpMethods;
        if (type == HttpMethodsType_2) {//日志域名
            httpMethods = SingletonHolder.INSTANCE2;
        } else {//默认api域名
            httpMethods = SingletonHolder.INSTANCE1;
        }
        return httpMethods;
    }

    public void getConfig(String channel, RequestListener<ConfigBean> listener) {
        JSONObject jsonObject = new JSONObject();
        String json = "";
        try {
            jsonObject.put("channel", channel);
            json = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
        Observable<HttpResult<ConfigBean>> observable = api.getConfig(requestBody);
        toSubscribe(observable, listener);
    }

    /**
     * 获取本人亲密关系列表
     */
    public void getMyInviteList(RequestListener<CloseList> listener) {
        Observable<HttpResult<CloseList>> observable = api.getMyInviteList();
        toSubscribe(observable, listener);
    }

    private <T> void toSubscribe(Observable<HttpResult<T>> observable, RequestListener<T> listener) {
        observable.subscribeOn(Schedulers.newThread())
                .map(httpResult -> {
                    if (httpResult == null) {
                        throw new RequestException(RequestException.NORMAL_NULL);
                    }
                    if (httpResult.getStatus() != RequestException.REQUEST_SUCCESS) {
                        //在这里抛出运行时异常，在onError()中捕获异常，并进行相应的处理
                        throw new RequestException(httpResult.getMsg());
                    }
                    return httpResult.getInfo();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t -> {
                    LogUtil.d("loadSuccess");
                    listener.onSuccess(t);
                }, throwable -> {
                    LogUtil.e("loadFailed: " + throwable.getMessage());
                    if (throwable instanceof RequestException) {
                        listener.onError((RequestException) throwable);
                    } else {
                        listener.onError(new RequestException(throwable.getMessage()));
                    }
                });
    }
}
