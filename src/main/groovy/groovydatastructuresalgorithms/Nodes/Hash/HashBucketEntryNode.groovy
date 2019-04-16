package groovydatastructuresalgorithms.Nodes.Hash

import groovydatastructuresalgorithms.CircularDoublyLinkedMap
import groovydatastructuresalgorithms.Nodes.ListNode
import groovydatastructuresalgorithms.Nodes.MapNode
import groovydatastructuresalgorithms.Nodes.TableNode
import groovydatastructuresalgorithms.Nodes.TreeNode

import java.security.MessageDigest

//WORK IN PROGRESS: HashingMap mostly Complete. Uses Cuckoo Hashing
class HashBucketEntryNode {

    private static HashBucketNode buckets
    private static int[] bucketEntries
    private static final int defaultCapacity = 11
    private static final double defaultLoadFactor = 0.8

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
            int idx = SHA1(value)
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

        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table1 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table2 = new CircularDoublyLinkedMap<>();

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
            HashingMap<K, V> node = new HashingMap(key, value);
            if(table1 == null || table1.isEmpty() && table2 == null || table2.isEmpty()) {
                table1.put(bucketEntries[MD5(key)], node);
                table2.put(bucketEntries[SHA1(key)], node);
            }
            if(table1.containsKey(bucketEntries[MD5(key)]) && table1 != null) {
                HashingMap<K, V> prev = table1.get(bucketEntries[MD5(key)]);
                if(!table2.containsKey(bucketEntries[SHA1(key)])) {
                    //: test if each HashFunction contains key to insert prev
                    table2.put(bucketEntries[SHA1(key)], prev);
                }
                table1.put(bucketEntries[MD5(key)], node);
            }
            if(table2.containsKey(bucketEntries[SHA1(key)]) && table2 != null) {
                HashingMap<K, V> prev = table2.get(bucketEntries[SHA1(key)]);
                if(!table1.containsKey(bucketEntries[MD5(key)])) {
                    //: test if each HashFunction contains key to insert prev
                    table1.put(bucketEntries[MD5(key)], prev);
                }
                table2.put(bucketEntries[SHA1(key)], node);
            }
            table1.put(bucketEntries[MD5(key)], node);
            table2.put(bucketEntries[SHA1(key)], node);
            return node;
        }

        V getEntry(K key) {
            if(table1.get(bucketEntries[MD5(key)]).getKey() == key) {
                return table1.get(bucketEntries[MD5(key)]).getValue();
            }
            if(table2.get(bucketEntries[SHA1(key)]).getKey() == key) {
                return table2.get(bucketEntries[SHA1(key)]).getValue();
            }
            return null;
        }

        void removeEntry(K key) {
            if (table1.get(bucketEntries[MD5(key)]).equals(getEntry(key))) {
                table1.remove(bucketEntries[MD5(key)])
            }
            if (table2.get(bucketEntries[SHA1(key)]).equals(getEntry(key))) {
                table2.remove(bucketEntries[SHA1(key)])
            }
        }

        private int SHA1(K key) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int MD5(K key) {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
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