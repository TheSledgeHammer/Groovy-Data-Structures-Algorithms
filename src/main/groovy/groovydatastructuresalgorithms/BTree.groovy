package groovydatastructuresalgorithms
//Work in Progress
class BTree<V> {

    private TernaryTree<V> root;
    private int size = 0;
    private int leftCounter = 0;
    private int rightCounter = 0;

    BTree() {
        root = null;
    }

    BTree(V value) {
        root = new TernaryTree<>(value);
    }

    boolean isEmpty() {
        if(root == null) {
            return true;
        }
        return false;
    }

    int size() {
        return size;
    }

    void add(V value) {
        TernaryTree<V> node = new TernaryTree<>(value);
        if (size == 0 || root == null) {
            node.addLeft(value);
            root = node;
        }
        if (size > 0 || root != null) {

        }
        root = node;
        size++;
    }

    V get(V value) {
        return root.get(value);
    }

    void delete(V value) {
        root.delete(value);
    }
}
