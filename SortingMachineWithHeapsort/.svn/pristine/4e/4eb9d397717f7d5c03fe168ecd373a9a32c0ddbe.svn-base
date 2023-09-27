import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Zhao Liu
 * @author Zishu Ling
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
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

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
     * Test add method. Test for add String values.
     */
    @Test
    public final void testAdd_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        mExpected.add("a");
        mExpected.add("b");
        mExpected.add("c");

        m.add("a");
        m.add("b");
        m.add("c");

        assertEquals(mExpected, m);
    }

    /*
     * Test changeToExtractionMode method. Test for empty SortingMachine
     */
    @Test
    public final void testChangeToExtractionMode_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        mExpected.changeToExtractionMode();

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    /*
     * Test changeToExtractionMode method. Test for non-empty SortingMachine
     */
    @Test
    public final void testChangeToExtractionMode_2() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");

        mExpected.changeToExtractionMode();

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    /*
     * Test removeFirst method. Test for non duplicated SortingMachine
     */
    @Test
    public final void testRemoveFirst_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "65", "24", "26", "14", "21", "32", "31", "13", "16", "19",
                "68");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "65",
                "24", "26", "14", "21", "32", "31", "13", "16", "19", "68");

        String valueExpected = mExpected.removeFirst();

        String value = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test removeFirst method. Test for duplicated SortingMachine
     */
    @Test
    public final void testRemoveFirst_2() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "65", "24", "26", "14", "21", "32", "31", "13", "16", "19",
                "68", "13");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "65",
                "24", "26", "14", "21", "32", "31", "13", "16", "19", "68",
                "13");

        String valueExpected = mExpected.removeFirst();

        String value = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test isInInsertionMode method. Test for empty SortingMachine
     */
    @Test
    public final void testIsInInsertionMode_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        boolean valueExpected = mExpected.isInInsertionMode();

        boolean value = m.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test isInInsertionMode method. Test for non empty SortingMachine
     */
    @Test
    public final void testIsInInsertionMode_2() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");

        boolean valueExpected = mExpected.isInInsertionMode();

        boolean value = m.isInInsertionMode();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test order method. Test for non empty SortingMachine
     */
    @Test
    public final void testOrder_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");

        Comparator<String> valueExpected = mExpected.order();

        Comparator<String> value = m.order();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test order method. Test for insert mode.
     */
    @Test
    public final void testSize_1() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b", "c");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b", "c");

        int valueExpected = mExpected.size();

        int value = m.size();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test removeFirst method. Test for Extraction Mode
     */
    @Test
    public final void testSize_2() {
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "65", "24", "26", "14", "21", "32", "31", "13", "16", "19",
                "68", "21");
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "65",
                "24", "26", "14", "21", "32", "31", "13", "16", "19", "68",
                "21");

        int valueExpected = mExpected.size();

        int value = m.size();

        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

}
