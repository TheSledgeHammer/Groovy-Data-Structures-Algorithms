package main.groovydatastructuresalgorithms.Nodes

import main.groovydatastructuresalgorithms.NodeInterfaces.ITableNode

class TableNode<R,C,V> implements ITableNode<R,C,V> {

    private R row;
    private C column;
    private V value;

    private TableNode<R,C,V> next;
    private TableNode<R,C,V> prev;

    TableNode(R row, C column, V value) {
        setRow(row);
        setColumn(column);
        setValue(value);
        setNext(null);
        setPrev(null);
    }

    void setNext(TableNode<R,C,V> next) {
        this.next = next;
    }

    void setPrev(TableNode<R,C,V> prev) {
        this.prev = prev;
    }

    TableNode<R,C,V> Next() {
        return next;
    }

    TableNode<R,C,V> Prev() {
        return prev;
    }

    @Override
    void setColumn(C column) {
        this.column = column;
    }

    @Override
    C getColumn() {
        return column;
    }

    @Override
    void setRow(R row) {
        this.row = row;
    }

    @Override
    R getRow() {
        return row;
    }

    @Override
    void setValue(V value) {
        this.value = value;
    }

    @Override
    V getValue() {
        return value;
    }
}