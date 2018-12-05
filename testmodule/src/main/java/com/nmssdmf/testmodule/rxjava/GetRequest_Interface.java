package com.nmssdmf.testmodule.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetRequest_Interface {
    //http://fy.iciba.com/ajax.php
    //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();

    // 网络请求1
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20register")
    Observable<Translation1> getRegister();

    // 网络请求2
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20login")
    Observable<Translation2> getLogin();

}
