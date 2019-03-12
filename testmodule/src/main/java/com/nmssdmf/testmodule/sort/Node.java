package com.nmssdmf.testmodule.sort;

import android.util.Log;

/**
 * 二叉树的节点类
 * Created by ${nmssdmf} on 2019/3/12 0012.
 */

public class Node {
    private final String TAG = Node.class.getSimpleName();

    public int data;//节点数据
    public Node leftChild;//左子节点的引用
    public Node rightChild;//右子节点的引用


    public void display(){
        Log.d(TAG, "data = " + data);
    }
}
