/**
 * Implementation of double ended queue using array.
 *
 * @author Wending Peng
 */
public class ArrayDeque<Item> {
    private int size;
    private int arrayLength;
    private Item[] arrayDeque;
    private int nextFirst, nextLast;

    /**
     * Constructor, initializing an empty ArrayDeque.
     */
    public ArrayDeque() {
        arrayLength = 8;
        arrayDeque = (Item[]) new Object[arrayLength];
        size = 0;
        nextFirst = arrayLength - 1;
        nextLast = 0;
    }

    private int minusOne(int index) {
        return (index + arrayLength - 1) % arrayLength;
    }

    private int plusOne(int index) {
        return (index + 1) % arrayLength;
    }

    /**
     * Add an item to the front.
     *
     * @param item to add
     */
    public void addFirst(Item item) {
        arrayDeque[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        ++size;
    }

    /**
     * Add an item to the back.
     *
     * @param item to add
     */
    public void addLast(Item item) {
        arrayDeque[nextLast] = item;
        nextLast = plusOne(nextLast);
        ++size;
    }

    /**
     * Check if the Deque is empty or not.
     *
     * @return is the Deque empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get size of the Deque
     *
     * @return size of the Deque
     */
    public int size() {
        return size;
    }

    /**
     * Print the items from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(arrayDeque[i] + " ");
        }
    }

    /**
     * removes and return the item at the front.
     *
     * @return the original front item; null if empty
     */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item firstItem = arrayDeque[plusOne(nextFirst)];
        arrayDeque[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        --size;
        return firstItem;
    }

    /**
     * Remove and return the item at the back.
     *
     * @return the original back item; null if empty
     */
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item lastItem = arrayDeque[minusOne(nextLast)];
        arrayDeque[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        --size;
        return lastItem;
    }

    /**
     * Get the item at the given index (zero based).
     * Take constant time.
     *
     * @param index the item's index
     * @return index-th item; null if no such item
     */
    public Item get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        int iterator = plusOne(nextFirst);
        return arrayDeque[(iterator + index) % arrayLength];
    }
}
