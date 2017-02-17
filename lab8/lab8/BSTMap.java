package lab8;

import java.util.*;

/**
 * Implements the Map61B interface using Binary Search Tree as
 * its core data structure.
 *
 * @author Wending Peng
 */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;              // root of BSTMap

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;           // number of nodes in the subtree, including root itself

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node x, K key) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsKey(x.left, key);
        } else {
            return containsKey(x.right, key);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.value;
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return get(x.right, key);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        /* Here assume null is a valid value. */
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.value = value;
        } else if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.right = put(x.right, key, value);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* Print the BSTMap in increasing-key order. */
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);
        printInOrder(x.right);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("method remove is not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("method remove is not supported");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("method iterator is not supported");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("method keyset is not supported");
    }

}
