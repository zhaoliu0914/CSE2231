import components.stack.Stack;
import components.stack.Stack2;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> stack = new Stack2<>();

        // This line added just to make the program compilable.
        return stack;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> stack = new Stack2<>();

        // This line added just to make the program compilable.
        return stack;
    }

}
