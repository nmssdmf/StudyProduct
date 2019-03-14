package com.nmssdmf.testmodule.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HenCoderTestView extends View {
    public HenCoderTestView(Context context) {
        super(context);
    }

    public HenCoderTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HenCoderTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawCircle(100, 100, 100, paint);
    }
}
