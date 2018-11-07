package com.nmssdmf.testmodule.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.nmssdmf.commonlib.util.JLog;

public class TouchView extends android.support.v7.widget.AppCompatTextView {
    private final String TAG = TouchView.class.getSimpleName();

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        JLog.d(TAG, "dispatchTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                JLog.d(TAG, "ACTION_DOWN");
//                return false;
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
