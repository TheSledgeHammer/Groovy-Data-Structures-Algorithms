package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.api.nodes.TreeNode

class BinaryIndexedTree<V> {

    private CircularDoublyLinkedMap<Integer, TreeNode<V>> root;
    private int size = 0;

    BinaryIndexedTree(int index, V value) {
        root = new CircularDoublyLinkedMap<Integer, TreeNode<V>>(index, new TreeNode<>(value));
    }

    BinaryIndexedTree() {
        root = new CircularDoublyLinkedMap<Integer, TreeNode<V>>();
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

    void addLeft(int index, V value) {
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
        root.putToHead(index, node);
        size++;
    }

    void addRight(int index, V value) {
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
        root.putToTail(index, node);
        size++;
    }

    V get(int index) {
        int nodeIdx = root.indexOfValue(root.getNode(index).getValue());
        if(nodeIdx == index && root.get(index) != null) {
            return root.getNode(index).getValue().getValue()
        }
        return null;
    }

    void delete(int index) {
        int nodeIdx = root.indexOfValue(root.getNode(index).getValue());
        if(nodeIdx == index) {
            root.remove(index);
        }
        size--;
    }
}