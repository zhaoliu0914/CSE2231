import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code prettyPrint} for
 * {@code Statement}.
 */
public final class Statement1PrettyPrint1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Constructs into the given {@code Statement} the BLOCK statement read from
     * the given input file.
     *
     * @param fileName
     *            the name of the file containing 0 or more statements
     * @param s
     *            the constructed BLOCK statement
     * @replaces s
     * @requires <pre>
     * [fileName is the name of a file containing 0 or more valid BL statements]
     * </pre>
     * @ensures s = [BLOCK statement from file fileName]
     */
    private static void loadStatement(String fileName, Statement s) {
        SimpleReader in = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(in);
        s.parseBlock(tokens);
        in.close();
    }

    /**
     * Prints the given number of spaces to the given output stream.
     *
     * @param out
     *            the output stream
     * @param numSpaces
     *            the number of spaces to print
     * @updates out.content
     * @requires out.is_open and spaces >= 0
     * @ensures out.content = #out.content * [numSpaces spaces]
     */
    private static void printSpaces(SimpleWriter out, int numSpaces) {
        for (int i = 0; i < numSpaces; i++) {
            out.print(' ');
        }
    }

    /**
     * Converts c into the corresponding BL condition.
     *
     * @param c
     *            the Condition to convert
     * @return the BL condition corresponding to c
     * @ensures toStringCondition = [BL condition corresponding to c]
     */
    private static String toStringCondition(Condition c) {
        String result;
        switch (c) {
            case NEXT_IS_EMPTY: {
                result = "next-is-empty";
                break;
            }
            case NEXT_IS_NOT_EMPTY: {
                result = "next-is-not-empty";
                break;
            }
            case NEXT_IS_ENEMY: {
                result = "next-is-enemy";
                break;
            }
            case NEXT_IS_NOT_ENEMY: {
                result = "next-is-not-enemy";
                break;
            }
            case NEXT_IS_FRIEND: {
                result = "next-is-friend";
                break;
            }
            case NEXT_IS_NOT_FRIEND: {
                result = "next-is-not-friend";
                break;
            }
            case NEXT_IS_WALL: {
                result = "next-is-wall";
                break;
            }
            case NEXT_IS_NOT_WALL: {
                result = "next-is-not-wall";
                break;
            }
            case RANDOM: {
                result = "random";
                break;
            }
            case TRUE: {
                result = "true";
                break;
            }
            default: {
                // this will never happen...
                result = "";
                break;
            }
        }
        return result;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1PrettyPrint1() {
        super();
    }

    /*
     * Secondary methods ------------------------------------------------------
     */

    /**
     * Pretty prints {@code this} to the given stream {@code out} {@code offset}
     * spaces from the left margin using
     * {@link components.program.Program#INDENT_SIZE Program.INDENT_SIZE} spaces
     * for each indentation level.
     *
     * @param out
     *            the output stream
     * @param offset
     *            the number of spaces to be placed before every nonempty line
     *            of output; nonempty lines of output that are indented further
     *            will, of course, continue with even more spaces
     * @updates out.content
     * @requires out.is_open and 0 <= offset
     * @ensures <pre>
     * out.content =
     *   #out.content * [this pretty printed offset spaces from the left margin
     *                   using Program.INDENT_SIZE spaces for indentation]
     * </pre>
     */
    @Override
    public void prettyPrint(SimpleWriter out, int offset) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";
        assert offset >= 0 : "Violation of: 0 <= offset";

        switch (this.kind()) {
            case BLOCK: {

                int numberOfBlocks = this.lengthOfBlock();

                for (int i = 0; i < numberOfBlocks; i++) {
                    Statement childStatement = this.removeFromBlock(i);

                    Statement temp = this.newInstance();
                    temp.transferFrom(this);

                    this.transferFrom(childStatement);
                    this.prettyPrint(out, offset);

                    temp.addToBlock(i, this);
                    this.transferFrom(temp);
                }

                break;
            }
            case IF: {

                Statement childBlockStatement = this.newInstance();
                Condition condition = this.disassembleIf(childBlockStatement);

                String conditionStr = toStringCondition(condition);
                printSpaces(out, offset);
                out.println("IF " + conditionStr + " THEN");

                offset = offset + 4;

                Statement temp = this.newInstance();
                temp.transferFrom(this);

                this.transferFrom(childBlockStatement);
                this.prettyPrint(out, offset);

                temp.assembleIf(condition, this);
                this.transferFrom(temp);

                offset = offset - 4;

                printSpaces(out, offset);
                out.println("END IF");

                break;
            }
            case IF_ELSE: {

                Statement trueBlock = this.newInstance();
                Statement falseBlock = this.newInstance();

                Condition condition = this.disassembleIfElse(trueBlock,
                        falseBlock);

                String conditionStr = toStringCondition(condition);
                printSpaces(out, offset);
                out.println("IF " + conditionStr + " THEN");

                offset = offset + 4;

                Statement temp = this.newInstance();
                temp.transferFrom(this);

                this.transferFrom(trueBlock);
                this.prettyPrint(out, offset);

                trueBlock.transferFrom(this);

                offset = offset - 4;

                printSpaces(out, offset);
                out.println("ELSE");

                offset = offset + 4;

                this.transferFrom(falseBlock);
                this.prettyPrint(out, offset);

                falseBlock.transferFrom(this);

                temp.assembleIfElse(condition, trueBlock, falseBlock);
                this.transferFrom(temp);

                offset = offset - 4;

                printSpaces(out, offset);
                out.println("END IF");

                break;
            }
            case WHILE: {

                Statement childBlockStatement = this.newInstance();

                Condition condition = this
                        .disassembleWhile(childBlockStatement);

                String conditionStr = toStringCondition(condition);
                printSpaces(out, offset);
                out.println("WHILE " + conditionStr + " DO");

                offset = offset + 4;

                Statement temp = this.newInstance();
                temp.transferFrom(this);

                this.transferFrom(childBlockStatement);
                this.prettyPrint(out, offset);

                temp.assembleWhile(condition, this);
                this.transferFrom(temp);

                offset = offset - 4;

                printSpaces(out, offset);
                out.println("END WHILE");

                break;
            }
            case CALL: {

                String instruction = this.disassembleCall();

                printSpaces(out, offset);
                out.println(instruction);

                this.assembleCall(instruction);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement file name: ");
        String fileName = in.nextLine();
        /*
         * Generate expected output in file "data/expected-output.txt"
         */
        out.println("*** Generating expected output ***");
        Statement s1 = new Statement1();
        loadStatement(fileName, s1);
        SimpleWriter ppOut = new SimpleWriter1L("data/expected-output.txt");
        s1.prettyPrint(ppOut, 2);
        ppOut.close();
        /*
         * Generate actual output in file "data/actual-output.txt"
         */
        out.println("*** Generating actual output ***");
        Statement s2 = new Statement1PrettyPrint1();
        loadStatement(fileName, s2);
        ppOut = new SimpleWriter1L("data/actual-output.txt");
        s2.prettyPrint(ppOut, 2);
        ppOut.close();
        /*
         * Check that prettyPrint restored the value of the statement
         */
        if (s2.equals(s1)) {
            out.println("Statement value restored correctly.");
        } else {
            out.println("Error: statement value was not restored.");
        }

        in.close();
        out.close();
    }

}
