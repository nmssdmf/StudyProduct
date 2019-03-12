package com.nmssdmf.testmodule.sort;

/**
 * Created by ${nmssdmf} on 2019/3/12 0012.
 */

public interface Tree {
    /**查找节点*/
    Node find(int key);

    /**插入新节点*/
    boolean insert(int key);

    /**删除节点*/
    boolean delete(int key);

    void traverse(Node node);
}
