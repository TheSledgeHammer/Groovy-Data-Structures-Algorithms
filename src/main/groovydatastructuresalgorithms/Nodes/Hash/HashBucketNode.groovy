package main.groovydatastructuresalgorithms.Nodes.Hash

import main.groovydatastructuresalgorithms.NodeInterfaces.IHashBucketNode

class HashBucketNode implements IHashBucketNode {

    private int hashFunction;
    private int capacity;
    private double loadFactor;
    private int load;

    HashBucketNode(int capacity, double loadFactor) {
        setCapacity(capacity);
        setLoadFactor(loadFactor);
    }

    @Override
    void Resize() {
        if(Load() >= getLoadFactor()) {
            setCapacity(getCapacity() * 2);
        }
    }

    @Override
    int getCapacity() {
        return capacity;
    }

    @Override
    double getLoadFactor() {
        return loadFactor;
    }

    @Override
    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    int Load() {
        this.load = (int) getLoadFactor() * getCapacity();
        return this.load;
    }
}
