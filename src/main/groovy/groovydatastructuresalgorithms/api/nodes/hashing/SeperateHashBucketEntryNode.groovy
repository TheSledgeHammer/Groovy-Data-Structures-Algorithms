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

package groovydatastructuresalgorithms.api.nodes.hashing

import groovydatastructuresalgorithms.CircularDoublyLinkedTable
import groovydatastructuresalgorithms.api.interfaces.IHashBucketNode
import groovydatastructuresalgorithms.api.nodes.ListNode
import groovydatastructuresalgorithms.api.nodes.MapNode
import groovydatastructuresalgorithms.api.nodes.TableNode
import groovydatastructuresalgorithms.api.nodes.TreeNode

import java.security.MessageDigest
// HashBucketEntryNodeSC: Refers to Hashing using Seperate Chaining
class SeperateHashBucketEntryNode {

    private static HashBucketNode buckets
    private static int[] bucketEntries
    private static final int defaultCapacity = 11
    private static final double defaultLoadFactor = 0.6

    static class HashingList<V> extends ListNode<V> implements IHashBucketNode {

        CircularDoublyLinkedTable<Integer, Integer, ListNode<V>> table = new CircularDoublyLinkedTable<>();

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
            super(null)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTree<V> putEntry(V value) {
            HashingList<V> node = new HashingList(value);
            if(table == null || table.isEmpty()) {
                table.put(bucketEntries[SHA1(value)], 0, node);
            }
            if(table.containsRow(bucketEntries[SHA1(value)]) && table != null) {
                for(int i = 0; i < table.size(); i++) {
                    if(table.indexOf(bucketEntries[SHA1(value)], i) == -1) {
                        table.put(bucketEntries[SHA1(value)], i, node);
                    } else {
                        break;
                    }
                }
            }
            table.put(bucketEntries[SHA1(value)], 0, node);
            return node;
        }

        V getEntry(V value) {
            for(int i = 0; i < table.size(); i++) {
                if(table.indexOf(bucketEntries[SHA1(value)], i) != -1) {
                    if(table.get(bucketEntries[SHA1(value)], i).getValue() == value) {
                        return table.get(bucketEntries[SHA1(value)], i).getValue()
                    }
                }
            }
            return null;
        }

        void removeEntry(V value) {
            for(int i = 0; i < table.size(); i++) {
                if (table.indexOf(bucketEntries[SHA1(value)], i) != -1) {
                    if (table.get(bucketEntries[SHA1(value)], i).getValue() == value) {
                        table.remove(bucketEntries[SHA1(value)], i);
                    }
                }
            }
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        @Override
        int getCapacity() {
            return buckets.getCapacity()
        }

        @Override
        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        @Override
        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        @Override
        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        @Override
        final int HashBucketLoad() {
            return buckets.HashBucketLoad();
        }
    }

    static class HashingTree<V> extends TreeNode<V> implements IHashBucketNode  {

        CircularDoublyLinkedTable<Integer, Integer, TreeNode<V>> table = new CircularDoublyLinkedTable<>();

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
            super(null)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTree<V> putEntry(V value) {
            HashingTree<V> node = new HashingTree(value);
            if(table == null || table.isEmpty()) {
                table.put(bucketEntries[SHA1(value)], 0, node);
            }
            if(table.containsRow(bucketEntries[SHA1(value)]) && table != null) {
                for(int i = 0; i < table.size(); i++) {
                    if(table.indexOf(bucketEntries[SHA1(value)], i) == -1) {
                        table.put(bucketEntries[SHA1(value)], i, node);
                    } else {
                        break;
                    }
                }
            }
            table.put(bucketEntries[SHA1(value)], 0, node);
            return node;
        }

        V getEntry(V value) {
            for(int i = 0; i < table.size(); i++) {
                if(table.indexOf(bucketEntries[SHA1(value)], i) != -1) {
                    if(table.get(bucketEntries[SHA1(value)], i).getValue() == value) {
                        return table.get(bucketEntries[SHA1(value)], i).getValue()
                    }
                }
            }
            return null;
        }

        void removeEntry(V value) {
            for(int i = 0; i < table.size(); i++) {
                if (table.indexOf(bucketEntries[SHA1(value)], i) != -1) {
                    if (table.get(bucketEntries[SHA1(value)], i).getValue() == value) {
                        table.remove(bucketEntries[SHA1(value)], i);
                    }
                }
            }
        }

        private int SHA1(V value) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(value.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        @Override
        int getCapacity() {
            return buckets.getCapacity()
        }

        @Override
        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        @Override
        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        @Override
        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        @Override
        final int HashBucketLoad() {
            return buckets.HashBucketLoad();
        }
    }

    static class HashingMap<K, V> extends MapNode<K, V> implements IHashBucketNode  {

        CircularDoublyLinkedTable<Integer, Integer, HashingMap<K, V>> table = new CircularDoublyLinkedTable<>();

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
            super(null, null)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingMap<K, V> putEntry(K key, V value) {
            HashingMap<K, V> node = new HashingMap(key, value);
            if(table == null || table.isEmpty()) {
                table.put(bucketEntries[SHA1(key)], 0, node);
            }
            if(table.containsRow(bucketEntries[SHA1(key)]) && table != null) {
                for(int i = 0; i < table.size(); i++) {
                    if(table.indexOf(bucketEntries[SHA1(key)], i) == -1) {
                        table.put(bucketEntries[SHA1(key)], i, node);
                    } else {
                        break;
                    }
                }
            }
            table.put(bucketEntries[SHA1(key)], 0, node);
            return node;
        }

        V getEntry(K key) {
            for(int i = 0; i < table.size(); i++) {
                if(table.indexOf(bucketEntries[SHA1(key)], i) != -1) {
                    if(table.get(bucketEntries[SHA1(key)], i).getKey() == key) {
                        return table.get(bucketEntries[SHA1(key)], i).getValue()
                    }
                }
            }
            return null;
        }

        void removeEntry(K key) {
            for(int i = 0; i < table.size(); i++) {
                if (table.indexOf(bucketEntries[SHA1(key)], i) != -1) {
                    if (table.get(bucketEntries[SHA1(key)], i).getKey() == key) {
                        table.remove(bucketEntries[SHA1(key)], i);
                    }
                }
            }
        }

        private int SHA1(K key) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        @Override
        int getCapacity() {
            return buckets.getCapacity()
        }

        @Override
        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        @Override
        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        @Override
        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        @Override
        final int HashBucketLoad() {
            return buckets.HashBucketLoad();
        }
    }

    static class HashingTable<R, C, V> extends TableNode<R, C, V> implements IHashBucketNode  {

        CircularDoublyLinkedTable<Integer, Integer, HashingTable<R, C, V>> table = new CircularDoublyLinkedTable<>();

        HashingTable(R row, C column, V value, int capacity, double loadFactor) {
            super(row, column, value);
            buckets = new HashBucketNode(capacity, loadFactor)
            bucketEntries = new int[capacity];
        }

        HashingTable(R row, C column, V value) {
            super(row, column, value)
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTable() {
            super(null, null, null);
            buckets = new HashBucketNode(defaultCapacity, defaultLoadFactor)
            bucketEntries = new int[defaultCapacity]
        }

        HashingTable<R, C, V> putEntry(R row, C column, V value) {
            HashingTable<R, C, V> node = new HashingTable(row, column, value);
            if(table == null || table.isEmpty()) {
                table.put(bucketEntries[SHA1(row, column)], 0, node);
            }
            if(table.containsRow(bucketEntries[SHA1(row, column)]) && table != null) {
                for(int i = 0; i < table.size(); i++) {
                    if(table.indexOf(bucketEntries[SHA1(row, column)], i) == -1) {
                        table.put(bucketEntries[SHA1(row, column)], i, node);
                    } else {
                        break;
                    }
                }
            }
            table.put(bucketEntries[SHA1(row, column)], 0, node);
            return node;
        }

        V getEntry(R row, C column) {
            for(int i = 0; i < table.size(); i++) {
                if(table.indexOf(bucketEntries[SHA1(row, column)], i) != -1) {
                    if(table.get(bucketEntries[SHA1(row, column)], i).getRow() == row && table.get(bucketEntries[SHA1(row, column)], i).getColumn() == column) {
                        return table.get(bucketEntries[SHA1(row, column)], i).getValue()
                    }
                }
            }
            return null;
        }

        void removeEntry(R row, C column) {
            for(int i = 0; i < table.size(); i++) {
                if (table.indexOf(bucketEntries[SHA1(row, column)], i) != -1) {
                    if (table.get(bucketEntries[SHA1(row, column)], i).getRow() == row && table.get(bucketEntries[SHA1(row, column)], i).getColumn() == column) {
                        table.remove(bucketEntries[SHA1(row, column)], i);
                    }
                }
            }
        }

        private int SHA1(R row, C column) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest1 = md.digest(row.toString().getBytes())
            byte[] messageDigest2 = md.digest(column.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest1 + messageDigest2)
            int hash = no % buckets.getCapacity()
            return hash;
        }

        @Override
        int getCapacity() {
            return buckets.getCapacity()
        }

        @Override
        double getLoadFactor() {
            return buckets.getLoadFactor()
        }

        @Override
        void setCapacity(int capacity) {
            buckets.setCapacity(capacity)
        }

        @Override
        void setLoadFactor(double loadFactor) {
            buckets.setLoadFactor(loadFactor)
        }

        @Override
        final int HashBucketLoad() {
            return buckets.HashBucketLoad();
        }
    }
}
