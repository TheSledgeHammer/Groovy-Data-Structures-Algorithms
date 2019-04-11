package groovydatastructuresalgorithms.NodeInterfaces

import groovydatastructuresalgorithms.Nodes.Hash.HashBucketEntryNode

interface IHashBucketEntryNode {

    interface HashingList<V> {

        HashBucketEntryNode.HashingList<V> addHashEntry(V value);

        V getHashEntry(int index);

        void addHash(int hash);

        int getHash(int index);
    }

    interface HashingTree<V> {

        HashBucketEntryNode.HashingTree<V> addHashEntry(V value);

        V getHashEntry(int index);

        void addHash(int hash);

        int getHash(int index);
    }

    interface HashingMap<K, V> {

        HashBucketEntryNode.HashingMap<K, V> putHashEntry(K key, V value);

        V getHashEntry(K key);

        void addHash(int hash);

        int getHash(int index);
    }

    interface HashingTable<R, C, V> {

        HashBucketEntryNode.HashingTable<R, C, V> putHashEntry(R row, C column, V value);

        V getHashEntry(R row, C column);

        void addHash(int hash);

        int getHash(int index);
    }
}