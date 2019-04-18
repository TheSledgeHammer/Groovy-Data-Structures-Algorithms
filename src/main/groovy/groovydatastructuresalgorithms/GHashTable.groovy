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

import groovydatastructuresalgorithms.Nodes.Hash.HashBucketEntryNodeCH

//Follows a doublylinkedlist, but each node is hashed/ rehashed from a HashBucketEntryNode
//Collisions are resolved using a variation on Cuckoo Hashing
class GHashTable<K, V> {

    private HashBucketEntryNodeCH.HashingMap<K, V> head
    private HashBucketEntryNodeCH.HashingMap<K, V> tail
    private int size

    GHashTable(K key, V value) {
        head = new HashBucketEntryNodeCH.HashingMap<K, V>(key, value)
    }

    GHashTable() {
        head = null
    }

    int size() {
        return size
    }

    HashBucketEntryNodeCH.HashingMap<K, V> put(K key, V value) {
        HashBucketEntryNodeCH.HashingMap<K, V> node = new HashBucketEntryNodeCH.HashingMap<K, V>(key, value)
        if (size == 0 || head == null) {
            node.setNext(node)
            node.setPrev(node)
            head = node
            tail = head
        } else {
            node.setPrev(tail)
            tail.setNext(node)
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
        return head.getEntry(key);
    }

    void remove(K key) {
        if (size == 0) {
            return
        } else {
            head = head.Next()
            size--
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

    private HashBucketEntryNodeCH.HashingMap<K, V> getNode(int i) {
        HashBucketEntryNodeCH.HashingMap<K, V> p = null
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

    private K searchNextKey(K key) {
        while (headHasNext()) {
            head = head.Next()
            if (head.getKey().equals(key)) {
                return head.getKey()
            }
        }
        return null
    }

    private V searchNextValue(V value) {
        while (headHasNext()) {
            head = head.Next()
            if (head.getValue().equals(value)) {
                return head.getValue()
            }
        }
        return null
    }

    private K searchPrevKey(K key) {
        while (tailHasPrev()) {
            tail = tail.Prev()
            if (tail.getKey().equals(key)) {
                return tail.getKey()
            }
        }
        return null
    }

    private V searchPrevValue(V value) {
        while (tailHasPrev()) {
            tail = tail.Prev()
            if (tail.getValue().equals(value)) {
                return tail.getValue()
            }
        }
        return null
    }

    private boolean headHasNext() {
        if (head.Next() != null) {
            return true
        }
        return false
    }

    private boolean headHasPrev() {
        if (head.Prev() != null) {
            return true
        }
        return false
    }

    private boolean tailHasNext() {
        if (tail.Next() != null) {
            return true
        }
        return false
    }

    private boolean tailHasPrev() {
        if (tail.Prev() != null) {
            return true
        }
        return false
    }

    private int Load() {
        return head.HashBucketLoad()
    }

    private void Resize(HashBucketEntryNodeCH.HashingMap<K, V> node) {
        node.setCapacity(node.getCapacity() * 2);
    }
}
