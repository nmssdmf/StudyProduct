package com.nmssdmf.testmodule.contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.nmssdmf.testmodule.R;

/**
 * contentProvider进程间通信
 */
public class ContentproviderProcessActivity extends AppCompatActivity {
    private final String TAG = ContentproviderProcessActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider_process);

        //设置uri
        final Uri uriUser = Uri.parse("content://com.nmssdmf.testmodule.contentprovider.MyProvider/user");
        //获取ContentResolver
        final ContentResolver resolver = getContentResolver();

        findViewById(R.id.btnInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(uriUser, resolver);
            }
        });

        findViewById(R.id.btnQuery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query(uriUser, resolver);
            }
        });

    }

    private void insert(Uri uriUser, ContentResolver resolver){
        //插入表中数据
        ContentValues values = new ContentValues();
        values.put("id", 4);
        values.put("name", "Jordan");


        //通过contentResolver 根据URI往ContentProvider中插入数据
        resolver.insert(uriUser, values);
    }

    private void query(Uri uriUser, ContentResolver resolver){

        String selection = "id=?";
        String[] selectionArgs = new String[]{"1"};
        //参数1：uri，参数2：字段，参数3：条件，参数4：条件中的值
        Cursor cursor = resolver.query(uriUser, new String[]{"id", "name"}, selection, selectionArgs, null);
        while (cursor.moveToNext()) {
            Log.d(TAG, "id = " + cursor.getInt(0) + " ; name = " + cursor.getString(1));
        }
        cursor.close();
    }
}
