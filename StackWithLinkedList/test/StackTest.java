import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Zhao Liu
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length
    /*
     * Test empty constructor
     */
    @Test
    public final void testEmptyConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.constructorRef();
        Stack<String> m = this.constructorTest();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test push method. Test for add String values.
     */
    @Test
    public final void testPushConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef();
        Stack<String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        mExpected.push("13");
        mExpected.push("6");
        mExpected.push("18");

        m.push("13");
        m.push("6");
        m.push("18");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test push method. Test for add String values.
     */
    @Test
    public final void testPushConstructor_2() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef();
        Stack<String> m = this.createFromArgsTest();

        /*
         * Call method under test
         */
        mExpected.push("13");
        mExpected.push("6");
        mExpected.push("18");
        mExpected.push("21");
        mExpected.push("33");
        mExpected.push("47");
        mExpected.push("52");
        mExpected.push("98");

        m.push("13");
        m.push("6");
        m.push("18");
        m.push("21");
        m.push("33");
        m.push("47");
        m.push("52");
        m.push("98");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * Test pop method. Test for only 1 element.
     */
    @Test
    public final void testPopConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("16");
        Stack<String> m = this.createFromArgsTest("16");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.pop();

        String value = m.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test pop method. Test for only 2 elements.
     */
    @Test
    public final void testPopConstructor_2() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("13", "6");
        Stack<String> m = this.createFromArgsTest("13", "6");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.pop();

        String value = m.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test pop method. Test for many elements.
     */
    @Test
    public final void testPopConstructor_3() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("13", "6", "16", "21",
                "33");
        Stack<String> m = this.createFromArgsTest("13", "6", "16", "21", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.pop();
        valueExpected = mExpected.pop();

        String value = m.pop();
        value = m.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test length method.
     */
    @Test
    public final void testLengthConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("13", "6", "16", "21",
                "33");
        Stack<String> m = this.createFromArgsTest("13", "6", "16", "21", "33");

        /*
         * Call method under test
         */
        int valueExpected = mExpected.length();

        int value = m.length();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test top method.
     */
    @Test
    public final void testTopConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("13", "6", "16", "21",
                "33");
        Stack<String> m = this.createFromArgsTest("13", "6", "16", "21", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.top();

        String value = m.top();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

    /*
     * Test replaceTop method.
     */
    @Test
    public final void testReplaceTopConstructor_1() {
        /*
         * Set up variables
         */
        Stack<String> mExpected = this.createFromArgsRef("13", "6", "16", "21",
                "33");
        Stack<String> m = this.createFromArgsTest("13", "6", "16", "21", "33");

        /*
         * Call method under test
         */
        String valueExpected = mExpected.replaceTop("985");

        String value = m.replaceTop("985");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(valueExpected, value);
    }

}
