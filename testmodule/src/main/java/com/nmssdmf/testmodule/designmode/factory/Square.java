package com.nmssdmf.testmodule.designmode.factory;

import android.util.Log;

import com.nmssdmf.commonlib.util.JLog;

public class Square implements Shape {
    private final String TAG = Square.class.getSimpleName();
    @Override
    public void draw() {
        JLog.d(TAG, "Square draw");
    }
}
