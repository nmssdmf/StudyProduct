package com.nmssdmf.testmodule.ThreadPoolExecutor;

/**
 * Created by ${nmssdmf} on 2019/2/19 0019.
 */

public interface HttpResponseCall {
    void onFailure();//请求失败
    void onResponseError();//请求成功，返回结果错误
    void onResponseSuccess();//请求成功，返回结果正确
}
