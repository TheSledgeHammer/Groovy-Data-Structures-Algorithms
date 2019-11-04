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

import groovydatastructuresalgorithms.api.interfaces.ITree

class CompleteBinaryTree<V> implements ITree<V> {

    private BinaryTree<V> root;
    private int size = 0;
    private int leftCounter = 0;
    private int rightCounter = 0;
    //Counter Lists, for keeping tree in sync upon deletion of objects or values stored
    private CircularDoublyLinkedList<BinaryTree<V>> left = new CircularDoublyLinkedList<>();
    private CircularDoublyLinkedList<BinaryTree<V>> right = new CircularDoublyLinkedList<>();

    CompleteBinaryTree() {
        root = null;
    }

    CompleteBinaryTree(V value) {
        root = new BinaryTree<V>(value);
    }

    boolean isEmpty() {
        if(root == null) {
            return true;
        }
        return false;
    }

    int size() {
        return size;
    }

    void add(V value) {
        BinaryTree<V> node = new BinaryTree<>(value);
        if (size == 0 || root == null) {
            node.addLeft(value);
            root = node;
        }
        if (size > 0 || root != null) {
            if (leftCounter > rightCounter) {
                node.addRight(value);
                rightCounter++;
                right.add(node);
            } else {
                node.addLeft(value);
                leftCounter++;
                left.add(node);
            }
        }
        root = node;
        size++;
    }

    V get(V value) {
        for(int i = 0; i < size; i++) {
            if(root.get(value) == null) {
                return null;
            }
        }
        return root.get(value);
    }

    V getByIndex(int index) {
        return root.get(index)
    }

    void delete(V value) {
        if(SyncLeft(value)) {
            left.remove(root);
            leftCounter--;
        }
        if(SyncRight(value)) {
            right.remove(root);
            rightCounter--;
        }
        root.delete(value);
    }

    boolean contains(V value) {
        return root.contains(value);
    }

    int indexOf(V value) {
        return root.indexOf(value);
    }

    private boolean SyncLeft(V value) {
        BinaryTree<V> node = null;
        for(int i = 0; i < left.size(); i++) {
            node = left.getNode(i).getValue();
            if(node.contains(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean SyncRight(V value) {
        BinaryTree<V> node = null;
        for(int i = 0; i < right.size(); i++) {
            node = right.getNode(i).getValue();
            if(node.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
