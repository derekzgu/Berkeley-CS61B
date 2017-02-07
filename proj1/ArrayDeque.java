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
     * Utility functions to perform two common circular
     * array arithmetic operations: index + 1 and index - 1.
     *
     * @param index index to minus or plus one
     * @return the result of the operation
     */
    private int minusOne(int index) {
        return (index + arrayLength - 1) % arrayLength;
    }

    private int plusOne(int index) {
        return (index + 1) % arrayLength;
    }

    /**
     * Utility methods to get the index of the first/last item.
     *
     * @return the index of the first/last item
     */
    private int getFirstIndex() {
        return plusOne(nextFirst);
    }

    private int getLastIndex() {
        return minusOne(nextLast);
    }

    /**
     * Adds an item to the front.
     *
     * @param item item to add
     */
    public void addFirst(Item item) {
        if (size == arrayLength) {
            resize(2.0);
        }
        arrayDeque[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        ++size;
    }

    /**
     * Adds an item to the back.
     *
     * @param item item to add
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
     * Sets all elements of the given array to null.
     *
     * @param arrayToEmpty the array to empty
     */
    private void emptyArray(Item[] arrayToEmpty) {
        for (int i = 0; i < arrayLength; ++i) {
            arrayToEmpty[i] = null;
        }
    }

    /**
     * Resizes the array and resets the indexes without
     * changing the relative order of elements.
     *
     * @param ratio new array length / original length
     */
    private void resize(double ratio) {
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
     * Checks if the Deque is empty or not.
     *
     * @return is the Deque empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets size of the Deque
     *
     * @return size of the Deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items from first to last, separated by a space.
     */
    public void printDeque() {
        for (int i = getFirstIndex(); i != nextLast; i = plusOne(i)) {
            System.out.print(arrayDeque[i] + " ");
        }
    }

    /**
     * Removes and return the item at the front.
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
     * Gets the item at the given index (zero based).
     * Takes constant time.
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
