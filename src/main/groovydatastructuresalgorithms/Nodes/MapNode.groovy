package main.groovydatastructuresalgorithms.Nodes

import main.groovydatastructuresalgorithms.NodeInterfaces.IMapNode

class MapNode<K,V> implements IMapNode<K,V> {

    private K key;
    private V value;
    private MapNode<K,V> next;
    private MapNode<K,V> prev;

    MapNode(K key, V value) {
        setKey(key);
        setValue(value);
        setNext(null);
        setPrev(null);
    }

    void setNext(MapNode<K,V> next) {
        this.next = next;
    }

    void setPrev(MapNode<K,V> prev) {
        this.prev = prev;
    }

    MapNode<K,V> Next() {
        return next;
    }

    MapNode<K,V> Prev() {
        return prev;
    }

    @Override
    void setKey(K key) {
        this.key = key;
    }

    @Override
    K getKey() {
        return key;
    }

    @Override
    void setValue(V value) {
        this.value = value;
    }

    @Override
    V getValue() {
        return value;
    }
}