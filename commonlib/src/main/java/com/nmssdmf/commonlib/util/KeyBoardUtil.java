package com.nmssdmf.commonlib.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘工具
 * Created by ${nmssdmf} on 2018/6/5 0005.
 */

public class KeyBoardUtil {
    private static final String TAG = KeyBoardUtil.class.getSimpleName();
    /**
    * @description 打开软键盘
    * @author nmssdmf
    * @date 2018/6/5 0005 14:42
    * @version v3.2.0
    */
    public static void openKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
    * @description 关闭软键盘
    * @author nmssdmf
    * @date 2018/6/5 0005 14:44
    * @version v3.2.0
    */
    public static void closeKeyWords(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive() && activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
