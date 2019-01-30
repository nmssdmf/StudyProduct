package com.nmssdmf.testmodule.rtsp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import com.nmssdmf.testmodule.R;

public class RtspActivity extends AppCompatActivity {
    private VideoView vv;
    private Button btn;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtsp);


//        final String url = "rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov";

        vv = findViewById(R.id.vv);
        btn = findViewById(R.id.btn);
        et = findViewById(R.id.et);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.setVideoURI(Uri.parse(et.getText().toString()));
                vv.requestFocus();
                vv.start();
            }
        });
    }
}
