package components.waitingline;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Customized JUnit test fixture for {@code WaitingLine1}.
 */
public class WaitingLine1Test extends WaitingLineTest {

    @Override
    protected final Queue<String> constructorRef() {
        return new Queue1L<String>();
    }

    @Override
    protected final WaitingLine<String> constructorTest() {
        return new WaitingLine1<String>();
    }

}
