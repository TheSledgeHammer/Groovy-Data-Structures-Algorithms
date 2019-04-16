package groovydatastructuresalgorithms

class Testing {

    static void main(String[] args) {

        GHashTable<Integer, Integer> GHT = new GHashTable<>()
        GHT.put(3, 22);
        GHT.put(2, 21);
        GHT.delete(2)
        println GHT.get(3)
    }
}

