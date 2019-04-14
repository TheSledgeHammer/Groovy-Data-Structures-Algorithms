package groovydatastructuresalgorithms.Nodes.Hash

import groovydatastructuresalgorithms.Nodes.ListNode
import groovydatastructuresalgorithms.Nodes.MapNode
import groovydatastructuresalgorithms.Nodes.TableNode
import groovydatastructuresalgorithms.Nodes.TreeNode

import java.security.MessageDigest

//The HashBucketEntryNode provides an extension to any node implementation in this library
//Currently uses SHA1
class HashBucketEntryNode {

    private static HashBucketNode buckets
    private static int[] bucketEntries
    private static final int defaultCapacity = 10
    private static final double defaultLoadFactor = 0.7

    //TODO:
    // Hash Collision Resolution, Hash Algorithm

    static class HashingList<V> extends ListNode<V> {

        private final List<HashingList<V>> entries = new LinkedList<>()

        HashingList(V value, int capacity, double loadFactor) {
            super(value)
            buckets = new HashBucketNode(capacity, loadFactor)
            bucketEntries = new int[capacity]
        }

        HashingList(V value) {
            super(value)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingList() {
            super()
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingList<V> addEntry(V value) {
            HashingList<V> node = new HashingList<>(value)
            int idx = SHA1(key)
            entries.add(bucketEntries[idx], node)
            return node
        }

        //TODO: Fix so index equals value
        V getEntry(int index) {
            HashingList<V> node
            int idx = SHA1()
            return node
        }

        void deleteEntry(V value) {
            int idx = SHA1(value)
            if (entries.get(bucketEntries[idx]).equals(getEntry(value))) {
                entries.remove(bucketEntries[idx])
            }
        }

        //Default Hash Algorithm
        private int Hash(V value) {
            int hash = (value.hashCode() % buckets.getCapacity())
            return hash
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash
        }

        //Node Access to Bucket
        int getCapacity() {
            return buckets.getCapacity()
        }

        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        final int HashBucketLoad() {
            return buckets.HashBucketLoad()
        }
    }

    static class HashingTree<V> extends TreeNode<V> {

        private final List<HashingTree<V>> entries = new LinkedList<>()

        HashingTree(V value, int capacity, double loadFactor) {
            super(value)
            buckets = new HashBucketNode(capacity, loadFactor)
            bucketEntries = new int[capacity]
        }

        HashingTree(V value) {
            super(value)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTree() {
            super()
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTree<V> addEntry(V value) {
            HashingTree<V> node = new HashingTree<>(value)
            int idx = SHA1(key)
            entries.add(bucketEntries[idx], node)
            return node
        }

        //TODO: Fix so index equals value
        V getEntry(int index) {
            HashingTree<V> node
            int idx = SHA1()
            return node
        }

        void deleteEntry(V value) {
            int idx = SHA1(value)
            if (entries.get(bucketEntries[idx]).equals(getEntry(value))) {
                entries.remove(bucketEntries[idx])
            }
        }

        //Default Hash Algorithm
        private int Hash(V value) {
            int hash = (value.hashCode() % buckets.getCapacity())
            return hash
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash
        }

        int getCapacity() {
            return buckets.getCapacity()
        }

        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        final int HashBucketLoad() {
            return buckets.HashBucketLoad()
        }
    }

    static class HashingMap<K, V> extends MapNode<K, V> {

        private final List<HashingMap<K, V>> entries = new LinkedList<>()

        HashingMap(K key, V value, int capacity, double loadFactor) {
            super(key, value)
            buckets = new HashBucketNode(capacity, loadFactor)
            bucketEntries = new int[capacity]
        }

        HashingMap(K key, V value) {
            super(key, value)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingMap() {
            super()
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingMap<K, V> putEntry(K key, V value) {
            HashingMap<K, V> node = new HashingMap(key, value)
            int idx = SHA1(key);

            //Hash Resolution
            /*for(int k = 0; k < entries.size(); k++) {
                if (HashCollisionDetected(entries.get(bucketEntries[k]).getKey(), key)) {
                    int idx0 = HashResolution(entries.get(bucketEntries[k]).getKey(), key);
                    entries.add(bucketEntries[idx0], node);
                } else {
                    entries.add(bucketEntries[idx], node);
                }
            }*/
            entries.add(bucketEntries[idx], node);
            return node;
        }

        //Cannot Currently Resolve Entries with Hash Resolution implemented
        V getEntry(K key) {
            int idx = SHA1(key)
            HashingMap<K, V> node = entries.get(bucketEntries[idx])
            while (node != null) {
                if (node.getKey() == key) {
                    return node.getValue()
                }
                node = node.Next()
            }
            return null
        }

        void deleteEntry(K key) {
            int idx = SHA1(key)
            if (entries.get(bucketEntries[idx]).equals(getEntry(key))) {
                entries.remove(bucketEntries[idx])
            }
        }

        private boolean HashCollisionDetected(K keyA, K keyB) {
            int idx0 = SHA1(keyA);
            int idx1 = SHA1(keyB);
            if(idx0 == idx1) {
                return true
            }
            return false;
        }

        //Linear Probing
        private int HashResolution(K keyA, K keyB) {
            int idx0 = SHA1(keyA);
            int idx1 = SHA1(keyB);
            if(idx0 == idx1) {
                for(int i = idx0; i < getCapacity(); i++) {
                    if(i != idx1) {
                        return i;
                    }
                }
            }
            return idx0;
        }

        //A Closed Addressing: List of matched hashed entries
        private final List<List<HashingMap<K, V>>> CAEntries = new LinkedList<>();

        //Default Hash Algorithm
        int Hash(K key) {
            int hash = (key.hashCode() % buckets.getCapacity())
            return hash
        }

        private int SHA1(K key) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash
        }

        int getCapacity() {
            return buckets.getCapacity()
        }

        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        final int HashBucketLoad() {
            return buckets.HashBucketLoad();
        }
    }

    static class HashingTable<R, C, V> extends TableNode<R, C, V> {

        private final List<HashingTable<R, C, V>> entries = new LinkedList<>()

        HashingTable(R row, C column, V value, int capacity, double loadFactor) {
            super(row, column, value)
            buckets = new HashBucketNode(capacity, loadFactor)
            bucketEntries = new int[capacity]
        }

        HashingTable(R row, C column, V value) {
            super(row, column, value)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTable() {
            super()
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTable<R, C, V> putEntry(R row, C column, V value) {
            HashingTable<R, C, V> node = new HashingTable<>(row, column, value)
            int idx = SHA1(row, column)
            entries.add(bucketEntries[idx], node)
            return node
        }

        V getEntry(R row, C column) {
            int idx = SHA1(row, column)
            HashingTable<R, C, V> node = entries.get(bucketEntries[idx])
            while (node != null) {
                if (node.getRow() == row && node.getColumn() == column) {
                    return node.getValue()
                }
                node = node.Next()
            }
            return null
        }

        void deleteEntry(R row, C column) {
            int idx = SHA1(row, column)
            if (entries.get(bucketEntries[idx]).equals(getEntry(row, column))) {
                entries.remove(bucketEntries[idx])
            }
        }

        //Default Hash Algorithm
        private int Hash(R row, C column) {
            int hash = (row.hashCode() + column.hashCode() % buckets.getCapacity())
            return hash
        }

        private int SHA1(R row, C column) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest1 = md.digest(row.toString().getBytes())
            byte[] messageDigest2 = md.digest(column.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest1 + messageDigest2)
            int hash = no % buckets.getCapacity()
            return hash
        }

        int getCapacity() {
            return buckets.getCapacity()
        }

        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        final int HashBucketLoad() {
            return buckets.HashBucketLoad()
        }
    }
}