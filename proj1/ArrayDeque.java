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

    /**
     * Two circular arithmetic operations: index + 1 and index - 1.
     *
     * @param index index to minus or plus one
     * @return the result
     */
    private int minusOne(int index) {
        return (index + arrayLength - 1) % arrayLength;
    }

    private int plusOne(int index) {
        return (index + 1) % arrayLength;
    }

    private int getFirstIndex() {
        return plusOne(nextFirst);
    }

    private int getLastIndex() {
        return minusOne(nextLast);
    }

    /**
     * Add an item to the front.
     *
     * @param item to add
     */
    public void addFirst(Item item) {
        if (size == arrayLength) {
            resize(2.0);
        }
//        printDeque();
//        System.out.println(nextFirst);
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
        if (size == arrayLength) {
            resize(2.0);
        }
        arrayDeque[nextLast] = item;
        nextLast = plusOne(nextLast);
        ++size;
    }

    /**
     * Set all elements of the array to null.
     *
     * @param arrayToEmpty the array to empty
     */
    private void emptyArray(Item[] arrayToEmpty) {
        for (int i = 0; i < arrayLength; ++i) {
            arrayToEmpty[i] = null;
        }
    }

    /**
     * Resize the array and reset the indexes without
     * changing the relative order of elements.
     *
     * @param ratio new array length / original length
     */
    public void resize(double ratio) {
        int newArrayLength = (int) (arrayLength * ratio);
        Item[] temp = (Item[]) new Object[newArrayLength];
        emptyArray(temp);

        for (int i = 0; i < size; ++i) {
            temp[i] = arrayDeque[(getFirstIndex() + i) % arrayLength];
        }

        arrayDeque = temp;

        /* Reset parameters. */
        arrayLength = newArrayLength;
        nextFirst = arrayLength - 1;
        nextLast = size;
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
        for (int i = getFirstIndex(); i != nextLast; i = plusOne(i)) {
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
        if (isTooEmpty()) {
            resize(0.5);
        }
        Item firstItem = arrayDeque[getFirstIndex()];
        arrayDeque[getFirstIndex()] = null;
        nextFirst = getFirstIndex();
        --size;
        return firstItem;
    }

    /**
     * Removes and returns the item at the back.
     *
     * @return the original back item; null if empty
     */
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        if (isTooEmpty()) {
            resize(0.5);
        }
        Item lastItem = arrayDeque[getLastIndex()];
        arrayDeque[getLastIndex()] = null;
        nextLast = getLastIndex();
        --size;
        return lastItem;
    }

    /**
     * Decides if the used space ratio of the array is too low.
     *
     * @return true if is too empty; false if not.
     */
    private boolean isTooEmpty() {
        final double LEAST_RATIO = 0.25D;
        double actualRatio = (double) size / (double) arrayLength;
        return arrayLength >= 16 && actualRatio < LEAST_RATIO;
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
        return arrayDeque[(getFirstIndex() + index) % arrayLength];
    }
}
