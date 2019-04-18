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

import groovydatastructuresalgorithms.NodeInterfaces.ITableNode

class TableNode<R, C, V> implements ITableNode<R, C, V> {

    private R row
    private C column
    private V value

    private TableNode<R, C, V> next
    private TableNode<R, C, V> prev

    TableNode(R row, C column, V value) {
        setRow(row)
        setColumn(column)
        setValue(value)
        setNext(null)
        setPrev(null)
    }

    void setNext(TableNode<R, C, V> next) {
        this.next = next
    }

    void setPrev(TableNode<R, C, V> prev) {
        this.prev = prev
    }

    TableNode<R, C, V> Next() {
        return next
    }

    TableNode<R, C, V> Prev() {
        return prev
    }

    @Override
    void setColumn(C column) {
        this.column = column
    }

    @Override
    C getColumn() {
        return column
    }

    @Override
    void setRow(R row) {
        this.row = row
    }

    @Override
    R getRow() {
        return row
    }

    @Override
    void setValue(V value) {
        this.value = value
    }

    @Override
    V getValue() {
        return value
    }
}