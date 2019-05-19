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
import groovydatastructuresalgorithms.api.nodes.MapNode

import java.security.MessageDigest
// HashBucketEntryNodeSC: Refers to Hashing using Seperate Chaining
class HashBucketEntrySCNode {

    private static HashBucketNode buckets
    private static int[] bucketEntries
    private static final int defaultCapacity = 11
    private static final double defaultLoadFactor = 0.6

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

        @Override
        int getCapacity() {
            return buckets.getCapacity()
        }

        private int SHA1(K key) {
            MessageDigest md = MessageDigest.getInstance("SHA1")
            byte[] messageDigest = md.digest(key.toString().getBytes())
            BigInteger no = new BigInteger(1, messageDigest)
            int hash = no % buckets.getCapacity()
            return hash;
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
