package com.nmssdmf.testmodule.falling;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.nmssdmf.commonlib.util.JLog;

import java.util.Random;

/**
 * Created by ${nmssdmf} on 2018/11/6 0006.
 */

public class FallObject {
    private final String TAG = FallObject.class.getSimpleName();
    private int initX;
    private int initY;
    private Random random;
    private int parentWidth;//父容器宽度
    private int parentHeight;//父容器高度
    private int objectWidth;//下落物体宽度
    private int objectHeight;//下落物体高度

    public int initSpeed;//初始下降速度

    public float presentX;//当前位置X坐标
    public float presentY;//当年位置Y坐标
    public float presentSpeed;//当前下降速度

    private Bitmap bitmap;
    public Builder builder;

    private static final int defaultSpeed = 10;//默认下降速度

    public FallObject(Builder buidler, int parentWidth, int parentHeight) {
        random = new Random();
        this.builder = buidler;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;

        initX = random.nextInt(parentWidth);
        initY = random.nextInt(parentHeight) - parentHeight;//随机物体Y的坐标，并让物体一开始从屏幕顶部下落

        presentX = initX;
        presentY = initY;

        initSpeed = buidler.initSpeed;
        bitmap = buidler.bitmap;

        randomSize();
        randomSpeed();
    }

    private FallObject(Builder builder) {
        this.builder = builder;
        initSpeed = builder.initSpeed;
        bitmap = builder.bitmap;
    }

    public static final class Builder {
        private int initSpeed;
        private Bitmap bitmap;

        public Builder(Drawable drawable) {
            this.initSpeed = defaultSpeed;
            bitmap = drawableToBitMap(drawable);
        }

        public Builder setSize(int w, int h) {
            this.bitmap = changeBitmapSize(this.bitmap, w, h);
            return this;
        }

        public Builder setSpeed(int speed) {
            initSpeed = speed;
            return this;
        }

        public FallObject build() {
            return new FallObject(this);
        }
    }

    /**
     * drawable图片资源转bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitMap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 改变bitmap的大小
     *
     * @param bitmap 目标bitmap
     * @param newW   目标宽度
     * @param newH   目标高度
     * @return
     */
    public static Bitmap changeBitmapSize(Bitmap bitmap, int newW, int newH) {
        int oldW = bitmap.getWidth();
        int oldH = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newW) / oldW;
        float scaleHeight = ((float) newH) / oldH;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, oldW, oldH, matrix, true);
        return bitmap;
    }

    /**
     * 随机物体初始下落速度
     */
    private void randomSpeed(){
        presentSpeed = (float)((random.nextInt(4)+1)*0.1+1)* initSpeed;//这些随机数大家可以按自己的需要进行调整
    }

    /**
     * 随机物体初始大小比例
     */
    private void randomSize() {
        float r = (random.nextInt(5) + 5) * 0.1f;
        float rW = r * builder.bitmap.getWidth();
        float rH = r * builder.bitmap.getHeight();
        bitmap = changeBitmapSize(builder.bitmap, (int) rW, (int) rH);

        objectWidth = bitmap.getWidth();
        objectHeight = bitmap.getHeight();
    }

    /**
     * 位置物体对象
     *
     * @param canvas
     */
    public void drawObject(Canvas canvas) {
        moveObject();
        canvas.drawBitmap(bitmap, presentX, presentY, null);
    }

    /**
     * 移动物体对象
     */
    private void moveObject() {
        moveY();
        if (presentY > parentHeight) {
            reset();
        }
    }

    /**
     * Y轴移动
     */
    private void moveY() {
        presentY += presentSpeed;
    }

    private void reset() {
        presentY = random.nextInt(parentHeight) - parentHeight;
        randomSize();
        randomSpeed();
    }
}
