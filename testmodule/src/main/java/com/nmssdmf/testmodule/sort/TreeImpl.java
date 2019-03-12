package com.nmssdmf.testmodule.sort;

import android.util.Log;

/**
 * Created by ${nmssdmf} on 2019/3/12 0012.
 */

public class TreeImpl implements Tree {
    private final String TAG = TreeImpl.class.getSimpleName();
    private Node root = new Node();

    /**
     * 创建一个根节点
     *
     * @param key
     */
    public TreeImpl(int key) {
        root.data = key;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data > key) {
                current = current.leftChild;
            } else if (current.data < key) {
                current = current.rightChild;
            } else {
                return current;
            }
        }
        return null;
    }

    @Override
    public boolean insert(int key) {
        Node newNode = new Node();
        newNode.data = key;

        Node current = root;
        while (true) {
            if (current.data > key) {
                if (current.leftChild != null) {
                    current = current.leftChild;
                } else {
                    current.leftChild = newNode;
                    return true;
                }
            } else {
                if (current.rightChild != null) {
                    current = current.rightChild;
                } else {
                    current.rightChild = newNode;
                    return true;
                }
            }
        }
    }

    @Override
    public boolean delete(int key) {

        return false;
    }

    @Override
    public void traverse(Node node) {
        if (node.leftChild != null) {
            traverse(node.leftChild);
        } else {
            Log.d(TAG, "data = " + node.data);
            if (node.rightChild != null) {
                traverse(node.rightChild);
            }
        }
    }

}
