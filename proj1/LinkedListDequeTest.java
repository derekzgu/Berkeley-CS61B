//import org.junit.Test;

/**
 * Performs some basic linked list tests.
 *
 * @author Wending Peng
 */
public class LinkedListDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /**
     * Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     * <p>
     * && is the "and" operation.
     */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        lld1.removeFirst();
        lld1.removeFirst(); // lld1 should be: last
        lld1.addFirst("java"); //lld1 should be: java last
        lld1.removeLast(); //lld1 should be: java
        lld1.removeFirst(); // lld1 should be empty.
        System.out.println("Printing out deque again: ");
        lld1.printDeque();

        passed = lld1.isEmpty() && passed;
        printTestStatus(passed);
    }

    /**
     * Adds an item, then removes an item, and ensures that dll is empty afterwards.
     */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void getNodeTest() {
        System.out.println("Running getRecursive() node test.");
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(111);
        lld.addLast(666);
        lld.addFirst(10);
        lld.addLast(1111);
        System.out.println("Actual result: " + lld.getRecursive(4) + lld.getRecursive(-1) + lld.getRecursive(0) +
                lld.getRecursive(2) + lld.getRecursive(1) + lld.getRecursive(3)); //
        System.out.println("Expected result: nullnull106661111111");
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
//        addIsEmptySizeTest();
//        addRemoveTest();
        getNodeTest();
        System.out.println("Finished tests.");
    }
} 