package com.nmssdmf.testmodule.touchevent.touchsolve;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.customerviewlib.databindingbase.BaseBindingViewHolder;
import com.nmssdmf.customerviewlib.databindingbase.BaseDataBindingAdapter;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.databinding.ItemTouchSolveBinding;

import java.util.List;

/**
 * Created by ${nmssdmf} on 2019/1/22 0022.
 */

public class TouchSolveAdapter extends BaseDataBindingAdapter<String, ItemTouchSolveBinding> {

    private final String TAG = TouchSolveAdapter.class.getSimpleName();
    float lastX = 0;
    boolean moveLeft = true;

    public TouchSolveAdapter(@Nullable List<String> data) {
        super(R.layout.item_touch_solve, data);
    }

    @Override
    protected void convert2(BaseBindingViewHolder<ItemTouchSolveBinding> helper, String item, int position) {
        final TouchSolveView solveView = helper.getBinding().hsv;

        solveView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        lastX = event.getX();
                        JLog.d(TAG, "ACTION_DOWN");
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        float x = event.getX();
                        if (x - lastX < 0) {//左滑动
                            moveLeft = true;
                            if (Math.abs(x - lastX) + solveView.getScrollX() > 300) {//已经滑动的距离+将要滑动的距离，不超过滑动菜单的距离，为了不露出白边
                                return true;
                            } else {
                                lastX = x;
                            }
                        } else {
                            moveLeft = false;
                            if (x - lastX - solveView.getScrollX() > 0) {
                                return true;
                            } else {
                                lastX = x;
                            }
                        }

                        JLog.d(TAG, "ACTION_MOVE");

                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        JLog.d(TAG, "ACTION_CANCEL");
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        JLog.d(TAG, "ACTION_UP");
                        if (moveLeft && solveView.getScrollX() >= 300 * 0.2) {
                            solveView.smoothScrollTo(300, 0);
                        } else if (moveLeft && solveView.getScrollX() < 300 * 0.2) {
                            solveView.smoothScrollTo(0, 0);
                        } else if (!moveLeft && solveView.getScrollX() <= 300 * 0.8) {
                            solveView.smoothScrollTo(0, 0);
                        } else if (!moveLeft && solveView.getScrollX() > 300 * 0.8) {
                            solveView.smoothScrollTo(300, 0);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
