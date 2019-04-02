package main.groovydatastructuresalgorithms

class Testing {

    static void main(String[] args) {


        GHashTable<Integer, Integer> GHT = new GHashTable<>();
        GHT.put(0, 1);
        GHT.put(1, 13);
        println GHT.get(1)
    }
}

