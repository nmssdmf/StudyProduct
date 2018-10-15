package com.nmssdmf.commonlib.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nmssdmf.commonlib.config.BaseConfig;

/**
 * Created by huscarter@163.com on 2015/5/7.
 */
public class JLog {
    private static final boolean DEBUG = BaseConfig.DEBUG;
    private static final String LOG_HEADER = "trading:";
    /**
     * string 打印的最大长度
     */
    private static final int MAX_LENGTH = 4000;

    public static void e(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.e(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.e(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.e(LOG_HEADER + tag, message);
            }
        }

    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.i(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.i(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.i(LOG_HEADER +tag, message);
            }
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.d(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.d(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.d(LOG_HEADER +tag, message);
            }
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.w(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.w(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.w(LOG_HEADER +tag, message);
            }
        }
    }

    public static void wtf(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.wtf(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.wtf(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.wtf(LOG_HEADER +tag, message);
            }
        }
    }

    public static void v(String tag, String message) {
        if (DEBUG) {
            if (message.length() > MAX_LENGTH) {
                for (int i = 0; i < message.length(); i += MAX_LENGTH) {
                    if (i + 4000 < message.length()) {
                        Log.v(LOG_HEADER + tag + i, message.substring(i, i + 4000));
                    } else {
                        Log.v(LOG_HEADER + tag + i, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.v(LOG_HEADER +tag, message);
            }
        }
    }

    public static void jsonD(String tag, String message, Object obj) {
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(new Gson().toJson(obj));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        d(tag, message + " \n " + gson.toJson(je));
    }
}
