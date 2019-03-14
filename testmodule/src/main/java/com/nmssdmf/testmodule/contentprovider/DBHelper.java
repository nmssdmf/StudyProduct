package com.nmssdmf.testmodule.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ${nmssdmf} on 2019/3/14 0014.
 */

public class DBHelper extends SQLiteOpenHelper {


    //数据库名
    private static final String DATABASE_NAME = "finch.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //数据库表名称
    public static final String USER_TABLE_NAME = "user";
    public static final String JOB_TABLE_NAME = "job";

    private Context context;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + USER_TABLE_NAME
                + "(id integer primary key autoincrement," + " name text)");
    }

    /**
     * 数据库版本更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
