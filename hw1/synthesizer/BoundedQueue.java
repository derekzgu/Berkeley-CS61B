package synthesizer;

import java.util.Iterator;

/**
 * @author Wending Peng
 */

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();          // returns size of the buffer

    int fillCount();         // returns number of items currently in the buffer

    void enqueue(T x);  // adds item x to the end

    T dequeue();        // deletes and return item from the front

    T peek();           // return (but does not delete) item from the front

    Iterator<T> iterator();

    default boolean isEmpty() {
        return fillCount() == 0;
    }

    default boolean isFull() {
        return fillCount() == capacity();
    }
}
