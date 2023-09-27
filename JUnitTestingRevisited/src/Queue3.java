import java.util.Iterator;

import components.queue.Queue;
import components.queue.QueueSecondary;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Queue} represented as a {@code Sequence} of entries, with
 * implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @correspondence this = $this.entries
 */
public class Queue3<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries included in {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Queue3() {
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

        int size = this.entries.length();
        this.entries.add(size, x);
    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T entry = this.entries.remove(0);

        // This line added just to make the component compilable.
        return entry;
    }

    @Override
    public final int length() {

        int size = this.entries.length();

        // This line added just to make the component compilable.
        return size;
    }

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T entry = this.entries.entry(0);

        return entry;
    }

    @Override
    public Queue<T> newInstance() {
        Queue<T> queue = new Queue3<>();
        return queue;
    }

    @Override
    public void clear() {
        this.entries.clear();
    }

    @Override
    public void transferFrom(Queue<T> queue) {
        this.entries.clear();
        int size = queue.length();
        for (int i = 0; i < size; i++) {
            T temp = queue.dequeue();
            this.entries.add(i, temp);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.entries.iterator();
    }

    /*
     * Iterator removed to reduce clutter...
     */

}
