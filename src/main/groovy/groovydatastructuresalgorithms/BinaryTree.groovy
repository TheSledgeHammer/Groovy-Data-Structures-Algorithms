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
import groovydatastructuresalgorithms.api.nodes.TreeNode

class BinaryTree<V> implements ITree<V>  {

    private CircularDoublyLinkedList<TreeNode<V>> root;
    private int size = 0;

    BinaryTree(V value) {
        root = new CircularDoublyLinkedList<TreeNode<V>>(new TreeNode<>(value));
    }

    BinaryTree() {
        root = new CircularDoublyLinkedList<TreeNode<V>>();
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

    void addLeft(V value) {
        TreeNode<V> node = new TreeNode<V>(value);
        if(size == 0 || root == null) {
            node.setLeft(node);
        } else {
            while(node.Left() != null) {
                node.Left().setLeft(node);
                if(node.Left() == null) {
                    node.Left().setLeft(node);
                }
            }
        }
        root.addToHead(node);
        size++;
    }

    void addRight(V value) {
        TreeNode<V> node = new TreeNode<>(value);
        if(size == 0 || root == null) {
            node.setRight(node);
        } else {
            while(node.Right() != null) {
                node.Right().setRight(node);
                if(node.Right() == null) {
                    node.Right().setRight(node);
                }
            }
        }
        root.addToTail(node);
        size++;
    }

    V get(V value) {
        TreeNode<V> node = null; //new TreeNode<>(value);
        for(int i = 0; i < size; i++) {
            node = root.getNode(i).getValue();
            if(node == null) {
                return null;
            }
            if(node.getValue().equals(value)) {
                return node.getValue();
            }
        }
        return null;
    }

    V get(int index) {
        TreeNode<V> node = root.getNode(index).getValue();
        return node.getValue();
    }

    void delete(V value) {
        TreeNode<V> node = null;// new TreeNode<>(value);
        for(int i = 0; i < size; i++) {
            node = root.getNode(i).getValue();
            if (node.getValue().equals(value)) {
                root.remove(node);
            }
        }
        size--;
    }

    boolean contains(V value) {
        if(value == null) {
            throw new IllegalArgumentException("argument to contains() is null")
        }
        return get(value) != null;
    }

    int indexOf(V value) {
        TreeNode<V> node = null;
        for(int i = 0; i < size; i++) {
            node = root.getNode(i).getValue();
            if (node.getValue().equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
