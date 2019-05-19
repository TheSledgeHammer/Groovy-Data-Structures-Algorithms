/*
 * Copyright [2018] [Martin Kelly]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package groovydatastructuresalgorithms.api.nodes.hashing

import groovydatastructuresalgorithms.api.interfaces.IHashBucketNode

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
