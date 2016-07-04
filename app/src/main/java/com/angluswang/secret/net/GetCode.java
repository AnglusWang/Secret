package com.angluswang.secret.net;

import com.angluswang.secret.entity.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jeson on 2016/7/4.
 * 获取手机验证码通信类
 */

public class GetCode {

    public GetCode(String phone, final SuccessCallback successCallback,
                   final FailedCallback failedCallback) {
        new NetConnection(Config.SERVER_URL, HttpMethod.POST,
                new NetConnection.SuccessCallback() {
                    @Override
                    public void onSuccess(String result) {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            switch (jsonObject.getInt(Config.KEY_STATUS)) {
                                case Config.RESULT_STATUS_SUCCESS:
                                    if (successCallback != null) {
                                        successCallback.onSuccess(result);
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
                }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, phone);
    }

    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailedCallback {
        void onFailed();
    }


}
