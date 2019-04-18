package groovydatastructuresalgorithms

import groovydatastructuresalgorithms.Nodes.Hash.HashBucketEntryNodeSC

class Testing {

    static void main(String[] args) {

        GHashTable<Integer, Integer> GHT = new GHashTable<>()
        GHT.put(3, 22);
        GHT.put(4, 23);
        //GHT.put(2, 21);
        //GHT.Col(3, 3)

        //println GHT.get(4)

        HashBucketEntryNodeSC.HashingMap<Integer, Integer> SC = new HashBucketEntryNodeSC.HashingMap<Integer, Integer>();
        SC.putEntry(1, 32);
        SC.removeEntry(1)
        println SC.getEntry(1)
    }
}

