package com.angluswang.secret.net;

import android.os.AsyncTask;

import com.angluswang.secret.entity.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Jeson on 2016/7/2.
 * 网络通信
 */

public class NetConnection {

    public NetConnection(final String url, final HttpMethod method,
                         final SuccessCallback successCallback,
                         final FailedCallback failedCallback, final String... kvs) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                StringBuffer paramsStr = new StringBuffer();
                for (int i = 0; i < kvs.length; i += 2) {
                    paramsStr.append(kvs[i]).append("=").append(kvs[i + 1]).append("&");
                }


                try {
                    URLConnection uc;
                    switch (method) {
                        case POST:
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(
                                    new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            break;
                        case GET:
                        default:
                            uc = new URL(url + "?" + paramsStr.toString()).openConnection();
                            break;
                    }

                    System.out.println("request url: " + uc.getURL());
                    System.out.println("request data: " + paramsStr);

                    //读取数据
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(uc.getInputStream(), Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }

                    System.out.println("result: " + result);
                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s != null) {
                    if (successCallback != null) {
                        successCallback.onSuccess(s);
                    }
                } else {
                    if (failedCallback != null) {
                        failedCallback.onFailed();
                    }
                }

                super.onPostExecute(s);
            }
        };
    }

    public static interface SuccessCallback {
        void onSuccess(String result);
    }

    public static interface FailedCallback {
        void onFailed();
    }
}
