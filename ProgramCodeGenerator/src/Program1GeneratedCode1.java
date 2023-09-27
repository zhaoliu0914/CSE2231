import java.util.Iterator;

import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.utilities.Reporter;

/**
 * Layered implementation of secondary method {@code generatedCode} for
 * {@code Program}.
 *
 * @author Zhao Liu
 *
 */
public final class Program1GeneratedCode1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Checks that value, an object of type V, is not aliased with any value
     * stored in a pair of map. The package-wide precondition, known as the
     * "repeated argument rule", that no argument may be aliased with any other,
     * does not apply to this method; hence, if one argument here is aliased
     * with the other, this method is obliged to terminate normally, returning
     * false. This behavior allows this method to be used to check for
     * violations of the repeated argument rule.
     *
     * @param <K>
     *            the type of the key in the map
     * @param <V>
     *            the type of the value in the map
     * @param value
     *            the object of type V to be checked for aliasing in the map
     * @param map
     *            the map in which aliasing is to be checked
     * @return whether value is aliased in map
     * @ensures notAliased = [value is not aliased in map]
     */
    private static <K, V> boolean notAliased(V value, Map<K, V> map) {
        assert value != null : "Violation of: value is not null";
        assert map != null : "Violation of: map is not null";

        boolean notAliased = true;
        Iterator<Map.Pair<K, V>> it = map.iterator();
        while (notAliased && it.hasNext()) {
            notAliased = value != it.next().value();
        }
        return notAliased;
    }

    /**
     * Constructs into the given {@code Program} the program read from the given
     * input file.
     *
     * @param fileName
     *            the name of the file containing the program
     * @param p
     *            the constructed program
     * @replaces p
     * @requires [fileName is the name of a file containing a valid BL program]
     * @ensures p = [program from file fileName]
     */
    private static void loadProgram(String fileName, Program p) {
        SimpleReader in = new SimpleReader1L(fileName);
        p.parse(in);
        in.close();
    }

    /**
     * Saves the given compiled program {@code cp} to the given output file.
     *
     * @param fileName
     *            the name of the file containing the program
     * @param cp
     *            the compiled program
     * @requires <pre>
     * [fileName is the name of a file to be used to save the compiled program]
     * </pre>
     * @ensures [cp is saved to to file fileName prefixed by the length of cp]
     */
    private static void saveCompiledProgram(String fileName,
            Sequence<Integer> cp) {
        SimpleWriter out = new SimpleWriter1L(fileName);
        out.println(cp.length());
        for (Integer i : cp) {
            out.println(i);
        }
        out.close();
    }

    /**
     * Converts {@code Condition} into corresponding conditional jump
     * instruction byte code.
     *
     * @param c
     *            the {@code Condition} to be converted
     * @return the conditional jump instruction byte code corresponding to
     *         {@code c}
     * @ensures <pre>
     * conditionalJump =
     *  [conditional jump instruction byte code corresponding to c]
     * </pre>
     */
    private static Instruction conditionalJump(Condition c) {
        assert c != null : "Violation of: c is not null";
        Instruction result;
        switch (c) {
            case NEXT_IS_EMPTY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_EMPTY;
                break;
            }
            case NEXT_IS_NOT_EMPTY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_EMPTY;
                break;
            }
            case NEXT_IS_ENEMY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_ENEMY;
                break;
            }
            case NEXT_IS_NOT_ENEMY: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_ENEMY;
                break;
            }
            case NEXT_IS_FRIEND: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_FRIEND;
                break;
            }
            case NEXT_IS_NOT_FRIEND: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_FRIEND;
                break;
            }
            case NEXT_IS_WALL: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_WALL;
                break;
            }
            case NEXT_IS_NOT_WALL: {
                result = Instruction.JUMP_IF_NOT_NEXT_IS_NOT_WALL;
                break;
            }
            case RANDOM: {
                result = Instruction.JUMP_IF_NOT_RANDOM;
                break;
            }
            default: { // case TRUE
                result = Instruction.JUMP_IF_NOT_TRUE;
                break;
            }
        }
        return result;
    }

    /**
     * Generates the sequence of virtual machine instructions ("byte codes")
     * corresponding to {@code s} and appends it at the end of {@code cp}.
     *
     * @param s
     *            the {@code Statement} for which to generate code
     * @param context
     *            the {@code Context} in which to find user defined instructions
     * @param cp
     *            the {@code Sequence} containing the generated code
     * @updates cp
     * @ensures <pre>
     * if [all instructions called in s are either primitive or
     *     defined in context]  and
     *    [context does not include any calling cycles, i.e., recursion] then
     *  cp = #cp * s[the sequence of virtual machine "byte codes" corresponding to s]
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void generateCodeForStatement(Statement s,
            Map<String, Statement> context, Sequence<Integer> cp) {
        assert s != null : "Violation of: s is not null";
        assert context != null : "Violation of: context is not null";
        assert cp != null : "Violation of: cp is not null";
        assert notAliased(s, context) : "Violation of: s is not "
                + "aliased in context (the repeated argument rule)";

        final int dummy = 0;

        switch (s.kind()) {
            case BLOCK: {

                int size = s.lengthOfBlock();
                for (int i = 0; i < size; i++) {
                    Statement tempBlock = s.removeFromBlock(i);

                    generateCodeForStatement(tempBlock, context, cp);

                    s.addToBlock(i, tempBlock);
                }

                break;
            }
            case IF: {
                Statement b = s.newInstance();

                Condition c = s.disassembleIf(b);

                cp.add(cp.length(), conditionalJump(c).byteCode());

                int jump = cp.length();
                cp.add(cp.length(), dummy);

                generateCodeForStatement(b, context, cp);

                cp.replaceEntry(jump, cp.length());

                s.assembleIf(c, b);

                break;
            }
            case IF_ELSE: {

                Statement trueStatement = s.newInstance();
                Statement falseStatement = s.newInstance();

                Condition condition = s.disassembleIfElse(trueStatement,
                        falseStatement);

                // write JUMP_IF_NOT_condition
                cp.add(cp.length(), conditionalJump(condition).byteCode());

                // write placeholder of starting address of false statement
                int firstJump = cp.length();
                cp.add(cp.length(), dummy);

                generateCodeForStatement(trueStatement, context, cp);

                // write JUMP
                cp.add(cp.length(), Instruction.JUMP.byteCode());

                // write placeholder of next address of end IF_ELSE statement
                int secondJump = cp.length();
                cp.add(cp.length(), dummy);

                // replace the placeholder of real starting address of false statement
                cp.replaceEntry(firstJump, cp.length());

                generateCodeForStatement(falseStatement, context, cp);

                // replace the placeholder of next address of end ...
                cp.replaceEntry(secondJump, cp.length());

                s.assembleIfElse(condition, trueStatement, falseStatement);

                break;
            }
            case WHILE: {

                Statement whileStatement = s.newInstance();

                Condition condition = s.disassembleWhile(whileStatement);

                // write JUMP_IF_NOT_condition
                int whileIndex = cp.length();
                cp.add(whileIndex, conditionalJump(condition).byteCode());

                // write placeholder of next address of after while block.
                int jump = cp.length();
                cp.add(cp.length(), dummy);

                generateCodeForStatement(whileStatement, context, cp);

                // write JUMP
                cp.add(cp.length(), Instruction.JUMP.byteCode());

                // write starting address of while block.
                cp.add(cp.length(), whileIndex);

                // replace the placeholder of next address of after while block
                cp.replaceEntry(jump, cp.length());

                s.assembleWhile(condition, whileStatement);

                break;
            }
            case CALL: {

                String instr = s.disassembleCall();

                // checking for primitive instruction
                if (instr.equals("move")) {
                    cp.add(cp.length(), Instruction.MOVE.byteCode());
                } else if (instr.equals("turnleft")) {
                    cp.add(cp.length(), Instruction.TURNLEFT.byteCode());
                } else if (instr.equals("turnright")) {
                    cp.add(cp.length(), Instruction.TURNRIGHT.byteCode());
                } else if (instr.equals("infect")) {
                    cp.add(cp.length(), Instruction.INFECT.byteCode());
                } else if (instr.equals("skip")) {
                    cp.add(cp.length(), Instruction.SKIP.byteCode());
                } else if (context.hasKey(instr)) {
                    // checking for user-defined instruction by looking for Context

                    Map.Pair<String, Statement> matchedContext = context
                            .remove(instr);
                    Statement userDefinedStatement = matchedContext.value();

                    generateCodeForStatement(userDefinedStatement, context, cp);

                    context.add(matchedContext.key(), matchedContext.value());

                } else {
                    // The instruction does not belong to primitive instruction
                    // and can not find in the Context
                    Reporter.assertElseFatalError(false,
                            "User-defined Instruction " + instr
                                    + " does not exist in context!!!");
                }

                s.assembleCall(instr);

                break;
            }
            default: {
                // this will never happen...
                break;
            }
        }
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Default constructor.
     */
    public Program1GeneratedCode1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public Sequence<Integer> generatedCode() {
        Sequence<Integer> cp = new Sequence1L<Integer>();

        Map<String, Statement> tempContext = this.newContext();
        Statement tempBody = this.newBody();

        this.swapContext(tempContext);
        this.swapBody(tempBody);

        generateCodeForStatement(tempBody, tempContext, cp);

        cp.add(cp.length(), Instruction.HALT.byteCode());

        this.swapContext(tempContext);
        this.swapBody(tempBody);

        return cp;
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Generate expected output in file "data/expected-output.txt"
         */
        out.println("*** Generating expected output ***");
        Program p1 = new Program1();
        loadProgram(fileName, p1);
        Sequence<Integer> cp1 = p1.generatedCode();
        saveCompiledProgram("data/expected-output.txt", cp1);
        /*
         * Disassemble generated code
         */
        out.println("*** Expected disassembled output ***");
        Program1.disassembleProgram(out, cp1);
        /*
         * Generate actual output in file "data/actual-output.txt"
         */
        out.println("*** Generating actual output ***");
        Program p2 = new Program1GeneratedCode1();
        loadProgram(fileName, p2);
        Sequence<Integer> cp2 = p2.generatedCode();
        saveCompiledProgram("data/actual-output.txt", cp2);
        /*
         * Disassemble generated code
         */
        out.println("*** Actual disassembled output ***");
        Program1.disassembleProgram(out, cp2);
        /*
         * Check that generatedCode restored the value of the program
         */
        out.println();
        if (p2.equals(p1)) {
            out.println("Program value restored correctly.");
        } else {
            out.println("Error: program value was not restored.");
        }

        in.close();
        out.close();
    }

}
