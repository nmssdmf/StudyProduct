package com.nmssdmf.testmodule.falling;

import android.os.Bundle;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.databinding.ActivityFallingBinding;

public class FallingActivity extends BaseTitleActivity {
    private final String TAG = FallingActivity.class.getSimpleName();
    private ActivityFallingBinding binding;
    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public BaseVM initViewModel() {
        return null;
    }

    @Override
    public String setTitle() {
        return "雪花飘";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        binding = (ActivityFallingBinding) baseViewBinding;

        //初始化一个雪球样式的fallObject
        FallObject.Builder builder = new FallObject.Builder(getResources().getDrawable(R.drawable.snow));
        FallObject fallObject = builder
                .setSpeed(10)
                .setSize(50, 50)
                .build();

        binding.fv.addFallObject(fallObject,50);//添加50个雪球对象
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_falling;
    }
}
