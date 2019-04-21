/*
 * Copyright [2018] [Martin Kelly]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package groovydatastructuresalgorithms.Nodes.Hash

import com.google.common.hash.HashCode
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import fnv.FNV
import groovydatastructuresalgorithms.CircularDoublyLinkedMap
import groovydatastructuresalgorithms.Nodes.ListNode
import groovydatastructuresalgorithms.Nodes.MapNode
import groovydatastructuresalgorithms.Nodes.TableNode
import groovydatastructuresalgorithms.Nodes.TreeNode

import java.security.MessageDigest

//HashBucketEntryNodeCH: Refers to Hashing using Cuckoo Collision
//WORK IN PROGRESS: HashingMap mostly Complete. Uses Cuckoo Hashing
class HashBucketEntryCHNode {

    private static HashBucketNode buckets
    private static int[] bucketEntries
    private static final int defaultCapacity = 11
    private static final double defaultLoadFactor = 0.9

    static class HashingList<V> extends ListNode<V> {

        private final CircularDoublyLinkedMap<Integer, HashingList<V>> table1 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingList<V>> table2 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingList<V>> table3 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingList<V>> table4 = new CircularDoublyLinkedMap<>();

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
            HashingList<V> node = new HashingList(value);
            if(table1 == null || table1.isEmpty() && table2 == null || table2.isEmpty() && table3 == null || table3.isEmpty() && table4 == null || table4.isEmpty()) {
                table1.put(bucketEntries[MD5(value)], node);
                table2.put(bucketEntries[SHA1(value)], node);
                table3.put(bucketEntries[FNV1A(value)], node);
                table4.put(bucketEntries[MURMUR3(value)], node);
            }
            //MD5
            if(table1.containsKey(bucketEntries[MD5(key)]) && table1 != null) {
                HashingList<V> prev = table1.get(bucketEntries[MD5(value)]);
                if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table1.put(bucketEntries[MD5(value)], node);
            }
            //SHA1
            if(table2.containsKey(bucketEntries[SHA1(value)]) && table2 != null) {
                HashingList<V> prev = table2.get(bucketEntries[SHA1(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table2.put(bucketEntries[SHA1(value)], node);
            }
            //FNV1A
            if(table3.containsKey(bucketEntries[FNV1A(value)]) && table3 != null) {
                HashingList<V> prev = table3.get(bucketEntries[FNV1A(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table3.put(bucketEntries[FNV1A(value)], node);
            }
            //MURMUR3
            if(table4.containsKey(bucketEntries[MURMUR3(value)]) && table4 != null) {
                HashingList<V> prev = table4.get(bucketEntries[MURMUR3(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                }
                table4.put(bucketEntries[MURMUR3(value)], node);
            }

            table1.put(bucketEntries[MD5(value)], node);
            table2.put(bucketEntries[SHA1(value)], node);
            table3.put(bucketEntries[FNV1A(value)], node);
            table4.put(bucketEntries[MURMUR3(value)], node);
            return node;
        }

        V getEntry(V value) {
            if(table1.get(bucketEntries[MD5(value)]).getValue() == value) {
                return table1.get(bucketEntries[MD5(value)]).getValue();
            }
            if(table2.get(bucketEntries[SHA1(value)]).getValue() == value) {
                return table2.get(bucketEntries[SHA1(value)]).getValue();
            }
            if(table3.get(bucketEntries[FNV1A(value)]).getValue() == value) {
                return table3.get(bucketEntries[FNV1A(key)]).getValue();
            }
            if(table4.get(bucketEntries[MURMUR3(value)]).getValue() == value) {
                return table4.get(bucketEntries[MURMUR3(value)]).getValue();
            }
            return null;
        }

        void removeEntry(V value) {
            if (table1.containsKey(bucketEntries[MD5(value)])) {
                table1.remove(bucketEntries[MD5(value)])
            }
            if (table2.containsKey(bucketEntries[SHA1(value)])) {
                table2.remove(bucketEntries[SHA1(value)])
            }
            if(table3.containsKey(bucketEntries[FNV1A(value)])) {
                table3.remove(bucketEntries[FNV1A(value)]);
            }
            if(table4.containsKey(bucketEntries[MURMUR3(value)])) {
                table4.remove(bucketEntries[MURMUR3(value)]);
            }
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

        private int MD5(V value) {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int FNV1A(V value) {
            byte[] digest = FNV.fnv1a(value.toString().getBytes(), 128);
            BigInteger no = new BigInteger(1, digest);
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int MURMUR3(V value) {
            HashFunction hf = Hashing.murmur3_128()
            HashCode hc = hf.newHasher()
                    .putBytes(value.toString().getBytes())
                    .hash()
            BigInteger no = new BigInteger(1, hc.asBytes())
            int hash = no % buckets.getCapacity();
            return hash;
        }
    }

    static class HashingTree<V> extends TreeNode<V> {

        private final CircularDoublyLinkedMap<Integer, HashingTree<V>> table1 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTree<V>> table2 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTree<V>> table3 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTree<V>> table4 = new CircularDoublyLinkedMap<>();

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
            HashingTree<V> node = new HashingTree(value);
            if(table1 == null || table1.isEmpty() && table2 == null || table2.isEmpty() && table3 == null || table3.isEmpty() && table4 == null || table4.isEmpty()) {
                table1.put(bucketEntries[MD5(value)], node);
                table2.put(bucketEntries[SHA1(value)], node);
                table3.put(bucketEntries[FNV1A(value)], node);
                table4.put(bucketEntries[MURMUR3(value)], node);
            }
            //MD5
            if(table1.containsKey(bucketEntries[MD5(key)]) && table1 != null) {
                HashingTree<V> prev = table1.get(bucketEntries[MD5(value)]);
                if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table1.put(bucketEntries[MD5(value)], node);
            }
            //SHA1
            if(table2.containsKey(bucketEntries[SHA1(value)]) && table2 != null) {
                HashingTree<V> prev = table2.get(bucketEntries[SHA1(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table2.put(bucketEntries[SHA1(value)], node);
            }
            //FNV1A
            if(table3.containsKey(bucketEntries[FNV1A(value)]) && table3 != null) {
                HashingTree<V> prev = table3.get(bucketEntries[FNV1A(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(value)])) {
                    table4.put(bucketEntries[MURMUR3(value)], prev);
                }
                table3.put(bucketEntries[FNV1A(value)], node);
            }
            //MURMUR3
            if(table4.containsKey(bucketEntries[MURMUR3(value)]) && table4 != null) {
                HashingTree<V> prev = table4.get(bucketEntries[MURMUR3(value)]);
                if(!table1.containsKey(bucketEntries[MD5(value)])) {
                    table1.put(bucketEntries[MD5(value)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(value)])) {
                    table2.put(bucketEntries[SHA1(value)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(value)])) {
                    table3.put(bucketEntries[FNV1A(value)], prev);
                }
                table4.put(bucketEntries[MURMUR3(value)], node);
            }

            table1.put(bucketEntries[MD5(value)], node);
            table2.put(bucketEntries[SHA1(value)], node);
            table3.put(bucketEntries[FNV1A(value)], node);
            table4.put(bucketEntries[MURMUR3(value)], node);
            return node;
        }

        V getEntry(V value) {
            if(table1.get(bucketEntries[MD5(value)]).getValue() == value) {
                return table1.get(bucketEntries[MD5(value)]).getValue();
            }
            if(table2.get(bucketEntries[SHA1(value)]).getValue() == value) {
                return table2.get(bucketEntries[SHA1(value)]).getValue();
            }
            if(table3.get(bucketEntries[FNV1A(value)]).getValue() == value) {
                return table3.get(bucketEntries[FNV1A(key)]).getValue();
            }
            if(table4.get(bucketEntries[MURMUR3(value)]).getValue() == value) {
                return table4.get(bucketEntries[MURMUR3(value)]).getValue();
            }
            return null;
        }

        void removeEntry(V value) {
            if (table1.containsKey(bucketEntries[MD5(value)])) {
                table1.remove(bucketEntries[MD5(value)])
            }
            if (table2.containsKey(bucketEntries[SHA1(value)])) {
                table2.remove(bucketEntries[SHA1(value)])
            }
            if(table3.containsKey(bucketEntries[FNV1A(value)])) {
                table3.remove(bucketEntries[FNV1A(value)]);
            }
            if(table4.containsKey(bucketEntries[MURMUR3(value)])) {
                table4.remove(bucketEntries[MURMUR3(value)]);
            }
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

        private int MD5(V value) {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int FNV1A(V value) {
            byte[] digest = FNV.fnv1a(value.toString().getBytes(), 128);
            BigInteger no = new BigInteger(1, digest);
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int MURMUR3(V value) {
            HashFunction hf = Hashing.murmur3_128()
            HashCode hc = hf.newHasher()
                    .putBytes(value.toString().getBytes())
                    .hash()
            BigInteger no = new BigInteger(1, hc.asBytes())
            int hash = no % buckets.getCapacity();
            return hash;
        }
    }

    static class HashingMap<K, V> extends MapNode<K, V> {

        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table1 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table2 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table3 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingMap<K, V>> table4 = new CircularDoublyLinkedMap<>();

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
            if(table1 == null || table1.isEmpty() && table2 == null || table2.isEmpty() && table3 == null || table3.isEmpty() && table4 == null || table4.isEmpty()) {
                table1.put(bucketEntries[MD5(key)], node);
                table2.put(bucketEntries[SHA1(key)], node);
                table3.put(bucketEntries[FNV1A(key)], node);
                table4.put(bucketEntries[MURMUR3(key)], node);
            }
            //MD5
            if(table1.containsKey(bucketEntries[MD5(key)]) && table1 != null) {
                HashingMap<K, V> prev = table1.get(bucketEntries[MD5(key)]);
                if(!table2.containsKey(bucketEntries[SHA1(key)])) {
                    table2.put(bucketEntries[SHA1(key)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(key)])) {
                    table3.put(bucketEntries[FNV1A(key)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(key)])) {
                    table4.put(bucketEntries[MURMUR3(key)], prev);
                }
                table1.put(bucketEntries[MD5(key)], node);
            }
            //SHA1
            if(table2.containsKey(bucketEntries[SHA1(key)]) && table2 != null) {
                HashingMap<K, V> prev = table2.get(bucketEntries[SHA1(key)]);
                if(!table1.containsKey(bucketEntries[MD5(key)])) {
                    table1.put(bucketEntries[MD5(key)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(key)])) {
                    table3.put(bucketEntries[FNV1A(key)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(key)])) {
                    table4.put(bucketEntries[MURMUR3(key)], prev);
                }
                table2.put(bucketEntries[SHA1(key)], node);
            }
            //FNV1A
            if(table3.containsKey(bucketEntries[FNV1A(key)]) && table3 != null) {
                HashingMap<K, V> prev = table3.get(bucketEntries[FNV1A(key)]);
                if(!table1.containsKey(bucketEntries[MD5(key)])) {
                    table1.put(bucketEntries[MD5(key)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(key)])) {
                    table2.put(bucketEntries[SHA1(key)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(key)])) {
                    table4.put(bucketEntries[MURMUR3(key)], prev);
                }
                table3.put(bucketEntries[FNV1A(key)], node);
            }
            //MURMUR3
            if(table4.containsKey(bucketEntries[MURMUR3(key)]) && table4 != null) {
                HashingMap<K, V> prev = table4.get(bucketEntries[MURMUR3(key)]);
                if(!table1.containsKey(bucketEntries[MD5(key)])) {
                    table1.put(bucketEntries[MD5(key)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(key)])) {
                    table2.put(bucketEntries[SHA1(key)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(key)])) {
                    table3.put(bucketEntries[FNV1A(key)], prev);
                }
                table4.put(bucketEntries[MURMUR3(key)], node);
            }

            table1.put(bucketEntries[MD5(key)], node);
            table2.put(bucketEntries[SHA1(key)], node);
            table3.put(bucketEntries[FNV1A(key)], node);
            table4.put(bucketEntries[MURMUR3(key)], node);
            return node;
        }

        V getEntry(K key) {
            if(table1.get(bucketEntries[MD5(key)]).getKey() == key) {
                return table1.get(bucketEntries[MD5(key)]).getValue();
            }
            if(table2.get(bucketEntries[SHA1(key)]).getKey() == key) {
                return table2.get(bucketEntries[SHA1(key)]).getValue();
            }
            if(table3.get(bucketEntries[FNV1A(key)]).getKey() == key) {
                return table3.get(bucketEntries[FNV1A(key)]).getValue();
            }
            if(table4.get(bucketEntries[MURMUR3(key)]).getKey() == key) {
                return table4.get(bucketEntries[MURMUR3(key)]).getValue();
            }
            return null;
        }

        void removeEntry(K key) {
            if (table1.containsKey(bucketEntries[MD5(key)])) {
                table1.remove(bucketEntries[MD5(key)])
            }
            if (table2.containsKey(bucketEntries[SHA1(key)])) {
                table2.remove(bucketEntries[SHA1(key)])
            }
            if(table3.containsKey(bucketEntries[FNV1A(key)])) {
                table3.remove(bucketEntries[FNV1A(key)]);
            }
            if(table4.containsKey(bucketEntries[MURMUR3(key)])) {
                table4.remove(bucketEntries[MURMUR3(key)]);
            }
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

        private int MD5(K key) {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int SHA1(K key) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int FNV1A(K key) {
            byte[] digest = FNV.fnv1a(key.toString().getBytes(), 128);
            BigInteger no = new BigInteger(1, digest);
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int MURMUR3(K key) {
            HashFunction hf = Hashing.murmur3_128()
            HashCode hc = hf.newHasher()
                    .putBytes(key.toString().getBytes())
                    .hash()
            BigInteger no = new BigInteger(1, hc.asBytes())
            int hash = no % buckets.getCapacity();
            return hash;
        }
    }

    static class HashingTable<R, C, V> extends TableNode<R, C, V> {

        private final CircularDoublyLinkedMap<Integer, HashingTable<R, C, V>> table1 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTable<R, C, V>> table2 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTable<R, C, V>> table3 = new CircularDoublyLinkedMap<>();
        private final CircularDoublyLinkedMap<Integer, HashingTable<R, C, V>> table4 = new CircularDoublyLinkedMap<>();

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
            if(table1 == null || table1.isEmpty() && table2 == null || table2.isEmpty() && table3 == null || table3.isEmpty() && table4 == null || table4.isEmpty()) {
                table1.put(bucketEntries[MD5(row, column)], node);
                table2.put(bucketEntries[SHA1(row, column)], node);
                table3.put(bucketEntries[FNV1A(row, column)], node);
                table4.put(bucketEntries[MURMUR3(row, column)], node);
            }
            //MD5
            if(table1.containsKey(bucketEntries[MD5(row, column)]) && table1 != null) {
                HashingTable<R, C, V> prev = table1.get(bucketEntries[MD5(row, column)]);
                if(!table2.containsKey(bucketEntries[SHA1(row, column)])) {
                    table2.put(bucketEntries[SHA1(row, column)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(row, column)])) {
                    table3.put(bucketEntries[FNV1A(row, column)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(row, column)])) {
                    table4.put(bucketEntries[MURMUR3(row, column)], prev);
                }
                table1.put(bucketEntries[MD5(row, column)], node);
            }
            //SHA1
            if(table2.containsKey(bucketEntries[SHA1(row, column)]) && table2 != null) {
                HashingTable<R, C, V> prev = table2.get(bucketEntries[SHA1(row, column)]);
                if(!table1.containsKey(bucketEntries[MD5(row, column)])) {
                    table1.put(bucketEntries[MD5(row, column)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(row, column)])) {
                    table3.put(bucketEntries[FNV1A(row, column)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(row, column)])) {
                    table4.put(bucketEntries[MURMUR3(row, column)], prev);
                }
                table2.put(bucketEntries[SHA1(row, column)], node);
            }
            //FNV1A
            if(table3.containsKey(bucketEntries[FNV1A(row, column)]) && table3 != null) {
                HashingTable<R, C, V> prev = table3.get(bucketEntries[FNV1A(row, column)]);
                if(!table1.containsKey(bucketEntries[MD5(row, column)])) {
                    table1.put(bucketEntries[MD5(row, column)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(row, column)])) {
                    table2.put(bucketEntries[SHA1(row, column)], prev);
                } else if(!table4.containsKey(bucketEntries[MURMUR3(row, column)])) {
                    table4.put(bucketEntries[MURMUR3(row, column)], prev);
                }
                table3.put(bucketEntries[FNV1A(row, column)], node);
            }
            //MURMUR3
            if(table4.containsKey(bucketEntries[MURMUR3(row, column)]) && table4 != null) {
                HashingTable<R, C, V> prev = table4.get(bucketEntries[MURMUR3(row, column)]);
                if(!table1.containsKey(bucketEntries[MD5(row, column)])) {
                    table1.put(bucketEntries[MD5(row, column)], prev);
                } else if(!table2.containsKey(bucketEntries[SHA1(row, column)])) {
                    table2.put(bucketEntries[SHA1(row, column)], prev);
                } else if(!table3.containsKey(bucketEntries[FNV1A(row, column)])) {
                    table3.put(bucketEntries[FNV1A(row, column)], prev);
                }
                table4.put(bucketEntries[MURMUR3(row, column)], node);
            }

            table1.put(bucketEntries[MD5(row, column)], node);
            table2.put(bucketEntries[SHA1(row, column)], node);
            table3.put(bucketEntries[FNV1A(row, column)], node);
            table4.put(bucketEntries[MURMUR3(row, column)], node);
            return node;
        }

        V getEntry(R row, C column) {
            if(table1.get(bucketEntries[MD5(row, column)]).getRow() == row && table1.get(bucketEntries[MD5(row, column)]).getColumn() == column) {
                return table1.get(bucketEntries[MD5(row, column)]).getValue();
            }
            if(table2.get(bucketEntries[SHA1(row, column)]).getRow() == row && table2.get(bucketEntries[SHA1(row, column)]).getColumn() == column) {
                return table2.get(bucketEntries[SHA1(row, column)]).getValue();
            }
            if(table3.get(bucketEntries[FNV1A(row, column)]).getRow() == row && table3.get(bucketEntries[FNV1A(row, column)]).getColumn() == column) {
                return table3.get(bucketEntries[FNV1A(row, column)]).getValue();
            }
            if(table4.get(bucketEntries[MURMUR3(row, column)]).getRow() == row && table4.get(bucketEntries[MURMUR3(row, column)]).getColumn() == column) {
                return table4.get(bucketEntries[MURMUR3(row, column)]).getValue();
            }
            return null;
        }

        void removeEntry(R row, C column) {
            if (table1.containsKey(bucketEntries[MD5(row, column)])) {
                table1.remove(bucketEntries[MD5(row, column)])
            }
            if (table2.containsKey(bucketEntries[SHA1(row, column)])) {
                table2.remove(bucketEntries[SHA1(row, column)])
            }
            if(table3.containsKey(bucketEntries[FNV1A(row, column)])) {
                table3.remove(bucketEntries[FNV1A(row, column)]);
            }
            if(table4.containsKey(bucketEntries[MURMUR3(row, column)])) {
                table4.remove(bucketEntries[MURMUR3(row, column)]);
            }
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

        private int MD5(R row, C column) {
            MessageDigest md = MessageDigest.getInstance("MD5")
            byte[] messageDigest1 = md.digest(row.toString().getBytes())
            byte[] messageDigest2 = md.digest(column.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest1 + messageDigest2)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int SHA1(R row, C column) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest1 = md.digest(row.toString().getBytes())
            byte[] messageDigest2 = md.digest(column.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest1 + messageDigest2)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int FNV1A(R row, C column) {
            byte[] digest1 = FNV.fnv1a(row.toString().getBytes(), 128);
            byte[] digest2 = FNV.fnv1a(column.toString().getBytes(), 128);
            BigInteger no = new BigInteger(1, digest1 + digest2);
            int hash = no % buckets.getCapacity()
            return hash;
        }

        private int MURMUR3(R row, C column) {
            HashFunction hf = Hashing.murmur3_128()
            HashCode hc = hf.newHasher()
                    .putBytes(row.toString().getBytes())
                    .putBytes(column.toString().getBytes())
                    .hash()
            BigInteger no = new BigInteger(1, hc.asBytes())
            int hash = no % buckets.getCapacity();
            return hash;
        }
    }
}