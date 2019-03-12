package com.nmssdmf.testmodule.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import retrofit2.Retrofit;

public class Client {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();//要代理的真实对象
        InvocationHandler handler = new MyProxy(realSubject);//将要代理的对象传进去

        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("word");

    }
}
