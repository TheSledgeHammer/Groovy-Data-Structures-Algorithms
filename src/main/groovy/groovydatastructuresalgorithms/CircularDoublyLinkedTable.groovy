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

import groovydatastructuresalgorithms.api.nodes.TableNode

class CircularDoublyLinkedTable<R, C, V> {

    private TableNode<R, C, V> head
    private TableNode<R, C, V> tail
    private int size

    CircularDoublyLinkedTable(R row, C column, V value) {
        head = new TableNode<R, C, V>(row, column, value)
    }

    CircularDoublyLinkedTable() {
        head = null
    }

    TableNode<R, C, V> Head() {
        return head
    }

    TableNode<R, C, V> Tail() {
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

    TableNode<R, C, V> put(R row, C column, V value) {
        TableNode<R, C, V> node// = new LinkedMapNode<K,V>(key, value);
        if (size % 2 == 0) {
            node = putToHead(row, column, value)
            return node
        }
        if (size % 2 != 0) {
            node = putToTail(row, column, value)
            return node
        }
        return null
    }

    TableNode<R, C, V> putToHead(R row, C column, V value) {
        TableNode<R, C, V> node = new TableNode<R, C, V>(row, column, value)
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

    TableNode<R, C, V> putToTail(R row, C column, V value) {
        TableNode<R, C, V> node = new TableNode<R, C, V>(row, column, value)
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

    private TableNode<R, C, V> getNode(int i) {
        TableNode<R, C, V> p = null
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

    V get(R row, C column) {
        int idx = indexOf(row, column);
        if(isEmpty()) {
            return null;
        }
        if (getNode(idx).getRow() == row && getNode(idx).getColumn() == column) {
            return getNode(idx).getValue()
        }
        return null
    }

    int indexOfRow(R row) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getRow().equals(row)) {
                return i;
            }
        }
        return -1;
    }

    int indexOfColumn(C column) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getColumn().equals(column)) {
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

    int indexOf(R row, C column) {
        if(indexOfRow(row) == indexOfColumn(column)) {
            int index = (indexOfRow(row) + indexOfColumn(column)) / 2;
            return index;
        }
        return -1;
    }

    int indexOf(R row, C column, V value) {
        if(indexOfRow(row) == indexOfColumn(column) && indexOfRow(row) == indexOfValue(value) &&  indexOfColumn(column) == indexOfValue(value)) {
            int index = (indexOfRow(row) + indexOfColumn(column) + indexOfValue(value)) / 3;
            return index;
        }
        return -1;
    }

    boolean containsRow(R row) {
        if (row == null) {
            throw new IllegalArgumentException("argument to contains() is null")
        }
        for(int i = 0; i < size; i++) {
            if(getNode(i).getRow() == row) {
                return getNode(i).getRow() != null
            }
        }
        return false;
    }

    boolean containsColumn(C column) {
        if (column == null) {
            throw new IllegalArgumentException("argument to contains() is null")
        }
        for(int i = 0; i < size; i++) {
            if(getNode(i).getColumn() == column) {
                return getNode(i).getColumn() != null
            }
        }
        return false;
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

    void removeFromHead() {
        if (size == 0) {
            return
        } else {
            System.out.println("\ndeleting node " + head.getValue() + " from start")
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
            TableNode<R, C, V> prevTail = tail.Prev()
            tail = prevTail
            tail.setNext(null)
            System.out.println("\ndeleting node " + x + " from end")
            size--
        }
    }

    void remove(R row, C column) {
        if (size == 0) {
            return
        } else {
            head = getNode(indexOf(row, column));
            head.setRow(null);
            head.setColumn(null);
            head.setValue(null);
            head = head.Next();
            size--;
        }
    }
}
