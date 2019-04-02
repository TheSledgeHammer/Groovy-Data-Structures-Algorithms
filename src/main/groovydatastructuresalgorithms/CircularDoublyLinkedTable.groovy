package main.groovydatastructuresalgorithms

import main.groovydatastructuresalgorithms.Nodes.TableNode

class CircularDoublyLinkedTable<R,C,V> {

    private TableNode<R,C,V> head;
    private TableNode<R,C,V> tail;
    private int size;

    CircularDoublyLinkedTable(R row, C column, V value) {
        head = new TableNode<R, C, V>(row, column, value);
    }

    CircularDoublyLinkedTable() {
        head = null;
    }

    TableNode<R,C,V> Head() {
        return head;
    }

    TableNode<R,C,V> Tail() {
        return tail;
    }

    boolean isEmpty() {
        if(head == null || tail == null) {
            return true;
        }
        return false;
    }

    int size() {
        return size;
    }

    TableNode<R,C,V>  putToHead(R row, C column, V value) {
        TableNode<R,C,V> node = new TableNode<R, C, V>(row, column, value);
        if(size == 0 || head == null) {
            node.setNext(node);
            node.setPrev(node);
            head = node;
            tail = head;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            head.setPrev(node);
            node.setNext(head);
            head = node;
        }
        size++;
        return node;
    }

    TableNode<R,C,V> putToTail(R row, C column, V value) {
        TableNode<R,C,V> node = new TableNode<R,C,V>(row, column, value);
        if(size == 0 || head == null) {
            node.setNext(node);
            node.setPrev(node);
            head = node;
            tail = head;
        } else {
            node.setPrev(tail);
            tail.setNext(node);
            head.setPrev(node);
            node.setNext(head);
            tail = node;
        }
        size++;
        return node;
    }

    protected TableNode<R,C,V> getNode(int i) {
        TableNode<R,C,V> p = null;
        if(i < size / 2) {
            p = head.Next();
            for(int j = 0; j < i; j++) {
                p = p.Next();
            }
        } else {
            p = head;
            for(int j = size - 1; j > i; j--) {
                p = p.Prev();
            }
        }
        return p;
    }

    V get(R row, C column) {
        while(headHasNext()) {
            head.setRow(searchNextRow(row));
            head.setColumn(searchNextColumn(column));
            if (head.getRow() == row && head.getColumn() == column) {
                return head.getValue();
            }
        }
        return null;
    }

    void deleteFromHead() {
        if(size == 0) {
            return;
        } else {
            System.out.println("\ndeleting node " + head.getValue() + " from start");
            head = head.Next();
            size--;
        }
    }

    void deleteFromTail() {
        if(size == 0) {
            return;
        } else if(size == 1) {
            deleteFromHead();
        } else {
            V x = tail.getValue();
            TableNode<R,C,V> prevTail = tail.Prev();
            tail = prevTail;
            tail.setNext(null);
            System.out.println("\ndeleting node " + x + " from end");
            size--;
        }
    }
//Buggy: Doesn't seem to delete;
    void deleteAtPos(int index) {
        if(index == 1) {
            if(size == 1) {
                head = null;
                tail = null;
                size = 0;
                return;
            }

            head = head.Next();
            head.setPrev(tail);
            tail.setNext(head);
            size--;
            return;
        }

        if(index == size) {
            tail = tail.Prev();
            tail.setNext(head);
            head.setPrev(tail);
            size--;
        }

        TableNode<R,C,V> other = head.Next();
        for(int i = 2; i <= size; i++) {
            if(i == index) {
                TableNode<R,C,V> p = other.Prev();
                TableNode<R,C,V> n = other.Next();
                p.setNext(n);
                n.setPrev(p);
                size--;
                return;
            }
            other = other.Next();
        }
    }
    /*
    int indexOfKey(R row) {
        int index = 0;
        while(headHasNext()) {
            index++;
            if(getNode(index).getRow().equals(row)) {
                break;
            }
        }
        if(index < 0) {
            return 0;
        }
        return index - 1;
    }


    int indexOfColumn(C column) {
        int index = 0;
        while(headHasNext()) {
            index++;
            if(getNode(index).getColumn().equals(column)) {
                break;
            }
        }
        if(index < 0) {
            return 0;
        }
        return index - 1;
    }

    int indexOfValue(V value) {
        int index = 0;
        while(headHasNext()) {
            index++;
            if(getNode(index).getValue().equals(value) ) {
                break;
            }
        }
        if(index < 0) {
            return 0;
        }
        return index - 1;
    }

    int indexOf(R row, C column, V value) {
        //int index = 0;
        if(indexOfKey(row) == indexOfColumn(column) && indexOfKey(row) == indexOfValue(value) &&  indexOfColumn(column) == indexOfValue(value)) {
            int index = (indexOfKey(key) + indexOfColumn(column) + indexOfValue(value)) / 3;
            return index;
        }
        return -1;
    }
    */

    boolean headHasNext() {
        if(head.Next() != null) {
            return true;
        }
        return false;
    }

    boolean headHasPrev() {
        if(head.Prev() != null) {
            return true;
        }
        return false;
    }

    boolean tailHasNext() {
        if(tail.Next() != null) {
            return true;
        }
        return false;
    }

    boolean tailHasPrev() {
        if(tail.Prev() != null) {
            return true;
        }
        return false;
    }

    R searchNextRow(R row) {
        while(headHasNext()) {
            head = head.Next();
            if(head.getRow().equals(row)) {
                return head.getRow();
            }
        }
        return null;
    }

    C searchNextColumn(C column) {
        while(headHasNext()) {
            head = head.Next();
            if(head.getColumn().equals(column)) {
                return head.getColumn();
            }
        }
        return null;
    }

    V searchNextValue(V value) {
        while(headHasNext()) {
            head = head.Next();
            if(head.getValue().equals(value)) {
                return head.getValue();
            }
        }
        return null;
    }

    R searchPrevRow(R row) {
        while(tailHasPrev()) {
            tail = tail.Prev();
            if(tail.getRow().equals(row)) {
                return tail.getRow();
            }
        }
        return null;
    }

    C searchPrevColumn(C column) {
        while(tailHasPrev()) {
            tail = tail.Prev();
            if(tail.getColumn().equals(column)) {
                return tail.getColumn();
            }
        }
        return null;
    }

    V searchPrevValue(V value) {
        while(tailHasPrev()) {
            tail = tail.Prev();
            if(tail.getValue().equals(value)) {
                return tail.getValue();
            }
        }
        return null;
    }
}
