package com.angluswang.secret.net;

import com.angluswang.secret.entity.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jeson on 2016/7/4.
 * 登录通信类
 */

public class Login {

    public Login(String phone_md5, String code,
                 final SuccessCallback successCallback,
                 final FailedCallback failedCallback) {
        new NetConnection(Config.SERVER_URL, HttpMethod.POST,
                new NetConnection.SuccessCallback() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            JSONObject jb = new JSONObject(result);
                            switch (jb.getInt(Config.KEY_STATUS)) {
                                case Config.RESULT_STATUS_SUCCESS:
                                    if (successCallback != null) {
                                        successCallback.onSuccess(jb.getString(Config.KEY_TOKEN));
                                    }
                                    break;
                                default:
                                    if (failedCallback != null) {
                                        failedCallback.onFailed();
                                    }
                                    break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (failedCallback != null) {
                                failedCallback.onFailed();
                            }
                        }
                    }
                },
                new NetConnection.FailedCallback() {
                    @Override
                    public void onFailed() {
                        if (failedCallback != null) {
                            failedCallback.onFailed();
                        }
                    }
                },
                Config.KEY_ACTION, Config.ACTION_LOGIN,
                Config.KEY_PHONE_MD5, phone_md5,
                Config.KEY_CODE, code);
    }

    public static interface SuccessCallback {
        void onSuccess(String token);
    }

    public static interface FailedCallback {
        void onFailed();
    }
}
