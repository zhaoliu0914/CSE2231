import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Zhao Liu
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    public final void testAdd_1() {
        Sequence<String> testSequence = this.createFromArgsTest();
        Sequence<String> refSequence = this.createFromArgsRef();

        testSequence.add(0, "a");
        refSequence.add(0, "a");

        assertEquals(testSequence, refSequence);
    }

    @Test
    public final void testAdd_2() {
        Sequence<String> testSequence = this.createFromArgsTest("a");
        Sequence<String> refSequence = this.createFromArgsRef("a");

        testSequence.add(0, "b");
        refSequence.add(0, "b");

        assertEquals(testSequence, refSequence);
    }

    @Test
    public final void testAdd_3() {
        Sequence<String> testSequence = this.createFromArgsTest("a", "b", "c");
        Sequence<String> refSequence = this.createFromArgsRef("a", "b", "c");

        testSequence.add(testSequence.length(), "d");
        refSequence.add(refSequence.length(), "d");

        assertEquals(refSequence, testSequence);
    }

    @Test
    public final void testAdd_4() {
        Sequence<String> testSequence = this.createFromArgsTest();
        Sequence<String> refSequence = this.createFromArgsRef();

        testSequence.add(testSequence.length(), "a");
        refSequence.add(refSequence.length(), "a");

        testSequence.add(testSequence.length(), "b");
        refSequence.add(refSequence.length(), "b");

        testSequence.add(testSequence.length(), "c");
        refSequence.add(refSequence.length(), "c");

        testSequence.add(testSequence.length(), "d");
        refSequence.add(refSequence.length(), "d");

        assertEquals(refSequence, testSequence);
    }

    @Test
    public final void testRemove_1() {
        Sequence<String> testSequence = this.createFromArgsTest("a", "b", "c");
        Sequence<String> refSequence = this.createFromArgsRef("a", "b", "c");

        testSequence.remove(0);
        refSequence.remove(0);

        assertEquals(testSequence, refSequence);
    }

    @Test
    public final void testRemove_2() {
        Sequence<String> testSequence = this.createFromArgsTest("a", "b", "c");
        Sequence<String> refSequence = this.createFromArgsRef("a", "b", "c");

        testSequence.remove(1);
        refSequence.remove(1);

        assertEquals(testSequence, refSequence);
    }

    @Test
    public final void testRemove_3() {
        Sequence<String> testSequence = this.createFromArgsTest("a", "b", "c",
                "d");
        Sequence<String> refSequence = this.createFromArgsRef("a", "b", "c",
                "d");

        testSequence.remove(testSequence.length() - 1);
        refSequence.remove(refSequence.length() - 1);

        assertEquals(testSequence, refSequence);
    }

    @Test
    public final void testLength_1() {
        Sequence<String> testSequence = this.createFromArgsTest();
        Sequence<String> refSequence = this.createFromArgsRef();

        int testLength = testSequence.length();
        int refLength = refSequence.length();

        assertEquals(testLength, refLength);
    }

    @Test
    public final void testLength_2() {
        Sequence<String> testSequence = this.createFromArgsTest("a", "b", "c");
        Sequence<String> refSequence = this.createFromArgsRef("a", "b", "c");

        int testLength = testSequence.length();
        int refLength = refSequence.length();

        assertEquals(testLength, refLength);
    }

}
