import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * Test Map constructor
     */
    @Test
    public final void testEmptyConstructor_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.constructorRef();
        Map<String, String> m = this.constructorTest();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test Add Method
     */
    @Test
    public final void testAdd_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.constructorRef();
        Map<String, String> m = this.constructorTest();

        /*
         * Call method under test
         */
        mExpected.add("123456", "abcd");
        m.add("123456", "abcd");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test Add Method
     */
    @Test
    public final void testAdd_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.constructorRef();
        Map<String, String> m = this.constructorTest();

        /*
         * Call method under test
         */
        mExpected.add("a", "1");
        m.add("a", "1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test Add Method
     */
    @Test
    public final void testAdd_3() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.constructorRef();
        Map<String, String> m = this.constructorTest();

        /*
         * Call method under test
         */
        mExpected.add("a", "1");
        mExpected.add("b", "2");
        mExpected.add("c", "3");

        m.add("a", "1");
        m.add("b", "2");
        m.add("c", "3");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test Add Method
     */
    @Test
    public final void testAdd_4() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        mExpected.add("1", "a");
        mExpected.add("2", "b");
        mExpected.add("3", "c");
        mExpected.add("4", "d");

        m.add("1", "a");
        m.add("2", "b");
        m.add("3", "c");
        m.add("4", "d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test remove Method
     */
    @Test
    public final void testRemove_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3");

        /*
         * Call method under test
         */
        Pair<String, String> pairExpected = mExpected.remove("a");
        Pair<String, String> pair = m.remove("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test remove Method
     */
    @Test
    public final void testRemove_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        Pair<String, String> pairExpected = mExpected.remove("c");
        Pair<String, String> pair = m.remove("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test remove Method
     */
    @Test
    public final void testRemove_3() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d");

        /*
         * Call method under test
         */
        Pair<String, String> pairExpected = mExpected.remove("4");
        Pair<String, String> pair = m.remove("4");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test remove Method
     */
    @Test
    public final void testRemove_4() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("4", "d");
        Map<String, String> m = this.createFromArgsTest("4", "d");

        /*
         * Call method under test
         */
        Pair<String, String> pairExpected = mExpected.remove("4");
        Pair<String, String> pair = m.remove("4");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test removeAny Method
     */
    @Test
    public final void testRemoveAny_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d");

        /*
         * Call method under test
         */
        Pair<String, String> pair = m.removeAny();

        boolean mExpectedKey = mExpected.hasKey(pair.key());
        assertEquals(true, mExpectedKey);

        Pair<String, String> pairExpected = mExpected.remove(pair.key());

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test removeAny Method
     */
    @Test
    public final void testRemoveAny_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        Pair<String, String> pair = m.removeAny();

        boolean mExpectedKey = mExpected.hasKey(pair.key());
        assertEquals(true, mExpectedKey);

        Pair<String, String> pairExpected = mExpected.remove(pair.key());

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test removeAny Method
     */
    @Test
    public final void testRemoveAny_3() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1");
        Map<String, String> m = this.createFromArgsTest("a", "1");

        /*
         * Call method under test
         */
        Pair<String, String> pair = m.removeAny();

        boolean mExpectedKey = mExpected.hasKey(pair.key());
        assertEquals(true, mExpectedKey);

        Pair<String, String> pairExpected = mExpected.remove(pair.key());

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(pairExpected, pair);
    }

    /*
     * Test value Method
     */
    @Test
    public final void testValue_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.value("a");
        String value = m.value("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test value Method
     */
    @Test
    public final void testValue_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.value("d");
        String value = m.value("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test value Method
     */
    @Test
    public final void testValue_3() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.value("1");
        String value = m.value("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test hasKey Method
     */
    @Test
    public final void testHasKey_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef();
        Map<String, String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        boolean keyExpected = mExpected.hasKey("1");
        boolean key = m.hasKey("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(keyExpected, key);
    }

    /*
     * Test hasKey Method
     */
    @Test
    public final void testHasKey_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        boolean keyExpected = mExpected.hasKey("a");
        boolean key = m.hasKey("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(keyExpected, key);
    }

    /*
     * Test hasKey Method
     */
    @Test
    public final void testHasKey_3() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("a", "1", "b",
                "2", "c", "3", "d", "4");
        Map<String, String> m = this.createFromArgsTest("a", "1", "b", "2", "c",
                "3", "d", "4");

        /*
         * Call method under test
         */
        boolean keyExpected = mExpected.hasKey("z");
        boolean key = m.hasKey("z");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(keyExpected, key);
    }

    /*
     * Test hasKey Method
     */
    @Test
    public final void testHasKey_4() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d");

        /*
         * Call method under test
         */
        boolean keyExpected = mExpected.hasKey("2");
        boolean key = m.hasKey("2");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(keyExpected, key);
    }

    /*
     * Test size Method
     */
    @Test
    public final void testSize_1() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef();
        Map<String, String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        int sizeExpected = mExpected.size();
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    /*
     * Test size Method
     */
    @Test
    public final void testSize_2() {
        /*
         * Set up variables
         */
        Map<String, String> mExpected = this.createFromArgsRef("1", "a", "2",
                "b", "3", "c", "4", "d");
        Map<String, String> m = this.createFromArgsTest("1", "a", "2", "b", "3",
                "c", "4", "d");

        /*
         * Call method under test
         */
        int sizeExpected = mExpected.size();
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

}
