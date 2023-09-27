import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Zhao Liu
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

    @Test
    public final void testAdd_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("4", "7");
        testMap.add("4", "7");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void testAdd_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void testAdd_3() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void testAdd_4() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("", "5");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("", "5");
        testMap.add("C", "3");
        testMap.add("D", "4");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void testRemove_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        Pair<String, String> refPair = refMap.remove("1");
        Pair<String, String> testPair = testMap.remove("1");

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

    @Test
    public final void testRemove_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        Pair<String, String> refPair = refMap.remove("3");
        Pair<String, String> testPair = testMap.remove("3");

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

    @Test
    public final void testRemove_3() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        Pair<String, String> refPair = refMap.remove("A");
        Pair<String, String> testPair = testMap.remove("A");

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

    @Test
    public final void testRemove_4() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        Pair<String, String> refPair = refMap.remove("C");
        Pair<String, String> testPair = testMap.remove("C");

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

    @Test
    public final void testValue_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        String refValue = refMap.value("1");
        String testValue = testMap.value("1");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testValue_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        String refValue = refMap.value("3");
        String testValue = testMap.value("3");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testValue_3() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        String refValue = refMap.value("C");
        String testValue = testMap.value("C");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testValue_4() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("", "5");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("", "5");
        testMap.add("C", "3");
        testMap.add("D", "4");

        String refValue = refMap.value("");
        String testValue = testMap.value("");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testHasKey_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        boolean refValue = refMap.hasKey("1");
        boolean testValue = testMap.hasKey("1");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testHasKey_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        boolean refValue = refMap.hasKey("3");
        boolean testValue = testMap.hasKey("3");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testHasKey_3() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        boolean refValue = refMap.hasKey("B");
        boolean testValue = testMap.hasKey("B");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testHasKey_4() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("", "5");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("", "5");
        testMap.add("C", "3");
        testMap.add("D", "4");

        boolean refValue = refMap.hasKey("");
        boolean testValue = testMap.hasKey("");

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testSize_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        int refValue = refMap.size();
        int testValue = testMap.size();

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testSize_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("", "E");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("", "E");
        testMap.add("3", "C");
        testMap.add("4", "D");

        int refValue = refMap.size();
        int testValue = testMap.size();

        assertEquals(refMap, testMap);
        assertEquals(refValue, testValue);
    }

    @Test
    public final void testRemoveAny_1() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("1", "A");
        refMap.add("2", "B");
        refMap.add("3", "C");
        refMap.add("4", "D");
        testMap.add("1", "A");
        testMap.add("2", "B");
        testMap.add("3", "C");
        testMap.add("4", "D");

        Pair<String, String> testPair = testMap.removeAny();

        boolean refMapKey = refMap.hasKey(testPair.key());
        assertEquals(true, refMapKey);

        Pair<String, String> refPair = refMap.remove(testPair.key());

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

    @Test
    public final void testRemoveAny_2() {
        Map<String, String> refMap = this.createFromArgsRef();
        Map<String, String> testMap = this.createFromArgsTest();

        refMap.add("A", "1");
        refMap.add("B", "2");
        refMap.add("C", "3");
        refMap.add("D", "4");
        testMap.add("A", "1");
        testMap.add("B", "2");
        testMap.add("C", "3");
        testMap.add("D", "4");

        Pair<String, String> testPair = testMap.removeAny();

        boolean refMapKey = refMap.hasKey(testPair.key());
        assertEquals(true, refMapKey);

        Pair<String, String> refPair = refMap.remove(testPair.key());

        assertEquals(refMap, testMap);
        assertEquals(refPair, testPair);
    }

}
