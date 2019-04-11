package groovydatastructuresalgorithms.Nodes

import groovydatastructuresalgorithms.NodeInterfaces.INodeValue

class TreeNode<V> implements INodeValue<V> {

    private V value
    private TreeNode<V> right
    private TreeNode<V> left
    private TreeNode<V> middle

    TreeNode(V value) {
        setValue(value)
        setLeft(null)
        setRight(null)
        setMiddle(null)
    }

    void setRight(TreeNode<V> right) {
        this.right = right
    }

    void setLeft(TreeNode<V> left) {
        this.left = left
    }

    void setMiddle(TreeNode<V> middle) {
        this.middle = middle
    }

    @Override
    void setValue(V value) {
        this.value = value
    }

    TreeNode<V> Right() {
        return right
    }

    TreeNode<V> Left() {
        return left
    }

    TreeNode<V> Middle() {
        return middle
    }

    @Override
    V getValue() {
        return value
    }
}

