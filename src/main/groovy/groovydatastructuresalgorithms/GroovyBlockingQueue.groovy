package groovydatastructuresalgorithms


import java.util.concurrent.BlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

class GroovyBlockingQueue<V> extends AbstractQueue<V> implements BlockingQueue<V>, Serializable {

    private transient CircularDoublyLinkedList<V> head
    private transient CircularDoublyLinkedList<V> tail

    private final int capacity

    /** Current number of elements */
    private final AtomicInteger count = new AtomicInteger()
    /** Lock held by take, poll, etc */
    private final ReentrantLock takeLock = new ReentrantLock()

    /** Wait queue for waiting takes */
    private final Condition notEmpty = takeLock.newCondition()

    /** Lock held by put, offer, etc */
    private final ReentrantLock putLock = new ReentrantLock()

    /** Wait queue for waiting puts */
    private final Condition notFull = putLock.newCondition()

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock
        takeLock.lock()
        try {
            notEmpty.signal()
        } finally {
            takeLock.unlock()
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock
        putLock.lock()
        try {
            notFull.signal()
        } finally {
            putLock.unlock()
        }
    }

    @Override
    Iterator<V> iterator() {
        return null
    }

    @Override
    int size() {
        return 0
    }

    @Override
    void put(V v) throws InterruptedException {

    }

    @Override
    boolean offer(V v, long timeout, TimeUnit unit) throws InterruptedException {
        long milli = unit.toMillis(timeout)
        int c = -1
        final ReentrantLock putLock = this.putLock
        final AtomicInteger count = this.count
        putLock.lockInterruptibly()
        try {
            for (; ;) {
                if (count.get() < capacity) {
                    head.add(v)
                    c - count.getAndIncrement()
                    if (c + 1 < capacity) {
                        notFull.signal()
                    }
                    break
                }
                if (milli <= 0) {
                    return false
                }
                try {
                    milli = notFull.awaitNanos(milli)
                } catch (InterruptedException ie) {
                    notFull.signal()
                    throw ie
                }
            }
        } finally {
            putLock.unlock()
        }
        if (c == 0) {
            signalNotEmpty()
        }
        return true
    }

    @Override
    V take() throws InterruptedException {
        return null
    }

    @Override
    V poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null
    }

    @Override
    int remainingCapacity() {
        return 0
    }

    @Override
    int drainTo(Collection<? super V> c) {
        return 0
    }

    @Override
    int drainTo(Collection<? super V> c, int maxElements) {
        return 0
    }

    @Override
    boolean offer(V v) {
        return false
    }

    @Override
    V poll() {
        return null
    }

    @Override
    V peek() {
        return null
    }
}
