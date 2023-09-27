import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int numberOfBlocks = s.lengthOfBlock();
                for (int i = 0; i < numberOfBlocks; i++) {
                    Statement childStatement = s.removeFromBlock(i);

                    int tempCount = countOfPrimitiveCalls(childStatement);
                    count = count + tempCount;

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

                count = count + countOfPrimitiveCalls(childBlockStatement);

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

                count = count + countOfPrimitiveCalls(trueBlock);

                count = count + countOfPrimitiveCalls(falseBlock);

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

                count = count + countOfPrimitiveCalls(childBlockStatement);

                s.assembleWhile(condition, childBlockStatement);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String instruction = s.disassembleCall();
                if (instruction.equals("move") || instruction.equals("turnleft")
                        || instruction.equals("turnright")
                        || instruction.equals("infect")
                        || instruction.equals("skip")) {
                    count = 1;
                }

                s.assembleCall(instruction);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

    /**
     * Reports the number of calls to a given instruction, {@code instr}, in a
     * given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @param instr
     *            the instruction name
     * @return the number of calls to {@code instr} in {@code s}
     * @ensures countOfInstructionCalls = [number of calls to instr in s]
     */
    public static int countOfInstructionCalls(Statement s, String instr) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                int numberOfBlocks = s.lengthOfBlock();
                for (int i = 0; i < numberOfBlocks; i++) {
                    Statement childStatement = s.removeFromBlock(i);

                    int tempCount = countOfInstructionCalls(childStatement,
                            instr);
                    count = count + tempCount;

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

                count = count
                        + countOfInstructionCalls(childBlockStatement, instr);

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

                count = count + countOfInstructionCalls(trueBlock, instr);

                count = count + countOfInstructionCalls(falseBlock, instr);

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

                count = count
                        + countOfInstructionCalls(childBlockStatement, instr);

                s.assembleWhile(condition, childBlockStatement);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String instruction = s.disassembleCall();
                if (instruction.equals(instr)) {
                    count = 1;
                }

                s.assembleCall(instruction);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
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
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                int numberOfBlocks = s.lengthOfBlock();
                for (int i = 0; i < numberOfBlocks; i++) {
                    Statement childStatement = s.removeFromBlock(i);

                    simplifyIfElse(childStatement);

                    s.addToBlock(i, childStatement);
                }

                break;
            }
            case IF: {

                Statement childBlockStatement = s.newInstance();
                Condition condition = s.disassembleIf(childBlockStatement);

                simplifyIfElse(childBlockStatement);

                s.assembleIf(condition, childBlockStatement);

                break;
            }
            case IF_ELSE: {

                Statement trueBlock = s.newInstance();
                Statement falseBlock = s.newInstance();

                Condition condition = s.disassembleIfElse(trueBlock,
                        falseBlock);

                simplifyIfElse(trueBlock);

                simplifyIfElse(falseBlock);

                boolean isSimplify = false;

                switch (condition) {
                    case NEXT_IS_NOT_EMPTY: {
                        condition = Condition.NEXT_IS_EMPTY;
                        isSimplify = true;
                        break;
                    }
                    case NEXT_IS_NOT_WALL: {
                        condition = Condition.NEXT_IS_WALL;
                        isSimplify = true;
                        break;
                    }
                    case NEXT_IS_NOT_FRIEND: {
                        condition = Condition.NEXT_IS_FRIEND;
                        isSimplify = true;
                        break;
                    }
                    case NEXT_IS_NOT_ENEMY: {
                        condition = Condition.NEXT_IS_ENEMY;
                        isSimplify = true;
                        break;
                    }
                    default: {
                        break;
                    }
                }

                if (isSimplify) {
                    s.assembleIfElse(condition, falseBlock, trueBlock);
                } else {
                    s.assembleIfElse(condition, trueBlock, falseBlock);
                }

                break;
            }
            case WHILE: {

                Statement childBlockStatement = s.newInstance();

                Condition condition = s.disassembleWhile(childBlockStatement);

                simplifyIfElse(childBlockStatement);

                s.assembleWhile(condition, childBlockStatement);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }

}
