package com.nmssdmf.testmodule.db;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.ipc.contentprovider.DbOpenHelper;

public class DbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

    }
}
