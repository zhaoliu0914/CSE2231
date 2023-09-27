import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code CountPrimitiveCalls}'s static method
 * countOfPrimitiveCalls.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCallsTest {

    /**
     * Creates and returns a {@code Statement} constructed from a given input
     * file.
     *
     * @param fileName
     *            the name of the file containing the statement
     * @param block
     *            flag to indicate whether to read an entire BLOCK (sequence of
     *            statements) or a single statement
     * @return the constructed statement
     * @requires <pre>
     * [fileName is the name of a file containing zero, one, or more
     *  valid BL statements]
     * </pre>
     * @ensures createFromArgs = [statement(s) from file fileName]
     */
    private Statement createFromArgs(String fileName, boolean block) {
        SimpleReader in = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(in);
        in.close();
        Statement s = new Statement1();
        if (block) {
            s.parseBlock(tokens);
        } else {
            s.parse(tokens);
        }
        return s;
    }

    @Test
    public void test1true() {
        Statement s1 = this.createFromArgs("data/test1.bl", true);
        Statement s2 = this.createFromArgs("data/test1.bl", true);
        int count = CountPrimitiveCalls.countOfPrimitiveCalls(s1);
        assertEquals(25, count);
        assertEquals(s2, s1);
    }

    // TODO: add other (smaller) test cases as needed
    @Test
    public void test_block_1() {
        Statement s1 = this.createFromArgs("data/test_block_1.bl", true);
        Statement s2 = this.createFromArgs("data/test_block_1.bl", true);
        int count = CountPrimitiveCalls.countOfPrimitiveCalls(s1);
        assertEquals(4, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_if_1() {
        Statement s1 = this.createFromArgs("data/test_if_1.bl", true);
        Statement s2 = this.createFromArgs("data/test_if_1.bl", true);
        int count = CountPrimitiveCalls.countOfPrimitiveCalls(s1);
        assertEquals(5, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_ifelse_1() {
        Statement s1 = this.createFromArgs("data/test_ifelse_1.bl", true);
        Statement s2 = this.createFromArgs("data/test_ifelse_1.bl", true);
        int count = CountPrimitiveCalls.countOfPrimitiveCalls(s1);
        assertEquals(10, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_while_1() {
        Statement s1 = this.createFromArgs("data/test_while_1.bl", true);
        Statement s2 = this.createFromArgs("data/test_while_1.bl", true);
        int count = CountPrimitiveCalls.countOfPrimitiveCalls(s1);
        assertEquals(3, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_countOfInstructionCalls_1() {
        Statement s1 = this.createFromArgs("data/test1.bl", true);
        Statement s2 = this.createFromArgs("data/test1.bl", true);
        int count = CountPrimitiveCalls.countOfInstructionCalls(s1, "infect");
        assertEquals(5, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_countOfInstructionCalls_2() {
        Statement s1 = this.createFromArgs("data/test1.bl", true);
        Statement s2 = this.createFromArgs("data/test1.bl", true);
        int count = CountPrimitiveCalls.countOfInstructionCalls(s1,
                "skip-again");
        assertEquals(1, count);
        assertEquals(s2, s1);
    }

    @Test
    public void test_renameInstruction_1() {
        Statement s1 = this.createFromArgs("data/test1.bl", true);
        Statement s2 = this.createFromArgs("data/test_rename_1.bl", true);
        CountPrimitiveCalls.renameInstruction(s1, "infect", "infect-move");
        assertEquals(s2, s1);
    }

    @Test
    public void test_simplifyIfElse_1() {
        Statement s1 = this.createFromArgs("data/test1.bl", true);
        Statement s2 = this.createFromArgs("data/test_simplify_IfElse_1.bl",
                true);
        CountPrimitiveCalls.simplifyIfElse(s1);
        assertEquals(s2, s1);
    }

}
