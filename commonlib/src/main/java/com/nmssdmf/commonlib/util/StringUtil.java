package com.nmssdmf.commonlib.util;

/**
 * Created by ${nmssdmf} on 2018/10/15 0015.
 */

public class StringUtil {
    public static boolean isEmpty(String string){
        if (string == null || string.length() == 0) {
            return true;
        }
        return false;
    }
}
