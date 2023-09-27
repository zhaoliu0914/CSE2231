import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
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
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in reverse lexicographic order.
     */
    private static class StringGT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s2.compareToIgnoreCase(s1);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    private static final StringGT ORDER_GT = new StringGT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * Test Add Method. Testing for adding a, b, c
     */
    @Test
    public final void testAdd_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        /*
         * Call method under test
         */
        mExpected.add("a");
        mExpected.add("b");
        mExpected.add("c");
        m.add("a");
        m.add("b");
        m.add("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test Add Method. Testing for adding 1, 2, 3
     */
    @Test
    public final void testAdd_2() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        /*
         * Call method under test
         */
        mExpected.add("1");
        mExpected.add("2");
        mExpected.add("3");
        m.add("1");
        m.add("2");
        m.add("3");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test changeToExtractionMode Method
     */
    @Test
    public final void testChangeToExtractionMode_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        /*
         * Call method under test
         */
        mExpected.changeToExtractionMode();
        m.changeToExtractionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test changeToExtractionMode Method
     */
    @Test
    public final void testChangeToExtractionMode_3() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");

        /*
         * Call method under test
         */
        mExpected.changeToExtractionMode();
        m.changeToExtractionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test removeFirst Method
     */
    @Test
    public final void testRemoveFirst_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");

        mExpected.changeToExtractionMode();
        m.changeToExtractionMode();
        /*
         * Call method under test
         */
        String elementExpected = mExpected.removeFirst();
        String element = m.removeFirst();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test removeFirst Method
     */
    @Test
    public final void testRemoveFirst_2() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        String elementExpected = mExpected.removeFirst();
        String element = m.removeFirst();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test isInInsertionMode Method
     */
    @Test
    public final void testIsInInsertionMode_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        boolean elementExpected = mExpected.isInInsertionMode();
        boolean element = m.isInInsertionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test isInInsertionMode Method
     */
    @Test
    public final void testIsInInsertionMode_2() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        boolean elementExpected = mExpected.isInInsertionMode();
        boolean element = m.isInInsertionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test order Method
     */
    @Test
    public final void testOrder_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        Comparator<String> elementExpected = mExpected.order();
        Comparator<String> element = m.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test order Method
     */
    @Test
    public final void testOrder_2() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER_GT,
                false, "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER_GT, false, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        Comparator<String> elementExpected = mExpected.order();
        Comparator<String> element = m.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test size Method
     */
    @Test
    public final void testSize_1() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "1", "2", "3");

        /*
         * Call method under test
         */
        int elementExpected = mExpected.size();
        int element = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

    /*
     * Test size Method
     */
    @Test
    public final void testSize_2() {
        /*
         * Set up variables
         */
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "1", "2", "3");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "1", "2", "3");

        mExpected.add("c");
        mExpected.add("d");

        m.add("c");
        m.add("d");

        /*
         * Call method under test
         */
        int elementExpected = mExpected.size();
        int element = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(elementExpected, element);
    }

}
