package com.nmssdmf.testmodule.touchevent.touchsolve;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by ${nmssdmf} on 2019/1/22 0022.
 */

public class TouchSolveView extends HorizontalScrollView {
    public TouchSolveView(Context context) {
        super(context);
    }

    public TouchSolveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchSolveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }
}
