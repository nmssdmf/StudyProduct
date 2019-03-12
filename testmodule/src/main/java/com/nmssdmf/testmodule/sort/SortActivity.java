package com.nmssdmf.testmodule.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nmssdmf.testmodule.R;

public class SortActivity extends AppCompatActivity {
    private final String TAG  = SortActivity.class.getSimpleName();

    private int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

//        quickSort(arr, 0, arr.length - 1);
//        JLog.d(TAG, "arr = " + new Gson().toJson(arr));

//        bubblingSort(arr);
//        insertSort(arr);
//        selectSort(arr);
        tree();
//        JLog.d(TAG, "arr = " + new Gson().toJson(arr));
    }

    /**
     * 选择排序
     * 第一次：找出最小的数据与数组第一个交换位置
     * 第二次：出去第一个，找出最小的，与第二个交换位置
     * ...
     * @param arr
     */
    public void selectSort(int[] arr){
        for (int i =0; i< arr.length; i++) {
            int a = arr[i];
            int jj = i;
            for (int j =i ; j < arr.length ;j++) {
                if (arr[j]< a) {
                    a = arr[j];
                    jj = j;
                }
            }
            arr[jj] = arr[i];
            arr[i] = a;
        }
    }

    /**
     * 插入排序
     * 第一次：取出第二个数据，如果比第一个大，则插入第一个后面，如果比前一个小，则插入第一个前面
     * 第二次：取出第三个数据，如果比第二个大，则不变，如果比第二个小，将第二个位置写入第三个位置，
     *         再与第一个比较，如果比第一个大，则插入第二个位置，小则将第一个位置插入第二个，将该数据插入第一个
     * @param arr
     */
    public void insertSort(int[] arr){
        for (int i =1; i< arr.length;i++) {
            int a = arr[i];
            int j;
            for (j =i-1; j >=0; j-=1) {
                if (a < arr[j]) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }

            arr[j+1] = a;
        }
    }

    /**
     * 冒泡
     * 将前一个数字与后一个比较，如果大了则交换位置
     * @param arr
     */
    public void bubblingSort(int[] arr) {
        for (int i = 0; i< arr.length -1;i++ ) {
            for (int j =0; j< arr.length -1 -i;j++) {
                if (arr[j] > arr[j+1]) {
                    int a = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = a;
                }
            }
        }

    }

    /**
     * 快速排序
     * 选第一个数据为基数，从右边开始找出第一个比基数小的，再从左边开始找出第一个比基数大的，然后交换位置，（右边指针必须大于等于左边指针）,当左右指针相等的时候，
     * 将该位子的数据与第一个基数交换，以基数为准，分成左右两个数组，在分别进行递归
     * @param arr
     * @param low
     * @param hign
     */
    public void quickSort(int[] arr, int low, int hign) {
        if (low >= hign)
            return;
        int a = arr[low];
        int i = low;
        int j = hign;

        while (i < j) {
            while (arr[j] >= a && i < j) {
                j -= 1;
            }

            while (arr[i] <= a&& i < j) {
                i += 1;
            }

            int b = arr[j];
            arr[j] = arr[i];
            arr[i] = b;
        }

        arr[low] = arr[i];
        arr[i] = a;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, hign);
    }

    public void tree(){
        TreeImpl tree = new TreeImpl(6);
        tree.insert(3);
        tree.insert(7);
        tree.insert(8);
        tree.insert(2);
        tree.insert(9);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(10);
        tree.traverse(tree.getRoot());
    }

}
