package main.groovydatastructuresalgorithms.Nodes

import main.groovydatastructuresalgorithms.NodeInterfaces.INodeValue

class ListNode<V> implements INodeValue<V> {

    private V value;
    private ListNode<V> next;
    private ListNode<V> prev;

    ListNode(V value) {
        setValue(value);
        setNext(null);
        setPrev(null);
    }

    void setNext(ListNode<V> next) {
        this.next = next;
    }

    void setPrev(ListNode<V> prev) {
        this.prev = prev;
    }

    @Override
    void setValue(V value) {
        this.value = value;
    }

    ListNode<V> Next() {
        return next;
    }

    ListNode<V> Prev() {
        return prev;
    }

    @Override
    V getValue() {
        return value;
    }
}
