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

import groovydatastructuresalgorithms.api.nodes.QueueNode
//Work in Progress
class CircularDoublyLinkedDeque<V> {

    private CircularDoublyLinkedList<QueueNode<V>> head;
    private int size;

    CircularDoublyLinkedDeque(V value) {
        this.head = new CircularDoublyLinkedList<QueueNode<V>>(value);
    }

    CircularDoublyLinkedDeque() {
        this.head = new CircularDoublyLinkedList<QueueNode<V>>();
    }

    boolean isEmpty() {
        if (head == null) {
            return true
        }
        return false
    }

    int size() {
        return size
    }

    void addFirst(V value) {
        QueueNode<V> node = new QueueNode<V>(value);
        if(size == 0 || head == null) {
            node.setFirst(node);
        } else {
            while(node.First() != null) {
                node.First().setFirst(node);
                if(node.First() == null) {
                    node.First().setFirst(node);
                }
            }
        }
        head.addToHead(node);
        size++;
    }

    void addLast(V value) {
        QueueNode<V> node = new QueueNode<V>(value);
        if(size == 0 || head == null) {
            node.setLast(node);
        } else {
            while(node.Last() != null) {
                node.Last().setLast(node);
                if(node.Last() == null) {
                    node.Last().setLast(node);
                }
            }
        }
        head.addToTail(node);
        size++;
    }

    V get(int index) {
        QueueNode<V> node = head.getNode(index).getValue();
        if(node == null) {
            return null;
        }
        return node.getValue();
    }

    V peekFirst() {
        QueueNode<V> node = head.Head().getValue();
        return node.getValue();
    }

    V pollFirst() {
        QueueNode<V> node = head.Head().getValue();
        head.remove(node);
        return node == null ? null : head.remove(node);
    }

    void removeFirst() {
        QueueNode<V> node = head.Head().getValue();
        head.remove(node);
    }

    V peekLast() {
        QueueNode<V> node = head.Tail().getValue();
        return node.getValue();
    }

    V pollLast() {
        QueueNode<V> node = head.Tail().getValue();
        head.remove(node);
        return node == null ? null : head.remove(node);
    }

    void removeLast() {
        QueueNode<V> node = head.Tail().getValue();
        head.remove(node);
    }
}
