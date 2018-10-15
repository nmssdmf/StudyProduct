package com.nmssdmf.commonlib.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author huscarter@163.com
 * @title toast 工具类
 * @description
 * @date 8/1/17
 */

public class ToastUtil {
    private static Toast toast;
    private static ToastUtil instance;
    private static Context context;

    private ToastUtil() {
        //
    }

    public static ToastUtil getInstance() {
        if (instance == null) {
            synchronized (ToastUtil.class) {
                if (instance == null) {
                    instance = new ToastUtil();
                }
            }
        }
        return instance;
    }

    /**
     * @param context 为了避免内存泄露请传入application context
     */
    public void init(Context context) {
        this.context = context;
    }

    public void showToast(String msg) {
        if (toast == null) {
            synchronized (ToastUtil.class) {
                if (ContextUtil.isContextExisted(context)) {
                    if (toast == null) {
                        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        show(msg);
                    }
                }
            }
        } else {
            show(msg);
        }
    }

    private static void show(String message) {
        toast.setText(message);
        toast.show();
    }
}
