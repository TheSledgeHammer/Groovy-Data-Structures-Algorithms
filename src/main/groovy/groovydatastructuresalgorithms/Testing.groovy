package groovydatastructuresalgorithms


import groovydatastructuresalgorithms.Nodes.Hash.HashBucketEntrySCNode

class Testing {

    static void main(String[] args) {

        CuckooHashTable<Integer, Integer> GHT = new CuckooHashTable<>()
        GHT.put(3, 22);
        GHT.put(4, 23);
        GHT.remove(4)
        //GHT.put(2, 21);
        //GHT.Col(3, 3)

        println GHT.get(3)


        HashBucketEntrySCNode.HashingMap<Integer, Integer> SC = new HashBucketEntrySCNode.HashingMap<Integer, Integer>();
        //SC.putEntry(1, 32);
        //SC.removeEntry(1)
        //println SC.getEntry(1)
    }
}

