import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Zhao Liu
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    @Test
    public final void testAdd_1() {
        Set<String> refSet = this.createFromArgsRef();
        Set<String> testSet = this.createFromArgsTest();

        refSet.add("4");
        testSet.add("4");

        assertEquals(refSet, testSet);
    }

    @Test
    public final void testAdd_2() {
        Set<String> refSet = this.createFromArgsRef("4");
        Set<String> testSet = this.createFromArgsTest("4");

        refSet.add("2");
        testSet.add("2");

        assertEquals(refSet, testSet);
    }

    @Test
    public final void testAdd_3() {
        Set<String> refSet = this.createFromArgsRef("4", "2");
        Set<String> testSet = this.createFromArgsTest("4", "2");

        refSet.add("7");
        refSet.add("1");
        testSet.add("7");
        testSet.add("1");

        assertEquals(refSet, testSet);
    }

    @Test
    public final void testAdd_4() {
        Set<String> refSet = this.createFromArgsRef();
        Set<String> testSet = this.createFromArgsTest();

        refSet.add("4");
        refSet.add("2");
        refSet.add("7");
        refSet.add("1");
        testSet.add("4");
        testSet.add("2");
        testSet.add("7");
        testSet.add("1");

        assertEquals(refSet, testSet);
    }

    @Test
    public final void testRemove_1() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        String refReturn = refSet.remove("4");
        String testReturn = testSet.remove("4");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testRemove_2() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        String refReturn1 = refSet.remove("7");
        String testReturn1 = testSet.remove("7");

        assertEquals(refSet, testSet);
        assertEquals(refReturn1, testReturn1);
    }

    @Test
    public final void testRemove_3() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "", "7", "1");

        String refReturn = refSet.remove("");
        String testReturn = testSet.remove("");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testContains_1() {
        Set<String> refSet = this.createFromArgsRef();
        Set<String> testSet = this.createFromArgsTest();

        boolean refReturn = refSet.contains("4");
        boolean testReturn = testSet.contains("4");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testContains_2() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        boolean refReturn = refSet.contains("7");
        boolean testReturn = testSet.contains("7");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testContains_3() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        boolean refReturn = refSet.contains("9");
        boolean testReturn = testSet.contains("9");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testContains_4() {
        Set<String> refSet = this.createFromArgsRef();
        Set<String> testSet = this.createFromArgsTest();

        boolean refReturn = refSet.contains("9");
        boolean testReturn = testSet.contains("9");

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testSize_1() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        int refReturn = refSet.size();
        int testReturn = testSet.size();

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testSize_2() {
        Set<String> refSet = this.createFromArgsRef();
        Set<String> testSet = this.createFromArgsTest();

        int refReturn = refSet.size();
        int testReturn = testSet.size();

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testRemoveAny_1() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        String testReturn = testSet.removeAny();

        boolean refSetKey = refSet.contains(testReturn);
        assertEquals(true, refSetKey);

        String refReturn = refSet.remove(testReturn);

        assertEquals(refSet, testSet);
        assertEquals(refReturn, testReturn);
    }

    @Test
    public final void testRemoveAny_2() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        String testReturn1 = testSet.removeAny();
        boolean refSetKey1 = refSet.contains(testReturn1);
        assertEquals(true, refSetKey1);
        String refReturn1 = refSet.remove(testReturn1);

        String testReturn2 = testSet.removeAny();
        boolean refSetKey2 = refSet.contains(testReturn2);
        assertEquals(true, refSetKey2);
        String refReturn2 = refSet.remove(testReturn2);

        assertEquals(refSet, testSet);
        assertEquals(refReturn1, testReturn1);
        assertEquals(refReturn2, testReturn2);
    }

    @Test
    public final void testRemoveAny_3() {
        Set<String> refSet = this.createFromArgsRef("4", "2", "7", "1");
        Set<String> testSet = this.createFromArgsTest("4", "2", "7", "1");

        String testReturn1 = testSet.removeAny();
        boolean refSetKey1 = refSet.contains(testReturn1);
        assertEquals(true, refSetKey1);
        String refReturn1 = refSet.remove(testReturn1);

        String testReturn2 = testSet.removeAny();
        boolean refSetKey2 = refSet.contains(testReturn2);
        assertEquals(true, refSetKey2);
        String refReturn2 = refSet.remove(testReturn2);

        String testReturn3 = testSet.removeAny();
        boolean refSetKey3 = refSet.contains(testReturn3);
        assertEquals(true, refSetKey3);
        String refReturn3 = refSet.remove(testReturn3);

        assertEquals(refSet, testSet);
        assertEquals(refReturn1, testReturn1);
        assertEquals(refReturn2, testReturn2);
        assertEquals(refReturn3, testReturn3);
    }
}
