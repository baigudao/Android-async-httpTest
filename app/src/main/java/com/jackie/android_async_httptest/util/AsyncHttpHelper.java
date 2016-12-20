package com.jackie.android_async_httptest.util;

import android.os.Build;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Administrator on 2016/12/21.
 */
public class AsyncHttpHelper {

    private static final AsyncHttpClient client;
    /**
     * android客户端信息
     */
    private static String appUserAgent;

    private AsyncHttpHelper() {
    }

    static {
        client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(3, 10000);
        client.setUserAgent(getAppUserAgent());
    }

    public static AsyncHttpClient getClient() {
        return client;
    }

    private static String getAppUserAgent() {
        if (appUserAgent == null || appUserAgent == "") {
            StringBuilder stringBuilder = new StringBuilder("jackie");
            stringBuilder.append("|android").append("|" + Build.VERSION.RELEASE).append("|" + Build.MODEL);
            appUserAgent = stringBuilder.toString();
        }
        return appUserAgent;
    }
}
