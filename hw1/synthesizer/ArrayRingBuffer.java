package synthesizer;

import org.omg.SendingContext.RunTime;
import javax.management.relation.RoleUnresolved;
import java.util.Iterator;

/**
 * @author Wending Peng
 */

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] ringBuffer;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        ringBuffer = (T[]) new Object[capacity];
        first = fillCount = 0;
        last = capacity - 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        ringBuffer[last] = x;
        last = (last + 1) % capacity;
        ++fillCount;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T originalFirstItem = ringBuffer[first];
        ringBuffer[first] = null;
        first = (first + 1) % capacity;
        --fillCount;
        return originalFirstItem;
    }

    /**
     * Returns the oldest item, but doesn't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return ringBuffer[first];
    }

    private class IteratorHelper implements Iterator<T> {
        private int currentIndex;

        private IteratorHelper() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < fillCount;
        }

        @Override
        public T next() {
            T currentItem = ringBuffer[currentIndex];
            ++currentIndex;
            return currentItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorHelper();
    }
}
