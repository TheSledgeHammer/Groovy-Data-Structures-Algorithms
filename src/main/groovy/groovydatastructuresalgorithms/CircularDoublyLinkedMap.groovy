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

import groovydatastructuresalgorithms.api.nodes.MapNode

class CircularDoublyLinkedMap<K, V> {

    private MapNode<K, V> head
    private MapNode<K, V> tail
    private int size = 0

    CircularDoublyLinkedMap(K key, V value) {
        head = new MapNode<>(key, value)
    }

    CircularDoublyLinkedMap() {
        head = null
    }

    MapNode<K, V> Head() {
        return head
    }

    MapNode<K, V> Tail() {
        return tail
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

    //Adds key-value pair to head or tail node depending on if size is even or odd numbered
    MapNode<K, V> put(K key, V value) {
        MapNode<K, V> node// = new LinkedMapNode<K,V>(key, value);
        if (size % 2 == 0) {
            node = putToHead(key, value)
            // System.out.println("Stored at Head Node: " + key + ':' + value);
            return node
        }
        if (size % 2 != 0) {
            node = putToTail(key, value)
            //   System.out.println("Stored at Tail Node: " + key + ':' + value);
            return node
        }
        return null
    }

    MapNode<K, V> put(int index, K key, V value) {
        MapNode<K, V> node = new MapNode<K, V>(key, value)
        if (index == 1) {
            node = putToHead(key, value)
        }
        MapNode<K, V> other = head
        for (int i = 2; i <= size; i++) {
            if (i == index) {
                MapNode<K, V> tmp = other.Next()
                other.setNext(node)
                node.setPrev(other)
                node.setNext(tmp)
                tmp.setPrev(node)
            }
            other = other.Next()
        }
        size++
        return node
    }

    MapNode<K, V> putToHead(K key, V value) {
        MapNode<K, V> node = new MapNode<K, V>(key, value)
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
        return node
    }

    MapNode<K, V> putToTail(K key, V value) {
        MapNode<K, V> node = new MapNode<K, V>(key, value)
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
            tail = node
        }
        size++
        return node
    }

    int indexOfKey(K key) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    int indexOfValue(V value) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getValue().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    int indexOf(K key, V value) {
        int index// = 0;
        if (indexOfKey(key) == indexOfValue(value)) {
            index = (indexOfKey(key) + indexOfValue(value)) / 2
            return index
        }
        return -1
    }

    protected MapNode<K, V> getNode(int i) {
        MapNode<K, V> p = null
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

    V get(K key) {
        int idx = indexOfKey(key);
        if(isEmpty()) {
            return null;
        }
        if (getNode(idx).getKey() == key) {
            return getNode(idx).getValue()
        }
        return null
    }

    void removeFromHead() {
        if (size == 0) {
            return
        } else {
            System.out.println("\n deleting node " + head.getValue() + " from start")
            head = head.Next()
            size--
        }
    }

    void removeFromTail() {
        if (size == 0) {
            return
        } else if (size == 1) {
            removeFromHead()
        } else {
            V x = tail.getValue()
            MapNode<K, V> prevTail = tail.Prev()
            tail = prevTail
            tail.setNext(null)
            System.out.println("\n deleting node " + x + " from end")
            size--
        }
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
    }

    boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null")
        }
        return get(key) != null
    }

    boolean containsValue(V value) {
        if(value == null) {
            throw new IllegalArgumentException("argument to contains() is null")
        }
        for(int i = 0; i < size; i++) {
            if(getNode(i).getValue() == value) {
                return getNode(i).getValue() != null
            }
        }
        return false;
    }
}
