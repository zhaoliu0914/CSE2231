package components.waitingline;

import components.standard.Standard;

/**
 * First-in-first-out (FIFO) WaitingLine kernel component with primary methods.
 * (Note: by package-wide convention, all references are non-null.).
 *
 * There are three important ways to distinguish Queue and WaitingLine: 1. The
 * entries in a WaitingLine must be unique. 2. It must be possible to remove a
 * given entry known to be in a WaitingLine. 3. It must be possible to find the
 * position of a given entry in a WaitingLine.
 *
 * @param <T>
 *            type of {@code WaitingLineKernel} entries
 * @mathmodel type WaitingLineKernel is modeled by string of T
 * @initially <pre>
 * default:
 *  ensures
 *   this = <>
 * </pre>
 * @iterator ~this.seen * ~this.unseen = this
 *
 * @author Zhao Liu
 * @author Ummehani Motiwala
 * @author Deepak Prabaharan
 */
public interface WaitingLineKernel<T>
        extends Standard<WaitingLine<T>>, Iterable<T> {

    /**
     * Adds {@code customer} to the end of ({@code this}. *
     *
     * @param customer
     *            the entry to be added
     * @aliases reference {@code customer}
     * @updates this
     * @requires {@code customer} is not in this
     * @ensures this = #this * <customer>
     */
    void addToLine(T customer);

    /**
     * Removes {@code customer} from {@code this} and returns {@code customer}.
     * *
     *
     * @param customer
     *            the entry to be removed
     * @return the entry removed
     * @updates this
     * @requires this /= <> && {@code customer} is in this
     * @ensures this = #this \ <removeFromLine>
     */
    T removeFromLine(T customer);

    /**
     * Return whether {@code customer} exist in the waiting line. *
     *
     * @param customer
     *            the element to check if in waiting line
     * @return true: customer is in line, false: customer is not in line
     * @ensure exists = (x is in this)
     */
    boolean exist(T customer);

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

}
