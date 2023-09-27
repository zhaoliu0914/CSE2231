import components.queue.QueueSecondary;

/**
 * {@code Queue} represented as a singly linked list, done "bare-handed", with
 * implementations of primary methods.
 *
 * <p>
 * Execution-time performance of all methods implemented in this class is O(1).
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @convention <pre>
 * $this.length >= 0  and
 * [$this.preFront is not null]  and
 * [$this.rear is not null]  and
 * [$this.preFront points to the first node of a singly linked list
 *  containing $this.length + 1 nodes]  and
 * [$this.rear points to the last node in that singly linked list]  and
 * [$this.rear.next is null]
 * </pre>
 * @correspondence <pre>
 * this = [data in nodes starting at $this.preFront.next and
 *  running through $this.rear]
 * </pre>
 */
public abstract class Queue2<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Node class for singly linked list nodes.
     */
    private final class Node {

        /**
         * Data in node.
         */
        private T data;

        /**
         * Next node in singly linked list, or null.
         */
        private Node next;

    }

    /**
     * "Smart node" before front node of singly linked list.
     */
    private Node preFront;

    /**
     * Rear node of singly linked list.
     */
    private Node rear;

    /**
     * One less than number of nodes in singly linked list, i.e., length =
     * |this|.
     */
    private int length;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.preFront = new Node();
        this.preFront.next = null;
        this.rear = this.preFront;
        this.length = 0;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Np-argument constructor.
     */
    public Queue2() {
        this.createNewRep();
    }

    /*
     * Standard methods removed to reduce clutter...
     */

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";
        Node p = new Node();
        Node q = this.rear;
        p.data = x;
        p.next = null;
        q.next = p;
        this.rear = p;
        this.length++;
    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";
        Node p = this.preFront;
        Node q = p.next;
        T result = q.data;
        this.preFront = q;
        this.length--;
        return result;
    }

    @Override
    public final int length() {
        return this.length;
    }

    /*
     * Iterator code removed to reduce clutter...
     */

    /*
     * Other methods (overridden for performance reasons) ---------------------
     */

    @Override
    public final T front() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.preFront.next.data;
    }

    @Override
    public final T replaceFront(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";
        T front = this.preFront.next.data;
        this.preFront.next.data = x;
        return front;
    }

}
