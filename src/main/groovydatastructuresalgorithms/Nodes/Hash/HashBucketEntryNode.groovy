package main.groovydatastructuresalgorithms.Nodes.Hash

import main.groovydatastructuresalgorithms.NodeInterfaces.IHashBucketEntryNode
import main.groovydatastructuresalgorithms.Nodes.ListNode
import main.groovydatastructuresalgorithms.Nodes.MapNode
import main.groovydatastructuresalgorithms.Nodes.TableNode
import main.groovydatastructuresalgorithms.Nodes.TreeNode

class HashBucketEntryNode {

    private static HashBucketNode buckets;
    private static int[] bucketEntries;
    private static final int defaultCapacity = 10;
    private static final double defaultLoadFactor = 0.75;

    //TODO:
    // Hash Collision Resolution
    // Hash Buckets Resize

    static class HashingList<V> extends ListNode<V> implements IHashBucketEntryNode.HashingList<V> {

        private final List<Integer> hashFunctions = new ArrayList<>();
        private final List<HashingList<V>> entries = new LinkedList<>();

        HashingList(V value, int capacity, double loadFactor) {
            super(value);
            buckets = new HashBucketNode(capacity, loadFactor);
            bucketEntries = new int[capacity];
        }

        HashingList(V value) {
            super(value);
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        HashingList() {
            super();
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        @Override
        HashingList<V> addHashEntry(V value) {
            HashingList<V> node = new HashingList<>(value);
            int idx = Hash(key);
            entries.add(bucketEntries[idx], node);
            return node;
        }

        //TODO: Fix so index equals value
        @Override
        V getHashEntry(int index) {
            HashingList<V> node;
            int idx = Hash();
            return node
        }

        @Override
        void addHash(int hash) {
            hashFunctions.add(hash);
        }

        @Override
        int getHash(int index) {
            return hashFunctions.get(index);
        }

        private int Hash(V value) {
            int hash = (value.hashCode() % buckets.getCapacity());
            hashFunctions.add(hash);
            return hash;
        }
    }

    static class HashingTree<V> extends TreeNode<V> implements IHashBucketEntryNode.HashingTree<V> {

        private final List<Integer> hashFunctions = new ArrayList<>();
        private final List<HashingTree<V>> entries = new LinkedList<>();

        HashingTree(V value, int capacity, double loadFactor) {
            super(value);
            buckets = new HashBucketNode(capacity, loadFactor);
            bucketEntries = new int[capacity];
        }

        HashingTree(V value) {
            super(value);
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        HashingTree() {
            super();
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        @Override
        HashingTree<V> addHashEntry(V value) {
            HashingTree<V> node = new HashingTree<>(value);
            int idx = Hash(key);
            entries.add(bucketEntries[idx], node);
            return node;
        }

        //TODO: Fix so index equals value
        @Override
        V getHashEntry(int index) {
            HashingTree<V> node;
            int idx = Hash();
            return node
        }

        @Override
        void addHash(int hash) {
            hashFunctions.add(hash);
        }

        @Override
        int getHash(int index) {
            return hashFunctions.get(index);
        }

        private int Hash(V value) {
            int hash = (value.hashCode() % buckets.getCapacity());
            hashFunctions.add(hash);
            return hash;
        }
    }

    static class HashingMap<K,V> extends MapNode<K,V> implements IHashBucketEntryNode.HashingMap<K,V> {

        private final List<Integer> hashFunctions = new ArrayList<>();
        private final List<HashingMap<K,V>> entries = new LinkedList<>();

        HashingMap(K key, V value, int capacity, double loadFactor) {
            super(key, value);
            buckets = new HashBucketNode(capacity, loadFactor);
            bucketEntries = new int[capacity];
        }

        HashingMap(K key, V value) {
            super(key, value);
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        HashingMap() {
            super();
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        @Override
        HashingMap<K, V> putHashEntry(K key, V value) {
            HashingMap<K, V> node = new HashingMap(key, value);
            int idx = Hash(key);
            entries.add(bucketEntries[idx], node);
            return node;
        }

        @Override
        V getHashEntry(K key) {
            int idx = Hash(key);
            HashingMap<K, V> node = entries.get(bucketEntries[idx]);
            while(node !=  null) {
                if(node.getKey() == key) {
                    return node.getValue();
                }
                node = node.Next();
            }
            return null;
        }

        @Override
        void addHash(int hash) {
            hashFunctions.add(hash);
        }

        @Override
        int getHash(int index) {
            return hashFunctions.get(index);
        }

        private int Hash(K key) {
            int hash = (key.hashCode() % buckets.getCapacity());
            hashFunctions.add(hash);
            return hash;
        }
    }

    static class HashingTable<R,C,V> extends TableNode<R,C,V> implements IHashBucketEntryNode.HashingTable<R,C,V> {

        private final List<Integer> hashFunctions = new ArrayList<>();
        private final List<HashingTable<R,C,V>> entries = new LinkedList<>();

        HashingTable(R row, C column, V value, int capacity, double loadFactor) {
            super(row, column, value);
            buckets = new HashBucketNode(capacity, loadFactor);
            bucketEntries = new int[capacity];
        }

        HashingTable(R row, C column, V value) {
            super(row, column, value);
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        HashingTable() {
            super();
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor);
            bucketEntries = new int[defaultCapacity];
        }

        @Override
        HashingTable<R, C, V> putHashEntry(R row, C column, V value) {
            HashingTable<R,C,V> node = new HashingTable<>(row, column, value);
            int idx = Hash(row, column);
            entries.add(bucketEntries[idx], node);
            return node;
        }

        @Override
        V getHashEntry(R row, C column) {
            int idx = Hash(row, column);
            HashingTable<R,C,V> node = entries.get(bucketEntries[idx]);
            while(node !=  null) {
                if (node.getRow() == row && node.getColumn() == column) {
                    return node.getValue();
                }
                node = node.Next();
            }
            return null
        }

        @Override
        void addHash(int hash) {
            hashFunctions.add(hash);
        }

        @Override
        int getHash(int index) {
            return hashFunctions.get(index);
        }

        private int Hash(R row, C column) {
            int hash = (row.hashCode() + column.hashCode() % buckets.getCapacity());
            hashFunctions.add(hash);
            return hash;
        }
    }
}
