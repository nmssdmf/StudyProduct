package com.nmssdmf.testmodule.customerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义view
 *      1.Top : getTop() 子view上边到父view上边的距离
 *      2.Bottom : getBottom() 子view下边到父view上面的距离
 *      3.left : getLeft() 子view左边到父view左边的距离
 *      4.right : getRight() 子view右边到父view左边的距离
 *
 *  在Event中：
 *      1.getX(),getY() 触摸点相对于其所在组件坐标系的坐标
 *      2.getRawX(),getRawY() 触摸点相对于屏幕默认坐标系的坐标
 *
 *  角度方向：从X轴正向坐标，逆时针旋转
 *
 *  onMeasure : MeasureSpec
 *      1.测量mode
 *          1.1 UNSPECIFIED 父视图不约束子视图；如ListView
 *          1.2 EXACTLY 父视图为子视图提供一个确切的尺寸，子视图必须在尺寸内；如match_parent 100dp
 *          1.3 AT_MOST 父视图为子视图提供一个最大的尺寸，子视图必须在尺寸内；如wrap_content
 *      2.测量size
 *      3.子view的大小，由父view的MeasureSpec和子view的layoutParams
 *      4.getWidth() / getHeight()：获得View最终的宽 / 高
 *        getMeasuredWidth() / getMeasuredHeight()：获得 View测量的宽 / 高
 */
public class TestCustomerView extends View {
    public TestCustomerView(Context context) {
        super(context);
    }

    public TestCustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCustomerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
