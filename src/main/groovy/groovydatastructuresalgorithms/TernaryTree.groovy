package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.api.nodes.TreeNode

class TernaryTree<V> {

    private CircularDoublyLinkedList<TreeNode<V>> root;
    private int size = 0;

    TernaryTree(V value) {
        root = new CircularDoublyLinkedList<TreeNode<V>>(new TreeNode<>(value));
    }

    TernaryTree() {
        root = new CircularDoublyLinkedList<TreeNode<V>>();
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

    void addMiddle(V value) {
        TreeNode<V> node = new TreeNode<V>(value);
        if(size == 0 || root == null) {
            node.setMiddle(node);
        } else {
            while(node.Middle() != null) {
                node.Middle().setMiddle(node);
                if(node.Middle() == null) {
                    node.Middle().setMiddle(node);
                }
            }
        }
        root.add(node);
        size++;
    }

    void addLeft(V value) {
        TreeNode<V> node = new TreeNode<>(value);
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
        root.add(node);
        size++;
    }

    void addRight(V value) {
        TreeNode<V> node = new TreeNode<>(value);
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
        root.add(node);
        size++;
    }

    V get(V value) {
        TreeNode<V> node = null; //new TreeNode<>(value);
        for(int i = 0; i < size; i++) {
            node = root.getNode(i).getValue();
            if(node == null) {
                return null;
            }
            if(node.getValue().equals(value)) {
                return node.getValue();
            }
        }
        return null;
    }

    void delete(V value) {
        TreeNode<V> node = null;// new TreeNode<>(value);
        for(int i = 0; i < size; i++) {
            node = root.getNode(i).getValue();
            if (node.getValue().equals(value)) {
                root.remove(node);
            }
        }
        size--;
    }
}
