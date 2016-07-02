package com.angluswang.secret.entity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jeson on 2016/7/2.
 * 配置信息类
 */

public class Config {

    public static final String KEY_TOKEN = "token";
    public static final String APP_ID = "com.angluswang.secret";

    /**
     * 获取到缓存的token
     */
    public static String getCachedToken(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
                .getString(KEY_TOKEN, null);
    }

    /**
     * 缓存token
     */
    public static void cachedToken(Context context, String token) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }
}
