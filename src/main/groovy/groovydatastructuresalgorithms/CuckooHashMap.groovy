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

import groovydatastructuresalgorithms.api.interfaces.IMap
import groovydatastructuresalgorithms.api.nodes.hashing.CuckooHashBucketEntryNode

//Follows a DoublyLinkedList for addition and deletion of values, but each node is hashed/rehashed by CuckooHashBucketEntryNode
class CuckooHashMap<K, V> implements IMap<K, V> {

    private CuckooHashBucketEntryNode.HashingMap<K, V> head
    private CuckooHashBucketEntryNode.HashingMap<K, V> tail
    private int size

    CuckooHashMap(K key, V value) {
        head = new CuckooHashBucketEntryNode.HashingMap<K, V>(key, value)
    }

    CuckooHashMap() {
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

    CuckooHashBucketEntryNode.HashingMap<K, V> put(K key, V value) {
        CuckooHashBucketEntryNode.HashingMap<K, V> node = new CuckooHashBucketEntryNode.HashingMap<K, V>(key, value)
        if (size == 0 || head == null) {
            node.setNext(node)
            node.setPrev(node);
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
            Expand(node)
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
        for(int i = 0; i < size; i++) {
            if (getNode(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private CuckooHashBucketEntryNode.HashingMap<K, V> getNode(int i) {
        CuckooHashBucketEntryNode.HashingMap<K, V> p = null
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

    Map.Entry<K, V> mapEntry() {
        for(int i = 0; i < size(); i++) {
            println indexOfKey(getNode(i).getKey())
            if(getNode(i) != null) {
                return new MapEntry(getNode(i).getKey(), getNode(i).getValue())
            };
        }
        return null;
    }

    Set<K> keySet() {
        Set<K> key = new LinkedHashSet<>();
        for (int i = 0; i < size(); i++) {
            key.add(getNode(i).getKey())
        }
        return key;
    }

    Set<V> valueSet() {
        Set<V> value = new LinkedHashSet<>();
        for (int i = 0; i < size(); i++) {
            value.add(getNode(i).getValue())
        }
        return value;
    }

    Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es = new LinkedHashSet<>();
        for(int i = 0; i < head.getCapacity(); i++) {
            if(getNode(i) != null) {
                es.add(new MapEntry(getNode(i).getKey(), getNode(i).getValue()));
            }
        }
        return es;
    }

    private int Load() {
        return head.HashBucketLoad()
    }

    private void Expand(CuckooHashBucketEntryNode.HashingMap<K, V> node) {
        node.setCapacity(node.getCapacity() * 2);
    }
/*
    private static void Shrink(CuckooHashBucketEntryNode.HashingMap<K, V> node) {
        node.setCapacity(node.getCapacity() / 2);
    }
    */
}
