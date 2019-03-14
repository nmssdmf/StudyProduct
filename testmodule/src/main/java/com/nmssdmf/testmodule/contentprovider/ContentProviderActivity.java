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

public class ContentProviderActivity extends AppCompatActivity {
    private final String TAG = ContentProviderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        //对user表进行操作
        //设置Uri
        final Uri uriUser = Uri.parse("content://" + MyProvider.AUTOHORITY + "/user");
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

    private void insert(Uri uri, ContentResolver resolver){
        //插入表中数据
        ContentValues values = new ContentValues();
        values.put("id", 3);
        values.put("name", "IVerson");

        //通过ContentResolver根据URI向ContentProvider中插入数据
        resolver.insert(uri, values);
    }

    private void query(Uri uri, ContentResolver resolver){
//        uri = ContentUris.withAppendedId(uri, 1);
        //通过ContentResolver向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri, new String[]{"id", "name"}, null, null, null);

        //读取数据
        while (cursor.moveToNext()) {
            Log.d(TAG,"query id = " + cursor.getInt(0)+ " ; name = " + cursor.getString(1));
        }
        //关闭游标
        cursor.close();
    }
}
