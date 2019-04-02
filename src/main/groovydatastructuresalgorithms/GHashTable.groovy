package main.groovydatastructuresalgorithms

import main.groovydatastructuresalgorithms.Nodes.Hash.HashBucketEntryNode

class GHashTable<K, V> {

    private HashBucketEntryNode.HashingMap<K, V> head;
    private HashBucketEntryNode.HashingMap<K, V> tail;
    private int size;

    GHashTable(K key, V value) {
        head = new HashBucketEntryNode.HashingMap<K, V>(key, value);
    }

    GHashTable() {
        head = null;
    }

    HashBucketEntryNode.HashingMap<K, V> Head() {
        return head;
    }

    HashBucketEntryNode.HashingMap<K, V> put(K key, V value) {
        HashBucketEntryNode.HashingMap<K, V> node = new HashBucketEntryNode.HashingMap<K, V>(key, value);
        if(size == 0 || head == null) {
            node.setNext(node);
            node.setPrev(node);
            head = node;
            tail = head;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            head.setPrev(node);
            node.setNext(head);
            head = node;
        }
        size++;
        return node.putHashEntry(key, value);
    }

    V get(K key) {
        return head.getHashEntry(key);
    }
}
