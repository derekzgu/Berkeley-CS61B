import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test buggy StudentLinkedListDeque with failure sequence.
 *
 * @author Wending Peng
 */
public class TestLinkedList1B {

    @Test
    public void testRemoveLast() {
        StudentLinkedListDeque<Integer> studentLinkedListDeque = new StudentLinkedListDeque<>();
        LinkedListDequeSolution<Integer> linkedListDequeSolution = new LinkedListDequeSolution<>();
        for (int i = 0; i < 8; ++i) {
            studentLinkedListDeque.addFirst(i);
            linkedListDequeSolution.addFirst(i);
        }
        for (int i = 1; i < 8; ++i) {
            studentLinkedListDeque.removeLast();
            linkedListDequeSolution.removeLast();
            assertEquals(Integer.valueOf(i), linkedListDequeSolution.get(linkedListDequeSolution.size() - 1));
            assertEquals(Integer.valueOf(i), studentLinkedListDeque.get(studentLinkedListDeque.size() - 1));
        }

    }

    @Test
    public void testRemoveLastWithFailureSequence() {
        StudentLinkedListDeque<Integer> studentLinkedListDeque = new StudentLinkedListDeque<>();
        LinkedListDequeSolution<Integer> linkedListDequeSolution = new LinkedListDequeSolution<>();
        FailureSequence failureSequence = new FailureSequence();
        DequeOperation dequeOperation;
        for (int i = 0; i < 8; ++i) {
            studentLinkedListDeque.addFirst(i);
            linkedListDequeSolution.addFirst(i);
            dequeOperation = new DequeOperation("addFirst", i);
            failureSequence.addOperation(dequeOperation);
        }
        studentLinkedListDeque.printDeque();
        studentLinkedListDeque.removeLast();
        linkedListDequeSolution.removeLast();
        dequeOperation = new DequeOperation("removeLast");
        failureSequence.addOperation(dequeOperation);

        Integer actual = studentLinkedListDeque.get(6);
        Integer expected = linkedListDequeSolution.get(6);
        dequeOperation = new DequeOperation("get", 6);
        failureSequence.addOperation(dequeOperation);
        assertEquals(failureSequence.toString(), expected, actual);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("all", StudentLinkedListDeque.class);
    }

}
