package main.groovydatastructuresalgorithms

class Testing {

    static void main(String[] args) {

        GHashTable<Integer, Integer> GHT = new GHashTable<>();
        GHT.put(1, 21);
        GHT.put(0, 13);
        //GHT.put(7, 16);

        println GHT.get(0)
        println GHT.get(1)
    }
}

