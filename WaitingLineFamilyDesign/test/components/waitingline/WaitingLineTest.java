package components.waitingline;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.queue.Queue;

/**
 * JUnit test fixture for {@code WaitingLine<String>}'s constructor and kernel
 * methods.
 *
 * @author Zhao Liu
 * @author Ummehani Motiwala
 * @author Deepak Prabaharan
 */
public abstract class WaitingLineTest {

    /**
     * Invokes the appropriate {@code Queue} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new queue
     * @ensures constructorRef = <>
     */
    protected abstract Queue<String> constructorRef();

    /**
     * Invokes the appropriate {@code WaitingLine} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new WaitingLine
     * @ensures constructorTest = <>
     */
    protected abstract WaitingLine<String> constructorTest();

    /**
     *
     * Creates and returns a {@code Queue<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the queue
     * @return the constructed queue
     * @ensures createFromArgsRef = [entries in args]
     */
    private Queue<String> createFromArgsRef(String... args) {
        Queue<String> queue = this.constructorRef();
        for (String s : args) {
            queue.enqueue(s);
        }
        return queue;
    }

    /**
     *
     * Creates and returns a {@code WaitingLine<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the WaitingLine
     * @return the constructed WaitingLine
     * @ensures createFromArgsTest = [entries in args]
     */
    private WaitingLine<String> createFromArgsTest(String... args) {
        WaitingLine<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.addToLine(s);
        }
        return sequence;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    @Test
    public final void testAddToLine_1() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");

        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green");
        /*
         * Call method under test
         */
        waitingLine.addToLine("yellow");

        while (qExpected.length() > 0) {
            String expectedElement = qExpected.dequeue();
            String element = waitingLine.removeFront();

            /*
             * Assert that values of variables match expectations
             */
            assertEquals(expectedElement, element);
        }
    }

    @Test
    public final void testRemoveFromLine_1() {
        /*
         * Set up variables
         */
        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");

        String expectedElement = "green";
        /*
         * Call method under test
         */
        String removedElement = waitingLine.removeFromLine(expectedElement);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedElement, removedElement);
    }

    @Test
    public final void testExist_1() {
        /*
         * Set up variables
         */
        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");

        String expectedElement = "green";
        /*
         * Call method under test
         */
        boolean isExist = waitingLine.exist(expectedElement);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, isExist);
    }

    @Test
    public final void testExist_2() {
        /*
         * Set up variables
         */
        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");

        String expectedElement = "green-green";
        /*
         * Call method under test
         */
        boolean isExist = waitingLine.exist(expectedElement);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, isExist);
    }

    @Test
    public final void testLength_1() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef();

        WaitingLine<String> waitingLine = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int expectedLength = qExpected.length();
        int length = waitingLine.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedLength, length);
    }

    @Test
    public final void testLength_2() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");

        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");
        /*
         * Call method under test
         */
        int expectedLength = qExpected.length();
        int length = waitingLine.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedLength, length);
    }

    @Test
    public final void testFront_1() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");

        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");
        /*
         * Call method under test
         */
        String expectedElement = qExpected.front();
        String element = waitingLine.front();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedElement, element);
    }

    @Test
    public final void testRemoveFront_1() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");

        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");
        /*
         * Call method under test
         */
        String expectedElement = qExpected.dequeue();
        String element = waitingLine.removeFront();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedElement, element);
    }

    @Test
    public final void testPosition_1() {
        /*
         * Set up variables
         */

        WaitingLine<String> waitingLine = this.createFromArgsTest("red", "blue",
                "green", "yellow");

        int expectedPosition = 2;
        /*
         * Call method under test
         */
        int position = waitingLine.position("green");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedPosition, position);
    }

    @Test
    public final void testSort_1() {
        /*
         * Set up variables
         */
        Queue<String> qExpected = this.createFromArgsRef("1", "2", "3", "4");

        WaitingLine<String> waitingLine = this.createFromArgsTest("3", "2", "1",
                "4");
        /*
         * Call method under test
         */
        waitingLine.sort(ORDER);

        while (qExpected.length() > 0) {
            String expectedElement = qExpected.dequeue();
            String element = waitingLine.removeFront();

            /*
             * Assert that values of variables match expectations
             */
            assertEquals(expectedElement, element);
        }
    }

}
