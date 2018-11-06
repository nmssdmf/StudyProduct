package com.nmssdmf.testmodule.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.nmssdmf.commonlib.util.JLog;

public class TouchViewGroupB extends RelativeLayout {
    private final String TAG = TouchViewGroupB.class.getSimpleName();
    public TouchViewGroupB(Context context) {
        super(context);
    }

    public TouchViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        JLog.d(TAG, "dispatchTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                JLog.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                JLog.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                JLog.d(TAG, "ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        JLog.d(TAG, "onInterceptTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                JLog.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                JLog.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                JLog.d(TAG, "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        JLog.d(TAG, "onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                JLog.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                JLog.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                JLog.d(TAG, "ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }
}
