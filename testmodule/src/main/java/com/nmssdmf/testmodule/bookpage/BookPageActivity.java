package com.nmssdmf.testmodule.bookpage;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.nmssdmf.commonlib.activity.BaseTitleActivity;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.commonlib.viewmodel.BaseVM;
import com.nmssdmf.testmodule.R;
import com.nmssdmf.testmodule.databinding.ActivityBookPageBinding;

public class BookPageActivity extends BaseTitleActivity {
    private final String TAG = BookPageActivity.class.getSimpleName();
    private ActivityBookPageBinding binding;

    private String style = null;

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
        return "翻页动画";
    }

    @Override
    public void initContent(Bundle savedInstanceState) {
        binding = (ActivityBookPageBinding) baseViewBinding;
        binding.bpv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                JLog.d(TAG, "event.getAction() = " + event.getAction());
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        float width = binding.bpv.getViewWidth();
                        float height = binding.bpv.getViewHeight();
                        float right = width * 2 / 3;
                        float top = height / 3;
                        float bottom = height * 2 / 3;
                        float left = width / 3;
                        if (x <= left) {//在左边
//                            style = BookPageView.STYLE_LEFT;
//                            binding.bpv.setTouchPoint(x, y, style);
                        } else if (x > right && y < top) {//在右上角
                            style = BookPageView.STYLE_TOP_RIGHT;
                            binding.bpv.setTouchPoint(x, y, style);
                        } else if (x > right && y > top && y < bottom) {//右边中间
                            style = BookPageView.STYLE_RIGHT;
                            binding.bpv.setTouchPoint(x, y, style);
                        } else if (x > right && y > bottom) {//右下角
                            style = BookPageView.STYLE_LOWER_RIGHT;
                            binding.bpv.setTouchPoint(x, y, style);
                        } else if (x > left && x < right /*&& y > top && y < bottom*/) {//中间
                            style = BookPageView.STYLE_MIDDLE;
                        }
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        binding.bpv.setTouchPoint(event.getX(), event.getY(), style);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        JLog.d(TAG, "MotionEvent.ACTION_UP");
                        binding.bpv.startCancelAnim();
                        break;
                    }
                }
                return true;
            }
        });
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_book_page;
    }
}
