package com.ring.basejavamvp.https;

//必须继承RuntimeException
public class RequestException extends RuntimeException{

    public static final int REQUEST_SUCCESS = 0;

    //常规错误
    public static final int NORMAL_NULL = -1;
    public static final int NORMAL_NETWORK = -2;

    public RequestException(int code) {
        this(getRequestException(code));
    }

    public RequestException(String errorMsg) {
        super(errorMsg);
    }

    private static String getRequestException(int code) {
        if (code == NORMAL_NULL) {
            return "返回数据为空";
        }
        if (code == NORMAL_NETWORK) {
            return "网络错误";
        }
        return "未知错误";
    }

}
