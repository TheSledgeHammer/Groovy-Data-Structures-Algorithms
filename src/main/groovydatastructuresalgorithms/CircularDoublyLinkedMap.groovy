package main.groovydatastructuresalgorithms


import main.groovydatastructuresalgorithms.Nodes.MapNode

class CircularDoublyLinkedMap<K,V> {

    private MapNode<K,V> head;
    private MapNode<K,V> tail;
    private int size = 0;

    CircularDoublyLinkedMap(K key, V value) {
        head = new MapNode<>(key, value);
    }

    CircularDoublyLinkedMap() {
        head = null;
    }

    MapNode<K,V> Head() {
        return head;
    }

    MapNode<K,V> Tail() {
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

    //Adds key-value pair to head or tail node depending on if size is even or odd numbered
    MapNode<K,V> put(K key, V value) {
        MapNode<K,V> node;// = new LinkedMapNode<K,V>(key, value);
        if(isEven(size)) {
            node = putToHead(key, value);
            // System.out.println("Stored at Head Node: " + key + ':' + value);
            return node;
        }
        if(!isEven(size)) {
            node = putToTail(key, value);
            //   System.out.println("Stored at Tail Node: " + key + ':' + value);
            return node;
        }
        return null;
    }

    MapNode<K,V> put(int index, K key, V value) {
        MapNode<K,V> node = new MapNode<K,V>(key, value);
        if(index == 1) {
            node = putToHead(key, value);
        }
        MapNode<K,V> other = head;
        for(int i = 2; i <= size; i++) {
            if(i == index) {
                MapNode<K,V> tmp = other.Next();
                other.setNext(node);
                node.setPrev(other);
                node.setNext(tmp);
                tmp.setPrev(node);
            }
            other = other.Next();
        }
        size++;
        return node;
    }

    MapNode<K,V> putToHead(K key, V value) {
        MapNode<K,V> node = new MapNode<K,V>(key, value);
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

    MapNode<K,V> putToTail(K key, V value) {
        MapNode<K,V> node = new MapNode<K,V>(key, value);
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

    K searchNextKey(K key) {
        while(headHasNext()) {
            head = head.Next();
            if(head.getKey().equals(key)) {
                return head.getKey();
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

    K searchPrevKey(K key) {
        while(tailHasPrev()) {
            tail = tail.Prev();
            if(tail.getKey().equals(key)) {
                return tail.getKey();
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

    V quickSearch(K key, V value) {
        MapNode<K,V> other = new MapNode<K,V>(key,value);
        if(other.getValue() == null) {
            return null;
        }
        if(countHead(value) <= countTail(value)) {
            other.setValue(searchNextValue(value));
        }
        if(countHead(value) > countTail(value)) {
            other.setValue(searchPrevValue(value));
        }
        other.setValue(value);
        return value;
    }

    private int countHead(V value) {
        int count = 0;
        while(headHasNext()) {
            head = head.Next();
            count++;
            if(head.getValue().equals(value)) {
                break;
            }
        }
        return count;
    }

    private int countTail(V value) {
        int count = 0;
        while(tailHasPrev()) {
            tail = tail.Prev();
            count++;
            if(tail.getValue().equals(value)) {
                break;
            }
        }
        return count;
    }

    private boolean isEven(int value) {
        if(value % 2 == 0) {
            return true;
        }
        return false;
    }

    int indexOfKey(K key) {
        int index = 0;
        while(headHasNext()) {
            index++;
            if(getNode(index).getKey().equals(key)) {
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

    int indexOf(K key, V value) {
        int index;// = 0;
        if(indexOfKey(key) == indexOfValue(value)) {
            index = (indexOfKey(key) + indexOfValue(value)) / 2;
            return index;
        }
        return -1;
    }

    MapNode<K,V> getNode(int i) {
        MapNode<K,V> p = null;
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

    V get(K key) {
        while(headHasNext()) {
            head.setKey(searchNextKey(key));
            if (head.getKey() == key) {
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
            MapNode<K,V> prevTail = tail.Prev();
            tail = prevTail;
            tail.setNext(null);
            System.out.println("\ndeleting node " + x + " from end");
            size--;
        }
    }

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

        MapNode<K,V> other = head.Next();
        for(int i = 2; i <= size; i++) {
            if(i == index) {
                MapNode<K,V> p = other.Prev();
                MapNode<K,V> n = other.Next();
                p.setNext(n);
                n.setPrev(p);
                size--;
                return;
            }
            other = other.Next();
        }
    }

    boolean contains(K key) {
        if(key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }
}
