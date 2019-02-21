package com.nmssdmf.testmodule.ThreadPoolExecutor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ${nmssdmf} on 2019/2/19 0019.
 * OkHttp HeaderInterceptor
 *
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //add header info
        builder.addHeader("Accept-Encoding", "identity");//确保请求能够获取文件长度s
        builder.addHeader("Connection","close");
        Response response = chain.proceed(builder.build());
        return response;
    }
}
