import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public abstract class StatementTest {

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/statement1.bl",
            FILE_NAME_2 = "test/statement2.bl",
            FILE_NAME_VALID_1 = "test/statement_valid_1.bl",
            FILE_NAME_VALID_2 = "test/statement_valid_2.bl",
            FILE_NAME_INVALID_1 = "test/statement_invalid_1.bl",
            FILE_NAME_INVALID_2 = "test/statement_invalid_2.bl",
            FILE_NAME_INVALID_3 = "test/statement_invalid_3.bl",
            FILE_NAME_INVALID_4 = "test/statement_invalid_4.bl",
            FILE_NAME_INVALID_5 = "test/statement_invalid_5.bl",
            FILE_NAME_INVALID_6 = "test/statement_invalid_6.bl",
            FILE_NAME_INVALID_7 = "test/statement_invalid_7.bl",
            FILE_NAME_INVALID_8 = "test/statement_invalid_8.bl",
            FILE_NAME_INVALID_9 = "test/statement_invalid_9.bl",
            FILE_NAME_INVALID_10 = "test/statement_invalid_10.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructorTest = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructorRef = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    // TODO - add more test cases for valid inputs for both parse and parseBlock

    /**
     * Test for multiple instructions within a while block.
     */
    @Test
    public final void testParseValid_1() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_VALID_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_VALID_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test for add user-defined instructions within a block. Test for parse()
     * method
     */
    @Test
    public final void testParseValid_2() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_VALID_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_VALID_2);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    // TODO - add more test cases for as many distinct syntax errors as possible
    //        for both parse and parseBlock

    /**
     * Test for invalid condition in while block.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_1() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for invalid user-defined instruction in a block.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_2() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for invalid else statement in the if else.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_3() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_3);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for missing END for while block
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_4() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_4);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for missing END for if block
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_5() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_5);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for invalid keyword IIF in if else. Test for parse() method.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_6() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_6);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test for valid THEN replaced by invalid DO in if else.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_7() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_7);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for valid DO replaced by invalid THEN in while.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_8() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_8);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parseBlock(tokens);
    }

    /**
     * Test for invalid keyword WHILEE. Test for parse() method.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_9() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_9);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test for invalid user-defined instruction in a block. Test for parse()
     * method.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseError_10() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_10);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

}
