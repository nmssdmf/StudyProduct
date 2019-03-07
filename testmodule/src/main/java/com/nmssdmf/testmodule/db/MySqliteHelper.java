package com.nmssdmf.testmodule.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {
    private final String TAG = MySqliteHelper.class.getSimpleName();

    private static final String CREATE_BOOK = "create table book " +
            "( id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    private static final String DB_NAME = "book.db";
    private Context context;
    private final int INIT_VERSION = 0;
    private int newVersion = 0;
    public MySqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        newVersion = version;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);//创建数据库表
        Log.d(TAG, "创建数据库表成功");
        onUpgrade(db ,INIT_VERSION, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        update(db, oldVersion, newVersion);
    }

    /**
     * 数据库版本递归更新
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void update(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TAG", "update oldVersion = " + ";newVersion = " + newVersion);

    }
}
