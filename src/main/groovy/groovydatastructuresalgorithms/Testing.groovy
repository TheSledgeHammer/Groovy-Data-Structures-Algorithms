package groovydatastructuresalgorithms;

class Testing {

    static void main(String[] args) {

        GHashTable<Integer, Integer> GHT = new GHashTable<>()
        GHT.put(2, 21);
        GHT.put(1, 22);

        println GHT.get(1)
    }
}

