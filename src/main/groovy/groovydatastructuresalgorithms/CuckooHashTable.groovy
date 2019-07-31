package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.api.nodes.hashing.CuckooHashBucketEntryNode

class CuckooHashTable<R, C, V> {

    private CuckooHashBucketEntryNode.HashingTable<R, C, V> head
    private CuckooHashBucketEntryNode.HashingTable<R, C, V> tail
    private int size

    CuckooHashTable(R row, C column, V value) {
        head = new CuckooHashBucketEntryNode.HashingTable<R, C, V>(row, column, value);
    }

    CuckooHashTable() {
        head =  null;
    }

    boolean isEmpty() {
        if (head == null || tail == null) {
            return true
        }
        return false
    }

    int size() {
        return size
    }

    CuckooHashBucketEntryNode.HashingTable<R, C, V> put(R row, C column, V value) {
        CuckooHashBucketEntryNode.HashingTable<R, C, V> node = new CuckooHashBucketEntryNode.HashingTable<R, C, V>(row, column, value)
        if(size == 0 || head == null) {
            node.setNext(node)
            node.setPrev(node);
            head = node
            tail = head
        } else {
            node.setPrev(tail)
            tail.setNext(node)
            head.setPrev(node)
            node.setNext(head)
            head = node
        }
        size++
        if (size >= Load()) {
            Expand(node)
        }
        return node.putEntry(row, column, value);
    }

    V get(R row, C column) {
        int idx = indexOf(row, column);
        if(isEmpty()) {
            return null;
        }
        if (getNode(idx).getRow() == row && getNode(idx).getColumn() == column) {
            return getNode(idx).getEntry(row, column);
        }
        return null
    }

    int indexOfRow(R row) {
        for(int i = 0; i <= size; i++) {
           if (getNode(i).getRow().equals(row)) {
                return i;
            }
        }
        return -1;
    }

    int indexOfColumn(C column) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getColumn().equals(column)) {
                return i;
            }
        }
        return -1;
    }

    int indexOfValue(V value) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getValue().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    int indexOf(R row, C column) {
        if(indexOfRow(row) == indexOfColumn(column)) {
            float index = (indexOfRow(row) + indexOfColumn(column)) / 2;
            return index;
        }
        return -1;
    }

    int indexOf(R row, C column, V value) {
        if(indexOfRow(row) == indexOfColumn(column) && indexOfRow(row) == indexOfValue(value) &&  indexOfColumn(column) == indexOfValue(value)) {
            float index = (indexOfRow(row) + indexOfColumn(column) + indexOfValue(value)) / 3;
            return index;
        }
        return -1;
    }

    private CuckooHashBucketEntryNode.HashingTable<R, C, V> getNode(int i) {
        CuckooHashBucketEntryNode.HashingTable<R, C, V> p = null
        if (i < size / 2) {
            p = head.Next()
            for (int j = 0; j < i; j++) {
                p = p.Next()
            }
        } else {
            p = head
            for (int j = size - 1; j > i; j--) {
                p = p.Prev()
            }
        }
        return p
    }

    void remove(R row, C column) {
        if (size == 0) {
            return
        } else {
            head = getNode(indexOf(row, column));
            head.setRow(null);
            head.setColumn(null);
            head.setValue(null);
            head = head.Next();
            size--;
        }
        head.removeEntry(row, column);
    }

    private int Load() {
        return head.HashBucketLoad()
    }

    private void Expand(CuckooHashBucketEntryNode.HashingTable<R, C, V> node) {
        node.setCapacity(node.getCapacity() * 2);
    }
}
