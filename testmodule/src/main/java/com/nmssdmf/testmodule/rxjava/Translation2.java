package com.nmssdmf.testmodule.rxjava;

import com.nmssdmf.commonlib.util.JLog;

public class Translation2 {

    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        JLog.d("RxJava", content.out );
    }

}
