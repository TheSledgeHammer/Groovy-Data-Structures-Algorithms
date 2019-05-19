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

package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.api.nodes.hashing.HashBucketEntryCHNode

//Follows a doublylinkedlist, but each node is hashed/ rehashed from a HashBucketEntryNode
//Collisions are resolved using a variation on Cuckoo Hashing
class CuckooHashTable<K, V> {

    private HashBucketEntryCHNode.HashingMap<K, V> head
    private HashBucketEntryCHNode.HashingMap<K, V> tail
    private int size

    CuckooHashTable(K key, V value) {
        head = new HashBucketEntryCHNode.HashingMap<K, V>(key, value)
    }

    CuckooHashTable() {
        head = null
    }

    boolean isEmpty() {
        if (head == null || tail == null) {
            return true
        }
        return false
    }

    int size() {
        return size
    }

    HashBucketEntryCHNode.HashingMap<K, V> put(K key, V value) {
        HashBucketEntryCHNode.HashingMap<K, V> node = new HashBucketEntryCHNode.HashingMap<K, V>(key, value)
        if (size == 0 || head == null) {
            head = node
            tail = head
        } else {
            head.setPrev(node)
            node.setNext(head)
            head = node
        }
        size++
        if (size >= Load()) {
            Resize(node)
        }
        return node.putEntry(key, value)
    }

    V get(K key) {
        int idx = indexOfKey(key);
        if(isEmpty()) {
            return null;
        }
        if (getNode(idx).getKey() == key) {
            return getNode(idx).getEntry(key)
        }
        return null
    }

    void remove(K key) {
        if (size == 0) {
            return
        } else {
            head = getNode(indexOfKey(key));
            head.setKey(null);
            head.setValue(null);
            head = head.Next();
            size--;
        }
        head.removeEntry(key)
    }

    int indexOfKey(K key) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private HashBucketEntryCHNode.HashingMap<K, V> getNode(int i) {
        HashBucketEntryCHNode.HashingMap<K, V> p = null
        if (i < size / 2) {
            p = head.Next()
            for (int j = 0; j < i; j++) {
                p = p.Next()
            }
        } else {
            p = head
            for (int j = size - 1; j > i; j--) {
                p = p.Prev()
            }
        }
        return p
    }

    private int Load() {
        return head.HashBucketLoad()
    }

    private void Resize(HashBucketEntryCHNode.HashingMap<K, V> node) {
        node.setCapacity(node.getCapacity() * 2);
    }
}
