package com.nmssdmf.testmodule.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ${nmssdmf} on 2019/3/14 0014.
 */

public class MyProvider extends ContentProvider {
    private final String TAG = MyProvider.class.getSimpleName();
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //设置ContentProvider的唯一标识
    public static final String AUTOHORITY = "com.nmssdmf.testmodule.contentprovider.MyProvider";

    public static final int USER_CODE = 1;
    public static final int JOB_CODE = 2;
    //UriMatcher使用：在ContentProvider中注册URI
    private static final UriMatcher matcher;

    static {
        //初始化
        matcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
        matcher.addURI(AUTOHORITY, DBHelper.USER_TABLE_NAME, USER_CODE);
        matcher.addURI(AUTOHORITY, DBHelper.USER_TABLE_NAME, USER_CODE);
    }

    @Override
    public boolean onCreate() {
        context = getContext();

        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        //清空user
        database.execSQL("delete from user");
        database.execSQL("insert into user values(1, 'Carson');");
        database.execSQL("insert into user values(2, 'Kobe');");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        String table = getTableName(uri);
        //查询数据
        cursor = database.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);


        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //根据Uri匹配URI_CODE,从而匹配ContentProvider中相应的表
        String table = getTableName(uri);
        //添加数据
        database.insert(table, null, values);
        //当该Uri的contentProvider数据发生改变时，通知外界（即访问该ContentProvider数据的访问者）
        context.getContentResolver().notifyChange(uri, null);
        //通过ContentUris类从URL中获取ID
//        long id = ContentUris.parseId(uri);
//        Log.d(TAG, "id = " + id);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 匹配表名
     *
     * @param uri
     * @return
     */
    private String getTableName(Uri uri) {
        String tableName = null;
        switch (matcher.match(uri)) {
            case USER_CODE: {
                tableName = DBHelper.USER_TABLE_NAME;
                break;
            }
            case JOB_CODE: {
                tableName = DBHelper.JOB_TABLE_NAME;
                break;
            }
        }
        return tableName;
    }
}
