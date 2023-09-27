import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size
    /*
     * Test empty constructor
     */
    @Test
    public final void testEmptyConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.constructorRef();
        Set<String> m = this.constructorTest();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test add method. Test for add String values.
     */
    @Test
    public final void testAddConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef();
        Set<String> m = this.createFromArgsTest();

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
     * Test add method. Test for add number value.
     */
    @Test
    public final void testAddConstructor_2() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef();
        Set<String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        mExpected.add("78");
        mExpected.add("56");
        mExpected.add("92");
        mExpected.add("44");
        mExpected.add("67");
        mExpected.add("85");
        mExpected.add("95");
        mExpected.add("33");

        m.add("78");
        m.add("56");
        m.add("92");
        m.add("44");
        m.add("67");
        m.add("85");
        m.add("95");
        m.add("33");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test remove method. Test for far ended value.
     */
    @Test
    public final void testRemoveConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.remove("33");

        String value = m.remove("33");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test remove method. Test for node only exist left node.
     */
    @Test
    public final void testRemoveConstructor_2() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.remove("44");

        String value = m.remove("44");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test remove method. Test for node exist left and right node.
     */
    @Test
    public final void testRemoveConstructor_3() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.remove("56");

        String value = m.remove("56");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test remove method. Test for remove root node.
     */
    @Test
    public final void testRemoveConstructor_4() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.remove("78");

        String value = m.remove("78");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test removeAny method.
     */
    @Test
    public final void testRemoveAnyConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */

        String value = m.removeAny();

        boolean mExpectedKey = mExpected.contains(value);
        assertEquals(true, mExpectedKey);

        String valueExpected = mExpected.remove(value);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test contains method. Test for exist value.
     */
    @Test
    public final void testContainsConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        boolean valueExpected = mExpected.contains("92");

        boolean value = mExpected.contains("92");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test contains method. Test for non exist value.
     */
    @Test
    public final void testContainsConstructor_2() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        boolean valueExpected = mExpected.contains("abc");

        boolean value = mExpected.contains("abc");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test contains method. Test for empty tree for contains.
     */
    @Test
    public final void testContainsConstructor_3() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef();
        Set<String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        boolean valueExpected = mExpected.contains("78");

        boolean value = mExpected.contains("78");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test size method. Test for empty tree size.
     */
    @Test
    public final void testSizeConstructor_1() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef();
        Set<String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        int valueExpected = mExpected.size();

        int value = mExpected.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test size method. Test for normal size tree.
     */
    @Test
    public final void testSizeConstructor_2() {
        /*
         * Set up variables
         */
        Set<String> mExpected = this.createFromArgsRef("78", "56", "92", "44",
                "67", "85", "95", "33");
        Set<String> m = this.createFromArgsTest("78", "56", "92", "44", "67",
                "85", "95", "33");

        /*
         * Call method under test
         */
        int valueExpected = mExpected.size();

        int value = mExpected.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

}
