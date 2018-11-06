package com.nmssdmf.testmodule.falling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.nmssdmf.commonlib.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${nmssdmf} on 2018/11/6 0006.
 */

public class FallingView extends View {
    private Context context;

    private int viewWidth;
    private int viewHeight;
    private int intervalTime = 5;//重绘间隔时间

    private int defaultWidth;//屏幕宽度
    private int defaultHeight;//屏幕高度

    private Paint fallPaint;
    private int snowY;

    private List<FallObject> fallObjectList;

    public FallingView(Context context) {
        super(context);
        init(context);
    }

    public FallingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;

        fallObjectList = new ArrayList<>();

        defaultHeight = DensityUtil.getScreenHeight(context);
        defaultWidth = DensityUtil.getScreenWidth(context);

        fallPaint = new Paint();
        fallPaint.setColor(Color.RED);
        fallPaint.setStyle(Paint.Style.FILL);
        snowY = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        setMeasuredDimension(width, height);

        viewWidth = width;
        viewHeight = height;
    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (fallObjectList.size() > 0) {
            for (int i = 0; i< fallObjectList.size(); i++) {
                fallObjectList.get(i).drawObject(canvas);
            }
            getHandler().postDelayed(runnable, intervalTime);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public void addFallObject(final FallObject fallObject, final int num){
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                for ( int i =0; i < num; i++) {
                    FallObject object = new FallObject(fallObject.builder, viewWidth, viewHeight);
                    fallObjectList.add(object);
                }
                invalidate();
                return true;
            }
        });
    }
}
