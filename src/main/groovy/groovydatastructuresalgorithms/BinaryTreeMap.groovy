package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.api.interfaces.IMap
import groovydatastructuresalgorithms.api.nodes.TreeMapNode

class BinaryTreeMap<K, V> implements IMap<K, V> {

    private BinaryTree<TreeMapNode<K, V>> root;
    private int size = 0;

    BinaryTreeMap(K key, V value) {
        root = new BinaryTree<TreeMapNode<K, V>>(new TreeMapNode<K, V>(key, value))
    }

    BinaryTreeMap() {
        root = new BinaryTree<TreeMapNode<K, V>>();
    }

    boolean isEmpty() {
        if(root == null) {
            return true;
        }
        return false;
    }

    int size() {
        return size;
    }

    void addLeft(K key, V value) {
        TreeMapNode<K, V> node = new TreeMapNode<K, V>(key, value);
        if(size == 0 || root == null) {
            node.setLeft(node);
        } else {
            while(node.Left() != null) {
                node.Left().setLeft(node);
                if(node.Left() == null) {
                    node.Left().setLeft(node);
                }
            }
        }
        root.addLeft(node);
        size++;
    }

    void addRight(K key, V value) {
        TreeMapNode<K, V> node = new TreeMapNode<K, V>(key, value);
        if(size == 0 || root == null) {
            node.setRight(node);
        } else {
            while(node.Right() != null) {
                node.Right().setRight(node);
                if(node.Right() == null) {
                    node.Right().setRight(node);
                }
            }
        }
        root.addRight(node);
        size++;
    }

    V get(K key) {
        TreeMapNode<K, V> node = null;
        for(int i = 0; i < size; i++) {
            node = root.get(i);
            if(node == null) {
                return null;
            }
            if(node.getKey().equals(key) && node.getValue().equals(root.get(i).getValue())) {
                return node.getValue();
            }
        }
        return null;
    }
}
