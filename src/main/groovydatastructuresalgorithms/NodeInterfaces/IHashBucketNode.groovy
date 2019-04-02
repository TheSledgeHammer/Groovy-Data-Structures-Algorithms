package main.groovydatastructuresalgorithms.NodeInterfaces

interface IHashBucketNode {

    void Resize();

    int getCapacity();

    double getLoadFactor();

    void setCapacity(int capacity);

    void setLoadFactor(double loadFactor);
}