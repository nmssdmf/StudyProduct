package com.nmssdmf.testmodule.bookpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nmssdmf.commonlib.util.DensityUtil;

/**
 * Created by ${nmssdmf} on 2018/11/7 0007.
 */

public class BookPageView extends View {
    private Paint pointPaint;//绘制各个标识点的画笔
    private Paint bgPaint;//背景画笔

    private Paint pathAPaint;//绘制A区域画笔
    private Paint pathBPaint;//绘制B区域画笔
    private Paint pathCPaint;//绘制C区域画笔

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

    public BookPageView(Context context) {
        super(context);

        init(context);
    }

    public BookPageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
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
        Xfermode xfermodeB = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        pathBPaint.setXfermode(xfermodeB);

        pathCPaint = new Paint();
        pathCPaint.setColor(Color.YELLOW);
        pathCPaint.setAntiAlias(true);//设置抗锯齿
        Xfermode xfermodeC = new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP);
        pathCPaint.setXfermode(xfermodeC);

        pathA = new Path();
        pathB = new Path();
        pathC = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);

        setMeasuredDimension(width, height);
         viewWidth = width;
         viewHeight = height;
         f.x = width;
         f.y = height;
         a.x = f.x * 0.7f;
         a.y = f.y * 0.7f;
         calcPointsXY(a, f);
    }

    private int measureSize(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else  if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, viewWidth, viewHeight, bgPaint);

        //绘制各个标记点
        canvas.drawText("a", a.x, a.y, pointPaint);
        canvas.drawText("b", b.x, b.y, pointPaint);
        canvas.drawText("c", c.x, c.y, pointPaint);
        canvas.drawText("d", d.x, d.y, pointPaint);
        canvas.drawText("e", e.x, e.y, pointPaint);
        canvas.drawText("f", f.x, f.y, pointPaint);
        canvas.drawText("g", g.x, g.y, pointPaint);
        canvas.drawText("h", h.x, h.y, pointPaint);
        canvas.drawText("i", i.x, i.y, pointPaint);
        canvas.drawText("j", j.x, j.y, pointPaint);
        canvas.drawText("k", k.x, k.y, pointPaint);

        bitmap = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawPath(getPathAFromLowerRight(), pathAPaint);
//        bitmapCanvas.drawPath(getPathAFromTopRight(), pathAPaint);

        bitmapCanvas.drawPath(getPathC(), pathCPaint);
//
        bitmapCanvas.drawPath(getPathB(), pathBPaint);

        canvas.drawBitmap(bitmap, 0, 0, null);
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
     * @return
     */
    private Path getPathAFromTopRight(){
        pathA.reset();
        pathA.lineTo(c.x,c.y);//移动到c点
        pathA.quadTo(e.x,e.y,b.x,b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x,a.y);//移动到a点
        pathA.lineTo(k.x,k.y);//移动到k点
        pathA.quadTo(h.x,h.y,j.x,j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth,viewHeight);//移动到右下角
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.close();
        return pathA;
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
}
