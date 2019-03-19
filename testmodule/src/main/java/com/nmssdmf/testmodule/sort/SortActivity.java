package com.nmssdmf.testmodule.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nmssdmf.commonlib.util.JLog;
import com.nmssdmf.testmodule.R;

public class SortActivity extends AppCompatActivity {
    private final String TAG = SortActivity.class.getSimpleName();

    private NodeTree nodeTree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);


        findViewById(R.id.btnBubbling).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7, 3, 8, 1, 4, 5, 9, 6, 2};
                bubblingSort(nums);
                Log.d(TAG, "冒泡排序nums = " + new Gson().toJson(nums));
            }
        });

        findViewById(R.id.btnQuick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7, 3, 8, 1, 4, 5, 9, 6, 2};
                quickSort(nums, 0, nums.length - 1);
                Log.d(TAG, "快速排序nums = " + new Gson().toJson(nums));
            }
        });

        findViewById(R.id.btnInsertion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7, 3, 8, 1, 4, 5, 9, 6, 2};
                insertSort(nums);
                Log.d(TAG, "插入排序nums = " + new Gson().toJson(nums));
            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7, 3, 8, 1, 4, 5, 9, 6, 2};
                selectSort(nums);
                Log.d(TAG, "选择排序nums = " + new Gson().toJson(nums));
            }
        });
        //二叉树插入
        findViewById(R.id.btnTreeInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] nums = {7, 3, 8, 1, 4, 5, 9, 6, 2};
                treeInsert(nums);
            }
        });
        //前序
        findViewById(R.id.btnTreePreorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treePreorder(nodeTree);
            }
        });
        //中序
        findViewById(R.id.btnTreeInorder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeInorder(nodeTree);
            }
        });
        //后序
        findViewById(R.id.btnTreeFollow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treeFollow(nodeTree);
            }
        });
    }

    private void treeFollow(NodeTree node) {
        if (node == null)
            return;

        if (node.left != null)
            treeFollow(node.left);

        if (node.right != null)
            treeFollow(node.right);
        JLog.d(TAG, "treeFollow node.data = " + node.data);
    }

    private void treeInorder(NodeTree node) {
        if (node == null)
            return;

        if (node.left != null)
            treeInorder(node.left);

        JLog.d(TAG, "treeInorder node.data = " + node.data);
        if (node.right != null)
            treeInorder(node.right);
    }

    private void treePreorder(NodeTree node) {
        if (node == null)
            return;
        JLog.d(TAG, "treePreorder node.data = " + node.data);
        if (node.left != null)
            treePreorder(node.left);

        if (node.right != null)
            treePreorder(node.right);
    }

    /**
     * 二叉树插入
     *
     * @param nums
     */
    private void treeInsert(int[] nums) {
        if (nodeTree == null) {
            nodeTree = new NodeTree();
            nodeTree.data = nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            NodeTree node = nodeTree;
            while (true) {
                if (nums[i] > node.data) {
                    if (node.right == null) {
                        node.right = new NodeTree();
                        node.right.data = nums[i];
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    if (node.left == null) {
                        node.left = new NodeTree();
                        node.left.data = nums[i];
                        break;
                    } else {
                        node = node.left;
                    }
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    private void bubblingSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int k = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = k;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param nums
     */
    private void quickSort(int[] nums, int low, int height) {
        int k = nums[low];
        int i = low;
        int j = height;
        while (i != j && j > i) {
            while (i < j && nums[j] >= k) {
                j -= 1;
            }
            while (i < j && nums[i] <= k) {
                i += 1;
            }
            int num = nums[i];
            nums[i] = nums[j];
            nums[j] = num;
        }
        nums[low] = nums[j];
        nums[j] = k;

        if (i - low > 1) {
            quickSort(nums, low, i - 1);
        }
        if (height - i > 1) {
            quickSort(nums, i + 1, height);
        }

    }

    /**
     * 插入排序
     *
     * @param nums
     */
    private void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (num < nums[j]) {
                    nums[j + 1] = nums[j];
                } else {
                    nums[j + 1] = num;
                    break;
                }
                if (j == 0) {
                    nums[j] = num;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param nums
     */
    private void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int k = i, num = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (num > nums[j]) {
                    num = nums[j];
                    k = j;
                }
            }
            nums[k] = nums[i];
            nums[i] = num;
        }
    }
}
