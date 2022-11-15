package com.ring.basejavamvp.https;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ring.basejavamvp.utils.SignUtil;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


public class HttpInterceptor implements Interceptor {
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        //在Header中添加签名参数
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        String sign = "";
        String time = String.valueOf(System.currentTimeMillis());
        try {
            if (request.method().equals("GET")) {
                //LogUtil.d("--------------------------------------GET");
                sign = createSign("[]", time);
            } else if (request.method().equals("POST") && hasRequestBody && requestBody instanceof FormBody) {//表单提交
                //LogUtil.d("--------------------------------------POST = FormBody");
                sign = createSign("[]", time);
            } else if (request.method().equals("POST") && hasRequestBody && requestBody.contentLength() == 0) {
                //LogUtil.d("--------------------------------------POST = body null");
                sign = createSign("[]", time);
            } else if (request.method().equals("POST") && hasRequestBody && requestBody.contentLength() > 0) {
                //LogUtil.d("--------------------------------------POST = has body");
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();

                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (isPlaintext(buffer) && null != charset) {
                    String readString = buffer.readString(charset);
                    //LogUtil.d("--------------------------------------body = " + readString);
                    if (contentType != null && contentType.type() != null && contentType.type().contains("multipart")) {
                        //LogUtil.d("--------multipart------");
                        sign = createSign("[]", time);
                    } else {
                        sign = createSign(readString, time);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //需要验签的业务接口：在header中添加sign和time参数,用于验签
        newBuilder.addHeader("sign", sign)
                .addHeader("time", time);
        //所有业务接口都要添加的：通用的Header参数
        request = newBuilder.addHeader("did", "d9cfba06-e2d8-4da6-a4a0-f6d2d118a7fd")
                .addHeader("umid", "aic56f411cd759a4b7a67006f285651404")
                .addHeader("versionCode", "105")
                .addHeader("subChannel", "xks_01")
                .header("Content-Type", "application/json; charset=utf-8")
                .build();
        return chain.proceed(request);
    }

    private String createSign(String readString, String time) {
        Gson gson = new Gson();
        Map<String, String> paramsMap = gson.fromJson(readString, new TypeToken<Map<String, String>>() {
        }.getType());//解析业务bean
        if (null == paramsMap) {
            paramsMap = new HashMap<>();
        }
        paramsMap.put("time", time);
        return SignUtil.getSign(paramsMap);
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }
}
