package groovydatastructuresalgorithms

class Testing {

    static void main(String[] args) {

        GHashTable<Integer, Integer> GHT = new GHashTable<>()
        GHT.put(3, 22);
        GHT.put(4, 23);
        //GHT.put(2, 21);
        //GHT.Col(3, 3)

        println GHT.get(4)

        CircularDoublyLinkedMap<Integer, Integer> HM = new CircularDoublyLinkedMap<>();
        HM.put(1, 23);
    }
}

