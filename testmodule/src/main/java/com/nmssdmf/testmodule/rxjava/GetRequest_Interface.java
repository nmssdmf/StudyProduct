package com.nmssdmf.testmodule.rxjava;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetRequest_Interface {
    //http://fy.iciba.com/ajax.php
    //http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<Translation> getCall();
}
