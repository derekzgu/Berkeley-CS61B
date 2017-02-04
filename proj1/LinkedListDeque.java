/**
 * Implementation of double ended queue using linked list.
 *
 * @author Wending Peng
 */
public class LinkedListDeque<Item> {
    /**
     * Node of linked list.
     */
    public class Node {
        private final Item item;
        private Node prev;
        private Node next;

        /**
         * Node Constructor, creating a node.
         *
         * @param i item in the node
         * @param p previous node
         * @param n next node
         */
        public Node(Item i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private Node front;
    private Node back;

    private int size;    // length of the Deque

    /**
     * Class constructor, creating a empty linked list deque.
     */
    public LinkedListDeque() {
        front = back = null;
        sentinel = new Node(null, null, null);
        size = 0;
    }

    /**
     * Adds the first node.
     *
     * @param item the item of the only one node
     */
    private void addTheOnlyNode(Item item) {
        front = back = new Node(item, sentinel, sentinel);
        sentinel.prev = back;
        sentinel.next = front;
        ++size;
    }

    /**
     * Adds an item to the front.
     *
     * @param item item to add
     */
    public void addFirst(Item item) {
        if (size == 0) {
            addTheOnlyNode(item);
            return;
        }
        Node newFront = new Node(item, sentinel, front);
        front.prev = newFront;
        sentinel.next = front = newFront;
        ++size;
    }

    /**
     * Adds an item to the back.
     *
     * @param item item to add
     */
    public void addLast(Item item) {
        if (size == 0) {
            addTheOnlyNode(item);
            return;
        }
        Node newBack = new Node(item, back, sentinel);
        back.next = newBack;
        sentinel.prev = back = newBack;
        ++size;
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
     * Gets the size of the Deque
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
        if (size == 0) {
            return;
        }
        Node iterator = front;
        while (iterator.item != null) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
    }

    private void removeTheOnlyItem() {
        front = back = sentinel.next = sentinel.prev = null;
        --size;
    }

    /**
     * removes and returns the item at the front.
     *
     * @return the original front item; null if empty
     */
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Node originalFront = front;
        if (size == 1) {
            removeTheOnlyItem();
            return originalFront.item;
        }
        sentinel.next = front = front.next;
        front.prev = sentinel;
        --size;
        return originalFront.item;
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
        Node originalBack = back;
        if (size == 1) {
            removeTheOnlyItem();
            return originalBack.item;
        }
        sentinel.prev = back = back.prev;
        back.next = sentinel;
        --size;
        return originalBack.item;
    }

    /**
     * Gets the item at the given index (zero based). Using iteration.
     *
     * @param index index of the given item
     * @return the index-th item; null if no such item
     */
    public Item get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node nodeToGet = front;
        for (int i = 0; i < index; ++i) {
            nodeToGet = nodeToGet.next;
        }
        return nodeToGet.item;
    }

    /**
     * Gets the item at the given index (zero based). Using recursion.
     *
     * @param index index of the given item
     * @return the index-th item; null if no such item
     */
    private Node currentFront = null;

    public Item getRecursive(int index) {
        currentFront = front;
        return getRecursiveHelper(index);
    }

    private Item getRecursiveHelper(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        if (index == 0) {
            Item item = currentFront.item;
            currentFront = front;
            return item;
        }
        currentFront = currentFront.next;
        return getRecursiveHelper(index - 1);
    }
}
