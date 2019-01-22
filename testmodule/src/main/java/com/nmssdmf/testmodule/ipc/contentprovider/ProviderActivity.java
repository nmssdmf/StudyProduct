package com.nmssdmf.testmodule.ipc.contentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.aidl.Book;

public class ProviderActivity extends AppCompatActivity {

    private final String TAG = ProviderActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Uri uri = Uri.parse("content://com.nmssdmf.testmodule.ipc.contentprovider.bookprovider/book");

        Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "name"}, null, null, null);
        while (cursor.moveToNext()) {
            Book book = new Book();
            book.bookId = cursor.getInt(0);
            book.bookName = cursor.getString(1);
            JLog.d(TAG, "book = " + new Gson().toJson(book));
        }
        cursor.close();
    }
}
