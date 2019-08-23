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

class QueueNode<V> implements INodeValue<V> {

    private V value;
    private QueueNode<V> first;
    private QueueNode<V> last;

    QueueNode(V value) {
        setValue(value);
        setFirst(null);
        setLast(null);
    }

    void setFirst(QueueNode<V> first) {
        this.first = first
    }

    void setLast(QueueNode<V> last) {
        this.last = last
    }

    @Override
    void setValue(V value) {
        this.value = value
    }

    QueueNode<V> First() {
        return first
    }

    QueueNode<V> Last() {
        return last;
    }

    @Override
    V getValue() {
        return value;
    }
}
