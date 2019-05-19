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

import groovydatastructuresalgorithms.api.interfaces.INodeValue

class TreeNode<V> implements INodeValue<V> {

    private V value
    private TreeNode<V> right
    private TreeNode<V> left
    private TreeNode<V> middle

    TreeNode(V value) {
        setValue(value)
        setLeft(null)
        setRight(null)
        setMiddle(null)
    }

    void setRight(TreeNode<V> right) {
        this.right = right
    }

    void setLeft(TreeNode<V> left) {
        this.left = left
    }

    void setMiddle(TreeNode<V> middle) {
        this.middle = middle
    }

    @Override
    void setValue(V value) {
        this.value = value
    }

    TreeNode<V> Right() {
        return right
    }

    TreeNode<V> Left() {
        return left
    }

    TreeNode<V> Middle() {
        return middle
    }

    @Override
    V getValue() {
        return value
    }
}

