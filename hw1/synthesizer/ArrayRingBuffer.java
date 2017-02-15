package synthesizer;

import org.omg.SendingContext.RunTime;

import javax.management.relation.RoleUnresolved;
import java.util.Iterator;

/**
 * @author Wending Peng
 */

//TODO: Make sure to make this class and all of its methods public
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
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
        rb[last] = x;
        last = last == capacity - 1 ? 0 : last + 1;
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
        T oldFirstItem = rb[first];
        rb[first] = null;
        first = first == capacity - 1 ? 0 : first + 1;
        --fillCount;
        return oldFirstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];
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
            T currentThing = rb[currentIndex];
            ++currentIndex;
            return currentThing;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorHelper();
    }

}
