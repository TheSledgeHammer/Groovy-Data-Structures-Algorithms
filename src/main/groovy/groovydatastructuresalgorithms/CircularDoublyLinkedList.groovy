package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.Nodes.ListNode


class CircularDoublyLinkedList<V> {

    private ListNode<V> head
    private ListNode<V> tail
    private int size = 0

    CircularDoublyLinkedList(V value) {
        head = new ListNode<>(value)
    }

    CircularDoublyLinkedList() {
        head = null
    }

    ListNode<V> Head() {
        return head
    }

    ListNode<V> Tail() {
        return tail
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

    ListNode<V> add(V value) {
        ListNode<V> node// = new LinkedMapNode<K,V>(key, value);
        if (isEven(size)) {
            node = addToHead(value)
            // System.out.println("Stored at Head Node: " + key + ':' + value);
            return node
        }
        if (!isEven(size)) {
            node = addToTail(value)
            //   System.out.println("Stored at Tail Node: " + key + ':' + value);
            return node
        }
        return null
    }

    ListNode<V> addToHead(V value) {
        ListNode<V> node = new ListNode<V>(value)
        if (size == 0 || head == null) {
            node.setNext(node)
            node.setPrev(node)
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
        return node
    }

    ListNode<V> addToTail(V value) {
        ListNode<V> node = new ListNode<V>(value)
        if (size == 0 || head == null) {
            node.setNext(node)
            node.setPrev(node)
            head = node
            tail = head
        } else {
            node.setPrev(tail)
            tail.setNext(node)
            head.setPrev(node)
            node.setNext(head)
            tail = node
        }
        size++
        return node
    }

    ListNode<V> addAtPos(int index, V value) {
        ListNode<V> node = new ListNode<V>(value)
        if (index == 1) {
            node = addToHead(value)
        }
        ListNode<V> other = head
        for (int i = 2; i <= size; i++) {
            if (i == index) {
                ListNode<V> tmp = other.Next()
                other.setNext(node)
                node.setPrev(other)
                node.setNext(tmp)
                tmp.setPrev(node)
            }
            other = other.Next()
        }
        size++
        return node
    }

    V getByIndex(int index) {
        return getNode(index).getValue()
    }

    V get(V value) {
        int idx = indexOfValue(value);
        if(isEmpty()) {
            return null;
        }
        if (getNode(idx).getValue() == value) {
            return getNode(idx).getValue()
        }
        return null
    }

    V set(int index, V value) {
        ListNode<V> node = getNode(index)
        V val = node.getValue()
        node.setValue(value)
        return val
    }

    int indexOfValue(V value) {
        for(int i = 0; i <= size; i++) {
            if (getNode(i).getValue().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    protected ListNode<V> getNode(int i) {
        ListNode<V> p = null
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

    void deleteFromHead() {
        if (size == 0) {
            return
        } else {
            System.out.println("\ndeleting node " + head.getValue() + " from start")
            head = head.Next()
            size--
        }
    }

    void deleteFromTail() {
        if (size == 0) {
            return
        } else if (size == 1) {
            deleteFromHead()
        } else {
            V x = tail.getValue()
            ListNode<V> prevTail = tail.Prev()
            tail = prevTail
            tail.setNext(null)
            System.out.println("\n deleting node " + x + " from end")
            size--
        }
    }

    void delete(V value) {
        if (size == 0) {
            return
        } else {
            head = getNode(indexOfValue(value));
            head.setValue(null);
            head = head.Next();
            size--;
        }
    }

    Iterator<V> iterator() {
        Set<V> cdll = new HashSet<>()
        for (int i = 0; i < size; i++) {
            cdll.add(head.getValue())
        }
        return cdll.iterator()
    }

    private boolean isEven(int value) {
        if (value % 2 == 0) {
            return true
        }
        return false
    }

    boolean headHasNext() {
        if (head.Next() != null) {
            return true
        }
        return false
    }

    boolean headHasPrev() {
        if (head.Prev() != null) {
            return true
        }
        return false
    }

    boolean tailHasNext() {
        if (tail.Next() != null) {
            return true
        }
        return false
    }

    boolean tailHasPrev() {
        if (tail.Prev() != null) {
            return true
        }
        return false
    }

    V searchNextValue(V value) {
        while (headHasNext()) {
            head = head.Next()
            if (head.getValue().equals(value)) {
                return head.getValue()
            }
        }
        return null
    }

    V searchPrevValue(V value) {
        while (tailHasPrev()) {
            tail = tail.Prev()
            if (tail.getValue().equals(value)) {
                return tail.getValue()
            }
        }
        return null
    }

    V quickSearch(V value) {
        ListNode<V> other = new ListNode<V>(value)
        if (other.getValue() == null) {
            return null
        }
        if (countHead(value) <= countTail(value)) {
            other.setValue(searchNextValue(value))
        }
        if (countHead(value) > countTail(value)) {
            other.setValue(searchPrevValue(value))
        }
        other.setValue(value)
        return value
    }

    private int countHead(V value) {
        int count = 0
        while (headHasNext()) {
            head = head.Next()
            count++
            if (head.getValue().equals(value)) {
                break
            }
        }
        return count
    }

    private int countTail(V value) {
        int count = 0
        while (tailHasPrev()) {
            tail = tail.Prev()
            count++
            if (tail.getValue().equals(value)) {
                break
            }
        }
        return count
    }
}
