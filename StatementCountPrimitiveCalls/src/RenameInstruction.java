import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Layered implementation of Rename Instruction {@code renameInstruction} for
 * {@code Program}.
 *
 * @author Zhao Liu
 * @author Qiao Xiao
 * @author Jingyu Fu
 * @author Zishu Ling
 */
public final class RenameInstruction extends Program1 {

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
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of oldName is
     *   replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int numberOfBlocks = s.lengthOfBlock();
                for (int i = 0; i < numberOfBlocks; i++) {
                    Statement childStatement = s.removeFromBlock(i);

                    renameInstruction(childStatement, oldName, newName);

                    s.addToBlock(i, childStatement);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement childBlockStatement = s.newInstance();
                Condition condition = s.disassembleIf(childBlockStatement);

                renameInstruction(childBlockStatement, oldName, newName);

                s.assembleIf(condition, childBlockStatement);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement trueBlock = s.newInstance();
                Statement falseBlock = s.newInstance();

                Condition condition = s.disassembleIfElse(trueBlock,
                        falseBlock);

                renameInstruction(trueBlock, oldName, newName);

                renameInstruction(falseBlock, oldName, newName);

                s.assembleIfElse(condition, trueBlock, falseBlock);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement childBlockStatement = s.newInstance();

                Condition condition = s.disassembleWhile(childBlockStatement);

                renameInstruction(childBlockStatement, oldName, newName);

                s.assembleWhile(condition, childBlockStatement);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String instruction = s.disassembleCall();
                if (instruction.equals(oldName)) {
                    s.assembleCall(newName);
                } else {
                    s.assembleCall(instruction);
                }

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }

    /**
     * Refactors the given {@code Program} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other Program is
     * left unmodified.
     *
     * @param program
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of oldName is
     *   replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Program program, String oldName,
            String newName) {
        //assert out != null : "Violation of: out is not null";
        //assert out.isOpen() : "Violation of: out.is_open";
        assert program != null : "Violation of: program is not null";
        assert oldName != null : "Violation of: oldName is not null";
        assert newName != null : "Violation of: newName is not null";

        //String programName = program.name();
        //out.println("PROGRAM " + programName + " IS");
        //out.println();

        Map<String, Statement> tempContext = program.newContext();
        Map<String, Statement> fixedContext = program.newContext();

        program.swapContext(tempContext);

        while (tempContext.size() > 0) {
            Map.Pair<String, Statement> temp = tempContext.removeAny();
            String contextName = temp.key();
            Statement contextStatement = temp.value();

            if (contextName.equals(oldName)) {
                contextName = newName;

                renameInstruction(contextStatement, oldName, newName);
            }

            fixedContext.add(contextName, contextStatement);
        }

        program.swapContext(fixedContext);

        Statement tempBody = program.newBody();

        program.swapBody(tempBody);

        renameInstruction(tempBody, oldName, newName);

        program.swapBody(tempBody);

    }

    /**
     * main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();

        out.print("Enter old instruction name: ");
        String oldName = in.nextLine();

        out.print("Enter new instruction name: ");
        String newName = in.nextLine();

        /*
         * Generate actual output in file "data/actual-output.txt"
         */
        out.println("*** Generating actual output ***");
        Program p2 = new RenameInstruction();
        loadProgram(fileName, p2);
        renameInstruction(p2, oldName, newName);

        p2.prettyPrint(out);

        in.close();
        out.close();
    }

}
