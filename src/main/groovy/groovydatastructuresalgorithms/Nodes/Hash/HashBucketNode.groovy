package groovydatastructuresalgorithms.Nodes.Hash

import groovydatastructuresalgorithms.NodeInterfaces.IHashBucketNode

//A Basic Bucket used for creating the bucket parameters in Hash Structures
class HashBucketNode implements IHashBucketNode {

    private int capacity
    private double loadFactor

    HashBucketNode(int capacity, double loadFactor) {
        setCapacity(capacity)
        setLoadFactor(loadFactor)
    }

    @Override
    int getCapacity() {
        return capacity
    }

    @Override
    double getLoadFactor() {
        return loadFactor
    }

    @Override
    void setCapacity(int capacity) {
        this.capacity = capacity
    }

    @Override
    void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor
    }

    @Override
    int HashBucketLoad() {
        return loadFactor * capacity
    }
}
