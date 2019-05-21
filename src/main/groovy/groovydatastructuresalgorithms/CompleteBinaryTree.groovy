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

class CompleteBinaryTree<V> {

    BinaryTree<V> root;
    private int size = 0;
    private int leftCounter = 0;
    private int rightCounter = 0;

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
            } else {
                node.addLeft(value);
                leftCounter++;
            }
        }
        root = node;
        size++;
    }

    V get(V value) {
        return root.get(value);
    }

    void delete(V value) {
        if(contains(value) && get(value).equals(value)) {
            println value;
            root.delete(value);
        }
    }

    boolean contains(V value) {
        return root.contains(value);
    }
}
