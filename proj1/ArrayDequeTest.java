import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test Array Deque.
 *
 * @author Wending Peng
 */
public class ArrayDequeTest {
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

    public static void arrayDequeTest() {
        System.out.println("Running test");
        ArrayDeque<String> arrdq = new ArrayDeque<>();

        boolean passed = checkEmpty(true, arrdq.isEmpty());
        arrdq.addFirst("front");
        passed = checkSize(1, arrdq.size());
        System.out.println(passed);
        arrdq.printDeque();

        arrdq.addLast("middle");
        passed = checkSize(2, arrdq.size()) && passed;

        arrdq.addLast("back");
        passed = checkSize(3, arrdq.size()) && passed;

        System.out.println("Printing out deque: ");
        arrdq.printDeque();

        arrdq.removeFirst();
        arrdq.removeFirst(); // lld1 should be: last
        arrdq.addFirst("java"); //lld1 should be: java last
        arrdq.removeLast(); //lld1 should be: java
        arrdq.removeFirst(); // lld1 should be empty.
        passed = checkEmpty(true, arrdq.isEmpty());

        arrdq.addFirst("again1");
        arrdq.addFirst("again2");
        arrdq.addFirst("again3");
        arrdq.addLast("again4");

        arrdq.printDeque();
        System.out.println("Should be again:3214");

        System.out.println(arrdq.get(1)); // should be 2
        System.out.println(arrdq.get(0)); // should be 3
        System.out.println(arrdq.get(3)); // should be 4
        System.out.println(arrdq.get(2)); // should be 1
        System.out.println(arrdq.get(4)); // should be null
        System.out.println("Should be 2341null");
        passed = !arrdq.isEmpty() && passed;
        printTestStatus(passed);

    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> studentArrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 8; ++i) {
            studentArrayDeque.addFirst(i);
        }
        studentArrayDeque.printDeque();
        for (int i = 0; i < 8; ++i) {
            studentArrayDeque.removeFirst();
            studentArrayDeque.printDeque();
        }
    }

    @Test
    public void testRemoveFirstWithFailureSequence() {
        ArrayDeque<Integer> studentArrayDeque = new ArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDequeSolution = new ArrayDequeSolution<>();
        FailureSequence failureSequence = new FailureSequence();
        DequeOperation dequeOperation;
        for (int i = 0; i < 8; ++i) {
            studentArrayDeque.addFirst(i);
            arrayDequeSolution.addFirst(i);
            dequeOperation = new DequeOperation("addFirst", i);
            failureSequence.addOperation(dequeOperation);
        }
        studentArrayDeque.printDeque();
        Integer actual, expected;
        for (int i = 0; i < 8; i++) {
            actual = studentArrayDeque.removeFirst();
            expected = arrayDequeSolution.removeFirst();
            dequeOperation = new DequeOperation("removeFirst");
            failureSequence.addOperation(dequeOperation);
            assertEquals(failureSequence.toString(), expected, actual);
        }
    }

    public static void main(String[] args) {

//        arrayDequeTest();
        jh61b.junit.TestRunner.runTests("all", ArrayDeque.class);
    }

}
