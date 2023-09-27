package components.waitingline;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code WaitingLine} represented as a {@code Sequence} with implementations of
 * primary methods.
 *
 * @param <T>
 *            type of {@code WaitingLine} entries
 * @correspondence this = [value of $this.rep]
 *
 * @author Zhao Liu
 * @author Ummehani Motiwala
 * @author Deepak Prabaharan
 */
public class WaitingLine1<T> extends WaitingLineSecondary<T> {

    /**
     * Representation of {@code this}.
     */
    private Sequence<T> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<T>();
    }

    /**
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public WaitingLine1() {
        this.createNewRep();
    }

    /**
     * Standard methods. -------------------------------------------------------
     */
    @Override
    public void clear() {
        this.createNewRep();

    }

    @Override
    @SuppressWarnings("unchecked")
    public WaitingLine<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void transferFrom(WaitingLine<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof WaitingLine1<?> : ""
                + "Violation of: source is of dynamic type WaitingLine1<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * WaitingLine1<?>, and the ? must be T or the call would not have
         * compiled.
         */
        WaitingLine1<T> localSource = (WaitingLine1<T>) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /**
     * Kernel methods. ---------------------------------------------------------
     */
    @Override
    public void addToLine(T customer) {
        assert customer != null : "Violation of: customer is not null";
        assert !this.exist(
                customer) : "Violation of: The entries in a WaitingLine must be unique.";

        this.rep.add(this.rep.length(), customer);
    }

    @Override
    public T removeFromLine(T customer) {
        assert customer != null : "Violation of: parameter can not be null.";
        assert this.rep
                .length() > 0 : "Violation of: The Waiting Line can not be empty";
        assert this.exist(customer) : "Violation of: " + customer
                + " must in the Waiting Line.";

        int index = 0;

        boolean isFound = false;
        Iterator<T> iterator = this.rep.iterator();
        while (iterator.hasNext() && !isFound) {
            T temp = iterator.next();
            if (temp.equals(customer)) {
                isFound = true;
            }

            if (!isFound) {
                index++;
            }
        }

        T removed = this.rep.remove(index);

        return removed;
    }

    @Override
    public boolean exist(T customer) {
        assert customer != null : "Violation of: parameter can not be null.";

        boolean isFound = false;
        Iterator<T> iterator = this.rep.iterator();
        while (iterator.hasNext() && !isFound) {
            T temp = iterator.next();
            if (temp.equals(customer)) {
                isFound = true;
            }
        }

        return isFound;
    }

    @Override
    public int length() {

        return this.rep.length();
    }

    /**
     * WaitingLine Method.
     */
    @Override
    public T front() {
        assert this.rep
                .length() > 0 : "Violation of: The Waiting Line can not be empty";

        T firstElement = this.rep.entry(0);
        return firstElement;
    }

    @Override
    public T removeFront() {
        assert this.rep
                .length() > 0 : "Violation of: The Waiting Line can not be empty";

        T removed = this.rep.remove(0);
        return removed;
    }

    @Override
    public int position(T customer) {
        assert customer != null : "Violation of: parameter can not be null.";
        assert this.rep
                .length() > 0 : "Violation of: The Waiting Line can not be empty";
        assert this.exist(customer) : "Violation of: " + customer
                + " must in the Waiting Line.";

        int index = 0;

        boolean isFound = false;
        Iterator<T> iterator = this.rep.iterator();
        while (iterator.hasNext() && !isFound) {
            T temp = iterator.next();
            if (temp.equals(customer)) {
                isFound = true;
            }

            if (!isFound) {
                index++;
            }
        }
        return index;
    }

    @Override
    public void sort(Comparator<T> order) {
        assert order != null : "Violation of: order can not be null.";
        assert this.rep
                .length() > 0 : "Violation of: The Waiting Line can not be empty";

        Sequence<T> sortedSequeue = this.rep.newInstance();
        Sequence<T> temp = this.rep.newInstance();
        temp.transferFrom(this.rep);

        while (temp.length() > 0) {
            T element = temp.remove(0);

            int index = 0;
            int insertIndex = sortedSequeue.length();
            boolean isContinue = true;
            while (index < sortedSequeue.length() && isContinue) {

                T sortedElement = sortedSequeue.entry(index);

                if (order.compare(sortedElement, element) > 0) {
                    insertIndex = index;
                    isContinue = false;
                }

                index++;
            }

            sortedSequeue.add(insertIndex, element);
        }

        this.rep.transferFrom(sortedSequeue);
    }

    /**
     * Implementation of {@code Iterator} interface for {@code Sequence1L}.
     */
    @Override
    public Iterator<T> iterator() {
        return new WaitingLine1Iterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code WaitingLine1}.
     */
    private final class WaitingLine1Iterator implements Iterator<T> {

        /**
         * Representation iterator.
         */
        private final Iterator<T> iterator;

        /**
         * No-argument constructor.
         */
        private WaitingLine1Iterator() {
            this.iterator = WaitingLine1.this.rep.iterator();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            return this.iterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
