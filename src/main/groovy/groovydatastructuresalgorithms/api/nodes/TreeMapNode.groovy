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

package groovydatastructuresalgorithms.api.nodes

import groovydatastructuresalgorithms.api.interfaces.IMap
import groovydatastructuresalgorithms.api.interfaces.INodeKey
import groovydatastructuresalgorithms.api.interfaces.INodeValue

class TreeMapNode<K, V> implements IMap<K, V>, INodeKey<K>, INodeValue<V> {

    private K key;
    private V value
    private TreeMapNode<K, V> right
    private TreeMapNode<K, V> left
    private TreeMapNode<K, V> middle

    TreeMapNode(K key, V value) {
        setKey(key)
        setValue(value)
        setLeft(null)
        setRight(null)
        setMiddle(null)
    }

    void setRight(TreeMapNode<K, V> right) {
        this.right = right
    }

    void setLeft(TreeMapNode<K, V> left) {
        this.left = left
    }

    void setMiddle(TreeMapNode<K, V> middle) {
        this.middle = middle
    }

    TreeMapNode<K, V> Right() {
        return right
    }

    TreeMapNode<K, V> Left() {
        return left
    }

    TreeMapNode<K, V> Middle() {
        return middle
    }

    @Override
    void setKey(K key) {
        this.key = key;
    }

    @Override
    void setValue(V value) {
        this.value = value;
    }

    @Override
    K getKey() {
        return key;
    }

    @Override
    V getValue() {
        return value
    }
}
