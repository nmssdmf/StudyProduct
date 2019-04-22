package com.nmssdmf.testmodule.designmode.factory;

import com.nmssdmf.commonlib.util.JLog;

public class Rectangle implements Shape {
    private final String TAG = Rectangle.class.getSimpleName();
    @Override
    public void draw() {
        JLog.d(TAG, "Rectangle draw");
    }
}
