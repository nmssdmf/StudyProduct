package com.nmssdmf.testmodule.ipc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nmssdmf.commonlib.util.JLog;

/**
 * Created by ${nmssdmf} on 2019/1/16 0016.
 */

public class BookProvider extends ContentProvider {

    private final static String TAG = BookProvider.class.getSimpleName();

    public static final String AUTHORITY = "com.nmssdmf.testmodule.ipc.contentprovider.bookprovider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        uriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private Context context;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        JLog.d(TAG, "onCreate, current = " + Thread.currentThread().getName());
        context = getContext();
        //实际开发，不在ui线程中操作
        initProviderData();
        return true;
    }

    private void initProviderData(){
        db = new DbOpenHelper(context).getWritableDatabase();
        db.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        db.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        db.execSQL("insert into book values(3, 'Android');");
        db.execSQL("insert into book values(4, 'Ios');");
        db.execSQL("insert into book values(5, 'Html5');");
        db.execSQL("insert into user values(1, 'jake', 1);");
        db.execSQL("insert into user values(2, 'jasmine', 0);");
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE: {
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            }
        }
        return tableName;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        JLog.d(TAG, "query, current = " + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null)
            throw new IllegalArgumentException("Unsupported Uri:" + uri);
        return db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        JLog.d(TAG, "insert, current = " + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null)
            throw new IllegalArgumentException("Unsupported Uri:" + uri);
        db.insert(tableName,  null, values);
        context.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        JLog.d(TAG, "delete, current = " + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null)
            throw new IllegalArgumentException("Unsupported Uri:" + uri);
        int count = db.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        JLog.d(TAG, "update, current = " + Thread.currentThread().getName());
        String tableName = getTableName(uri);
        if (tableName == null)
            throw new IllegalArgumentException("Unsupported Uri:" + uri);
        int row = db.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }
}
