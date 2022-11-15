package com.ring.basejavamvp.https;

public interface RequestListener<T> {
    void onSuccess(T t);
    void onError(RequestException e);
}
