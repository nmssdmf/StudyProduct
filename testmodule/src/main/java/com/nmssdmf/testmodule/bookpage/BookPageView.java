package com.nmssdmf.testmodule.bookpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.nmssdmf.commonlib.util.DensityUtil;
import com.nmssdmf.commonlib.util.JLog;

/**
 * Created by ${nmssdmf} on 2018/11/7 0007.
 */

public class BookPageView extends View {
    private final String TAG = BookPageView.class.getSimpleName();

    private Paint pointPaint;//绘制各个标识点的画笔
    private Paint bgPaint;//背景画笔

    private Paint pathAPaint;//绘制A区域画笔
    private Paint pathBPaint;//绘制B区域画笔
    private Paint pathCPaint;//绘制C区域画笔
    private Paint textPaint;//绘制文字画笔
    private Paint pathCContentPaint;

    private Path pathA;
    private Path pathB;
    private Path pathC;
    private Bitmap bitmap;//缓存bitmap
    private Canvas bitmapCanvas;

    private MyPoint a, b, c, d, e, f, g, h, i, j, k;

    private int defaultWidth;//默认宽度
    private int defaultHeight;//默认高度
    private int viewWidth;
    private int viewHeight;

    private String style;
    public static final String STYLE_TOP_RIGHT = "STYLE_TOP_RIGHT";//f点在右上角
    public static final String STYLE_LOWER_RIGHT = "STYLE_LOWER_RIGHT";//f点在右下角
    public static final String STYLE_LEFT = "STYLE_LEFT";//点击左边区域
    public static final String STYLE_RIGHT = "STYLE_RIGHT";//点击右边区域
    public static final String STYLE_MIDDLE = "STYLE_MIDDLE";//点击中间区域

    private Scroller mScroller;


    public BookPageView(Context context) {
        super(context);

        init(context);
    }

    public BookPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context, new LinearInterpolator());

        defaultWidth = DensityUtil.getScreenWidth(context);
        defaultHeight = DensityUtil.getScreenHeight(context) - DensityUtil.dpToPx(context, 48) - DensityUtil.getStatusBarHeight(context);

        viewWidth = defaultWidth;
        viewHeight = defaultHeight;

//        a = new MyPoint(defaultWidth * 0.7f, defaultHeight * 0.7f);
//        a = new MyPoint(defaultWidth * 0.7f, defaultHeight * 0.3f);
        a = new MyPoint();
        b = new MyPoint();
        c = new MyPoint();
        d = new MyPoint();
        e = new MyPoint();
        f = new MyPoint();
//        f = new MyPoint(viewWidth, viewHeight);
//        f = new MyPoint(viewWidth, 0);
        g = new MyPoint();
        h = new MyPoint();
        i = new MyPoint();
        j = new MyPoint();
        k = new MyPoint();

//        calcPointsXY(a, f);

        pointPaint = new Paint();
        pointPaint.setColor(Color.RED);
        pointPaint.setTextSize(25);

        bgPaint = new Paint();
        bgPaint.setColor(Color.WHITE);

        pathAPaint = new Paint();
        pathAPaint.setColor(Color.GREEN);
        pathAPaint.setAntiAlias(true);//设置抗锯齿

        pathBPaint = new Paint();
        pathBPaint.setColor(Color.BLUE);
        pathBPaint.setAntiAlias(true);//设置抗锯齿
//        Xfermode xfermodeB = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
//        pathBPaint.setXfermode(xfermodeB);

        pathCPaint = new Paint();
        pathCPaint.setColor(Color.YELLOW);
        pathCPaint.setAntiAlias(true);//设置抗锯齿
        Xfermode xfermodeC = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        pathCPaint.setXfermode(xfermodeC);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setSubpixelText(true);//设置自像素，将有助于LCD屏幕上的显示效果
        textPaint.setTextSize(30);

        pathCContentPaint = new Paint();
        pathCContentPaint.setColor(Color.YELLOW);
        pathCContentPaint.setAntiAlias(true);

        pathA = new Path();
        pathB = new Path();
        pathC = new Path();
    }

    @Override
    public void computeScroll() {
        JLog.d(TAG, "computeScroll");
        if (mScroller.computeScrollOffset()) {
            float x = mScroller.getCurrX();
            float y = mScroller.getCurrY();

            if (style.equals(STYLE_TOP_RIGHT)) {
                setTouchPoint(x, y, STYLE_TOP_RIGHT);
            } else {
                setTouchPoint(x, y, STYLE_LOWER_RIGHT);
            }
            if (mScroller.getFinalX() == x && mScroller.getFinalY() == y) {
                setPathDefault();
            }
        }
        super.computeScroll();
    }

    /**
     * 取消翻页动画,计算滑动位置与时间
     */
    public void startCancelAnim() {
        int dx, dy;
        //让a滑动到f点所在的位置，流出1像素是为了防止当a和f重叠时候出现view闪烁的情况
        if (style.equals(STYLE_TOP_RIGHT)) {
            dx = (int) (viewWidth - 1 - a.x);
            dy = (int) (1 - a.y);
        } else {
            dx = (int) (viewWidth - 1 - a.x);
            dy = (int) (viewHeight - 1 - a.y);
        }

        mScroller.startScroll((int) a.x, (int) a.y, dx, dy, 400);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);

        setMeasuredDimension(width, height);
        viewWidth = width;
        viewHeight = height;
        if (f.x == 0 && f.y == 0) {
            f.x = viewWidth;
            f.y = viewHeight;
            a.x = -1;
            a.y = -1;
            calcPointsXY(a, f);
        }
//        f.x = width;
//        f.y = height;
//        a.x = -1;//f.x * 0.7f;
//        a.y = -1;//f.y * 0.7f;
//
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
        canvas.drawRect(0, 0, viewWidth, viewHeight, bgPaint);

        //绘制各个标记点
//        canvas.drawText("a", a.x, a.y, pointPaint);
//        canvas.drawText("b", b.x, b.y, pointPaint);
//        canvas.drawText("c", c.x, c.y, pointPaint);
//        canvas.drawText("d", d.x, d.y, pointPaint);
//        canvas.drawText("e", e.x, e.y, pointPaint);
//        canvas.drawText("f", f.x, f.y, pointPaint);
//        canvas.drawText("g", g.x, g.y, pointPaint);
//        canvas.drawText("h", h.x, h.y, pointPaint);
//        canvas.drawText("i", i.x, i.y, pointPaint);
//        canvas.drawText("j", j.x, j.y, pointPaint);
//        canvas.drawText("k", k.x, k.y, pointPaint);

        bitmap = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);

//        bitmapCanvas.drawPath(getPathAFromLowerRight(), pathAPaint);
//        bitmapCanvas.drawPath(getPathAFromTopRight(), pathAPaint);
//        bitmapCanvas.drawPath(getPathC(), pathCPaint);
//        bitmapCanvas.drawPath(getPathB(), pathBPaint);

        if (a.x == -1 && a.y == -1) {
            drawPathAContext(bitmapCanvas, getPathDefault(), pathAPaint);//bitmapCanvas.drawPath(getPathDefault(), pathAPaint);
        } else {
            if (f.x == viewWidth && f.y == 0) {
                drawPathAContext(bitmapCanvas, getPathAFromTopRight(), pathAPaint);//bitmapCanvas.drawPath(getPathAFromTopRight(), pathAPaint);
                bitmapCanvas.drawPath(getPathC(), pathCPaint);
                drawPathCContent(bitmapCanvas,getPathAFromTopRight(),pathCContentPaint);
                drawPathBContext(bitmapCanvas, getPathAFromTopRight(), pathBPaint);
            } else {
                drawPathAContext(bitmapCanvas, getPathAFromLowerRight(), pathAPaint);//bitmapCanvas.drawPath(getPathAFromLowerRight(), pathAPaint);
                bitmapCanvas.drawPath(getPathC(), pathCPaint);
                drawPathCContent(bitmapCanvas,getPathAFromLowerRight(),pathCContentPaint);
                drawPathBContext(bitmapCanvas, getPathAFromLowerRight(), pathBPaint);
            }
//            bitmapCanvas.drawPath(getPathC(), pathCPaint);
//            bitmapCanvas.drawPath(getPathB(), pathBPaint);
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    private void drawPathCContent(Canvas canvas, Path pathA, Paint pathPaint) {
        Bitmap contentBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
        Canvas contentCanvas = new Canvas(contentBitmap);

        //下面开始绘制区域内的内容
        String text = "这是在A区域的内容";
        contentCanvas.drawPath(getPathB(), pathPaint);
        contentCanvas.drawText(text, (int) (viewWidth - textPaint.measureText(text)), viewHeight - 100, textPaint);
        //结束绘制区域内的内容
        canvas.save();
        canvas.clipPath(pathA);
//        canvas.clipPath(getPathC(), Region.Op.REVERSE_DIFFERENCE);//裁剪出C区域不同于A区域

        canvas.clipPath(getPathC(), Region.Op.UNION);
        canvas.clipPath(getPathC(), Region.Op.INTERSECT);

        float eh = (float) Math.hypot(f.x - e.x, h.y - f.y);
        float sin0 = (f.x - e.x) / eh;
        float cos0 = (h.y - f.y) / eh;
        //设置翻转和旋转矩阵
        float[] mMatrixArray = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        mMatrixArray[0] = -(1 - 2 * sin0 * sin0);
        mMatrixArray[1] = 2 * sin0 * cos0;
        mMatrixArray[3] = 2 * sin0 * cos0;
        mMatrixArray[4] = 1 - 2 * sin0 * sin0;

        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setValues(mMatrixArray);//翻转和旋转
        matrix.preTranslate(-e.x, -e.y);//沿当前XY轴负方向位移得到 矩形A₃B₃C₃D₃
        matrix.postTranslate(e.x, e.y);//沿原XY轴方向位移得到 矩形A4 B4 C4 D4

        canvas.drawBitmap(contentBitmap, matrix, null);
        drawPathCShadow(canvas);
        canvas.restore();
    }

    private void drawPathBContext(Canvas canvas, Path pathA, Paint pathPaint) {
        Bitmap contentBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
        Canvas contentCanvas = new Canvas(contentBitmap);

        //下面开始绘制区域内的内容
        String text = "这是在B区域的内容";
        contentCanvas.drawPath(getPathB(), pathPaint);
        contentCanvas.drawText(text, (int) (viewWidth - textPaint.measureText(text)), viewHeight - 100, textPaint);

        //结束绘制区域内的内容
        canvas.save();
        canvas.clipPath(pathA);//裁剪出A区域
        canvas.clipPath(getPathC(), Region.Op.UNION);//裁剪出A和C区域的全集
        canvas.clipPath(getPathB(), Region.Op.REVERSE_DIFFERENCE);//裁剪出B区域不同于Ac区域的部分
        canvas.drawBitmap(contentBitmap, 0, 0, null);
//        canvas.restore();
//        canvas.save();

        drawPathBShadow(canvas);//绘制阴影
        canvas.restore();
    }

    /**
     * 绘制C区域阴影，阴影左浅右深
     * @param canvas
     */
    private void drawPathCShadow(Canvas canvas){
        int deepColor = 0xff111111;//为了让效果更明显使用此颜色代码，具体可根据实际情况调整
//        int deepColor = 0x55333333;
        int lightColor = 0x00333333;
        int[] gradientColors = {lightColor,deepColor};//渐变颜色数组

        int deepOffset = 1;//深色端的偏移值
        int lightOffset = -30;//浅色端的偏移值
        float viewDiagonalLength = (float) Math.hypot(viewWidth, viewHeight);//view对角线长度
        int midpoint_ce = (int) (c.x + e.x) / 2;//ce中点
        int midpoint_jh = (int) (j.y + h.y) / 2;//jh中点
        float minDisToControlPoint = Math.min(Math.abs(midpoint_ce - e.x), Math.abs(midpoint_jh - h.y));//中点到控制点的最小值

        int left;
        int right;
        int top = (int) c.y;
        int bottom = (int) (viewDiagonalLength + c.y);
        GradientDrawable gradientDrawable;
        if (style.equals(STYLE_TOP_RIGHT)) {
            gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, gradientColors);
            gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);

            left = (int) (c.x - lightOffset);
            right = (int) (c.x + minDisToControlPoint + deepOffset);
        } else {
            gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, gradientColors);
            gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);

            left = (int) (c.x - minDisToControlPoint - deepOffset);
            right = (int) (c.x + lightOffset);
        }
        gradientDrawable.setBounds(left,top,right,bottom);

        float mDegrees = (float) Math.toDegrees(Math.atan2(e.x- f.x, h.y - f.y));
        canvas.rotate(mDegrees, c.x, c.y);
        gradientDrawable.draw(canvas);
    }


    /**
     * 绘制B区域的阴影
     * @param canvas
     */
    private void drawPathBShadow(Canvas canvas){
        int deepColor = 0xff111111;//为了让效果更明显使用此颜色代码，具体可根据实际情况调整
//        int deepColor = 0x55111111;
        int lightColor = 0x00111111;
        int[] gradientColors = new int[] {deepColor,lightColor};//渐变颜色数组

        int deepOffset = 0;//深色端的偏移值
        int lightOffset = 0;//浅色端的偏移值
        float aTof =(float) Math.hypot((a.x - f.x),(a.y - f.y));//a到f的距离
        float viewDiagonalLength = (float) Math.hypot(viewWidth, viewHeight);//对角线长度

        int left;
        int right;
        int top = (int) c.y;
        int bottom = (int) (viewDiagonalLength + c.y);
        GradientDrawable gradientDrawable;
        if(style.equals(STYLE_TOP_RIGHT)){//f点在右上角
            //从左向右线性渐变
            gradientDrawable =new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,gradientColors);
            gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);//线性渐变

            left = (int) (c.x - deepOffset);//c点位于左上角
            right = (int) (c.x + aTof/4 + lightOffset);
        }else {
            //从右向左线性渐变
            gradientDrawable =new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT,gradientColors);
            gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);

            left = (int) (c.x - aTof/4 - lightOffset);//c点位于左下角
            right = (int) (c.x + deepOffset);
        }
        gradientDrawable.setBounds(left,top,right,bottom);//设置阴影矩形

        float rotateDegrees = (float) Math.toDegrees(Math.atan2(e.x- f.x, h.y - f.y));//旋转角度
        canvas.rotate(rotateDegrees, c.x, c.y);//以c为中心点旋转
        gradientDrawable.draw(canvas);
    }

    /**
     * 画A面的内容
     *
     * @param canvas
     * @param pathA
     * @param pathPaint
     */
    private void drawPathAContext(Canvas canvas, Path pathA, Paint pathPaint) {
        Bitmap contentBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
        Canvas contentCanvas = new Canvas(contentBitmap);

        //下面开始绘制区域内的内容
        String text = "这是在A区域的内容";
        contentCanvas.drawPath(pathA, pathPaint);
        contentCanvas.drawText(text, (int) (viewWidth - textPaint.measureText(text)), viewHeight - 100, textPaint);

        //结束绘制区域绘制内容
        canvas.save();
        canvas.clipPath(pathA, Region.Op.INTERSECT);//对绘制内容进行裁剪，取和A的交集
        canvas.drawBitmap(contentBitmap, 0, 0, null);
        canvas.restore();
    }

    public void setTouchPoint(float x, float y, String style) {
        MyPoint touchPoint = new MyPoint();
        a.x = x;
        a.y = y;
        this.style = style;
        switch (style) {
            case STYLE_TOP_RIGHT:
            case STYLE_LOWER_RIGHT: {
                if (STYLE_LOWER_RIGHT.equals(style)) {
                    f.x = viewWidth;
                    f.y = viewHeight;
                } else if (STYLE_TOP_RIGHT.equals(style)) {
                    f.x = viewWidth;
                    f.y = 0;
                }
                calcPointsXY(a, f);

                touchPoint.x = x;
                touchPoint.y = y;
                if (calcPointC(touchPoint, f) > 0) {//按照实际书本的模型，左边是装订的，不能被翻起来

                } else {
                    calcPointAByTouchPoint(touchPoint);
                    calcPointsXY(a, f);
                }

                postInvalidate();
                break;
            }
            case STYLE_RIGHT:
            case STYLE_LEFT: {
                a.y = viewHeight - 1;
                f.x = viewWidth;
                f.y = viewHeight;
                calcPointsXY(a, f);
                postInvalidate();
                break;
            }
            case STYLE_MIDDLE: {
                break;
            }
            default:
                break;
        }
    }

    /**
     * 回到默认状态
     */
    public void setPathDefault() {
        a.x = -1;
        a.y = -1;

        postInvalidate();
    }

    private Path getPathDefault() {
        pathA.reset();
        ;
        pathA.lineTo(0, viewHeight);
        pathA.lineTo(viewWidth, viewHeight);
        pathA.lineTo(viewWidth, 0);
        pathA.close();
        return pathA;
    }

    private Path getPathB() {
        pathB.reset();
        pathB.lineTo(viewWidth, 0);
        pathB.lineTo(viewWidth, viewHeight);
        pathB.lineTo(0, viewHeight);
        pathB.close();
        return pathB;
    }

    private Path getPathC() {
        pathC.reset();
        pathC.moveTo(i.x, i.y);
        pathC.lineTo(d.x, d.y);
        pathC.lineTo(b.x, b.y);
        pathC.lineTo(a.x, a.y);
        pathC.lineTo(k.x, k.y);
        pathC.close();
        return pathC;
    }

    /**
     * 获取f点在右下角的pathA
     *
     * @return
     */
    private Path getPathAFromLowerRight() {
        pathA.reset();
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.lineTo(c.x, c.y);//移动到c点
        pathA.quadTo(e.x, e.y, b.x, b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x, a.y);//移动到a点
        pathA.lineTo(k.x, k.y);//移动到k点
        pathA.quadTo(h.x, h.y, j.x, j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth, 0);//移动到右上角
        pathA.close();//闭合区域
        return pathA;
    }

    /**
     * 获取f点在右上角的pathA
     *
     * @return
     */
    private Path getPathAFromTopRight() {
        pathA.reset();
        pathA.lineTo(c.x, c.y);//移动到c点
        pathA.quadTo(e.x, e.y, b.x, b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x, a.y);//移动到a点
        pathA.lineTo(k.x, k.y);//移动到k点
        pathA.quadTo(h.x, h.y, j.x, j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth, viewHeight);//移动到右下角
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.close();
        return pathA;
    }

    /**
     * 当c.x的坐标小与0之后，重新计算a点坐标，为了保持跟手势同步，不产生停顿不动的情况
     */
    private void calcPointAByTouchPoint(MyPoint touchPoint) {
        float w0 = viewWidth - c.x;
        float w1 = Math.abs(f.x - a.x);//viewWidth - touchPoint.x;
        float w2 = viewWidth * w1 / w0;
        a.x = Math.abs(f.x - w2);//viewWidth - w2;

        float h1 = Math.abs(f.y - touchPoint.y);
        float h2 = w2 * h1 / w1;
        a.y = Math.abs(f.y - h2);
    }

    /**
     * 计算c点横向坐标
     *
     * @return
     */
    private float calcPointC(MyPoint a, MyPoint f) {
        MyPoint g = new MyPoint();
        MyPoint e = new MyPoint();
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);

        float x = (3 * e.x - f.x) / 2;//f.x-3 / 2 * (f.x-e.x);
        return x;
    }

    /**
     * 计算各个坐标
     */
    private void calcPointsXY(MyPoint a, MyPoint f) {
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        h.x = f.x;
        h.y = g.y - (f.x - g.x) * (f.x - g.x) / (f.y - g.y);

        c.x = (3 * e.x - f.x) / 2;//f.x-3 / 2 * (f.x-e.x);
        c.y = f.y;

        j.x = f.x;
        j.y = (3 * h.y - f.y) / 2;//f.y - 3/2*(f.y - h.y);

        b = getIntersectionPoint(a, e, c, j);
        k = getIntersectionPoint(a, h, c, j);

        d.x = (c.x + 2 * e.x + b.x) / 4;
        d.y = (2 * e.y + c.y + b.y) / 4;

        i.x = (j.x + 2 * h.x + k.x) / 4;
        i.y = (2 * h.y + j.y + k.y) / 4;
    }

    /**
     * 计算两线段相交点坐标
     *
     * @param lineOne_My_pointOne
     * @param lineOne_My_pointTwo
     * @param lineTwo_My_pointOne
     * @param lineTwo_My_pointTwo
     * @return 返回该点
     */
    private MyPoint getIntersectionPoint(MyPoint lineOne_My_pointOne, MyPoint lineOne_My_pointTwo, MyPoint lineTwo_My_pointOne, MyPoint lineTwo_My_pointTwo) {
        float x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = lineOne_My_pointOne.x;
        y1 = lineOne_My_pointOne.y;
        x2 = lineOne_My_pointTwo.x;
        y2 = lineOne_My_pointTwo.y;
        x3 = lineTwo_My_pointOne.x;
        y3 = lineTwo_My_pointOne.y;
        x4 = lineTwo_My_pointTwo.x;
        y4 = lineTwo_My_pointTwo.y;

        float pointX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
                / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        float pointY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
                / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

        return new MyPoint(pointX, pointY);
    }

    public float getViewWidth() {
        return viewWidth;
    }

    public float getViewHeight() {
        return viewHeight;
    }
}