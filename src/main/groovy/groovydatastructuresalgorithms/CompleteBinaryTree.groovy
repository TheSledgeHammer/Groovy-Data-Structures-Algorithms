package groovydatastructuresalgorithms

class CompleteBinaryTree<V> {

    private BinaryTree<V> root;
    private int size = 0;
    private int leftCounter = 0;
    private int rightCounter = 0;
    private int depthCount = 0;
    private boolean depth = false;

    CompleteBinaryTree() {
        root = null;
    }

    CompleteBinaryTree(V value) {
        root = new BinaryTree<V>(value);
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
        BinaryTree<V> node = new BinaryTree<>(value);
        if(size == 0 || root == null) {
            node.addLeft(value);
        }
        if(size > 0 || root != null) {
            if(leftCounter > rightCounter) {
                node.addRight(value);
                rightCounter++;
            } else {
                depth = true;
                node.addLeft(value);
                leftCounter++;
            }
        }
        root = node;
        size++;
        //Depth Calculation: To ensure the tree depth is only increased when each branch has 2 child nodes both left & right
        if(size() < 2) {
            depthCount++
        } else {
            if (leftCounter == rightCounter && depth) {
                println "depth should be + 1"
                if (leftCounter % 2 == 0 && rightCounter % 2 == 0) {
                    println "left is divisible by 2"
                    println "right is divisible by 2"
                    depthCount++;
                }
                depth = false;
            }
        }
    }
}
