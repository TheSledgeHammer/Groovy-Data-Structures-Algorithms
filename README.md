# Groovy-Data-Structures-Algorithms
The following Contains various helpful data structure classes. It is a re-write in groovy of my Java Data Structures & Algorithms Project.

### Includes:

#### Lists:
* DoublyLinkedList

* CircularDoublyLinkedList

#### Maps:
* CircularDoublyLinkedMap: A Map implementation of a CircularDoublyLinkedList with a key-value pair.

#### Tables:
* CircularDoublyLinkedTable: A Table implementation of a CircularDoublyLinkedList with a row-column-value triplet.

#### Trees:
* BinaryTree: A tree with 2 branches, left and right. Backed by a CircularDoublyLinkedList

* BinaryIndexedTree (Fenwick Tree): A binary tree where each branch value is mapped to an integer or index within that tree. Backed by a CircularDoublyLinkedMap

* TernaryTree: A tree with 3 branches, left, middle and right. Backed by a CircularDoublyLinkedList

* TernaryIndexedTree (Fenwick Tree): A ternary tree where each branch value is mapped to an integer or index within that tree. Backed by a CircularDoublyLinkedMap

* CompleteBinaryTree: A binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

#### Queues/ Deques:
* CircularDoublyLinkedDeque: A Deque that is backed by a CircularDoublyLinkedList.

#### Hashing:
* CuckooHashMap: A HashMap using the Cuckoo Hash Collision Algorithm. Uses the following 4 hash algorithms: SHA1, FNV1a_128, Murmur3_128 & MD5 (in no set order)

* CuckooHashTable A HashTable using the Cuckoo Hash Collision Algorithm. Uses the following 4 hash algorithms: SHA1, FNV1a_128, Murmur3_128 & MD5 (in no set order)

#### Algorithms:
* DammAlgorithm: Can calculate a check digit using Damm's Algorithm. Uses the QuasiGroupGenerator to perform calculations.

* QuasiGroupGenerator: Can create QuasiGroups of size n with an order of magnitude n. Form a Latin Square. Is generated Randomly.

### Apache Licence 2.0
Copyright [2018] [Martin Kelly]

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
