package groovydatastructuresalgorithms

class Testing {

    static void main(String[] args) throws InterruptedException {

        CuckooHashTable<Integer, Integer> GHT = new CuckooHashTable<>()
        GHT.put(3, 22);
        GHT.put(4, 23);
        //GHT.remove(4)
        GHT.put(2, 21);
        //GHT.Col(3, 3)

        println GHT.get(3)

        TernaryIndexedTree<String> BT = new TernaryIndexedTree<>();
        BT.addRight(0, "Hello");
        BT.addLeft(1, "World");
        BT.addMiddle(2, "Bye");
        BT.delete(1);
        println BT.get(1)


        //HashBucketEntrySCNode.HashingMap<Integer, Integer> SC = new HashBucketEntrySCNode.HashingMap<Integer, Integer>();
        //SC.putEntry(1, 32);
        //SC.removeEntry(1)
        //println SC.getEntry(1)
/*
        Cups cup1 = new Cups();
        Producer p1 = new Producer(cup1, 1);
        Producer p2 = new Producer(cup1, 2);
        Consumer c1 = new Consumer(cup1, 1);
        p1.start();
        c1.start();
        */
    }
}

