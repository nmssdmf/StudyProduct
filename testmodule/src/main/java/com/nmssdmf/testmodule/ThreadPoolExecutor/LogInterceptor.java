package com.nmssdmf.testmodule.ThreadPoolExecutor;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${nmssdmf} on 2019/2/19 0019.
 */

public class LogInterceptor implements Interceptor {
    private final String TAG = LogInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        Log.d(TAG, String.format("url::::: %s\nrequest mode:::::\n%s\n request head:::::\n%s \n request body:::::\n%s \nresponse header:::::\n%s \nresponse body:::::\n%s",
                request.url(),request.method(),
                request.headers().toString(),
                new Gson().toJson(request.body()),
                response.headers().toString(),
                new Gson().toJson(request.body())));
        return response;
    }
}
