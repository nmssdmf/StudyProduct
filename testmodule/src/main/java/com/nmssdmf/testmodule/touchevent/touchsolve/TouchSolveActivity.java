package com.nmssdmf.testmodule.touchevent.touchsolve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nmssdmf.customerviewlib.CustomerRecyclerView;
import com.nmssdmf.testmodule.R;

import java.util.ArrayList;
import java.util.List;

public class TouchSolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_solve);

        CustomerRecyclerView crv = findViewById(R.id.crv);
        List<String> list = new ArrayList<>();
        while (list.size() < 20) {
            list.add("Number:"+ list.size());
        }
        TouchSolveAdapter adapter = new TouchSolveAdapter(list);
        crv.setAdapter(adapter);
    }
}
