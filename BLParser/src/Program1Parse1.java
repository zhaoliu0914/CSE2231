import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Zhao Liu
 * @author Zishu Ling
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        //   INSTRUCTION FindObstacle IS
        //      infect
        //      WHILE next-is-empty DO
        //         move
        //      END WHILE
        //   END FindObstacle

        // The identifier at the end of each new instruction definition
        // must be the same as the identifier at the beginning of the definition.

        // The name of each new user-defined instruction must be unique,
        // i.e., there cannot be two user-defined instructions with the same name.

        // The name of each new user-defined instruction must not be
        // the name of one of the primitive instructions,
        // i.e., move, turnleft, turnright, infect, or skip.

        tokens.dequeue();

        String instructionName = tokens.dequeue();

        boolean isValid = Tokenizer.isIdentifier(instructionName);
        if (!isValid) {
            Reporter.assertElseFatalError(false, "Instruction name "
                    + instructionName + " is not a valid name.");
        }
        if (instructionName.equals("move") || instructionName.equals("turnleft")
                || instructionName.equals("turnright")
                || instructionName.equals("infect")
                || instructionName.equals("skip")) {
            Reporter.assertElseFatalError(false, "Instruction name "
                    + instructionName + " can not be a name of primitive.");
        }

        String isKeyword = tokens.dequeue();
        if (!isKeyword.equals("IS")) {
            Reporter.assertElseFatalError(false, "Missing keyword IS.");
        }

        body.parseBlock(tokens);

        // Checking for END keyword
        String endKeyword = tokens.dequeue();
        if (!endKeyword.equals("END")) {
            Reporter.assertElseFatalError(false, "Missing keyword END.");
        }

        // Checking for program name
        String endInstructionName = tokens.dequeue();
        isValid = Tokenizer.isIdentifier(endInstructionName);
        if (!isValid) {
            Reporter.assertElseFatalError(false, "Instruction name "
                    + endInstructionName + " is not a valid name.");
        }
        if (!instructionName.equals(endInstructionName)) {
            Reporter.assertElseFatalError(false, "Instruction name "
                    + endInstructionName
                    + " does not match name at the end of Instruction.");
        }

        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // PROGRAM SampleProgram IS
        //
        //   INSTRUCTION FindObstacle IS
        //      WHILE next-is-empty DO
        //         move
        //      END WHILE
        //   END FindObstacle
        //
        //   INSTRUCTION TurnAround IS
        //      turnright
        //      turnright
        //    END TurnAround
        //
        // BEGIN
        //    turnright
        //    infect
        //    WHILE true DO
        //       FindObstacle
        //       turnleft
        //    END WHILE
        //    IF next-is-wall THEN
        //       turnleft
        //    ELSE
        //       TurnAround
        //    END IF
        // END SampleProgram

        // The identifier at the end of the program must be the same as
        // the identifier at the beginning of the program.

        // Checking for the PROGRAM keyword
        String programKeyword = tokens.dequeue();
        if (!programKeyword.equals("PROGRAM")) {
            Reporter.assertElseFatalError(false, "Missing keyword PROGRAM.");
        }

        // Checking whether program name is a valid name
        String programName = tokens.dequeue();
        boolean isValid = Tokenizer.isIdentifier(programName);
        if (!isValid) {
            Reporter.assertElseFatalError(false,
                    "Program name " + programName + " is not a valid name.");
        }

        // Checking for the IS keyword
        String isKeyword = tokens.dequeue();
        if (!isKeyword.equals("IS")) {
            Reporter.assertElseFatalError(false, "Missing keyword IS.");
        }

        Map<String, Statement> context = this.newContext();

        // Call parseInstruction method,
        // and put instruction name and statement into context.

        String instructionKeyword = tokens.front();
        while (instructionKeyword.equals("INSTRUCTION")) {
            Statement statement = this.newBody();

            String instructionName = parseInstruction(tokens, statement);

            // The name of each new user-defined instruction must be unique,
            // i.e., there cannot be two user-defined instructions with the same name.
            if (context.hasKey(instructionName)) {
                Reporter.assertElseFatalError(false, "Instruction name "
                        + instructionName + " is duplicated");
            }

            context.add(instructionName, statement);

            instructionKeyword = tokens.front();
        }

        // Checking for BEGIN keyword
        String beginKeyword = tokens.dequeue();
        if (!beginKeyword.equals("BEGIN")) {
            Reporter.assertElseFatalError(false, "Missing keyword BEGIN.");
        }

        // parse block by calling parseBlock defined in Statement1.
        Statement bodyStatement = this.newBody();
        bodyStatement.parseBlock(tokens);

        // Checking for END keyword
        String endKeyword = tokens.dequeue();
        if (!endKeyword.equals("END")) {
            Reporter.assertElseFatalError(false, "Missing keyword END.");
        }

        // Checking for program name
        String endProgramName = tokens.dequeue();
        isValid = Tokenizer.isIdentifier(endProgramName);
        if (!isValid) {
            Reporter.assertElseFatalError(false,
                    "Program name " + endProgramName + " is not a valid name.");
        }
        if (!programName.equals(endProgramName)) {
            Reporter.assertElseFatalError(false,
                    "Program name " + endProgramName
                            + " does not match name at the end of program.");
        }

        // Checking whether tokens contain Tokenizer.END_OF_INPUT
        if (tokens.length() != 1) {
            Reporter.assertElseFatalError(false,
                    "There are some invalid syntax sentence at the end of program");
        }

        this.setName(programName);
        this.swapContext(context);
        this.swapBody(bodyStatement);
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
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
