package com.nmssdmf.testmodule.customerview;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nmssdmf.commonlib.util.DensityUtil;
import com.nmssdmf.testmodule.R;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 一个时钟的自定义view
 */
public class WatchBoardView extends View {
    private final String TAG = WatchBoardView.class.getSimpleName();
    private float radius;//圆形半径
    private float padding;//边距
    private float textSize;//文字大小
    private float hourPointWidth;//时针宽度
    private float minutePointWidth;//分针宽度
    private float secondPointWidth;//秒针宽度
    private float pointRadius;//指针圆角
    private float pointEndLength;//指针末尾长度

    private int hourPointColor;//时针的颜色
    private int minutePointColor;//分针的颜色
    private int secondPointColor;//秒针的颜色
    private int colorLong;//时刻刻度的颜色
    private int colorShort;//非时刻刻度的颜色

    private Paint paint;//画笔
    private PaintFlagsDrawFilter drawFilter;//为画布设置抗锯齿

    private int width;

    private HandlerThread handlerThread;
    private Handler delayHandler;
    private Handler mainHandler;

    public WatchBoardView(Context context) {
        this(context, null);
    }

    public WatchBoardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性
        obtainStyledAttrs(attrs);
        //初始化画笔
        initPaint();
        //为画布实现抗锯齿
        drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        //测量手机的宽度
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        //默认和屏幕的宽高最小值相等
        width = Math.min(widthPixels, heightPixels);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //传入相同的数width, height 确保是正方形背景
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    private int measureSize(int measureSpec) {
        int size = MeasureSpec.getSize(measureSpec);
        width = Math.min(width, size);
        return width;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        radius = (Math.min(w, h) - padding /** 2*/) / 2;//这里没有减去padding*2是为了给刻度留间距
        pointEndLength = radius / 6;//超过圆心长度为半径的1/6
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        long startTime = System.currentTimeMillis();
//        设置抗锯齿
        canvas.setDrawFilter(drawFilter);
        //画表盘
        drawCircle(canvas);
        //画刻度和数字
        drawScale(canvas);
        //画指针
        drawPointer(canvas);

        // 绘制原点
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, width / 2, secondPointWidth * 4, paint);
        long time = System.currentTimeMillis() - startTime;
        Log.d(TAG, "time = " + time);
        // 每一秒刷新一次
//        postInvalidateDelayed(1000);
            onStart();
    }

    private void drawPointer(Canvas canvas) {

        //获取时间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        //计算旋转的角度
        float angleHour = hour * 6 * 5 + minute / 60f * 6 * 5;
        float angleMinute = minute * 6 + second / 60f * 6;
        float angleSecond = second * 6;
        //绘制指针
        canvas.save();
        canvas.rotate(angleHour, width / 2, width / 2);//旋转到时针的角度
        RectF rectHour = new RectF(width / 2 - hourPointWidth / 2, width / 2 - radius * 3 / 5,
                width / 2 + hourPointWidth / 2, width / 2 + pointEndLength);
        paint.setColor(hourPointColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(hourPointWidth);
        canvas.drawRoundRect(rectHour, pointRadius, pointRadius, paint);
        canvas.restore();
        // 绘制分针
        canvas.save();
        canvas.rotate(angleMinute, width / 2, width / 2); // 旋转到分针的角度
        RectF rectMinute = new RectF(width / 2 - minutePointWidth / 2, width / 2 - radius * 3.5f / 5,
                width / 2 + minutePointWidth / 2, width / 2 + pointEndLength);
        paint.setColor(minutePointColor);
        paint.setStrokeWidth(minutePointWidth);
        canvas.drawRoundRect(rectMinute, pointRadius, pointRadius, paint);
        canvas.restore();
        // 绘制秒针
        canvas.save();
        canvas.rotate(angleSecond, width / 2, width / 2); // 旋转到分针的角度
        RectF rectSecond = new RectF(width / 2 - secondPointWidth / 2, width / 2 - radius + dpToPx(10),
                width / 2 + secondPointWidth / 2, width / 2 + pointEndLength);
        paint.setStrokeWidth(secondPointWidth);
        paint.setColor(secondPointColor);
        canvas.drawRoundRect(rectSecond, pointRadius, pointRadius, paint);
        canvas.restore();


    }

    private void drawScale(Canvas canvas) {
        //绘制刻度
        int lineHeight;
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                paint.setStrokeWidth(dpToPx(1.5f));
                paint.setColor(colorLong);
                lineHeight = 40;

                //这里绘制数字
                paint.setTextSize(textSize);
                String text = String.valueOf((i / 5) == 0 ? 12 : (i / 5));
                Rect textBound = new Rect();
                paint.getTextBounds(text, 0, text.length(), textBound);
                paint.setColor(Color.BLACK);
                //因为文字在坐标上面,所以需要加上textBound.height
                canvas.drawText(text, width / 2 - textBound.width() / 2, padding + lineHeight + dpToPx(5) + textBound.height(), paint);
            } else {
                paint.setStrokeWidth(dpToPx(1));
                paint.setColor(colorShort);
                lineHeight = 30;
            }
            //画一根12点中方向的刻度
            canvas.drawLine(width / 2, padding, width / 2, padding + lineHeight, paint);
            //旋转画布6度
            canvas.rotate(6, width / 2, width / 2);
        }
    }

    /**
     * 画表盘
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, width / 2, radius, paint);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WatchBoardView);
        padding = typedArray.getDimension(R.styleable.WatchBoardView_wb_paddding, dpToPx(10));
        textSize = typedArray.getDimension(R.styleable.WatchBoardView_wb_text_size, dpToPx(16));
        hourPointWidth = typedArray.getDimension(R.styleable.WatchBoardView_wb_hour_pointer_width, dpToPx(5));
        minutePointWidth = typedArray.getDimension(R.styleable.WatchBoardView_wb_minute_pointer_width, dpToPx(3));
        secondPointWidth = typedArray.getDimension(R.styleable.WatchBoardView_wb_second_pointer_width, dpToPx(2));
        pointRadius = typedArray.getDimension(R.styleable.WatchBoardView_wb_pointer_corner_radius, dpToPx(10));
        pointEndLength = typedArray.getDimension(R.styleable.WatchBoardView_wb_pointer_end_length, dpToPx(10));

        hourPointColor = typedArray.getColor(R.styleable.WatchBoardView_wb_hour_pointer_color, Color.BLACK);
        minutePointColor = typedArray.getColor(R.styleable.WatchBoardView_wb_minute_pointer_color, Color.BLACK);
        secondPointColor = typedArray.getColor(R.styleable.WatchBoardView_wb_second_pointer_color, Color.RED);
        colorLong = typedArray.getColor(R.styleable.WatchBoardView_wb_scale_long_color, Color.argb(225, 0, 0, 0));
        colorShort = typedArray.getColor(R.styleable.WatchBoardView_wb_scale_short_color, Color.argb(125, 0, 0, 0));

        typedArray.recycle();
    }

    private int dpToPx(float dp) {
        return DensityUtil.dpToPx(getContext(), dp);
    }

    /**
     * 时钟开始走时
     */
    public void onStart(){
        if (handlerThread == null) {
            handlerThread = new HandlerThread("refresh");
            handlerThread.start();
            delayHandler = new Handler(handlerThread.getLooper()){
                @Override
                public void handleMessage(Message msg) {
                    try {
                        Thread.sleep(1000);
                        mainHandler.sendEmptyMessage(0);
                        delayHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            mainHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    invalidate();
                    return true;
                }
            });
            delayHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 在ui结束时必须调用，否则造成内存泄漏
     * 时钟停止走时
     */
    public void onStop(){
        if (handlerThread != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                handlerThread.quitSafely();
            } else {
                handlerThread.quit();
            }
            delayHandler.removeMessages(0);
            mainHandler.removeMessages(0);
        }
    }

}
