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

package groovydatastructuresalgorithms.Nodes

import groovydatastructuresalgorithms.NodeInterfaces.INodeValue

class ListNode<V> implements INodeValue<V> {

    private V value
    private ListNode<V> next
    private ListNode<V> prev

    ListNode(V value) {
        setValue(value)
        setNext(null)
        setPrev(null)
    }

    void setNext(ListNode<V> next) {
        this.next = next
    }

    void setPrev(ListNode<V> prev) {
        this.prev = prev
    }

    @Override
    void setValue(V value) {
        this.value = value
    }

    ListNode<V> Next() {
        return next
    }

    ListNode<V> Prev() {
        return prev
    }

    @Override
    V getValue() {
        return value
    }
}
