package com.nmssdmf.testmodule.proxy;

import io.reactivex.Observable;

public interface Subject {
    void rent();
    void hello(String str);
    Observable<String> Login();
}
