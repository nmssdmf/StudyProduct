package com.nmssdmf.testmodule.sort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nmssdmf.testmodule.R;

public class SortActivity extends AppCompatActivity {
    private final String TAG = SortActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);



        findViewById(R.id.btnBubbling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7,3,8,1,4,5,9,6,2};
                bubblingSort(nums);
            }
        });

        findViewById(R.id.btnQuick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7,3,8,1,4,5,9,6,2};
                quickSort(nums, 0, nums.length -1);
            }
        });

        findViewById(R.id.btnInsertion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 冒泡排序
     * @param nums
     */
    private void bubblingSort(int[] nums){
        for (int i =0; i < nums.length - 1; i ++) {
            for (int j =0; j< nums.length -1 -i;j++) {
                if (nums[j] > nums[j+1]){
                    int k = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = k;
                }
            }
        }
        Log.d(TAG, "冒泡排序nums = " + new Gson().toJson(nums));
    }

    /**
     * 快速排序
     * @param nums
     */
    private void quickSort(int[] nums, int low, int height){
        int k = nums[low];
        int i = low;
        int j = height;
        while(i != j && j>i) {
            while(i<j && nums[j] >= k) {
                j -= 1;
            }
            while(i< j && nums[i] <= k) {
                i += 1;
            }
            int num = nums[i];
            nums[i] = nums[j];
            nums[j] = num;
        }
        nums[low] = nums[j];
        nums[j] = k;

        if ( i - low > 1) {
            quickSort(nums, low, i -1);
        }
        if (height -i > 1) {
            quickSort(nums, i +1, height);
        }
        Log.d(TAG, "快速排序nums = " + new Gson().toJson(nums));
    }
}
