package com.nmssdmf.testmodule.touchevent;

import android.os.Bundle;
import android.view.MotionEvent;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.databinding.ActivityTouchBinding;

/**
 * 用于测试事件传递
 */
public class TouchActivity extends BaseTitleActivity {
    private final String TAG = TouchActivity.class.getSimpleName();
    private ActivityTouchBinding binding;
    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        return null;
    }

    @Override
    public String setTitle() {
        return "事件分发";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        binding = (ActivityTouchBinding) baseViewBinding;

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_touch;
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
/* 事件触发日志1:正常分发，不做处理
TouchActivity: dispatchTouchEvent.ACTION_DOWN
TouchViewGroupA: dispatchTouchEvent.ACTION_DOWN
TouchViewGroupA: onInterceptTouchEvent.ACTION_DOWN
TouchViewGroupB: dispatchTouchEvent.ACTION_DOWN
TouchViewGroupB: onInterceptTouchEvent.ACTION_DOWN
TouchView: dispatchTouchEvent.ACTION_DOWN
TouchView: onTouchEvent.ACTION_DOWN
TouchViewGroupB: onTouchEvent.ACTION_DOWN
TouchViewGroupA: onTouchEvent.ACTION_DOWN
TouchActivity: onTouchEvent.ACTION_DOWN

TouchActivity: dispatchTouchEvent. ACTION_UP
TouchActivity: onTouchEvent.ACTION_UP
*/

/*事件触发日志2，在Activity的dispatchTouchEvent中的ActionDown事件拦截
TouchActivity: dispatchTouchEvent.ACTION_DOWN
TouchActivity: dispatchTouchEvent.ACTION_MOVE
TouchActivity: onTouchEvent.ACTION_MOVE
TouchActivity: dispatchTouchEvent.ACTION_MOVE
TouchActivity: onTouchEvent.ACTION_MOVE
TouchActivity: dispatchTouchEvent.ACTION_UP
TouchActivity: onTouchEvent.ACTION_UP
* */

/*
*事件触发日志3，在TouchViewGroupA的dispatchTouchEvent中的ActionDown事件拦截
TouchActivity: dispatchTouchEvent.ACTION_DOWN
TouchViewGroupA: dispatchTouchEvent.ACTION_DOWN
TouchActivity: dispatchTouchEvent.ACTION_UP
TouchViewGroupA: dispatchTouchEvent.ACTION_UP
TouchViewGroupA: onTouchEvent.ACTION_UP
TouchActivity: onTouchEvent.ACTION_UP
 */
