package com.ring.basejavamvp.utils;

import android.text.TextUtils;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignUtil {

    /**
     * 根据请求参数的map集合获取sign签名信息
     */
    public static String getSign(Map<String, String> map) {
        try {
            StringBuilder sb = new StringBuilder();
            //头部拼接秘钥
            sb.append("eqEG2KmWyypPLhmMCBL5WhKd5");
            List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());
            //对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, (o1, o2) -> (o1.getKey()).compareTo(o2.getKey()));
            //将非空键值对进行拼接
            for (Map.Entry<String, String> item : infoIds) {
                if (item.getValue() != null && !item.getValue().equals("")) {
                    String key = item.getKey();
                    String val = item.getValue();
                    sb.append(key).append(val);
                }
            }
            String str = sb.toString();
            //转Base64
            String strBase64 = Base64.encodeToString(str.getBytes(), Base64.DEFAULT).replaceAll("[\\s*\t\n\r]", "");
            //获取strBase64的MD5值并返回
            return getStringMD5(strBase64);
        } catch (Exception e) {
            return "";
        }
    }


    /***
     * 获取字符串的MD5信息摘要
     */
    public static String getStringMD5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
